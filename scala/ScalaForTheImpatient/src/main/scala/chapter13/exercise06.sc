package chapter13

object excercise06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val lst = List(1, 2, 3)                         //> lst  : List[Int] = List(1, 2, 3)
  
  (lst :\ List[Int]())(_ :: _)                    //> res0: List[Int] = List(1, 2, 3)
  
  (List[Int]() /: lst)(_ :+ _)                    //> res1: List[Int] = List(1, 2, 3)

  (lst :\ List[Int]())((x, y) => y :+ x)          //> res2: List[Int] = List(3, 2, 1)
  
  (List[Int]() /: lst)((x, y) => y +: x)          //> res3: List[Int] = List(3, 2, 1)
  
  // 另外一种解法，更具易读性。
  
  (List[Int]() /: lst) { (reversedList, each) => each :: reversedList }
                                                  //> res4: List[Int] = List(3, 2, 1)
  
  
  // +: 等同 ::
  // ++: 等同 :::
}