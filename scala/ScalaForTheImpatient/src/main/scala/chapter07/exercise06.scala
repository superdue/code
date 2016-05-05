package chapter07

object exercise06 extends App {

  import java.util.{ HashMap => JavaHashMap }
  import scala.collection.mutable.{ HashMap => ScalaHashMap }
  
  def toScalaMap[K, V](jMap: JavaHashMap[K, V]) = {
    val sMap = ScalaHashMap[K, V]()
    import scala.collection.JavaConversions.mapAsScalaMap
    for ((k, v) <- jMap) sMap.put(k, v)
    sMap
  }

}