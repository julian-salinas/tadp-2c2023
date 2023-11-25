package modelo.competidor

import modelo.dragon.Dragon
import modelo.posta.Posta
import modelo.torneo.GanadorTorneo

case class Vikingo (
                    peso: Double,
                    velocidad: Double,
                    barbarosidad: Int,
                    item: Option[Item],
                    hambre: Double = 0,
                    equipo: Option[Equipo] = None
                  ) extends Competidor with GanadorTorneo {
  require(0 <= hambre && hambre <= 100)

  def montar(dragon: Dragon): Competidor = {
    if(dragon.puedeSerMontadoPor(this)) {
      Jinete(this, dragon)
    } else {
      this
    }
  }

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

}

//TODO test
object Hipo extends Vikingo(60, 20, 30, Some(SistemaDeVuelo))
object Astrid extends Vikingo(60, 30, 50, Some(Arma(30.0)))
object Patan extends Vikingo(100, 10, 70, Some(Arma(100.0)))
object Patapez extends Vikingo(120, 5, 150, Some(Alimento(10))) {
  override def incrementarHambrePosta(variacion: Double): Vikingo = super.incrementarHambrePosta(variacion * 2 - item.get.alimento())
  override def puedePermitirseParticiparEn(posta: Posta): Boolean = super.puedePermitirseParticiparEn(posta) && hambre <= 50
}