package week6

object books {

  case class Book(title: String, authors: List[String])
  
  //val books = List(
  val books = Set(
    Book(title = "t1", authors = List("a1", "a2")),
    Book(title = "t2", authors = List("a3", "a4")),
    Book(title = "t3", authors = List("a5")),
    Book(title = "t4", authors = List("a5", "a6")),
    Book(title = "t5", authors = List("a7", "a8", "a9")),
    Book(title = "t6", authors = List("a5"))
  )                                               //> books  : scala.collection.immutable.Set[week6.books.Book] = Set(Book(t3,List
                                                  //| (a5)), Book(t2,List(a3, a4)), Book(t5,List(a7, a8, a9)), Book(t1,List(a1, a2
                                                  //| )), Book(t4,List(a5, a6)), Book(t6,List(a5)))
  
  for (b <- books; a <- b.authors if a startsWith "a5") yield b.title
                                                  //> res0: scala.collection.immutable.Set[String] = Set(t3, t4, t6)
  
  for {
    b1 <- books
    b2 <- books
    if b1.title < b2.title
    //if b1 != b2
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1                                      //> res1: scala.collection.immutable.Set[String] = Set(a5)
}