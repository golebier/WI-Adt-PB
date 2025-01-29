package gra.wi.price.basket

import gra.wi.price.basket.discounts.Discounts
import gra.wi.price.basket.prices.Prices
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BasketTest extends AnyFunSuite with Matchers {
  test ("basket without discount") {
    val basketItems = Array("milk", "bread", "soap")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
        |Subtotal: £2.10
        |(No offers available)
        |Total price: £2.10
        |""".stripMargin
    assert(expected.equals(result))
  }
  test ("basket with apples discount only") {
    val basketItems = Array("apples", "milk", "bread")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
        |Subtotal: £3.10
        |apples 10% off: 10p
        |Total price: £3
        |""".stripMargin
    assert(expected.equals(result))
  }
  test ("basket with double apples discount only") {
    val basketItems = Array("apples", "milk", "bread", "apples")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
        |Subtotal: £4.10
        |apples 10% off: 20p
        |Total price: £3.90
        |""".stripMargin
    assert(expected.equals(result))
  }
  test ("basket with single bread/double soap discount") {
    val basketItems = Array("milk", "bread", "soap", "soap")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
        |Subtotal: £2.10
        |bread 50% off: 40p
        |Total price: £1.70
        |""".stripMargin
    assert(expected.equals(result))
  }
  test ("basket with two breads/four soaps discount") {
    val basketItems = Array("milk", "bread", "bread", "soap", "soap", "soap", "soap")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
        |Subtotal: £2.90
        |bread 50% off: 80p
        |Total price: £2.10
        |""".stripMargin
    assert(expected.equals(result))
  }
  test ("basket with single bread/four soaps discount") {
    val basketItems = Array("milk", "bread", "soap", "soap", "soap", "soap")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
                     |Subtotal: £2.10
                     |bread 50% off: 40p
                     |Total price: £1.70
                     |""".stripMargin
    assert(expected.equals(result))
  }
  test ("basket with double discount on apples and bread/soap") {
    val basketItems = Array("apples", "soap", "milk", "bread", "soap")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
                     |Subtotal: £3.10
                     |apples 10% off: 10p
                     |bread 50% off: 40p
                     |Total price: £2.60
                     |""".stripMargin
    assert(expected.equals(result))
  }
}
