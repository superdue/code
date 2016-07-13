package ch02

object Question03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 3. 指出在 Scala 中何种情况下赋值语句 x = y = 1 是合法的。（提示：给 x 找个合适的类型定义。）
  
  //  Any, Nothing, Null, Unit
  
  var x: Unit = 0                                 //> x  : Unit = ()
  var y: Int = 0                                  //> y  : Int = 0
  
  x = y = 1
  
}