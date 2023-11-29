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

  // Corregido - [Corrección] aca no están realmente aprovechando el optional ya que están basicamente
  // haciendo lo mismo que preguntar si el item no sea null
  private def danioItem: Double = {
    item.map {
      case a: Arma => a.danioAdicional
      case _ => 0
    }.getOrElse(0)
  }

  override def cantidadDePescadoQuePuedeTransportar(): Double = this.peso + this.barbarosidad * 2

}

object Hipo extends Vikingo(60, 20, 30, Some(SistemaDeVuelo))
object Astrid extends Vikingo(60, 30, 50, Some(Arma(30.0)))
object Patan extends Vikingo(100, 10, 70, Some(Arma(100.0)))
object Patapez extends Vikingo(120, 5, 150, Some(Alimento(10))) {
  override def incrementarHambrePosta(variacion: Double): Vikingo = {
    val alimentoConsumido = item.collect { case Alimento(a) => a }.getOrElse(0.0)
    super.incrementarHambrePosta(variacion * 2 - alimentoConsumido)
  }

  override def puedePermitirseParticiparEn(posta: Posta): Boolean =
    super.puedePermitirseParticiparEn(posta) && hambre <= 50
}