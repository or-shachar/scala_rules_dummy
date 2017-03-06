load("@io_bazel_rules_scala//scala:scala.bzl", "scala_library", "scala_binary", "scala_test")

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
    main_class = "com.example.main.ManyDependencies",
    deps = ["//src/main/scala/com/example/other","@twitter_utils//jar"]
)
