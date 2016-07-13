package day2

object FizzBuzzWhizz2 {
    type Pred = Int => Boolean
    type Action = Int => String
  
	def times(n: Int) = (x: Int) => x % n == 0
	def contains(n: Int) = (x: Int) => x.toString contains n.toString
	
	type RuleResult = Option[String]
	
	trait Rule
	case class ATOM(pred: Pred, action: Action) extends Rule
	case class AND(r1: Rule, r2: Rule) extends Rule
	case class OR(r1: Rule, r2: Rule) extends Rule
	
	def atom(pred: Pred, action: Action): Rule = ATOM(pred, action)
	def and(r1: Rule, r2: Rule): Rule = AND(r1, r2)
	def and3(r1: Rule, r2: Rule, r3: Rule): Rule = AND(r1, AND(r2, r3))
	def or(r1: Rule, r2: Rule): Rule = OR(r1, r2)
	def or3(r1: Rule, r2: Rule, r3: Rule) = OR(r1, OR(r2, r3))
	def or4(r1: Rule, r2: Rule, r3: Rule, r4: Rule) = OR(r1, or3(r2, r3, r4))
	
	def apply_rule(r: Rule, n: Int): RuleResult = r match {
	  case ATOM(pred, action) => pred(n) match {
	    case true => Some(action(n))
	    case false => None
	  }
	  case AND(r1, r2) => apply_rule(r1, n) match {
	    case Some(res1) => apply_rule(r2, n) match {
	      case Some(res2) => Some(res1 + res2)
	      case None => None
	    }
	    case None => None
	  }
	  case OR(r1, r2) => apply_rule(r1, n) match {
	    case Some(res) => Some(res)
	    case None => apply_rule(r2, n)
	  }
	}
	
	val R1_3 = atom(times(3), (_) => "Fizz")
	val R1_5 = atom(times(5), (_) => "Buzz")
	val R1_7 = atom(times(7), (_) => "Whizz")
	
	val R1 = or3(R1_3, R1_5, R1_7)
	val R2 = or4(and3(R1_3, R1_5, R1_7),
	             and(R1_3, R1_5),
	             and(R1_5, R1_7),
	             and(R1_5, R1_7))
	val R3 = atom(contains(3), (_) => "Fizz")
	val Rd = atom((_) => true, _.toString)
	val Spec = or4(R3, R2, R1, Rd)
	
	def main(args: Array[String]): Unit = {
	  (1 to 20).map(apply_rule(Spec, _)).map(_.get).foreach(println)
	}
}