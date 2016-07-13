package chapter12

object exercise02 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def maxElem[T <% Ordered[T]](array: Array[T]): T =
    array.reduceLeft((x, y) => if (x > y) x else y)
                                                  //> maxElem: [T](array: Array[T])(implicit evidence$2: T => Ordered[T])T
  maxElem(Array(-1,5,1))                          //> res0: Int = 5
  
}