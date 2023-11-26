package modelo.competidor

//[Corrección] el daño y el valor alimenticio no son datos que realmente se necesitan en el padre de todos los objetos
trait Item {
  def danioAdicional() = 0.0
  def alimento() = 0.0
}

case class Arma(override val danioAdicional: Double) extends Item {}

case class Alimento(override val alimento: Double) extends Item {}

case object SistemaDeVuelo extends Item()

