package circectx

import io.circe.parser.{parse => circeParse}
import JsonInterpolator._
import org.scalatest._

class CirceInterpolatorTest extends FunSuite with Matchers {
  test("interpolate") {
    json"{}" shouldEqual circeParse("{}").right.get
    json"aoeu"
  }
}
