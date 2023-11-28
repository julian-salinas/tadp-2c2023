package modelo.competidor

//[Corrección] el daño y el valor alimenticio no son datos que realmente se necesitan en el padre de todos los objetos
trait Item

case class Arma(danioAdicional: Double) extends Item

case class Alimento(alimento: Double) extends Item

case object SistemaDeVuelo extends Item
