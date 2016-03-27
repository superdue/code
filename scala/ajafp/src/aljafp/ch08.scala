package aljafp

object ch08 {
  
  trait Expr {
    def accept(ask: ExprVisitor): Any
  }
  class Plus(l: Expr, r: Expr) extends Expr {
    def accept(ask: ExprVisitor) = ask.forPlus(l, r)
  }
  class Diff(l: Expr, r: Expr) extends Expr {
    def accept(ask: ExprVisitor) = ask.forDiff(l, r)
  }
  class Prod(l: Expr, r: Expr) extends Expr {
    def accept(ask: ExprVisitor) = ask.forProd(l, r)
  }
  class Const(c: Any) extends Expr {
    def accept(ask: ExprVisitor) = ask.forConst(c)
  }
  
  trait ExprVisitor {
    def forPlus(l: Expr, r: Expr): Any
    def forDiff(l: Expr, r: Expr): Any
    def forProd(l: Expr, r: Expr): Any
    def forConst(c: Any)
  }
  class IntEval extends ExprVisitor {
    def forPlus(l: Expr, r: Expr) = plus(l.accept(this), r.accept(this))
    def forDiff(l: Expr, r: Expr) = diff(l.accept(this), r.accept(this))
    def forProd(l: Expr, r: Expr) = prod(l.accept(this), r.accept(this))
    def forConst(c: Any) = c
    private def plus(l: Any, r: Any) = l.asInstanceOf[Int] + r.asInstanceOf[Int]
    private def diff(l: Any, r: Any) = l.asInstanceOf[Int] - r.asInstanceOf[Int]
    private def prod(l: Any, r: Any) = l.asInstanceOf[Int] * r.asInstanceOf[Int]
  }
  
  trait Set {
    def add(i: Int): Set = if (mem(i)) this else new Add(i, this)
    def mem(i: Int): Boolean
    def plus(s: Set): Set
    def diff(s: Set): Set
    def prod(s: Set): Set
  }
  class Empty extends Set {
    def mem(i: Int) = false
    def plus(s: Set) = s
    def diff(s: Set) = new Empty
    def prod(s: Set) = new Empty
  }
  class Add(i: Int, s: Set) extends Set {
    def mem(n: Int) = if (i == n) true else s.mem(n)
    def plus(t: Set) = s.plus(t.add(i))
    def diff(t: Set) = if (t.mem(i)) s.diff(t) else s.diff(t).add(i)
    def prod(t: Set) = if (t.mem(i)) s.prod(t).add(i) else s.prod(t)
  }
  
  class SetEval extends IntEval {
    def plus(l: Any, r: Any) = l.asInstanceOf[Set].plus(r.asInstanceOf[Set])
    def diff(l: Any, r: Any) = l.asInstanceOf[Set].diff(r.asInstanceOf[Set])
    def prod(l: Any, r: Any) = l.asInstanceOf[Set].prod(r.asInstanceOf[Set])
  }

}