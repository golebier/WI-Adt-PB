package gra.wi.price.basket.model.qualification.pattern.present.week

import gra.wi.price.basket.common.week.check.WeekCheck
import gra.wi.price.basket.model.qualification.pattern.QualificationPattern

import java.time.LocalDate

/**
 * Generic pattern - defined by name
 * Defines present week discounts implementing QualificationPattern
 * @param name Name of the discounted item
 * @param discountLevel Level of discount in % for example: 10% as 10 Int
 * @param value Value of the discount, counted and set automatically via countDiscountValue method
 * @param weekCheck Impl. of WeekCheck validates if the present day is in a week range.
 */
case class PresentWeek(name: String, discountLevel: Int, value: Double = 0.0
                       , weekCheck: WeekCheck) extends QualificationPattern {
  /**
   * Checks if the discount is applicable for the `name` of item.
   * Expects that discount can apply only if the `name`/item is bought in the present week.
   * @param items Array of String/Item names - all the items existing in the store.
   * @return True if the `name` and discounted list match, otherwise False.
   */
  override def isDiscountable(items: Array[String]): Boolean = weekCheck.isPresentWeek(LocalDate.now())

  /**
   * Counts value of the amount of discount combined with amount of items the discount can apply
   * @param itemsCounted Map of Item name and amount/sum of items in the basket
   * @param prices Map of Item name and the price of the item without discount
   * @return Value of discount combined with amount of items and level of discount
   */
  override def countDiscountValue(itemsCounted: Map[String, Int], prices: Map[String, Double]): Double = (
    discountLevel * toPercentage
      * itemsCounted.getOrElse(name, throw new Exception("Should never happen!")).toDouble
      * prices.getOrElse(name, throw new Exception("Should never happen!"))
    )
}
