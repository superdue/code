package chapter07

object exercise09 extends App {
  
  import java.lang.System
  println("Authorization token:")
  val username = System getProperty "user.name"
  val password = readLine
  if ("secret" equals password) println("Hello," + username + ", Welcome to the real world.")
  else System.err println "Sorry, you are not identified."

}