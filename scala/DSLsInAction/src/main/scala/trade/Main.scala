package driver

import dsl._

object Main {
  import TradeImplicits._

  import api._
  import Accounts._
  import Instruments._
  import Markets._
  import Currencies._

  // 为客户账号NOMURA在纽约证券交易所按72美元的单价买入200张IBM的折扣债券
  val fixedIncomeTrade = 200 discount_bonds IBM for_client NOMURA on NYSE at 72.ccy(USD)
  // 为客户账号NOMURA在东京证券交易所按10000日元的单价卖出200股Google的股票
  //val equityTrade      = 200 equities GOOGLE for_client NOMURA on TKY at 10000.ccy(JPY)

  def main(args: Array[String]): Unit = {
    import api.FixedIncomeTradingService._
    println(cashValue(200 discount_bonds IBM for_client NOMURA on NYSE at 72.ccy(USD)))
  }

  def normalAPI: Unit = {
    val t1 = FixedIncomeTradeImpl(
      tradingAccount = NOMURA,
      instrument = IBM,
      currency = USD,
      market = NYSE,
      quantity = 100,
      unitPrice = 42)
  }

}