package gra.wi.price.basket.prices

/**
 * Definition of the price per item name.
 * Implements helper methods to make discount definition easy.
 */
object Prices {
  /**
   * Dummy method. Produces Map of ItemName and ItemPrice pairs.
   * I didn't want to spend additional time on this part.
   * @return Map of item -> price
   */
  def load(): Map[String, Double] = Map(
    "soup" -> .65
    , "bread" -> .8
    , "milk" -> 1.3
    , "apples" -> 1.0
  )
}
