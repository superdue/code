package design

// What's the problem?
// 给定多手牌,按照规则找出获胜者

// 5张手牌

// The Rules
// 0 -- High Card                   散牌
// 1 -- One Pair (2kind)            1对
// 2 -- Two Pair (Two 2kind)        2对
// 3 -- Three of a Kind             3带1
// 4 -- Straight                    顺子
// 5 -- Flush                       同一个花色
// 6 -- Full House (2kind+3kind)    3带2
// 7 -- Four of a Kind              4带1
// 8 -- Straight Flush              同花顺

// The Details
// 一手牌(hand)由5张牌组成,每张牌有自己的花色(suit)和分值(rank)
// 根据规则,计算出每手牌的分值(hand rank),分值最高的那手牌即为获胜者

object PokerEngine {

  type Suit = String
  type Hand = List[Card]

  class Rank(val name: String, val value: Int) {
    override def toString: String = name
  }
  object Rank {
    def apply(name: String, rank: Int): Rank = new Rank(name, rank)
  }

  case class Card(suit: Suit, rank: Rank) {
    override def toString: String = (suit, rank).toString
  }

  val `2` = Rank("2", 2)
  val `3` = Rank("3", 3)
  val `4` = Rank("4", 4)
  val `5` = Rank("5", 5)
  val `6` = Rank("6", 6)
  val `7` = Rank("7", 7)
  val `8` = Rank("8", 8)
  val `9` = Rank("9", 9)
  val `10` = Rank("10", 10)
  val `J` = Rank("J", 11)
  val `Q` = Rank("Q", 12)
  val `K` = Rank("K", 13)
  val `A` = Rank("A", 14)



  val suits = List("Spade", "Heart", "Diamond", "Club")
  val ranks = List(`2`,`3`,`4`,`5`,`6`,`7`,`8`,`9`,`10`,`J`,`Q`,`K`,`A`)

  import scala.util.Random._
  def randomSuit = suits(nextInt(suits.size))
  def randomRank = ranks(nextInt(ranks.size))
  def randomCard = Card(randomSuit, randomRank)
  def randomHand = List(randomCard, randomCard, randomCard, randomCard, randomCard)

  // 花色

  // 对子,顺子

  // 两张牌之间的关系有 大小,和花色

  private def groupByRank(xs: List[Card]): List[List[Card]] = xs.groupBy(_.rank.value).values.toList
  private def groupBySuit(xs: List[Card]): List[List[Card]] = xs.groupBy(_.suit).values.toList

  type Rule = Hand => Boolean

  val StraightFlush: Rule = x => {
    Straight(x) && Flush(x)
  }

  val FourOfAKind: Rule = x => {
    val xs = groupByRank(x)
    xs.count(_.size == 4) == 1 && xs.count(_.size == 1) == 1
  }

  val FullHouse: Rule = x => {
    val xs = groupByRank(x)
    xs.count(_.size == 3) == 1 && xs.count(_.size == 2) == 1
  }
  val Flush: Rule = x => {
    val xs = groupBySuit(x)
    xs.count(_.size == 5) == 1
  }
  val Straight: Rule = x => {
    x.map(_.rank.value).sliding(2).forall(y => y.head + 1 == y.last)
  }
  val ThreeOfAKind: Rule = x => {
    val xs = groupByRank(x)
    xs.count(_.size == 3) == 1 && xs.count(_.size == 1) == 2
  }
  val TwoPair: Rule = x => {
    val xs = groupByRank(x)
    xs.count(_.size == 2) == 2 && xs.count(_.size == 1) == 1
  }
  val OnePair: Rule = x => {
    val xs = groupByRank(x)
    xs.count(_.size == 2) == 1 && xs.count(_.size == 1) == 3
  }

  val HighCard: Rule = x => true

  val ruleMap: Rule => Int = {
    case StraightFlush => 8
    case FourOfAKind   => 7
    case FullHouse     => 6
    case Flush         => 5
    case Straight      => 4
    case ThreeOfAKind  => 3
    case TwoPair       => 2
    case OnePair       => 1
    case HighCard      => 0
  }

  val rules = List(StraightFlush, FourOfAKind, FullHouse, Flush, Straight, ThreeOfAKind, TwoPair, OnePair, HighCard)

  def calculate(x: Hand): Rule = rules.find(_(x)).get

  def main(args: Array[String]): Unit = {

    (1 to 1000).map(_ => randomHand).map(x => (ruleMap(calculate(x)), x)).filter(_._1 == 3).foreach(println)

    /*
(8,List((Spade,10), (Spade,J), (Spade,Q), (Spade,K), (Spade,A)))
(8,List((Spade,2), (Spade,3), (Spade,4), (Spade,5), (Spade,6)))
(8,List((Heart,2), (Heart,3), (Heart,4), (Heart,5), (Heart,6)))
(8,List((Spade,8), (Spade,9), (Spade,10), (Spade,J), (Spade,Q)))
     */

  }


}
