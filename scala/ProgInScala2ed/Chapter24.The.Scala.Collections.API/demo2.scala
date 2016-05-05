// Sorted sets

val myOrdering = Ordering.fromLessThan[String](_ > _)

import scala.collection.immutable.TreeSet

val set = TreeSet.empty(myOrdering)

val numbers = set + ("one", "two", "three", "four")

println(numbers)

println(numbers range ("three", "four"))
println(numbers from "three")