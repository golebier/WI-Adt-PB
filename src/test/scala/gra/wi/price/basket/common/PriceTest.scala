package gra.wi.price.basket.common

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class PriceTest extends AnyFunSuite with Matchers {
  test ("correctness for £10") {
    assert("£10".equals(Price.displayable(10.0)))
  }
  test ("correctness for £10.10") {
    assert("£10.10".equals(Price.displayable(10.1)))
  }
  test ("correctness for 10p") {
    assert("10p".equals(Price.displayable(.1)))
  }
}
