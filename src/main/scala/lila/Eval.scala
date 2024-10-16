package lila.tree

import chess.format.Uci

case class Eval(
    cp: Option[Eval.Cp],
    mate: Option[Eval.Mate],
    best: Option[Uci.Move]
):

  def isEmpty = cp.isEmpty && mate.isEmpty

  def dropBest = copy(best = None)

  def invert = copy(cp = cp.map(_.invert), mate = mate.map(_.invert))

object Eval:

  case class Score(value: Either[Cp, Mate]) extends AnyVal:

    def cp: Option[Cp]     = value.left.toOption
    def mate: Option[Mate] = value.right.toOption

    def isCheckmate = value == Score.checkmate
    def mateFound   = value.isRight

    def invert                  = copy(value = value.left.map(_.invert).right.map(_.invert))
    def invertIf(cond: Boolean) = if cond then invert else this

  object Score:

    def cp(x: Cp): Score     = Score(Left(x))
    def mate(y: Mate): Score = Score(Right(y))

    val checkmate: Either[Cp, Mate] = Right(Mate(0))

  case class Cp(value: Int) extends AnyVal with Ordered[Cp]:

    def centipawns = value

    def pawns: Float      = value / 100f
    def showPawns: String = "%.2f".format(pawns)

    def ceiled =
      if value > Cp.CEILING then Cp(Cp.CEILING)
      else if value < -Cp.CEILING then Cp(-Cp.CEILING)
      else this

    def invert                  = Cp(value = -value)
    def invertIf(cond: Boolean) = if cond then invert else this

    def compare(other: Cp) = value.compare(other.value)

    def signum: Int = Math.signum(value).toInt

  object Cp:

    val CEILING = 1000

    val initial = Cp(15)

  case class Mate(value: Int) extends AnyVal with Ordered[Mate]:

    def moves = value

    def invert                  = Mate(value = -value)
    def invertIf(cond: Boolean) = if cond then invert else this

    def compare(other: Mate) = value.compare(other.value)

    def signum: Int = Math.signum(value).toInt

    def positive = value > 0
    def negative = value < 0

  val initial = Eval(Some(Cp.initial), None, None)

  val empty = Eval(None, None, None)
