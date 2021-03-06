package aljafp

object ch05_2 {

  abstract class Pie {
    val remFn = new Rem
    val substFn = new Subst
    def rem(o: Any): Pie
    def subst(n: Any, o: Any): Pie
  }
  class Bot extends Pie {
    def rem(o: Any) = remFn.forBot(o)
    def subst(n: Any, o: Any) = substFn.forBot(n, o)
  }
  class Top(t: Any, r: Pie) extends Pie {
    def rem(o: Any) = remFn.forTop(t, r, o)
    def subst(n: Any, o: Any) = substFn.forTop(t, r, n, o)
  }
  
  abstract class Fish
  class Anchovy extends Fish
  class Salmon extends Fish
  class Tuna extends Fish
  
  class Rem {
    def forBot(o: Any): Pie = new Bot
    def forTop(t: Any, r: Pie, o: Any) = if (o equals t) r.rem(o) else new Top(t, r.rem(o))
  }
  
  class Subst {
    def forBot(n: Any, o: Any): Pie = new Bot
    def forTop(t: Any, r: Pie, n: Any, o: Any): Pie = if (o equals t) new Top(n, r.subst(n, o)) else new Top(t, r.subst(n, o))
  }
}