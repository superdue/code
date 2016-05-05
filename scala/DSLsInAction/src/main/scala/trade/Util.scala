package api

object Util {
  val TODAY = java.util.Calendar.getInstance.getTime
}

case class Mailer(user: String) {
  def mail(trade: Trade) = println("sending mail to user: " + user)
}

object Logger {
  def log(trade: Trade) = println("logging trade: " + trade)
}
