package aljafp

object ch05 {

  abstract class Pie {
    val raFn = new RemA
    val rfFn = new RemFish
    def remA: Pie
    def remFish(f: Fish): Pie
  }
  class Bot extends Pie {
    def remA = raFn.forBot
    def remFish(f: Fish) = rfFn.forBot(f)
  }
  class Top(t: Any, r: Pie) extends Pie {
    def remA = raFn.forTop(t, r)
    def remFish(f: Fish) = rfFn.forTop(t, r, f)
  }
  
  abstract class Fish
  class Anchovy extends Fish
  class Salmon extends Fish
  class Tuna extends Fish
  
  class RemA {
    def forBot: Pie = new Bot
    def forTop(t: Any, r: Pie): Pie = if (t.isInstanceOf[Anchovy]) r.remA else new Top(t, r.remA)
  }
  class RemFish {
    def forBot(f: Fish): Pie = new Bot
    def forTop(t: Any, r: Pie, f: Fish): Pie = if (f equals t) r.remFish(f) else new Top(t, r.remFish(f))
  }
}