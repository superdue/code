package chapter09

object exercise08 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val html = io.Source.fromURL("http://horstmann.com", "UTF-8").mkString
                                                  //> html  : String = "<?xml version="1.0" encoding="UTF-8"?>
                                                  //| <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
                                                  //|       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
                                                  //| <html xmlns="http://www.w3.org/1999/xhtml">
                                                  //| <head>
                                                  //|   <title>Cay Horstmann's Home Page</title>
                                                  //|   <link href="styles.css" rel="stylesheet" type="text/css" />
                                                  //|   <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
                                                  //| </head>
                                                  //| 
                                                  //| <body>
                                                  //| <h1>Welcome to Cay Horstmann's Home Page!<img class="sideimage"
                                                  //| alt="Photo of Cay Horstmann" src="images/cay-rowing.gif" /></h1>
                                                  //| 
                                                  //| <p><a href="mailto:cay@horstmann.com">cay@horstmann.com</a> | <a
                                                  //| href="caypubkey.txt">PGP Key</a></p>
                                                  //| 
                                                  //| 
                                                  //| <p>I grew up in Northern Germany and attended the <a
                                                  //| href="http://www.uni-kiel.de/">Christian-Albrechts-Universit√§t</a> in <a
                                                  //| href="http://www.kiel.de/">Kiel</a>, a harbor town at the Baltic sea. I
                                                  //| received a M.S. in computer science from <a href="http://www.s
                                                  //| Output exceeds cutoff limit.

  val srcPattern = """(?is)<\s*img[^>]*src\s*=\s*['"\s]*([^'"]+)['"\s]*[^>]*>""".r
                                                  //> srcPattern  : scala.util.matching.Regex = (?is)<\s*img[^>]*src\s*=\s*['"\s]*
                                                  //| ([^'"]+)['"\s]*[^>]*>

  for (srcPattern(s) <- srcPattern findAllIn html) println(s)
                                                  //> images/cay-rowing.gif
                                                  //| images/violet.jpg
                                                  //| images/zork1.gif
}