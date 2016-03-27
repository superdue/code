package aljafp

object ch07 {
  
  trait Fruit
  case class Apple extends Fruit
  case class Pear extends Fruit
  case class Lemon extends Fruit
  case class Fig extends Fruit
  
  trait Tree {
    def accept(ask: bTreeVisitor): Boolean
    def accept(ask: iTreeVisitor): Int
    def accept(ask: tTreeVisitor): Tree
  }
  case class Bud extends Tree {
    def accept(ask: bTreeVisitor) = ask.forBud
    def accept(ask: iTreeVisitor) = ask.forBud
    def accept(ask: tTreeVisitor) = ask.forBud
  }
  case class Flat(f: Fruit, t: Tree) extends Tree {
    def accept(ask: bTreeVisitor) = ask.forFlat(f, t)
    def accept(ask: iTreeVisitor) = ask.forFlat(f, t)
    def accept(ask: tTreeVisitor) = ask.forFlat(f, t)
  }
  case class Split(l: Tree, r: Tree) extends Tree {
    def accept(ask: bTreeVisitor) = ask.forSplit(l, r)
    def accept(ask: iTreeVisitor) = ask.forSplit(l, r)
    def accept(ask: tTreeVisitor) = ask.forSplit(l, r)
  }
  
  trait bTreeVisitor {
    def forBud: Boolean
    def forFlat(f: Fruit, t: Tree): Boolean
    def forSplit(l: Tree, r: Tree): Boolean
  }
  class blsFlat extends bTreeVisitor {
    def forBud = true
    def forFlat(f: Fruit, t: Tree) = t accept this
    def forSplit(l: Tree, r: Tree) = false
  }
  class blsSplit extends bTreeVisitor {
    def forBud = true
    def forFlat(f: Fruit, t: Tree) = false
    def forSplit(l: Tree, r: Tree) = if (l accept this) r accept this else false
  }
  class bHasFruit extends bTreeVisitor {
    def forBud = false
    def forFlat(f: Fruit, t: Tree) = true
    def forSplit(l: Tree, r: Tree) = if (l accept this) true else r accept this
  }
  
  trait iTreeVisitor {
    def forBud: Int
    def forFlat(f: Fruit, t: Tree): Int
    def forSplit(l: Tree, r: Tree): Int
  }
  class iHeight extends iTreeVisitor {
    def forBud = 0
    def forFlat(f: Fruit, t: Tree) = t.accept(this) + 1
    def forSplit(l: Tree, r: Tree) = l.accept(this) max r.accept(this) + 1
  }
  
  trait tTreeVisitor {
    def forBud: Tree
    def forFlat(f: Fruit, t: Tree): Tree
    def forSplit(l: Tree, r: Tree): Tree
  }
  class tSubst(n: Fruit, o: Fruit) extends tTreeVisitor {
    def forBud = new Bud
    def forFlat(f: Fruit, t: Tree) = if (o equals f) new Flat(n, t accept this) else new Flat(f, t accept this)
    def forSplit(l: Tree, r: Tree) = new Split(l accept this, r accept this)
  }
  
  class iOccurs(a: Fruit) extends iTreeVisitor {
    def forBud = 0
    def forFlat(f: Fruit, t: Tree) = if (f equals a) t.accept(this) + 1 else t.accept(this)
    def forSplit(l: Tree, r: Tree) = l.accept(this) + r.accept(this)
  }

}