package day1

object FizzBuzzWhizz {
	type Rule = Int => Option[String]
	type Op = (String, String) => String
	def times(n: Int) = (x: Int) => x % n == 0
	def contains(n: Int) = (x: Int) => x.toString contains n.toString
	def rule(pred: Int => Boolean, trans: Int => String): Rule = (x: Int) => if (pred(x)) Some(trans(x)) else None
	def OR(rules: List[Rule]): Rule = rules match {
	  case r :: rest => (x: Int) => r(x) match {
	    case Some(a) => Some(a)
	    case None => OR(rest)(x)
	  }
	  case Nil => (x: Int) => None
	}
	def AND(rules: List[Rule], op: Op = (acc, a) => acc + a, acc: String = ""): Rule = rules match {
	  case r :: rest => (x: Int) => r(x) match {
	    case Some(a) => AND(rest, op, op(acc, a))(x)
	    case None => None
	  }
	  case Nil => (x: Int) => Some(acc)
	}
	
	// foldLeft implementation
	def OR1(rules: List[Rule]): Rule = 
	  (x: Int) => rules.foldLeft(None: Option[String])((acc, r) => acc match {
	  	case Some(a) => Some(a)
	  	case None => r(x)
	  })
	def AND1(rules: List[Rule], op: Op = (acc, a) => acc + a): Rule = 
	  (x: Int) => rules.foldLeft(Some(""): Option[String])((acc, r) => acc match {
	  	case Some(a) => r(x) match {
	  	  case Some(b) => Some(op(a, b))
	  	  case None => None
	  	}
	  	case None => None
	  })
	
	val R1_3 = rule(times(3), (_) => "Fizz")
	val R1_5 = rule(times(5), (_) => "Buzz")
	val R1_7 = rule(times(7), (_) => "Whizz")
	val R1 = OR1(List(R1_3, R1_5, R1_7))
	val R2 = OR1(List(AND1(List(R1_3, R1_5, R1_7)),
	                 AND1(List(R1_3, R1_5)),
	                 AND1(List(R1_5, R1_7))))
	val R3 = rule(contains(3), (_) => "Fizz")
	val Rd = rule((_) => true, _.toString)
	val Spec = OR1(List(R3, R2, R1, Rd))
	
	def main(args: Array[String]): Unit = {
	  1 to 20 map Spec map (_.get) foreach println
	}
}