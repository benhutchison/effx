package effx

sealed trait LogLevel
case object Error extends LogLevel
case object Warn extends LogLevel
case object Info extends LogLevel
case object Debug extends LogLevel

case class LogMsg(msg: String, level: LogLevel)

