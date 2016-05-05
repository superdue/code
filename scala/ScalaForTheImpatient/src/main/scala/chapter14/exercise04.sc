package chapter14

object exercise04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  abstract class Item
  case class Article(description: String, price: Double) extends Item
  case class Bundle(description: String, discount: Double, items: Item*) extends Item
  case class Multiple(count: Int, item: Item) extends Item
  
  def price(it: Item): Double = it match {
    case Article(_, p) => p
    case Bundle(_, disc, its @ _*) => its.map(price _).sum - disc
    case Multiple(count, item) => price(item) * count
  }                                               //> price: (it: chapter14.exercise04.Item)Double
  
  val x = Bundle("Father's day special", 20.0,
    Multiple(1, Article("Scala for the Impatient", 39.95)),
    Bundle("Anchor Distillery Sampler", 10.0, Article("A", 79.95), Article("B", 32.95)))
                                                  //> x  : chapter14.exercise04.Bundle = Bundle(Father's day special,20.0,WrappedA
                                                  //| rray(Multiple(1,Article(Scala for the Impatient,39.95)), Bundle(Anchor Disti
                                                  //| llery Sampler,10.0,WrappedArray(Article(A,79.95), Article(B,32.95)))))
    
  price(x)                                        //> res0: Double = 122.85000000000002
  // 79.95 + 32.95 - 10.0 = 112.9 - 10.0 = 102.9
  // 102.9 + 39.95 - 20.0 = 142.85 - 20.0 = 122.85
}