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

  //TODO: Hacer que el require tire excepción o prevenirlo limitándolo a 100? Pensar en los requisitos de admisión de las postas
  def incrementarHambrePosta(variacion: Double): Vikingo = this.copy(hambre = Math.min(hambre + variacion, 100))

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

  override def puedePermitirseParticiparEn(posta: Posta): Boolean = posta.puedeParticipar(this)
}

//TODO test & agregar valores en los ???

object Hipo extends Vikingo(???, ???, ???, Some(SistemaDeVuelo), 0)
object Astrid extends Vikingo(???, ???, ???, Some(Arma(30.0)), 0)
object Patan extends Vikingo(???, ???, ???, Some(Arma(100.0)), 0)
object Patapez extends Vikingo(???, ???, ???, Some(Alimento(???)), 0) {
  override def incrementarHambrePosta(variacion: Double): Vikingo = super.incrementarHambrePosta(Math.max(variacion * 2 - item.get.alimento(), 0))
  override def puedePermitirseParticiparEn(posta: Posta): Boolean = super.puedePermitirseParticiparEn(posta) && hambre <= 50
}