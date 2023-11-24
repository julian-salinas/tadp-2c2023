package modelo.competidor

import modelo.dragon.Dragon
import modelo.posta.Posta

case class Vikingo (
                    peso: Double,
                    velocidad: Double,
                    barbarosidad: Int,
                    item: Option[Item],
                    hambre: Double
                  ) extends Competidor {
  require(0 <= hambre && hambre <= 100)

  override def montar(dragon: Dragon): Competidor = {
    if(dragon.puedeSerMontadoPor(this)) {
      Jinete(this, dragon)
    } else {
      this
    }
  }

  def incrementarHambre(variacion: Double): Vikingo = this.copy(hambre = Math.min(hambre + variacion, 100))

  def danio(): Double = { this.barbarosidad + danioItem}

  private def danioItem: Double = {
    item match {
      case Some(i) => i match {
        case a: Arma => a.danioAdicional
        case _ => 0
    }
      case _ => 0
    }
  }

  override def cantidadDePescadoQuePuedeTransportar(): Double = this.peso + this.barbarosidad * 2

  override def puedePermitirseParticiparEn(posta: Posta): Boolean = ???
}