package gra.wi.price.basket.model.qualification.pattern

/**
 * Trait for any discount qualification/definition of the pattern as a definition
 * Define isDiscountable and countDiscountValue to match and count discounts
 */
trait QualificationPattern {
  protected val toPercentage = .01
  // Name of the discounted item
  val name: String
  // Level of discount in % for example: 10% as 10 Int
  val discountLevel: Int
  // Value of the discount, counted and set automatically via countDiscountValue method
  val value: Double

  /**
   * Checks if the discount is applicable for the `name` of item
   * @param items Array of String/Item names - all the items existing in the store.
   * @return True if the `name` and discounted list match, otherwise False.
   */
  def isDiscountable(items: Array[String]): Boolean = false

  /**
   * Counts value of the amount of discount combined with amount of items the discount can apply
   * @param itemsCounted Map of Item name and amount/sum of items in the basket
   * @param prices Map of Item name and the price of the item without discount
   * @return Value of discount combined with amount of items and level of discount
   */
  def countDiscountValue(itemsCounted: Map[String, Int], prices: Map[String, Double]): Double
}
