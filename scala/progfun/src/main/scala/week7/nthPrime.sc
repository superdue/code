package week7

object nthPrime {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def isPrime(n: Int): Boolean = (2 until n) forall (d => n%d != 0)
                                                  //> isPrime: (n: Int)Boolean
  ((1000 to 10000) filter isPrime)(1)             //> res0: Int = 1013
  
  def secondPrime(from: Int, to: Int) = nthPrime(from, to , 2)
                                                  //> secondPrime: (from: Int, to: Int)Int
  def nthPrime(from: Int, to: Int, n: Int): Int =
    if (from >= to) throw new Error("no prime")
    else if (isPrime(from))
      if (n == 1) from else nthPrime(from + 1, to, n - 1)
    else nthPrime(from + 1, to, n)                //> nthPrime: (from: Int, to: Int, n: Int)Int
    
  nthPrime(1000, 10000, 2)                        //> res1: Int = 1013
  
  ((1000 to 10000).toStream filter isPrime)(1)    //> res2: Int = 1013
}