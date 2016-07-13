package aljafp

object ch06_3 {

  trait Pie {
    def accept(ask: PieVisitor): Pie
  }
  class Bot extends Pie {
    def accept(ask: PieVisitor) = ask.forBot
  }
  class Top(t: Any, r: Pie) extends Pie {
    def accept(ask: PieVisitor) = ask.forTop(t, r)
  }
  
  abstract class Fish
  class Anchovy extends Fish
  class Salmon extends Fish
  class Tuna extends Fish
  
  trait PieVisitor {
    def forBot: Pie
    def forTop(t: Any, r: Pie): Pie
  }
  
  class Rem(o: Any) extends PieVisitor {
    def forBot: Pie = new Bot
    def forTop(t: Any, r: Pie) = if (o equals t) r.accept(this) else new Top(t, r.accept(this))
  }
  
  class Subst(n: Any, o: Any) extends PieVisitor {
    def forBot: Pie = new Bot
    def forTop(t: Any, r: Pie): Pie = if (o equals t) new Top(n, r.accept(this)) else new Top(t, r.accept(this))
  }
  
  class LtdSubst(c: Int, n: Any, o: Any) extends PieVisitor {
    def forBot = new Bot
    def forTop(t: Any, r: Pie) = if (c == 0) new Top(t, r) else if (o equals t) new Top(n, r.accept(new LtdSubst(c-1, n, o))) else new Top(t, r.accept(this)) 
  }

}