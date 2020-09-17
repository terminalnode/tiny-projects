import scala.collection.mutable.ArrayBuffer

sealed trait PizzaTopping
  case object Cheese extends PizzaTopping
  case object Pepperoni extends PizzaTopping
  case object Sausage extends PizzaTopping
  case object Mushrooms extends PizzaTopping

sealed trait PizzaSize
  case object KiddyPizza extends PizzaSize
  case object NormalPizza extends PizzaSize
  case object GigaPizza extends PizzaSize

sealed trait PizzaCrust
  case object ThinCrust extends PizzaCrust
  case object RegularCrust extends PizzaCrust
  case object ThickCrust extends PizzaCrust

class Pizza(
    var pizzaSize: PizzaSize = NormalPizza,
    var pizzaCrust: PizzaCrust = RegularCrust
  ) {
  val toppings = ArrayBuffer[PizzaTopping]()

  def addTopping(t: PizzaTopping): Unit = toppings += t
  def removeTopping(t: PizzaTopping): Unit = toppings -= t
  def clearToppings(): Unit = toppings.clear()

  override def toString(): String = {
    s"""
    |My Pizza:
    |  Size: ${pizzaSize}
    |  Crust: ${pizzaCrust}
    |  Toppings: ${toppings}
    """.stripMargin.trim()
  }
}

object PizzaTest extends App {
  val pizza = new Pizza
  pizza.addTopping(Sausage)
  pizza.addTopping(Cheese)
  pizza.addTopping(Mushrooms)
  pizza.addTopping(Cheese)
  pizza.removeTopping(Cheese)
  println(pizza)
}
