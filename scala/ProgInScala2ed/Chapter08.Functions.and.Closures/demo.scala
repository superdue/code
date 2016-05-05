def test(args: String*) {
  for (arg <- args) println(arg)
}

val arr = Array("a", "b", "c")

test(arr: _*)