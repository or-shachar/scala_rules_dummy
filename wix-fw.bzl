
def _build_deployable(ctx, main_jar, runtime_jars):
    # the _jar_bin program we call below expects one optional argument:
    # -m is the argument to pass a manifest to our jar creation code
    # the next argument is the path manifest itself
    # the manifest is set up by methods that call this function (see usages
    # of _build_deployable and note that they always first call write_manifest)
    # that is what creates the manifest content
    #
    # following the manifest argument and the manifest, the next argument is
    # the output path for the target jar
    #
    # finally all the rest of the arguments are jars to be flattened into one
    # fat jar
    args = [
            main_jar.path,
            "-m",ctx.outputs.manifest.path,
            ctx.outputs.service_jar.path,
            ]
    args.extend([j.path for j in runtime_jars])
    ctx.action(
        inputs=list(runtime_jars) + [ctx.outputs.manifest],
        outputs=[ctx.outputs.service_jar],
        executable=ctx.executable._service_bin,
        mnemonic="ScalaDeployJar",
        progress_message="scala deployable %s" % ctx.label,
        arguments=args)

def write_manifest(ctx):
    # TODO(bazel-team): I don't think this classpath is what you want
    #manifest = "Class-Path: %s\n" % ctx.file._scalalib.path
    manifest = "Manifest-Version: 1.0\n" + \
               "Archiver-Version: Plexus Archiver\n" + \
               "Created-By: 25.111-b14 (Oracle Corporation)\n"
    ctx.file_action(
        output=ctx.outputs.manifest,
        content=manifest)

# Common code shared by all scala binary implementations.
def _scala_binary_common(ctx, sjar, rjars):
  write_manifest(ctx)
  #outputs = _compile_or_empty(ctx, cjars, [], False)  # no need to build an ijar for an executable
  _build_deployable(ctx, sjar, list(rjars))

  #runfiles = ctx.runfiles(
  #    files = list(rjars) + [ctx.outputs.executable] + [ctx.file._java] + ctx.files._jdk,
  #    collect_data = True)

  #jars = _collect_jars(ctx.attr.deps) //redundat???
  rule_outputs = struct(service_jar=ctx.outputs.service_jar)
  return rule_outputs


def _collect_jars(service):
    """compute the runtime jars needed"""  # noqa
    service_jar = ""
    runtime_jars = set()
    if hasattr(service, "scala"):
        service_jar = service.scala.outputs.class_jar
        runtime_jars += service.scala.transitive_runtime_deps
        runtime_jars += service.scala.transitive_runtime_exports
    elif hasattr(service, "java"):
        service_jar = service.scala.outputs.class_jar
        runtime_jars += service.java.transitive_runtime_deps
    else:
        fail("service must be a scala or java target")
    return struct(service_jar = service_jar, runtime = runtime_jars)

def _boostrap_binary_impl(ctx):
  jars = _collect_jars(ctx.attr.service)
  return _scala_binary_common(ctx, jars.service_jar, jars.runtime)


boostrap_binary = rule(
  implementation=_boostrap_binary_impl,
  attrs={
      "service" : attr.label(),
      "_service_bin" : attr.label(executable=True, cfg="host", default=Label("//packager/src/java/com/wixpress:service_assembly")),
      },
  outputs={
      "service_jar": "%{name}_deployable.jar",
      "manifest": "%{name}_MANIFEST.MF",
      }
)