package gra.wi.price.basket

import gra.wi.price.basket.discounts.Discounts
import gra.wi.price.basket.prices.Prices

/**
 * Main object of the app
 * Executed all the steps to achieve app goal
 */
object PriceBasket {
  /**
   * App Price Basket that will count items, discount and display information to STDOUT based on Items from args.
   * Display example:
   *  Subtotal: £3.10
   *  apples 10% off: 10p
   *  bread 50% off: 40p
   *  Total price: £2.60
   * OR:
   *  Subtotal: £2.10
   *  (No offers available)
   *  Total price: £2.10
   *
   * @param args Array of Strings: items in the basket.
   */
  def main(args: Array[String]): Unit = {
    val basketItems = prepareBasketItems(args)
    val prices = Prices.load()
    val discounts = Discounts.load()
    println(Basket(basketItems, prices, discounts).invoice())
  }

  /**
   * Prepares basket items from arguments.
   * Dummy method, expects that Array of Strings has correct items for `Basket` implementation.
   * @param args Array of Strings: items in the basket to be counted and used as a base for price discount count.
   * @return Array of items as strings.
   */
  private def prepareBasketItems(args: Array[String]): Array[String] = if (args.isEmpty) {
    throw new Exception("No items in the basket!")
  } else args.map(_.toLowerCase)
}
