package fpinscala.datastructures

sealed trait List[+A] {

  private def ff[B >: A](a: B): Int = a.asInstanceOf[Int]

  def isEmpty: Boolean =
    this match {
      case Nil => true
      case _ => false
    }

  def head: A =
    this match {
      case Nil => sys.error("head of Nil")
      case Cons(h, _) => h
    }

  def sum: Int =
    this match {
      case Nil => 0
      case Cons(h, t) => ff(h) + t.sum
    }

  def product: Double =
    this match {
      case Nil => 1.0
      case Cons(0.0, _) => 0.0
      case Cons(h, t) => ff(h) * t.product
    }

  def append[B >: A](xs: List[B]): List[B] =
    this match {
      case Nil => xs
      case Cons(h, t) => Cons(h, t.append(xs))
    }

  def foldRight[B](z: B)(f: (A, B) => B): B =
    this match {
      case Nil => z
      case Cons(h, t) => f(h, t.foldRight(z)(f))
    }

  def sum2: Int =
    foldRight(0)((x, acc) => acc + ff(x))

  def product2: Double =
    foldRight(1.0)((x, acc) => acc * ff(x))

  def tail: List[A] =
    this match {
      case Nil => sys.error("tail fo empty list")
      case Cons(_, t) => t
    }

  def setHead[B >: A](h: B): List[B] =
    this match {
      case Nil => sys.error("setHead on empty list")
      case Cons(_, t) => Cons(h, t)
    }

  def drop(n: Int): List[A] =
    if (n <= 0) this
    else this match {
      case Nil => Nil
      case Cons(_, t) => t.drop(n-1)
    }

  def dropWhile(f: A => Boolean): List[A] =
    this match {
      case Cons(h, t) if f(h) => t.dropWhile(f)
      case _ => this
    }

  def init: List[A] =
    this match {
      case Nil => sys.error("init of empty list")
      case Cons(_, Nil) => Nil
      case Cons(h, t) => Cons(h, t.init)
    }

  def init2: List[A] = {
    import collection.mutable.ListBuffer
    val buf = new ListBuffer[A]
    @annotation.tailrec
    def go(cur: List[A]): List[A] = cur match {
      case Nil => sys.error("init of empty list")
      case Cons(_, Nil) => List(buf.toList: _*)
      case Cons(h, t) => buf += h; go(t)
    }
    go(this)
  }

  def length: Int =
    foldRight(0)((_, acc) => acc + 1)
  
  def foldLeft[B](z: B)(f: (B, A) => B): B =
    this match {
      case Nil => z
      case Cons(h, t) => t.foldLeft(f(z, h))(f)
    }
  
  def sum3: Int =
    foldLeft(0)((acc, x) => acc + ff(x))

  def product3: Double =
    foldLeft(1.0)((acc, x) => acc * ff(x))

  def length2: Int =
    foldLeft(0)((acc, _) => acc + 1)

  def reverse: List[A] =
    foldRight(List[A]())((x, acc) => acc.append(List(x)))

  def reverse2: List[A] =
    foldLeft(List[A]())((acc, x) => Cons(x, acc))

  def foldRightViaFoldLeft[B](z: B)(f: (A, B) => B): B =
    reverse.foldLeft(z)((acc, x) => f(x, acc))

  def foldRightViaFoldLeft_1[B](z: B)(f: (A, B) => B): B =
    foldLeft((b: B) => b)((g, a) => (b: B) => g(f(a, b)))(z) // burning brain...

  def foldLeftViaFoldRight[B](z: B)(f: (B, A) => B): B =
    foldRight((b: B) => b)((a, g) => (b: B) => g(f(b, a)))(z)

  def appendViaFoldRight[B >: A](xs: List[B]): List[B] =
    foldRight(xs)((x, acc) => Cons(x, acc))

  def map[B](f: A => B): List[B] =
    foldRight(Nil: List[B])((x, acc) => Cons(f(x), acc))

  def map_1[B](f: A => B): List[B] =
    foldRightViaFoldLeft(Nil: List[B])((x, acc) => Cons(f(x), acc))

  def map_2[B](f: A => B): List[B] = {
    val buf = new collection.mutable.ListBuffer[B]
    def go(xs: List[A]): Unit = xs match {
      case Nil => ()
      case Cons(h, t) => buf += f(h); go(t)
    }
    go(this)
    List(buf.toList: _*)
  }

  def filter(f: A => Boolean): List[A] =
    foldRight(Nil: List[A])((x, acc) => if (f(x)) Cons(x, acc) else acc)

  def filter_1(f: A => Boolean): List[A] =
    foldRightViaFoldLeft(Nil: List[A])((x, acc) => if (f(x)) Cons(x, acc) else acc)

  def filter_2(f: A => Boolean): List[A] = {
    val buf = new collection.mutable.ListBuffer[A]
    def go(xs: List[A]): Unit = xs match {
      case Nil => ()
      case Cons(h, t) => if (f(h)) buf += h; go(t)
    }
    go(this)
    List(buf.toList: _*)
  }

  def flatMap[B](f: A => List[B]): List[B] =
    List.concat(map(f))

  def filterViaFlatMap(f: A => Boolean): List[A] =
    flatMap(a => if(f(a)) List(a) else Nil)

}
case object Nil extends List[Nothing]
case class Cons[+A](h: A, t: List[A]) extends List[A]

object List {

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + t.sum
    case _ => 101
  }

  def concat[A](xs: List[List[A]]): List[A] =
    xs.foldRight(Nil: List[A])((x, acc) => x.append(acc))

  def add1(xs: List[Int]): List[Int] =
    xs.foldRight(Nil: List[Int])((x, acc) => Cons(x+1, acc))

  def doubleToString(xs: List[Double]): List[String] =
    xs.foldRight(Nil: List[String])((x, acc) => Cons(x.toString, acc))

  def addPairwise(a: List[Int], b: List[Int]): List[Int] = (a, b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1,t1), Cons(h2,t2)) => Cons(h1+h2, addPairwise(t1,t2))
  }

  def zipWith[A, B, C](a: List[A], b: List[B])(f: (A, B) => C): List[C] = (a, b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1,t1), Cons(h2,t2)) => Cons(f(h1,h2), zipWith(t1,t2)(f))
  }

  @annotation.tailrec
  def startsWith[A](xs: List[A], prefix: List[A]): Boolean = (xs, prefix) match {
    case (_, Nil) => true
    case (Cons(h,t), Cons(h2,t2)) if h == h2 => startsWith(t, t2)
    case _ => false
  }

  @annotation.tailrec
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = sup match {
    case Nil => sub == Nil
    case _ if startsWith(sup, sub) => true
    case Cons(h, t) => hasSubsequence(t, sub)
  }

}
