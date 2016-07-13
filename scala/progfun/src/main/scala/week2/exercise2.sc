package week2

object exercise2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def product(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)              //> product: (f: Int => Int)(a: Int, b: Int)Int
    
  product(x => x * x)(3, 4)                       //> res0: Int = 144
  
  def factorial(n: Int) = product(x => x)(1, n)   //> factorial: (n: Int)Int
  factorial(5)                                    //> res1: Int = 120
  
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
                                                  //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:
                                                  //|  Int)Int
    
  def prod(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)
                                                  //> prod: (f: Int => Int)(a: Int, b: Int)Int
  prod(x => x * x)(3, 4)                          //> res2: Int = 144
  
  def sum(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x + y, 0)(a, b)
                                                  //> sum: (f: Int => Int)(a: Int, b: Int)Int
  sum(x => x)(3, 4)                               //> res3: Int = 7
}