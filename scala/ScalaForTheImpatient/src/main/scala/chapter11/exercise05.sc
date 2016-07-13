package chapter11

object exercise05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  class Table {
    import collection.mutable.ArrayBuffer
    private val chunks = new ArrayBuffer[String]

    def |(chunk: String) = {
      chunks += "<td>%s</td>\n".format(chunk)
      this
    }

    def ||(chunk: String) = {
      chunks += "</tr><tr>\n<td>%s</td>".format(chunk)
      this
    }

    override def toString = "<table><tr>\n%s</tr></table>".format(chunks.mkString)
  }

  object Table { def apply() = new Table() }

  val t = Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM,.NET"
                                                  //> t  : chapter11.exercise05.Table = <table><tr>
                                                  //| <td>Java</td>
                                                  //| <td>Scala</td>
                                                  //| </tr><tr>
                                                  //| <td>Gosling</td><td>Odersky</td>
                                                  //| </tr><tr>
                                                  //| <td>JVM</td><td>JVM,.NET</td>
                                                  //| </tr></table>

}