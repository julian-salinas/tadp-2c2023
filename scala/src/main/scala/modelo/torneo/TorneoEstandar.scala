package modelo.torneo

import modelo.competidor.{Competidor, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.Posta

class TorneoEstandar(
    competidores: List[Vikingo],
    postas: List[Posta],
    dragones: List[Dragon]
  ) extends Torneo(
    competidores,
    postas,
    dragones,
    Primero, // criterioGanador
    PrimeraMitad // criterioSiguienteRonda
  ) {

}