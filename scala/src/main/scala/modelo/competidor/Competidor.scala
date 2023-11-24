package modelo.competidor

import modelo.dragon.Dragon
import modelo.posta.Posta

trait Competidor {
  def velocidad(): Double

  def danio(): Double

  def incrementarHambrePosta(variacion: Double): Competidor

  def cantidadDePescadoQuePuedeTransportar(): Double

  def hambre(): Double

  def puedePermitirseParticiparEn(posta: Posta): Boolean

  def montar(dragon: Dragon): Competidor = ???
}
