package gra.wi.price.basket

import gra.wi.price.basket.discounts.Discounts
import gra.wi.price.basket.prices.Prices
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BasketTest extends AnyFunSuite with Matchers {
  test ("basket without discount") {
    val basketItems = Array("milk", "bread", "soup")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
        |Subtotal: £2.75
        |(No offers available)
        |Total price: £2.75
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
  test ("basket with single bread/double soup discount") {
    val basketItems = Array("milk", "bread", "soup", "soup")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
        |Subtotal: £3.40
        |bread 50% off: 40p
        |Total price: £3
        |""".stripMargin
    assert(expected.equals(result))
  }
  test ("basket with two breads/four soups discount") {
    val basketItems = Array("milk", "bread", "bread", "soup", "soup", "soup", "soup")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
        |Subtotal: £5.50
        |bread 50% off: 80p
        |Total price: £4.70
        |""".stripMargin
    assert(expected.equals(result))
  }
  test ("basket with single bread/four soups discount") {
    val basketItems = Array("milk", "bread", "soup", "soup", "soup", "soup")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
                     |Subtotal: £4.70
                     |bread 50% off: 40p
                     |Total price: £4.30
                     |""".stripMargin
    assert(expected.equals(result))
  }
  test ("basket with double discount on apples and bread/soup") {
    val basketItems = Array("apples", "soup", "milk", "bread", "soup")
    val result = Basket(basketItems, Prices.load(), Discounts.load()).invoice()
    val expected = """
                     |Subtotal: £4.40
                     |apples 10% off: 10p
                     |bread 50% off: 40p
                     |Total price: £3.90
                     |""".stripMargin
    assert(expected.equals(result))
  }
}
