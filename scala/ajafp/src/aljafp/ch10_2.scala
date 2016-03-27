package aljafp

object ch10_2 {
  
  trait Pieman {
    def addTop(t: Any): Int
    def remTop(t: Any): Int
    def substTop(n: Any, o: Any): Int
    def occTop(o: Any): Int
  }
  trait PieVisitor {
    def forBot(that: Bot): Any
    def forTop(that: Top): Any
  }
  trait Pie {
    def accept(ask: PieVisitor): Any
  }
  class Bot extends Pie {
    def accept(ask: PieVisitor) = ask.forBot(this)
  }
  class Top(var t: Any, val r: Pie) extends Pie {
    def accept(ask: PieVisitor) = ask.forTop(this)
  }
  class ThePieman extends Pieman {
    private var p: Pie = new Bot
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
    def forBot(that: Bot) = that
    def forTop(that: Top) = if (o equals that.t) that.r.accept(this) else new Top(that.t, that.r.accept(this).asInstanceOf[Pie])
  }
  class Occurs(a: Any) extends PieVisitor {
    def forBot(that: Bot) = 0
    def forTop(that: Top) = if (that.t equals a) that.r.accept(this).asInstanceOf[Int] + 1 else that.r.accept(this)
  }
  class Subst(n: Any, o: Any) extends PieVisitor {
    def forBot(that: Bot) = that
    def forTop(that: Top) = if (o equals that.t) {
      that.t = n
      that.r.accept(this)
    } else {
      that.r.accept(this)
      that
    }
  }

}