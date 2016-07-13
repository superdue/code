package chapter07

object exercise07 extends App {
  
  import java.util.{ HashMap => JavaHashMap }
  
  def toScalaMap[K, V](jMap: JavaHashMap[K, V]) = {
    import scala.collection.mutable.{ HashMap => ScalaHashMap }
    val sMap = ScalaHashMap[K, V]()
    import scala.collection.JavaConversions.mapAsScalaMap
    for ((k, v) <- jMap) sMap.put(k, v)
    sMap
  }
  

}