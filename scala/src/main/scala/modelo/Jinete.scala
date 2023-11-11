package modelo

case class Jinete (vikingo: Vikingo, dragon: Dragon) extends Competidor {
  override def incrementarHambre(variacion: Double): Jinete = this.copy(vikingo = vikingo.incrementarHambre(variacion))

  override def cantidadDePescadoQuePuedeTransportar(): Double = dragon.pesoMaximoCarga - vikingo.peso

  override def danio(): Double = { dragon.danioBase + vikingo.danio }

  override def velocidad(): Double = dragon.velocidadBase - vikingo.peso
}
