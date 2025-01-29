package gra.wi.price.basket.model.qualification.pattern.buy.n.get.item.discounted

import gra.wi.price.basket.model.qualification.pattern.QualificationPattern

/**
 * Defines discount for `Buy `buyLevel` of `buyName` and get `name` for `discountLevel` price`
 * for example: `Buy 2 tins of soup and get a loaf of bread for half price`
 * @param buyName String name of the item expected in the basket to receive discount
 * @param buyLevel Int amount of items defined by `buyName` expected in the basket to receive discount
 * @param name String name of the item the discount will be counted for
 * @param discountLevel Int percentage of discount used to define the discount on the `name` item in the basket
 * @param value Double counted value/sum of the discount per each item in the basket
 */
case class BuyTwoSoapsGetBreadForHalfPrice(buyName: String, buyLevel: Int, name: String, discountLevel: Int
                                           , value: Double = 0.0) extends QualificationPattern {
  override def isDiscountable(items: Array[String]): Boolean = items.count(_.equals(buyName)) >= buyLevel
  override def countDiscountValue(itemsCounted: Map[String, Int], prices: Map[String, Double]): Double = {
    val amountOfBuy = itemsCounted.getOrElse(buyName, throw new Exception("Should never happen!"))
    val amountOfDiscounted = itemsCounted.getOrElse(name, throw new Exception("Should never happen!"))
    val priceOfDiscounted = prices.getOrElse(name, throw new Exception("Should never happen!"))
    val possibleDiscounts = amountOfBuy / buyLevel
    val applicableDiscounts = Math.min(possibleDiscounts, amountOfDiscounted)
    val discountPerItem = priceOfDiscounted * (discountLevel / 100.0)
    applicableDiscounts * discountPerItem
  }
}
