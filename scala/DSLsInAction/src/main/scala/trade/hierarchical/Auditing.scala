package dsl

import api._

// Auditing不但能与其他Portfolio DSL进行组合,还保证被组合的Portfolio(即semantics)与它嵌入到自身的Balances DSL使用同一个实现
// Balances嵌入到Auditing的父类Portfolio
// 我们为了施加这样的约束,将Auditing的成员bal声明为semantics.bal,从而将它定义为一个Scala单例类型
trait Auditing extends Portfolio {
  val semantics: Portfolio

  // Scala单例类型
  val bal: semantics.bal.type
  import bal._

  // 按基本货币报告结余
  override def currentPortfolio(account: Account) = inBaseCurrency(semantics.currentPortfolio(account))._1
}