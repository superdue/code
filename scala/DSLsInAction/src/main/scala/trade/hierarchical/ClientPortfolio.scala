package dsl

import api._
import Currencies._
import Util._

// ClientPortfolio DSL已经落实到Balances的一个具体实现,下一步需要保证,当ClientPortfolio与其他同样用到Balances的DSL组合时
// 双方拥有相同的Balances实现
trait ClientPortfolio extends Portfolio {
  val bal = new BalancesImpl
  import bal._
  override def currentPortfolio(account: Account) = {
    // lookup database to get the amount credited for this account &
    // the currency in which it is booked
    val amount = 10000 //... stubbed
    val ccy = HKD      //... stubbed
    val asOfDate = TODAY

    balance(amount, ccy, asOfDate)
  }
}
object ClientPortfolio extends ClientPortfolio
