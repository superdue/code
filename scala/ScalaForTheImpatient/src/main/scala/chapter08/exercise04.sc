package chapter08

object exercise04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  import collection.immutable.List

  abstract class Item {
    def description: String
    def price: Double
    override def toString = "%s(%s: %f)".format(this.getClass.getSimpleName, description, price)
  }

  class SimpleItem(
    override val description: String,
    override val price: Double) extends Item

  class Bundle extends Item {
    private var items: List[Item] = List()
    def add(item: Item) = { items = item :: items }
    def price: Double = items.map(_.price).sum
    def description: String = items.map(_.description).mkString(", ")
  }

  var l: List[Item] = List(
    new SimpleItem("iMac 21", 1500),
    new SimpleItem("iPhone 4s", 800))             //> l  : List[chapter08.exercise04.Item] = List(anonfun$main$1$SimpleItem$1(iMac
                                                  //|  21: 1500.000000), anonfun$main$1$SimpleItem$1(iPhone 4s: 800.000000))

  val b = new Bundle                              //> b  : chapter08.exercise04.Bundle = anonfun$main$1$Bundle$1(: 0.000000)
  b.add(new SimpleItem("iPad2", 500))
  b.add(new SimpleItem("MacBook Air 13", 1200))

  l = b :: l

  l.foreach(println(_))                           //> anonfun$main$1$Bundle$1(MacBook Air 13, iPad2: 1700.000000)
                                                  //| anonfun$main$1$SimpleItem$1(iMac 21: 1500.000000)
                                                  //| anonfun$main$1$SimpleItem$1(iPhone 4s: 800.000000)
}