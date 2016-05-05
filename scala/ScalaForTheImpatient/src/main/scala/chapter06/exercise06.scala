package chapter06

object exercise06 extends App {

  object Suits extends Enumeration {
    type Suits = Value
    val Spade = Value("♠")
    val Club = Value("♣")
    val Heart = Value("♥")
    val Diamond = Value("♦")
  }

  println(Suits.values);

}