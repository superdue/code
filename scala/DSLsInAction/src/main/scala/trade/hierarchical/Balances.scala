package dsl

import java.util.Date
import api._
import Util._
import Currencies._

trait Balances {
  type Balance

  def balance(amount: BigDecimal, ccy: Currency, asOf: Date): Balance
  def inBaseCurrency(b: Balance): (Balance, Currency)
  def getBaseCurrency: Currency = USD
  def getConversionFactor(c: Currency) = 0.9
}
class BalancesImpl extends Balances {
  // 将抽象类型Balance落实为由金额,货币,日期(结余总是针对具体日期进行计算)构成的三元组
  case class BalanceRep(amount: BigDecimal, ccy: Currency, asOfDate: Date)
  type Balance = BalanceRep

  override def balance(amount: BigDecimal, ccy: Currency, asOf: Date) = BalanceRep(amount, ccy, asOf)
  override def inBaseCurrency(b: BalanceRep) = (BalanceRep(b.amount * getConversionFactor(getBaseCurrency), b.ccy, b.asOfDate), getBaseCurrency)
}
object Balances extends BalancesImpl
