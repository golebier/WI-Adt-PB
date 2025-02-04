package gra.wi.price.basket

import gra.wi.price.basket.common.Price
import gra.wi.price.basket.model.qualification.pattern.QualificationPattern
import gra.wi.price.basket.model.qualification.pattern.buy.n.get.item.discounted.BuyTwoSoupsGetBreadForHalfPrice
import gra.wi.price.basket.model.qualification.pattern.present.week.PresentWeek

/**
 * Default Basket definition:
 *  When each of parameters added each variable will count partial values used in the display method.
 *  The Basket counts:
 *    Subtotal - sum of item cost with amount in the basket without discount included
 *    Total - sum of item cost with amount in the basket with discount included
 *    countedAndQualifiedDiscounts - discount per each item in a basket with percentage of discount and sum of values of discount per item
 * @param items Array of String/names of items in the basket - what's bought
 * @param prices Map of item name and price without a discount per item
 * @param discounts Map of item name and `QualificationPattern` definition of the discount counting pattern
 */
case class Basket(items: Array[String], prices: Map[String, Double], discounts: Map[String, QualificationPattern]) {
  private val qualifiedDiscounts: Array[QualificationPattern] = items.flatMap(discounts.get).filter(_.isDiscountable(items)).distinct
  private val itemsCounted: Map[String, Int] = items.groupBy(identity).mapValues(_.length)
  private val countedAndQualifiedDiscounts: Array[QualificationPattern] = qualifiedDiscounts.map{
    case pw: PresentWeek =>
      PresentWeek(pw.name, pw.discountLevel, pw.countDiscountValue(itemsCounted, prices), pw.weekCheck)
    case bt: BuyTwoSoupsGetBreadForHalfPrice =>
      BuyTwoSoupsGetBreadForHalfPrice(bt.buyName, bt.buyLevel, bt.name, bt.discountLevel
        , bt.countDiscountValue(itemsCounted, prices))
    case _ => throw new Exception("Not defined match!")
  }
  private val subtotal: Double = items.flatMap(prices.get).sum
  private val total: Double = subtotal - countedAndQualifiedDiscounts.map(_.value).sum

  /**
   * Prepare String version of the Invoice.
   * In the case of the exercise/our case it's the output in the format we expect for the user to view.
   *    Subtotal - sum of item cost with amount in the basket without discount included
   *    /section with discount information/
   *    Total - sum of item cost with amount in the basket with discount included
   */
  def invoice(): String =
    f"""
       |Subtotal: ${Price.displayable(subtotal)}
       |${if (countedAndQualifiedDiscounts.isEmpty) "(No offers available)"
        else countedAndQualifiedDiscounts.map{qp =>
          f"""${qp.name} ${qp.discountLevel}%% off: ${Price.displayable(qp.value)}"""
        }.mkString("\n")}
       |Total price: ${Price.displayable(total)}
       |""".stripMargin
}
