git_repository(
    name = "io_bazel_rules_scala",
    remote = "https://github.com/bazelbuild/rules_scala.git",
    commit = "a5c0a8aa2f072879f27329455b4f419a7cd7c8a6", # update this as needed
)

git_repository(
    name = "io_bazel",
    remote = "https://github.com/bazelbuild/bazel.git",
    tag = "0.4.4", # update this as needed
)

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories()

maven_jar(
    name="twitter_utils",
    artifact="com.twitter:util-core_2.11:6.40.0"
)
