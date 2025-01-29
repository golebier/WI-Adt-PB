package gra.wi.price.basket.common.week.check

import java.time.{DayOfWeek, LocalDate}

/**
 * Helper class to define if the present day is a day of a week we can give a discount or not
 * @param dayOfAWeek LocalDate: a day of a week when the discount can be given
 */
case class WeekCheck(dayOfAWeek: LocalDate) {
  private val startOfWeek: LocalDate = dayOfAWeek.`with`(DayOfWeek.MONDAY)
  private val endOfWeek: LocalDate = startOfWeek.plusDays(6)

  /**
   * Check if the `date` is included in a week days defined as discount week
   * @param date a date to be checked
   * @return True if a date is a week day or False otherwise
   */
  def isPresentWeek(date: LocalDate): Boolean = !date.isBefore(startOfWeek) && !date.isAfter(endOfWeek)
}
