# loading what's needed for bazel?
git_repository(
    name = "com_google_protobuf",
    remote = "https://github.com/google/protobuf.git",
    commit = "b4b0e304be5a68de3d0ee1af9b286f958750f5e4",
)

# cc_proto_library rules implicitly depend on @com_google_protobuf_cc//:cc_toolchain,
# which is the C++ proto runtime (base classes and common utilities).
git_repository(
    name = "com_google_protobuf_cc",
    remote = "https://github.com/google/protobuf.git",
    commit = "b4b0e304be5a68de3d0ee1af9b286f958750f5e4",
)

# java_proto_library rules implicitly depend on @com_google_protobuf_java//:java_toolchain,
# which is the Java proto runtime (base classes and common utilities).
git_repository(
    name = "com_google_protobuf_java",
    remote = "https://github.com/google/protobuf.git",
    commit = "b4b0e304be5a68de3d0ee1af9b286f958750f5e4",
)

git_repository(
    name = "io_bazel",
    remote = "https://github.com/bazelbuild/bazel.git",
    tag = "0.4.4", # update this as needed
)

# loading what's needed for scala
git_repository(
    name = "io_bazel_rules_scala",
    remote = "https://github.com/bazelbuild/rules_scala.git",
    commit = "a5c0a8aa2f072879f27329455b4f419a7cd7c8a6", # update this as needed
)
load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories()

# general 3rd party for the source code
maven_jar(
    name="twitter_utils",
    artifact="com.twitter:util-core_2.11:6.40.0"
)
