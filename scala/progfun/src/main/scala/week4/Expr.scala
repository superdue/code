package week4

// 5 * 8 - 3 * 5 = 25

trait Expr {
  def isNumber: Boolean
  def isSum: Boolean
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
  
  // Prod
  def isProd: Boolean
  // Var
  def name: String
  def isVar: Boolean
  
  // Writing all these classification and accessor functions quickly becomes tedious!
  def eval(e: Expr): Int = {
    if (e.isNumber) e.numValue
    else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else throw new Error("Unknown expression " + e)
  }
  
  def eval2(e: Expr): Int =
    if (e.isInstanceOf[Number])
      e.asInstanceOf[Number].numValue
    else if (e.isInstanceOf[Sum])
      eval(e.asInstanceOf[Sum].leftOp) +
      eval(e.asInstanceOf[Sum].rightOp)
    else throw new Error("Unknown expression " + e)
  
  def eval3(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1, e2) => eval3(e1) + eval3(e2)
  }
  
  def eval4: Int = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval4 + e2.eval4
  }
}
case class Number(n: Int) extends Expr {
  def isNumber: Boolean = true
  def isSum: Boolean = false
  def numValue: Int = n
  def leftOp: Expr = throw new Error("Number.leftOp")
  def rightOp: Expr = throw new Error("Number.rightOp")
  
  def isProd: Boolean  = false
  def name: String = throw new Error("Var.name")
  def isVar: Boolean = false
}
case class Sum(e1: Expr, e2: Expr) extends Expr {
  def isNumber: Boolean = false
  def isSum: Boolean = true
  def numValue: Int = throw new Error("Sum.numValue")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
  
  def isProd: Boolean  = false
  def name: String = throw new Error("Sum.name")
  def isVar: Boolean = false
}
case class Prod(e1: Expr, e2: Expr) extends Expr {
  def isNumber: Boolean = false
  def isSum: Boolean = false
  def numValue: Int = throw new Error("Prod.numValue")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
  
  def isProd: Boolean  = true
  def name: String = throw new Error("Prod.name")
  def isVar: Boolean = false
}
case class Var(x: String) extends Expr {
  def isNumber: Boolean = false
  def isSum: Boolean = false
  def numValue: Int = throw new Error("Var.numValue")
  def leftOp: Expr = throw new Error("Var.leftOp")
  def rightOp: Expr = throw new Error("Var.rightOp")
  
  def isProd: Boolean  = false
  def name: String = x
  def isVar: Boolean = true
}
