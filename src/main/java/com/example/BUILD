load("@bazel_tools//tools/build_defs/pkg:pkg.bzl", "pkg_tar")



java_binary(
    name = "run",
    main_class = "com.example.JavaBinary",
    srcs = ["JavaBinary.java"],
    resources = [":hello"],
    deps = ["//src/main/java/com/example/rand"],
    classpath_resources = ["//src/main/resources:resources"],
    visibility = ["//visibility:public"]
)

filegroup(
    name = "sources",
    data = glob(["**"]),
    visibility = ["//visibility:public"]
)

genrule(
  name = "hello",
  outs = ["hello_world.txt"],
  cmd = "echo Hello World > $@",
)

genrule(
  name = "double",
  srcs = [":hello"],
  outs = ["double_hello.txt"],
  cmd = "cat $< $< > $@",
)

pkg_tar(
    name = "packman",
    files = [
        ":hello",
        ":double",
        "//src/main/scala/com/example/main:hello"
    ]
)
