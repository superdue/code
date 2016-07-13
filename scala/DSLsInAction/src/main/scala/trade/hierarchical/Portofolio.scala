package dsl

import api._

trait Portfolio {
  val bal: Balances
  import bal._
  def currentPortfolio(account: Account): Balance
}
