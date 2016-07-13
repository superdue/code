package chapter10

object exercise01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  trait RectangleLike {
    def getX(): Double
    def getY(): Double
    def getWidth(): Double
    def getHeight(): Double
    def setFrame(x: Double, y: Double, width: Double, height: Double)

    def translate(dx: Double, dy: Double) = {
      setFrame(getX() + dx, getY() + dy, getWidth(), getHeight())
    }

    def grow(dx: Double, dy: Double) = {
      setFrame(getX() - dx, getY() - dy, getWidth() + 2 * dx, getHeight() + 2 * dy)
    }

    override def toString = "[%f, %f, %f, %f]".format(getX(), getY(), getWidth(), getHeight())

  }

  val egg = new java.awt.geom.Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
                                                  //> egg  : java.awt.geom.Ellipse2D.Double with chapter10.exercise01.RectangleLik
                                                  //| e = [5.000000, 10.000000, 20.000000, 30.000000]
  egg                                             //> res0: java.awt.geom.Ellipse2D.Double with chapter10.exercise01.RectangleLike
                                                  //|  = [5.000000, 10.000000, 20.000000, 30.000000]

  egg.translate(10, -10)
  egg                                             //> res1: java.awt.geom.Ellipse2D.Double with chapter10.exercise01.RectangleLike
                                                  //|  = [15.000000, 0.000000, 20.000000, 30.000000]

  egg.grow(10, 20)
  egg                                             //> res2: java.awt.geom.Ellipse2D.Double with chapter10.exercise01.RectangleLike
                                                  //|  = [5.000000, -20.000000, 40.000000, 70.000000]
}