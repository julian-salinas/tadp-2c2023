package modelo

trait Competidor {
  def velocidad(): Double

  def danio(): Double

  def incrementarHambre(variacion: Double): Competidor

  def cantidadDePescadoQuePuedeTransportar(): Double

  def hambre(): Double

}
