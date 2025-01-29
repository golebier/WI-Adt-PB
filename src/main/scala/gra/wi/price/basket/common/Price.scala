package gra.wi.price.basket.common

import scala.math.BigDecimal.RoundingMode

/**
 * Helper object price related
 */
object Price {
  /**
   * Fix the Double type to displayable form:
   * for Double 10.1 we expect £10.10
   * for Double 10.0 we expect £10
   * for Double .1 we expect 10p
   * @param rawValue Double value of the price/money
   * @return fixed/reformated Double to string with expected format.
   */
  def displayable(rawValue: Double): String = {
    val rounded = BigDecimal(rawValue).setScale(2, RoundingMode.HALF_UP)
    val upper = rounded.toInt
    val lower = ((rounded - upper) * 100).toInt
    (upper, lower) match {
      case (0, c) => s"${c}p"
      case (d, 0) => s"£$d"
      case (d, c) => f"£$d.$c%02d"
    }
  }
}
