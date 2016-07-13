package chapter09

object exercise05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val out = new java.io.PrintWriter("/Users/stephansun/Desktop/1.txt")
                                                  //> out  : java.io.PrintWriter = java.io.PrintWriter@159b5217
  for(i <- 0 to 20) out.println("%8.0f    %f".format(math.pow(2.0, i), math.pow(2.0, -i)))
  
  out.flush
}