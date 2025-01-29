package gra.wi.price.basket.common.week.check

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import java.time.LocalDate

class WeekCheckTest extends AnyFunSuite with Matchers {
  test ("date is a day of a week") {
    (20 to 26).foreach { day =>
      assert(WeekCheck(LocalDate.of(2025, 1, 23)).isPresentWeek(LocalDate.of(2025, 1, day)))
    }
  }
  test ("date is not a day of a week") {
    assert(!WeekCheck(LocalDate.of(2025, 1, 15)).isPresentWeek(LocalDate.of(2025, 1, 30)))
  }
}
