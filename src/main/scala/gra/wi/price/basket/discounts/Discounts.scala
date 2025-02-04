package gra.wi.price.basket.discounts

import gra.wi.price.basket.common.week.check.WeekCheck
import gra.wi.price.basket.model.qualification.pattern.QualificationPattern
import gra.wi.price.basket.model.qualification.pattern.buy.n.get.item.discounted.BuyTwoSoupsGetBreadForHalfPrice
import gra.wi.price.basket.model.qualification.pattern.present.week.PresentWeek

import java.time.LocalDate

/**
 * Definition of the discount per item name.
 * Implements helper methods to make discount definition easy.
 */
object Discounts {
  /**
   * Dummy load of discount definitions - only for the assignment usage.
   * @return MAp of item name and discount qualification pattern pair
   */
  def load(): Map[String, QualificationPattern] = Map(
    // Apples have a 10% discount off their normal price this week
    "apples" -> PresentWeek("apples", 10, weekCheck = WeekCheck(LocalDate.now())) // now() to make it simple for cmd test
    // Buy 2 tins of soup and get a loaf of bread for half price
    , "soup" -> BuyTwoSoupsGetBreadForHalfPrice("soup", 2, "bread", 50)
  )
}
