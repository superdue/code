import java.util._
import java.text._

val date = new SimpleDateFormat("MM/dd/yyyy").parse("09/20/2012")

def tellFortune(closure: (Date, String) => Unit): Unit = {
	closure(date, "Your day is filled with ceremony")
}
tellFortune({ (date: Date, fortune: String) => println(s"Fortune for ${date} is '${fortune}'") })