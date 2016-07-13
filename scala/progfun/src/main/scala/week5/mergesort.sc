package week5

object mergesort {
  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
    
      def merge_not_good(xs: List[Int], ys: List[Int]): List[Int] =
        xs match {
          case Nil =>
            ys
          case x :: xs1 =>
            ys match {
              case Nil =>
                xs
              case y :: ys1 =>
                if (x < y) x :: merge_not_good(xs1, ys)
                else y :: merge_not_good(xs, ys1)
            }
        }

      // Pairs and Tuples
    
      def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
        
      val (fst, snd) = xs splitAt n  // splitAt
      merge(msort(fst), msort(snd))
    }
  }                                               //> msort: (xs: List[Int])List[Int]
  
  val nums = List(2, -4, 5, 7, 1)                 //> nums  : List[Int] = List(2, -4, 5, 7, 1)
  msort(nums)                                     //> res0: List[Int] = List(-4, 1, 2, 5, 7)
  
}