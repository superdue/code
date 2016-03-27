package aljafp

object ch06_2 {

  abstract class Pie {
    def rem(remFn: Rem): Pie
    def subst(substFn: Subst): Pie
  }
  class Bot extends Pie {
    def rem(remFn: Rem) = remFn.forBot
    def subst(substFn: Subst) = substFn.forBot
  }
  class Top(t: Any, r: Pie) extends Pie {
    def rem(remFn: Rem) = remFn.forTop(t, r)
    def subst(substFn: Subst) = substFn.forTop(t, r)
  }
  
  abstract class Fish
  class Anchovy extends Fish
  class Salmon extends Fish
  class Tuna extends Fish
  
  class Rem(o: Any) {
    def forBot: Pie = new Bot
    def forTop(t: Any, r: Pie) = if (o equals t) r.rem(this) else new Top(t, r.rem(this))
  }
  
  class Subst(n: Any, o: Any) {
    def forBot: Pie = new Bot
    def forTop(t: Any, r: Pie): Pie = if (o equals t) new Top(n, r.subst(this)) else new Top(t, r.subst(this))
  }

}