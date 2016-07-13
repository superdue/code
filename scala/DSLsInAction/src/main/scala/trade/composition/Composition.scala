package dsl

object EquityTradeMarketRuleDsl extends MarketRuleDsl {
  val semantics = EquityTradeDsl
}
object FixedIncomeTradeMarketRuleDsl extends MarketRuleDsl {
  val semantics = FixedIncomeTradeDsl
}

object ComposedDsl {
  def main(args: Array[String]): Unit = {
    // 用我们的"交易创建DSL"建立一笔交易,然后执行充实,验证等各个步骤,完成交易处理的全过程,最后联合市场规则DSL算出交易最后的现金价值
    import TradeImplicits._

    import api._
    import Accounts._
    import Instruments._
    import Markets._
    import Currencies._
    import FixedIncomeTradeMarketRuleDsl._

    withTrade(
      200 discount_bonds IBM
        for_client NOMURA
        on NYSE
        at 72.ccy(USD)) { trade =>
      Mailer("user") mail trade
      Logger log trade
    } cashValue
  }
}
