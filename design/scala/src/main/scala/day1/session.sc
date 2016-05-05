package day1

object session {
  type Rule = Int => (Boolean, String)
  type Op = (String, String) => String
  def times(n: Int) = (x: Int) => x % n == 0      //> times: (n: Int)Int => Boolean
  def contains(n: Int) = (x: Int) => x.toString.contains(n.toString)
                                                  //> contains: (n: Int)Int => Boolean
  def rule(f: Int => Boolean, g: Int => String): Rule = (x: Int) => (f(x), g(x))
                                                  //> rule: (f: Int => Boolean, g: Int => String)Int => (Boolean, String)
  
  def OR(rules: List[Rule]): Rule =
    rules match {
      case r :: rs => (x: Int) => r(x) match {
        case (true, a) => (true, a)
        case (false, _) => OR(rs)(x)
      }
      case Nil => (x: Int) => (false, x.toString)
    }                                             //> OR: (rules: List[Int => (Boolean, String)])Int => (Boolean, String)
  
  def OR2(rules: List[Rule], op: Op = (x, y) => x + y, acc: String = ""): Rule =
    rules match {
      case r :: rs => (x: Int) => r(x) match {
        case (true, a) => (true, op(acc, a))
        case (false, _) => OR2(rs, op, acc)(x)
      }
      case Nil => (x: Int) => (false, x.toString)
    }                                             //> OR2: (rules: List[Int => (Boolean, String)], op: (String, String) => String,
                                                  //|  acc: String)Int => (Boolean, String)
  def AND(rules: List[Rule], op: Op = (x, y) => x + y, acc: String = ""): Rule =
    rules match {
      case r :: rs => (x: Int) => r(x) match {
        case (true, a) => AND(rs, op, op(acc, a))(x)
        case (false, whatever) => (false, x.toString)
      }
      case Nil => (x: Int) => (true, acc)
    }                                             //> AND: (rules: List[Int => (Boolean, String)], op: (String, String) => String
                                                  //| , acc: String)Int => (Boolean, String)
  
  val R1_3 = rule(times(3), (_) => "Fizz")        //> R1_3  : Int => (Boolean, String) = <function1>
  val R1_5 = rule(times(5), (_) => "Buzz")        //> R1_5  : Int => (Boolean, String) = <function1>
  val R1_7 = rule(times(7), (_) => "Whizz")       //> R1_7  : Int => (Boolean, String) = <function1>
  val R1 = OR(List(R1_3, R1_5, R1_7))             //> R1  : Int => (Boolean, String) = <function1>
  val R2 = OR(List(AND(List(R1_3, R1_5, R1_7)),
                   AND(List(R1_3, R1_5)),
                   AND(List(R1_3, R1_7)),
                   AND(List(R1_5, R1_7))))        //> R2  : Int => (Boolean, String) = <function1>
  val R3 = rule(contains(3), (_) => "Fizz")       //> R3  : Int => (Boolean, String) = <function1>
  val Rd = rule((_) => true, _.toString)          //> Rd  : Int => (Boolean, String) = <function1>
  val Spec = OR(List(R3, R2, R1, Rd))             //> Spec  : Int => (Boolean, String) = <function1>
  
  (1 to 20) map Spec map (_._2) foreach println   //> 1
                                                  //| 2
                                                  //| Fizz
                                                  //| 4
                                                  //| Buzz
                                                  //| Fizz
                                                  //| Whizz
                                                  //| 8
                                                  //| Fizz
                                                  //| Buzz
                                                  //| 11
                                                  //| Fizz
                                                  //| Fizz
                                                  //| Whizz
                                                  //| FizzBuzz
                                                  //| 16
                                                  //| 17
                                                  //| Fizz
                                                  //| 19
                                                  //| Buzz

  
  
  
  // Spec Definition
  
  // R1_3 = rule(times(3), fun(_) -> "Fizz" end),
  // R1_5 = rule(times(5), fun(_) -> "Buzz" end),
  // R1_7 = rule(times(7), fun(_) -> "Whizz" end),
  
  // R1 = 'OR'([R1_3, R1_5, R1_7]),
  
  // R2 = 'OR'(['AND'([R1_3, R1_5, R1_7]),
  //            'AND'([R1_3, R1_5]),
  //            'AND'([R1_3, R1_7]),
  //            'AND'([R1_5, R1_7])]),
  
  // R3 = rule(contains(3), fun(_) -> "Fizz" end),
  
  // Rd = rule(fun(_) -> true end, fun(N) -> N end),
  
  // Spec = 'OR'([R3, R2, R1, Rd]).
  
  // [Spec(I) || I<-lists:seq(1,100)]
  
  
}