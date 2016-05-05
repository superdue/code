package dsl

import api._

object TradeImplicits {

  type Quantity = Int
  type WithInstrumentQuantity = (Instrument, Quantity)
  type WithAccountInstrumentQuantity = (Account, Instrument, Quantity)
  type WithMarketAccountInstrumentQuantity = (Market, Account, Instrument, Quantity)
  type Money = (Int, Currency)

  class InstrumentHelper(quantity: Quantity) {
    def discount_bonds(discountBond: DiscountBond) = (discountBond, quantity)
  }
  class AccountHelper(withInstrumentQuantity: WithInstrumentQuantity) {
    def for_client(clientAccount: ClientAccount) = (clientAccount, withInstrumentQuantity._1, withInstrumentQuantity._2)
  }
  class MarketHelper(withAccountInstrumentQuantity: WithAccountInstrumentQuantity) {
    def on(market: Market) = (market, withAccountInstrumentQuantity._1, withAccountInstrumentQuantity._2, withAccountInstrumentQuantity._3)
  }
  class RichInt(value: Int) {
    def ccy(currency: Currency) = (value, currency)
  }
  class PriceHelper(withMarketAccountInstrumentQuantity: WithMarketAccountInstrumentQuantity) {
    def at(count: Money) = (count, withMarketAccountInstrumentQuantity._1, withMarketAccountInstrumentQuantity._2, withMarketAccountInstrumentQuantity._3, withMarketAccountInstrumentQuantity._4)
  }

  implicit def quantity2InstrumentHelper(quantity: Quantity) = new InstrumentHelper(quantity)
  implicit def withAccount(withInstrumentQuantity: WithInstrumentQuantity) = new AccountHelper(withInstrumentQuantity)
  implicit def withMarket(withAccountInstrumentQuantity: WithAccountInstrumentQuantity) = new MarketHelper(withAccountInstrumentQuantity)
  implicit def wintPrice(withMarketAccountInstrumentQuantity: WithMarketAccountInstrumentQuantity) = new PriceHelper(withMarketAccountInstrumentQuantity)
  implicit def int2RichInt(value: Int) = new RichInt(value)

  import Util._
  implicit def Tuple2Trade(t: (Money, Market, Account, Instrument, Quantity)) = {
    t match {
      case ((money, mkt, account, ins: DiscountBond, qty)) =>
        FixedIncomeTradeImpl(
          tradingAccount = account,
          instrument =  ins,
          currency = money._2,
          tradeDate = TODAY,
          market = mkt,
          quantity = qty,
          unitPrice = money._1)
    }
  }

}
