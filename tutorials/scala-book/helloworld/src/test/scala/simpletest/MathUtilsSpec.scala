package simpletest

import org.scalatest.flatspec.AnyFlatSpec

class MathUtilsSpec extends AnyFlatSpec {
  behavior of "An Int"
  it should "double when used in MathUtils.double" in {
    val n: Int = 32
    assert(MathUtils.double(n) == 64)
  }

  behavior of "A Double"
  it should "double when used in MathUtils.double" in {
    val n: Double = 32.0
    assert(MathUtils.double(n) == 64.0)
  }

  behavior of "A Float"
  it should "double when used in MathUtils.double" in {
    val n: Float = 32.0F
    assert(MathUtils.double(n) == 64.0F)
  }
}
