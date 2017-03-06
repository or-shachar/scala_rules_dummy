
external_data = ["@local_jdk//:jdk"]

sources = [
    ".bazelrc",
    "//:sources",
    "//src/main/java/com/example:sources",
]

sh_test(
    name = "run_JavaBinary",
    size = "small",
    data = sources + external_data,
    srcs = ["test_run.sh"],
)