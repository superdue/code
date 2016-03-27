package aljafp

object ch01 {
  
  abstract class Point
  class CartesianPt(x: Int, y: Int) extends Point
  class ManhattanPt(x: Int, y: Int) extends Point
  
  abstract class Num
  class Zero extends Num
  class OneMoreThan(predecessor: Num) extends Num
  
  abstract class Layer
  class Base(o: Any) extends Layer
  class Slice(o: Any) extends Layer
  

}