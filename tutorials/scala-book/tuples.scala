class Person(var name: String) {
  override def toString(): String = s"Person<name='${name}'>"
}

object Main extends App {
  // Tuples work basically like in python, except they can be
  // typed and are limited to 22 items for some reason.
  // Apparently under the hood java has implementations for
  // Tuple2, Tuple3 ... all the way up to Tuple22. And that's the reason.
  //
  // This is being improved in Scala 3.
  val tup: (Int, String, Person) = (1, "hi", new Person("Mad Max"))

  // This wouldn't work because of typing.
  //val tup: (Int, String, Person) = ("1", "hi", new Person("Mad Max"))
  println(tup)
  println(tup._1)

  // We can also do a python style unpacking of the values in a tuple
  val (num, str, pers) = tup
  println(num)
  println(str)
  println(pers)

  // Tuples are not collections:
  // tup.map(/* something */) WILL throw an exception
}
