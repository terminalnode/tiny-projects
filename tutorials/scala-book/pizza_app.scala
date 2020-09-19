import scala.collection.mutable.ArrayBuffer

// Indendation isn't necessary, but I like it.
sealed trait Topping
  case object Cheese extends Topping
  case object Pepperoni extends Topping
  case object Sausage extends Topping
  case object Mushrooms extends Topping
  case object Onions extends Topping

sealed trait CrustSize
  case object SmallCrustSize extends CrustSize
  case object MediumCrustSize extends CrustSize
  case object LargeCrustSize extends CrustSize

sealed trait CrustType
  case object RegularCrustType extends CrustType
  case object ThinCrustType extends CrustType
  case object ThickCrustType extends CrustType

class Pizza (
  var crustSize: CrustSize,
  var crustType: CrustType,
  var toppings: ArrayBuffer[Topping]
) {
  def addTopping(t: Topping): Unit = toppings += t
  def removeTopping(t: Topping): Unit = toppings -= t
  def removeAllToppings(t: Topping): Unit = toppings.clear()

  def getPrice(
    toppingsPrices: Map[Topping, Double],
    crustSizePrices: Map[CrustSize, Double],
    crustTypePrices: Map[CrustType, Double]
  ): Double = {
    val crustTypePrice = crustTypePrices(crustType)
    val crustSizePrice = crustSizePrices(crustSize)
    val toppingsPrice = toppings.map(toppingsPrices(_)).sum
    crustTypePrice + crustSizePrice // + toppingsPrice
  }
}

class Order (
  var pizzas: ArrayBuffer[Pizza],
  var customer: Customer,
  var toppingsPrices: Map[Topping, Double],
  var crustSizePrices: Map[CrustSize, Double],
  var crustTypePrices: Map[CrustType, Double]
) {
  def this(
  customer: Customer,
  toppingsPrices: Map[Topping, Double],
  crustSizePrices: Map[CrustSize, Double],
  crustTypePrices: Map[CrustType, Double]
  ) = this(new ArrayBuffer(), customer, toppingsPrices, crustSizePrices, crustTypePrices)

  def addPizza(p: Pizza) = pizzas += p
  def removePizza(p: Pizza) = pizzas -= p

  def getBasePrice(): Double = pizzas.map(_.getPrice(toppingsPrices, crustSizePrices, crustTypePrices)).sum
  def getTaxes(): Double = getBasePrice() * 0.25
  def getTotalPrice(): Double = getBasePrice() + getTaxes()
}

class Address (
  var street: String,
  var city: String,
  // var state: String, // Yankee nonsense
  var zipCode: String
)

class Customer (
  var name: String,
  var phone: String,
  var address: Address
)

object MainDriver extends App {
  val toppingPrices: Map[Topping, Double] = Map(
    Cheese -> 1.0,
    Pepperoni -> 2.0,
    Sausage -> 3.0,
    Mushrooms -> 4.0,
    Onions -> 5.0
  )

  val crustSizePrices: Map[CrustSize, Double] = Map(
    SmallCrustSize -> 1.0,
    MediumCrustSize -> 1.0,
    LargeCrustSize -> 1.0
  )

  val crustTypePrices: Map[CrustType, Double] = Map(
    RegularCrustType -> 1.0,
    ThinCrustType -> 1.0,
    ThickCrustType -> 1.0
  )

  val p1 = new Pizza(
    MediumCrustSize,
    ThinCrustType,
    ArrayBuffer(Cheese)
  )

  val p2 = new Pizza(
    SmallCrustSize,
    ThickCrustType,
    ArrayBuffer(Cheese, Sausage)

  )

  val address = new Address("123 Fakestreet", "Fakecity", "123 45")
  val customer = new Customer("Fake Namingson", "031-13 13 13", address)
  val order = new Order(customer, toppingPrices, crustSizePrices, crustTypePrices)
  order.addPizza(p1)
  order.addPizza(p2)
  println(order.getBasePrice())
  println(order.getTaxes())
  println(order.getTotalPrice())
}
