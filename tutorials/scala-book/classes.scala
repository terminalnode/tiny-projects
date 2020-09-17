object Main {
  val defaultVarue = "defaultVar"
  val defaultValue = "defaultVal"

  def main(args: Array[String]) = {
    println(new MyClass)
    println(new MyClass("undefaultValue"))
    println(new MyClass("undefaultValue", "undefaultVarue"))
    println()
    println(new KeywordParameters(10, 20, 30))
    println(new KeywordParameters(10, 20))
    println(new KeywordParameters(var3=30, var2=20, var1=10))
    new KeywordParameters().printSomething // look mommy, no parens!
    println(s"kwparam.sum with default values: ${new KeywordParameters().sum}")
  }

  class MyClass(val value: String, var varue: String) {
    println("Running constructor for MyClass!")

    def this(value: String) = {
      this(value, defaultVarue)
    }

    def this() = {
      this(defaultValue, defaultVarue)
    }

    override def toString(): String = s"  => Value is ${value} and varue is ${varue}!"

    println("Finished running constructor for MyClass!")
  }

  class KeywordParameters(var var1: Int = 1, var var2: Int = 2, var var3: Int = 3) {
    def printSomething: Unit = {
      println("Hasta la vista, bèbè!")
    }

    def sum: Int = {
      var1 + var2 + var3
    }

    override def toString(): String = s"KeywordParameters<var1=${var1}, var2=${var2}, var3=${var3}>"
  }
}

