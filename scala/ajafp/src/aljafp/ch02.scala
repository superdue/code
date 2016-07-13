package aljafp

import scala.math._

object ch02 {
  
  implicit def intToDouble(x: Int): Double = x.toDouble
  implicit def doubleToInt(x: Double): Int = x.toInt
  
  /*
  abstract class Point {
    def distanceToO: Int
  }
  class CartesianPt(x: Int, y: Int) extends Point {
    def distanceToO = sqrt(pow(x, 2) + pow(y, 2))
  }
  class ManhanttanPt(x: Int, y: Int) extends Point {
    def distanceToO = x + y
  }
  */
  
  abstract class Shish {
    def onlyOnions: Boolean
    def isVegetarian: Boolean
  }
  case class Skewer extends Shish {
    def onlyOnions = true
    def isVegetarian = true
  }
  case class Onion(s: Shish) extends Shish {
    def onlyOnions = s.onlyOnions
    def isVegetarian = s.isVegetarian
  }
  case class Lamb(s: Shish) extends Shish {
    def onlyOnions = false
    def isVegetarian = false
  }
  case class Tomato(s: Shish) extends Shish {
    def onlyOnions = false
    def isVegetarian = s.isVegetarian
  }
  
  abstract class Kebab {                               // 烤串
    def isVeggie: Boolean
    def whatHolder: Any
  }
  case class Holder(o: Any) extends Kebab {            // 烤盘
    def isVeggie = true
    def whatHolder = o
  }
  case class Shallot(k: Kebab) extends Kebab {         // 葱
    def isVeggie = k.isVeggie
    def whatHolder = k.whatHolder
  }
  case class Shrimp(k: Kebab) extends Kebab {          // 虾
    def isVeggie = false
    def whatHolder = k.whatHolder
  }
  case class Radish(k: Kebab) extends Kebab {          // 萝卜
    def isVeggie = k.isVeggie
    def whatHolder = k.whatHolder
  }
  case class Pepper(k: Kebab) extends Kebab {          // 胡椒
    def isVeggie = k.isVeggie
    def whatHolder = k.whatHolder
  }
  case class Zucchini(k: Kebab) extends Kebab {        // 西葫芦
    def isVeggie = k.isVeggie
    def whatHolder = k.whatHolder
  }
  
  abstract class Rod
  case class Dagger extends Rod
  case class Sabre extends Rod
  case class Sword extends Rod
  
  abstract class Plate
  case class Gold extends Plate
  case class Silver extends Plate
  case class Brass extends Plate
  case class Copper extends Plate
  case class Wood extends Plate
  
  //
  
  /*
  abstract class Point {
    def distanceToO: Int
    def closerToO(p: Point): Boolean = distanceToO <= p.distanceToO
  }
  class CartesianPt(x: Int, y: Int) extends Point {
    def distanceToO = sqrt(pow(x, 2) + pow(y, 2))
  }
  class ManhanttanPt(x: Int, y: Int) extends Point {
    def distanceToO = x + y
  }
  */
  
  abstract class Point(x: Int, y: Int) {
    def distanceToO: Int
    def closerToO(p: Point): Boolean = distanceToO <= p.distanceToO
  }
  class CartesianPt(x: Int, y: Int) extends Point(x, y) {
    def distanceToO = sqrt(pow(x, 2) + pow(y, 2))
  }
  class ManhanttanPt(x: Int, y: Int) extends Point(x, y) {
    def distanceToO = x + y
  }
  // Scala的这个没有Java简洁，Java可以去掉子类的成员变量，但Scala写在构造器中的变量，类方法里面都可以访问得到
  
  def main(args: Array[String]): Unit = {
    Onion(Lamb(Skewer())).onlyOnions == Lamb(Skewer()).onlyOnions
  }

}