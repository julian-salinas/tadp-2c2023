package modelo.competidor

trait Item {
  def danioAdicional() = 0.0
}

case class Arma(override val danioAdicional: Double) extends Item {

}

