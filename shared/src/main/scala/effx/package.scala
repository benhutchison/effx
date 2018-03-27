import cats._
import cats.data._
import cats.implicits._

import org.atnos.eff._
import org.atnos.eff.all._
import org.atnos.eff.syntax.all._

package object effx {

  type Log[R] = Writer[LogMsg, ?] |= R

  type WithErr[E, R] = Fx.prepend[Either[E, ?], R]

  /** the `in` combinator creates a context with an effect stack R, from which operations may be performed.*/
  def inStack[R] = new InStack[R]
  class InStack[R] {

    type Rwith[E] = Fx.prepend[Either[E, ?], R]


    /** get an effectful `value` of type T. Run predicate `pred` over it. If its not satisfied, fail the stack with an
    Either[E, ?] error effect, deriving the error value with `error` */
    def errorUnless[T, E](value: Eff[R, T])(pred: T => Boolean, error: T => E): Eff[Rwith[E], Unit] = for {
      v <- value.into[Rwith[E]]
      _ <- if (pred(v)) ().pureEff[Rwith[E]]
      else left[Rwith[E], E, Unit](error(v))
    } yield ()
  }

}
