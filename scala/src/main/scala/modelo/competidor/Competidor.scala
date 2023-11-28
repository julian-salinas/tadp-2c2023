package modelo.competidor

import modelo.dragon.Dragon
import modelo.posta.Posta

trait Competidor {
  // Corregido - [Corrección] Ojo con esta implementación porque el tipo está cayendo en un caso borde
  // El retorno de getOrElse en este caso es Any porque tiene que ser un supertipo de Item y de Boolean
  // ende el pattern matching se aplica sobre el item o sobre false, esto puede llevar a bugs dificiles de encontrar.
  def tieneArma(): Boolean = {
    this.item() match {
      case Some(arma: Arma) => true
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
