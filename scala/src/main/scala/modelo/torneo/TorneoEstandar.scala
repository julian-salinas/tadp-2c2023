package modelo.torneo

import modelo.Competidor
import modelo.dragon.Dragon
import modelo.posta.Posta

class TorneoEstandar(
    competidores: List[Competidor],
    postas: List[Posta],
    dragones: List[Dragon]
  ) extends Torneo(
    competidores,
    postas,
    dragones,
    Primero, // criterioGanador
    PrimeraMitad // criterioSiguienteRonda
  ) {

  override def elegirMonturas(): Nothing = {
    ???
  }
}