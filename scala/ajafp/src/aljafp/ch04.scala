package aljafp

object ch04 {
  
  // Q: Wasn't this last collection overwhelming?
  // A: It sure was. We defined seven classes and each contained three method defintions.
  
  // Q: Could it get worse?
  // A: It sure could. For everything we want to do with Pizza, we must add a method definition to each class.
  
  // Q: Why does that become overwhelming?
  // A: Because it becomes more and more difficult to understand the retionale for each of the methods in a variant and
  //    what the relationship is between methods of the same name in the different variants.
  
  class OnlyOnions {
    def forSkewer = true
    def forOnion(s: Shish) = s.onlyOnions
    def forLamb(s: Shish) = false
    def forTomato(s: Shish) = false
  }
  class IsVegetarian {
    def forSkewer = true
    def forOnion(s: Shish) = s.isVegetarian
    def forLamb(s: Shish) = false
    def forTomato(s: Shish) = s.isVegetarian
  }
  abstract class Shish {
    val ooFn = new OnlyOnions
    val ivFn = new IsVegetarian
    def onlyOnions: Boolean
    def isVegetarian: Boolean
  }
  class Skewer extends Shish {
    def onlyOnions = ooFn.forSkewer
    def isVegetarian = ivFn.forSkewer
  }
  class Onions(s: Shish) extends Shish {
    def onlyOnions = ooFn.forOnion(s)
    def isVegetarian = ivFn.forOnion(s)
  }
  class Lamb(s: Shish) extends Shish {
    def onlyOnions = ooFn.forLamb(s)
    def isVegetarian = ivFn.forLamb(s)
  }
  class Tomato(s: Shish) extends Shish {
    def onlyOnions = ooFn.forTomato(s)
    def isVegetarian = ivFn.forTomato(s)
  }
  

  abstract class Pizza {
    val remFn = new RemA
    val topFn = new TopAwC
    val subFn = new SubAbC
    def remA: Pizza
    def topAwC: Pizza
    def subAbC: Pizza
  }
  class Crust extends Pizza {
    def remA = remFn.forCrust
    def topAwC = topFn.forCrust
    def subAbC = subFn.forCrust
  }
  class Cheese(p: Pizza) extends Pizza {
    def remA = remFn.forCheese(p)
    def topAwC = topFn.forCheese(p)
    def subAbC = subFn.forCheese(p)
  }
  class Olive(p: Pizza) extends Pizza {
    def remA = remFn.forOlive(p)
    def topAwC = topFn.forOlive(p)
    def subAbC = subFn.forOlive(p)
  }
  class Anchovy(p: Pizza) extends Pizza {
    def remA = remFn.forAnchovy(p)
    def topAwC = topFn.forAnchovy(p)
    def subAbC = subFn.forAnchovy(p)
  }
  class Sausage(p: Pizza) extends Pizza {
    def remA = remFn.forSausage(p)
    def topAwC = topFn.forSausage(p)
    def subAbC = subFn.forSausage(p)
  }
  class RemA {
    def forCrust: Pizza = new Crust
    def forCheese(p: Pizza): Pizza = new Cheese(p.remA)
    def forOlive(p: Pizza): Pizza = new Olive(p.remA)
    def forAnchovy(p: Pizza): Pizza = p.remA
    def forSausage(p: Pizza): Pizza = new Sausage(p.remA)
  }
  class TopAwC {
    def forCrust: Pizza = new Crust
    def forCheese(p: Pizza): Pizza = new Cheese(p.topAwC)
    def forOlive(p: Pizza): Pizza = new Olive(p.topAwC)
    def forAnchovy(p: Pizza): Pizza = new Cheese(new Anchovy(p.topAwC))
    def forSausage(p: Pizza): Pizza = new Sausage(p.topAwC)
  }
  class SubAbC {
    def forCrust: Pizza = new Crust
    def forCheese(p: Pizza): Pizza = new Cheese(p.subAbC)
    def forOlive(p: Pizza): Pizza = new Olive(p.subAbC)
    def forAnchovy(p: Pizza): Pizza = new Cheese(p.subAbC)
    def forSausage(p: Pizza): Pizza = new Sausage(p.subAbC)
  }
}