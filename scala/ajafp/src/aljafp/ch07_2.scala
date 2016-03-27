package aljafp

object ch07_2 {
  
  trait Fruit
  case class Apple extends Fruit
  case class Pear extends Fruit
  case class Lemon extends Fruit
  case class Fig extends Fruit
  
  trait TreeVisitor {
    def forBud: Any
    def forFlat(f: Fruit, t: Tree): Any
    def forSplit(l: Tree, r: Tree): Any
  }
  trait Tree {
    def accept(ask: TreeVisitor): Any
  }
  case class Bud extends Tree {
    def accept(ask: TreeVisitor) = ask.forBud
  }
  case class Flat(f: Fruit, t: Tree) extends Tree {
    def accept(ask: TreeVisitor) = ask.forFlat(f, t)
  }
  case class Split(l: Tree, r: Tree) extends Tree {
    def accept(ask: TreeVisitor) = ask.forSplit(l, r)
  }
  
  class IsFlat extends TreeVisitor {
    def forBud = true
    def forFlat(f: Fruit, t: Tree) = t accept this
    def forSplit(l: Tree, r: Tree) = false
  }
  class IsSplit extends TreeVisitor {
    def forBud = true
    def forFlat(f: Fruit, t: Tree) = false
    def forSplit(l: Tree, r: Tree) = if (l.accept(this).asInstanceOf[Boolean]) r accept this else false
  }
  class HasFruit extends TreeVisitor {
    def forBud = false
    def forFlat(f: Fruit, t: Tree) = true
    def forSplit(l: Tree, r: Tree) = if (l.accept(this).asInstanceOf[Boolean]) true else r accept this
  }
  class Height extends TreeVisitor {
    def forBud = 0
    def forFlat(f: Fruit, t: Tree) = t.accept(this).asInstanceOf[Int] + 1
    def forSplit(l: Tree, r: Tree) = l.accept(this).asInstanceOf[Int] max r.accept(this).asInstanceOf[Int] + 1
  }
  class Subst(n: Fruit, o: Fruit) extends TreeVisitor {
    def forBud = new Bud
    def forFlat(f: Fruit, t: Tree) = if (o equals f) new Flat(n, t.accept(this).asInstanceOf[Tree]) else new Flat(f, t.accept(this).asInstanceOf[Tree])
    def forSplit(l: Tree, r: Tree) = new Split(l.accept(this).asInstanceOf[Tree], r.accept(this).asInstanceOf[Tree])
  }
  class Occurs(a: Fruit) extends TreeVisitor {
    def forBud = 0
    def forFlat(f: Fruit, t: Tree) = if (f equals a) t.accept(this).asInstanceOf[Int] + 1 else t.accept(this).asInstanceOf[Int]
    def forSplit(l: Tree, r: Tree) = l.accept(this).asInstanceOf[Int] + r.accept(this).asInstanceOf[Int]
  }

}