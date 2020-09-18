object Main extends App {
  // Lets do some methods and stuff.
  val numbers = List.range(1, 11)
  val doubleNumbers = numbers.map(_ * 2)
  val stringNumbers = numbers.map("¡" + _ + "!")
  val evenNumbers = numbers.filter(_ % 2 == 0)
  println(s"Double numbers: ${doubleNumbers}")
  println(s"String numbers: ${stringNumbers}")
  println(s"Even numbers:   ${evenNumbers}")

  // foreach
  println("Foreach: ")
  evenNumbers.foreach(println)

  // head and tail
  println(s"Head of evenNumbers: ${evenNumbers.head}")
  println(s"Tail of evenNumbers: ${evenNumbers.tail}")
  println()
  println(s"Take 3 evenNumbers: ${evenNumbers.take(3)}")
  println(s"Drop 3 evenNumbers: ${evenNumbers.take(3)}")
  println()
  println(s"Take evenNumbers while < 5: ${evenNumbers.takeWhile(_ < 5)}")
  println(s"Drop evenNumbers while < 5: ${evenNumbers.dropWhile(_ < 5)}")

  // reduction
  def add(x: Int, y: Int): Int = {
    val theSum = x + y;
    println(s"${x} + ${y} = ${theSum}")
    theSum
  }
  println(s"Reduction of evenNumbers: ${evenNumbers.reduce(add)}")

  // Map examples
  val abcMap = Map(1 -> "a", 2 -> "b", 3 -> "c")
  println(abcMap)
  val ABCMap = abcMap.transform((k,v) => v.toUpperCase());
  println(ABCMap)
  val bcMap = abcMap.filterKeys(_ > 1).toMap // filterKeys seems to be lazy
  println(bcMap)
}
