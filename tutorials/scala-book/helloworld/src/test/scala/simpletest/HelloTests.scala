package simpletest

import org.scalatest.funsuite.AnyFunSuite

class HelloTests extends AnyFunSuite {
  test("the name is set correctly in constructor") {
    val p = new Person("Barney Rubble")
    assert(p.name == "Barney Rubble")
  }

  test("a Person's name can be changed") {
    val p = new Person("Noam Chomsky")
    p.name = "Karl Marx"
    assert(p.name == "Karl Marx")
  }
}
