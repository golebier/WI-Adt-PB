package gra.wi.price.basket.model.qualification.pattern.buy.n.get.item.discounted

import gra.wi.price.basket.prices.Prices
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.math.BigDecimal.RoundingMode

class BuyTwoSoupsGetBreadForHalfPriceTest extends AnyFunSuite with Matchers {
  private val tested: BuyTwoSoupsGetBreadForHalfPrice = BuyTwoSoupsGetBreadForHalfPrice("soup", 2, "bread", 10)
  test ("discount is applicable") {
    val exists = Array("expectedName", "expectedName", "unexpectedName")
    val result = BuyTwoSoupsGetBreadForHalfPrice("expectedName", 2, "not important", 10).isDiscountable(exists)
    assert(result)
  }
  test ("discount is not applicable") {
    val exists = Array("expectedName", "unexpectedName")
    val result = BuyTwoSoupsGetBreadForHalfPrice("expectedName", 2, "not important", 10).isDiscountable(exists)
    assert(!result)
  }
  test ("discount is applicable one time") {
    val result = prepareTest(Map("bread" -> 1, "soup" -> 2))
    assert(result == .08)
  }
  test ("discount is applicable once as one bread is included") {
    val result = prepareTest(Map("bread" -> 1, "soup" -> 4))
    assert(result == .08)
  }
  test ("discount is applicable twice as two breads and four soups included") {
    val result = prepareTest(Map("bread" -> 2, "soup" -> 4))
    assert(result == .16)
  }
  private def prepareTest(itemsCounted: Map[String, Int]) = BigDecimal(tested.countDiscountValue(
    itemsCounted, Prices.load())).setScale(2, RoundingMode.HALF_UP)
}
