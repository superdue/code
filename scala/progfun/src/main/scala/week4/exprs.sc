package week4

object exprs {
  // not very beautiful, but worked.
  def show(e: Expr): String = e match {
    case Number(x) => x.toString
    case Sum(l, r) => show(l) + " + " + show(r)
    case Prod(l, e) => l match {
      case Sum(e1, e2) => "(" + show(l) + ")" + " * " + show(e)
      case _ => show(l) + " * " + show(e)
    }
    case Var(x) => x
  }                                               //> show: (e: week4.Expr)String
  
  show(Sum(Number(1), Number(44)))                //> res0: String = 1 + 44
  
  show(Sum(Prod(Number(2), Var("x")), Var("y")))  //> res1: String = 2 * x + y
  show(Prod(Sum(Number(2), Var("x")), Var("y")))  //> res2: String = (2 + x) * y
}