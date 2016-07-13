package chapter09

object exercise09 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  import java.io.File
  import scala.util.matching.Regex

  def countMatchFiles(dir: File, pattern: Regex): Int = {
    val subdirs = dir.listFiles.filter(_.isDirectory)
    val fileNames = dir.listFiles.filter(!_.isDirectory).map(_.getName)

    subdirs.map(countMatchFiles(_, pattern)).sum +
      (for (f <- fileNames; s <- pattern findFirstIn f) yield s).size
  }                                               //> countMatchFiles: (dir: java.io.File, pattern: scala.util.matching.Regex)Int

  println("Count of *.scala files: %d".format(countMatchFiles(new File("../"), "\\.scala$".r)))
                                                  //> Count of *.scala files: 0

  // more elegant solution
  def getFileTree(f: File): Stream[File] =
    f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree) else Stream.empty)
                                                  //> getFileTree: (f: java.io.File)Stream[java.io.File]

  println("More elegant solution: %d".format(getFileTree(new File("../")).filter(_.getName.endsWith(".scala")).size))
                                                  //> More elegant solution: 0
}