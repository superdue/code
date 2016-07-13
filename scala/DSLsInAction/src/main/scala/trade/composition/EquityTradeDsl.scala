package dsl

import api._

trait EquityTradeDsl extends TradeDsl {
  type T = EquityTrade

  import EquityTradingService._

  override def enrich: PartialFunction[T, T] = {
    case t =>
      t.cashValue = cashValue(t)
      t.taxes = taxes(t)
      t
  }
}
object EquityTradeDsl extends EquityTradeDsl