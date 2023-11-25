package modelo.torneo

import modelo.competidor.Vikingo
import modelo.posta.Posta

case class TorneoPorEquipos(
                             postas: List[Posta]
                           ) extends Torneo(
  postas,
  criterioGanador = EquipoGanador,
  criterioSiguienteRonda = PrimeraMitadPasaDeRonda
){}

case object EquipoGanador extends CriterioGanador {
  def apply(competidores: List[Vikingo]): Option[GanadorTorneo] = {
    Option(competidores.groupBy(_.equipo.get).mapValues(_.size).maxBy(_._2)._1)
  }
}