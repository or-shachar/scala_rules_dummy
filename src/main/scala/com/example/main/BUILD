load("@io_bazel_rules_scala//scala:scala.bzl", "scala_library", "scala_binary", "scala_test")
load("//:wix-fw.bzl","boostrap_binary")

scala_binary(
    name = "standalone",
    srcs = ["StandAlone.scala"],
    main_class = "com.example.main.StandAlone",
)

scala_binary(
    name = "onedep",
    srcs = ["OneDependency.scala"],
    main_class = "com.example.main.OneDependency",
    deps = ["//src/main/scala/com/example/other","@twitter_utils//jar"]

)

scala_binary(
    name = "manydep",
    srcs = ["ManyDependencies.scala"],
    resources = [":hello"],
    main_class = "com.example.main.ManyDependencies",
    deps = ["//src/main/scala/com/example/other","@twitter_utils//jar"]
)


boostrap_binary(
    name="foo",
    service=":manydep",
)

genrule(
  name = "hello2",
  outs = ["hello_world2.erb"],
  cmd = "echo Hello World > $@",
  visibility = ["//visibility:public"]
)

genrule(
  name = "hello",
  outs = ["hello_world.xml.erb"],
  cmd = "echo Hello World > $@",
)
