package fpinscala.errorhandling

import fpinscala.datastructures._

sealed trait Either[+E, +A] {
  def map[B](f: A => B): Either[E, B] =
    this match {
      case Left(e) => Left(e)
      case Right(a) => Right(f(a))
    }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] =
    this match {
      case Left(e) => Left(e)
      case Right(a) => f(a)
    }

  def orElse[EE >: E, AA >: A](b: => Either[EE, AA]): Either[EE, AA] = ???

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
   this.flatMap(x => b.map(y => f(x,y)))

}
case class Left[+E](get: E) extends Either[E, Nothing]
case class Right[+A](get: A) extends Either[Nothing, A]

object Either {

  def mean(xs: List[Double]): Either[String, Double] =
    if (xs.isEmpty) Left("mean of empty list!")
    else Right(xs.sum / xs.length)

  def safeDiv(x: Int, y: Int): Either[Exception, Int] =
    try Right(x / y)
    catch { case e: Exception => Left(e) }

  def Try[A](a: => A): Either[Exception, A] =
    try Right(a)
    catch { case e: Exception => Left(e) }

  def traverse[E, A, B](es: List[A])(f: A => Either[E, B]): Either[E, List[B]] =
    es match {
      case Nil => Right(Nil)
      case Cons(h, t) => f(h).map2(traverse(t)(f))((b: B, bs: List[B]) => Cons(b, bs))
    }

  def traverse_1[E, A, B](es: List[A])(f: A => Either[E, B]): Either[E, List[B]] =
    es.foldRight[Either[E, List[B]]](Right(Nil))((a, b) => f(a).map2(b)(Cons(_, _)))

  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] =
    traverse(es)(x => x)



}
