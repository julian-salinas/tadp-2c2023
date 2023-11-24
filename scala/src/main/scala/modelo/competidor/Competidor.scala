package modelo.competidor

import modelo.dragon.Dragon
import modelo.posta.Posta

trait Competidor {
  def tieneArma(): Boolean = {
    this.item().getOrElse(false) match {
      case _: Arma => true
      case _ => false
    }
  }

  def velocidad(): Double

  def danio(): Double

  def incrementarHambrePosta(variacion: Double): Competidor

  def cantidadDePescadoQuePuedeTransportar(): Double

  def hambre(): Double

  def puedePermitirseParticiparEn(posta: Posta): Boolean = posta.puedeParticipar(this)

  def barbarosidad(): Int

  def item(): Option[Item]

  def esJinete(): Boolean = {
    this match {
      case _: Jinete => true
      case _ => false
    }
  }
}
