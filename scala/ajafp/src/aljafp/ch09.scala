package aljafp

object ch09 {
  
  abstract class Point(val x: Int, val y: Int) {
    def closerToO(p: Point): Boolean = distanceToO <= p.distanceToO
    def minus(p: Point): Point = new CartesianPt(x-p.x, y-p.y)
    def distanceToO: Int
  }
  class CartesianPt(x: Int, y: Int) extends Point(x, y) {
    import math._
    def distanceToO = sqrt(pow(x,2) + pow(y,2)).toInt
  }
  class ManhattanPt(x: Int, y: Int) extends Point(x, y) {
    def distanceToO = x + y 
  }
  class ShadowedManhattanPt(x: Int, y: Int, _x: Int, _y: Int) extends ManhattanPt(x, y) {
    override def distanceToO = super.distanceToO + _x + _y
  }
  class ShadowedCartesianPt(x: Int, y: Int, _x: Int, _y: Int) extends CartesianPt(x, y) {
    import math._
    override def distanceToO = new CartesianPt(x + _x, y + _y).distanceToO
  }
  
  trait Shape {
    def accept(ask: ShapeVisitor): Boolean
  }
  class Circle(r: Int) extends Shape {
    def accept(ask: ShapeVisitor) = ask.forCircle(r)
  }
  class Square(s: Int) extends Shape {
    def accept(ask: ShapeVisitor) = ask.forSquare(s)
  }
  class Trans(q: Point, s: Shape) extends Shape {
    def accept(ask: ShapeVisitor) = ask.forTrans(q, s)
  }
  
  trait ShapeVisitor {
    def forCircle(r: Int): Boolean
    def forSquare(s: Int): Boolean
    def forTrans(q: Point, s: Shape): Boolean
  }
  class HasPt(p: Point) extends ShapeVisitor {
    def forCircle(r: Int) = p.distanceToO <= r
    def forSquare(s: Int) = if (p.x <= s) p.y <= s else false
    def forTrans(q: Point, s: Shape) = s.accept(newHasPt(p.minus(q)))
    def newHasPt(p: Point): ShapeVisitor = new HasPt(p)
  }
  
  trait UnionVisitor extends ShapeVisitor {
    def forUnion(s: Shape, t: Shape): Boolean
  }
  class Union(s: Shape, t: Shape) extends Shape {
    def accept(ask: ShapeVisitor) = ask.asInstanceOf[UnionVisitor].forUnion(s, t)
  }
  class UnionHasPt(p: Point) extends HasPt(p) with UnionVisitor {
    def forUnion(s: Shape, t: Shape) = if (s.accept(this)) true else t.accept(this)
    override def newHasPt(p: Point) = new UnionHasPt(p)
  }

}