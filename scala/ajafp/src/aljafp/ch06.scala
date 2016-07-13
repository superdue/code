package aljafp

object ch06 {

  abstract class Pie {
    def rem(remFn: Rem, o: Any): Pie
    def subst(substFn: Subst, n: Any, o: Any): Pie
  }
  class Bot extends Pie {
    def rem(remFn: Rem, o: Any) = remFn.forBot(o)
    def subst(substFn: Subst, n: Any, o: Any) = substFn.forBot(n, o)
  }
  class Top(t: Any, r: Pie) extends Pie {
    def rem(remFn: Rem, o: Any) = remFn.forTop(t, r, o)
    def subst(substFn: Subst, n: Any, o: Any) = substFn.forTop(t, r, n, o)
  }
  
  abstract class Fish
  class Anchovy extends Fish
  class Salmon extends Fish
  class Tuna extends Fish
  
  class Rem {
    def forBot(o: Any): Pie = new Bot
    def forTop(t: Any, r: Pie, o: Any) = if (o equals t) r.rem(this, o) else new Top(t, r.rem(this, o))
  }
  
  class Subst {
    def forBot(n: Any, o: Any): Pie = new Bot
    def forTop(t: Any, r: Pie, n: Any, o: Any): Pie = if (o equals t) new Top(n, r.subst(this, n, o)) else new Top(t, r.subst(this, n, o))
  }

}