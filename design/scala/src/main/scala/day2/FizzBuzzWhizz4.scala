package day2

object FizzBuzzWhizz4 {
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
	
	def eval(Codes: List[Any], n: Int): RuleResult = eval(Codes, n, List[Option[String]]())
	def eval(Codes: List[Any], n: Int, stack: List[Option[String]]): RuleResult = {
	  Codes match {
	    case "op_atom" :: pred :: action :: rest => pred.asInstanceOf[Pred](n) match {
	      case true  => eval(rest, n, Some(action.asInstanceOf[Action](n)) :: stack)
	      case false => eval(rest, n, None :: stack)
	    }
	    case "op_and" :: rest => stack match {
	      case v1 :: v2 :: stack => (v1, v2) match {
	        case (Some(s1), Some(s2)) => eval(rest, n, Some(s2+s1) :: stack)
	        case _                    => eval(rest, n, None :: stack)
	      }
	      case _ => throw new Error
	    }
	    case "op_or" :: rest => stack match {
	      case v1 :: v2 :: stack => (v2, v1) match {
	        case (Some(s1), _) => eval(rest, n, Some(s1) :: stack)
	        case (_, Some(s2)) => eval(rest, n, Some(s2) :: stack)
	        case _             => eval(rest, n, None :: stack) 
	      }
	      case _ => throw new Error
	    }
	    case Nil => stack.head match {
	      case Some(x) => Some(x)
	      case None => Some(n.toString)
	    }
	    case _ => throw new Error
	  }
	}
	
	type EvalOnce = (List[Any], Int, List[Option[String]])
	def eval2(evalOnce: EvalOnce): EvalOnce = {
	  val Codes = evalOnce._1
	  val n = evalOnce._2
	  val stack = evalOnce._3
	  Codes match {
	    case "op_atom" :: pred :: action :: rest => pred.asInstanceOf[Pred](n) match {
	      case true  => (rest, n, Some(action.asInstanceOf[Action](n)) :: stack)
	      case false => (rest, n, None :: stack)
	    }
	    case "op_and" :: rest => stack match {
	      case v1 :: v2 :: stack => (v1, v2) match {
	        case (Some(s1), Some(s2)) => (rest, n, Some(s2+s1) :: stack)
	        case _                    => (rest, n, None :: stack)
	      }
	      case _ => throw new Error
	    }
	    case "op_or" :: rest => stack match {
	      case v1 :: v2 :: stack => (v2, v1) match {
	        case (Some(s1), _) => (rest, n, Some(s1) :: stack)
	        case (_, Some(s2)) => (rest, n, Some(s2) :: stack)
	        case _             => (rest, n, None :: stack) 
	      }
	      case _ => throw new Error
	    }
	    case Nil => stack.head match {
	      case Some(x) => (Nil, n, List(Some(x)))
	      case None => (Nil, n, List(Some(n.toString)))
	    }
	    case _ => throw new Error
	  }
	}
	
	def compile(prog: Rule): List[Any] = compile(prog, List[Any]())
	def compile(prog: Rule, insts: List[Any]): List[Any] = prog match {
	  case ATOM(pred, action) => insts ++ List("op_atom", pred, action)
	  case AND(r1, r2) => {
	    val i1 = compile(r1, List[Any]())
	    val i2 = compile(r2, List[Any]())
	    insts ++ i1 ++ i2 ++ List("op_and")
	  }
	  case OR(r1, r2) => {
	    val i1 = compile(r1, List[Any]())
	    val i2 = compile(r2, List[Any]())
	    insts ++ i1 ++ i2 ++ List("op_or")
	  }
	}
	
	def schedule(progs: List[(String, EvalOnce)]): Unit = {
	  progs match {
	    case Nil => 
	    case (progName, (Nil, n, stack)) :: rest => {
	      println("prog[" + progName + "] result:" + n + " -> " + stack)
	      schedule(rest)
	    }
	    case (progName, (codes, n, stack)) :: rest => {
	      Thread.sleep(2000)
	      println("scheduling[" + progName +"]: codes:" + codes + " stack:" + stack)
	      val (rest2, n2, stack2) = eval2(codes, n, stack)
	      schedule(rest ++ List((progName, (rest2, n2, stack2)))) // reverse
	    }
	  }
	}
	
	def main(args: Array[String]): Unit = {
	  /*
	  {
	    val R1 = atom(times(3), (_) => "Fizz")
        val R2 = atom(times(5), (_) => "Buzz")
        
        val R3 = and(R1, R2)
        val R4 = or(R1, R2)
	    
	    schedule(("prog1", (compile(R3), 15, List[Option[String]]())) :: ("prog2", (compile(R4), 17, List[Option[String]]())) :: Nil)
	  }
	  */
	  
	  {
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
	    schedule(("prog1", (compile(Spec), 13, List[Option[String]]())) :: ("prog2", (compile(Spec), 17, List[Option[String]]())) :: Nil)
	  }
	  
	  {
	    // atom, and, or 变成硬件资源
	  }
	}
}