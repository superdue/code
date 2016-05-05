def totalSelectValues(acc: Int, n: Int)(closure: (Int) => Boolean)(closure2: (Int, Int) => Int): Int = {
	(1 to n).filter(closure(_)).foldLeft(acc)(closure2(_, _))
}
println(totalSelectValues(0, 10)(_ % 2 == 0)(_ + _))
println(totalSelectValues(1, 10)(_ % 2 == 0)(_ * _))

// 突然想用Scala实现一下Groovy的这个例子。
// 写完发现Scala真好玩。