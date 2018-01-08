import cats._
import cats.data._
import cats.implicits._

import org.atnos.eff._
import org.atnos.eff.all._
import org.atnos.eff.syntax.all._

package object effx {

  type Log[R] = Writer[LogMsg, ?] |= R

}
