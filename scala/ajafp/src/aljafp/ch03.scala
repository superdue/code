package aljafp

object ch03 {
  
  abstract class Pizza {
    def remA: Pizza
    def topAwC: Pizza
    def subAbC: Pizza
  }
  class Crust extends Pizza {
    def remA = new Crust
    def topAwC = new Crust
    def subAbC = new Crust
  }
  class Cheese(p: Pizza) extends Pizza {
    def remA = new Cheese(p.remA)
    def topAwC = new Cheese(p.topAwC)
    def subAbC = new Cheese(p.subAbC)
  }
  class Olive(p: Pizza) extends Pizza {
    def remA = new Olive(p.remA)
    def topAwC = new Olive(p.topAwC)
    def subAbC = new Olive(p.subAbC)
  }
  class Anchovy(p: Pizza) extends Pizza {
    def remA = p.remA
    def topAwC = new Cheese(new Anchovy(p.topAwC))
    def subAbC = new Cheese(p.subAbC)
  }
  class Sausage(p: Pizza) extends Pizza {
    def remA = new Sausage(p.remA)
    def topAwC = new Sausage(p.topAwC)
    def subAbC = new Sausage(p.subAbC)
  }
  class Spinach(p: Pizza) extends Pizza {
    def remA = new Spinach(p.remA)
    def topAwC = new Spinach(p.topAwC)
    def subAbC = new Spinach(p.subAbC)
  }
  
  def main(args: Array[String]): Unit = {
    
  }

}