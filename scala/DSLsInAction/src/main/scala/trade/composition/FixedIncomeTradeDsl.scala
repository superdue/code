package dsl

import api._

trait FixedIncomeTradeDsl extends TradeDsl {
  type T = FixedIncomeTrade

  import FixedIncomeTradingService._

  override def enrich: PartialFunction[T, T] = {
    case t =>
      t.cashValue = cashValue(t)
      t.taxes = taxes(t)
      t.accruedInterest = accruedInterest(t)
      t
  }
}
object FixedIncomeTradeDsl extends FixedIncomeTradeDsl