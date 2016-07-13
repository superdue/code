object intsets {
  val t1 = new NonEmpty(3, Empty, Empty)          //> t1  : NonEmpty = {.3.}
  val t2 = t1 incl 4                              //> t2  : IntSet = {.3{.4.}}
  // no handling for "="
  t2 incl 3                                       //> res0: IntSet = {.3{.4.}}
  t1 union t2                                     //> res1: IntSet = {.3{.4.}}
  
  val t3 = new NonEmpty(2, Empty, Empty)          //> t3  : NonEmpty = {.2.}
  t1 union t3 union t2                            //> res2: IntSet = {{.2.}3{.4.}}

}

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def union(other: IntSet): IntSet = other
  override def toString = "."
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  
  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
    
  override def toString = "{" + left + elem + right + "}"
  
  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}

/*
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def toString = "."
  def union(other: IntSet): IntSet = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  
  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
    
  override def toString = "{" + left + elem + right + "}"
  
  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}
*/