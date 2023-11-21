package modelo

import modelo.posta.Posta

trait Competidor {
  def velocidad(): Double

  def danio(): Double

  def incrementarHambre(variacion: Double): Competidor

  def cantidadDePescadoQuePuedeTransportar(): Double

  def hambre(): Double

  def puedePermitirseParticiparEn(posta: Posta): Boolean
}
