package aljafp

object ch10 {
  
  trait Pieman {
    def addTop(t: Any): Int
    def remTop(t: Any): Int
    def substTop(n: Any, o: Any): Int
    def occTop(o: Any): Int
  }
  trait PieVisitor {
    def forBot: Any
    def forTop(t: Any, r: Pie)
  }
  trait Pie {
    def accept(ask: PieVisitor): Any
  }
  class Bot extends Pie {
    def accept(ask: PieVisitor) = ask.forBot
  }
  class Top(t: Any, r: Pie) extends Pie {
    def accept(ask: PieVisitor) = ask.forTop(t, r)
  }
  class ThePieman extends Pieman {
    var p: Pie = new Bot
    def addTop(t: Any) = {
      p = new Top(t, p)
      occTop(t)
    }
    def remTop(t: Any) = {
      p = p.accept(new Rem(t)).asInstanceOf[Pie]
      occTop(t)
    }
    def substTop(n: Any, o: Any) = {
      p = p.accept(new Subst(n, o)).asInstanceOf[Pie]
      occTop(n)
    }
    def occTop(o: Any) = p.accept(new Occurs()).asInstanceOf[Int]
  }
  
  class Rem(o: Any) extends PieVisitor {
    def forBot = new Bot
    def forTop(t: Any, r: Pie) = if (o equals t) r.accept(this) else new Top(t, r.accept(this).asInstanceOf[Pie])
  }
  class Occurs(a: Any) extends PieVisitor {
    def forBot = 0
    def forTop(t: Any, r: Pie) = if (t equals a) r.accept(this).asInstanceOf[Int] + 1 else r.accept(this)
  }
  class Subst(n: Any, o: Any) extends PieVisitor {
    def forBot = new Bot
    def forTop(t: Any, r: Pie) = if (o equals t) new Top(n, r.accept(this).asInstanceOf[Pie]) else new Top(t, r.accept(this).asInstanceOf[Pie])
  }

}