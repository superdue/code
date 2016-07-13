package chapter12

object exercise01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def values(fun: (Int) => Int, low: Int, high: Int) =
    Range(low, high).map(x => (x, fun(x)))        //> values: (fun: Int => Int, low: Int, high: Int)scala.collection.immutable.Ind
                                                  //| exedSeq[(Int, Int)]
    
  values(x => x * x, -5, 5)                       //> res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((-5,25), (-
                                                  //| 4,16), (-3,9), (-2,4), (-1,1), (0,0), (1,1), (2,4), (3,9), (4,16))
  
  // 另外一种写法
  
  def values2(fun: (Int) => Int, low: Int, high: Int) =
    low to high map { x => (x, fun(x)) }          //> values2: (fun: Int => Int, low: Int, high: Int)scala.collection.immutable.In
                                                  //| dexedSeq[(Int, Int)]
}