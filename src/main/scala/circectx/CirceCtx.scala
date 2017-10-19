package circectx

import contextual._
import io.circe.Json
import io.circe.parser.{parse => circeParse}

object JsonInterpolator extends Interpolator {

  type Out = Json

  override def contextualize(interpolation: StaticInterpolation) = {
    val lit@Literal(_, jsonString) = interpolation.parts.head
    if(!checkValidJson(jsonString))
      interpolation.abort(lit, 0, "Invalid JSON")

    Nil
  }

  def checkValidJson(s: String): Boolean =
    circeParse(s).isRight

  def evaluate(interpolation: RuntimeInterpolation): Json =
    circeParse(interpolation.literals.head).right.get

  implicit class JsonStringContext(sc: StringContext) {
    val json = Prefix(JsonInterpolator, sc)
  }

}
