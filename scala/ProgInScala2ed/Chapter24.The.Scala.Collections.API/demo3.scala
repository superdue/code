def fibFrom(a: Int, b: Int): Stream[Int] =
  a #:: fibFrom(b, a + b)

val fibs = fibFrom(1, 1).take(7)

println(fibs.toList)