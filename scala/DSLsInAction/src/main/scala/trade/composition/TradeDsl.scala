package dsl

import api._
import api.Markets._

trait TradeDsl {
  type T <: Trade

  def withTrade(trade: T)(op: T => Unit): T = {
    if (validate(trade))
      (enrich andThen journalize andThen op)(trade)
    trade
  }
  def validate(trade: T): Boolean = ???
  def enrich: PartialFunction[T, T]
  def journalize: PartialFunction[T, T] = {
    case t => ???
  }
}