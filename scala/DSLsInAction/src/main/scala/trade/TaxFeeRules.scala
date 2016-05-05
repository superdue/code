package api

trait TaxFeeRules {
  /** 返回给定交易应缴纳的税费品种列表 */
  def forTrade(trade: Trade): List[TaxFee]
  /** 针对给定交易计算具体某一项税费 */
  def calculatedAs(trade: Trade): PartialFunction[TaxFee, BigDecimal]
}
