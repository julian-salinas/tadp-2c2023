package modelo.torneo

import modelo.Competidor
import modelo.dragon.Dragon
import modelo.posta.Posta

case class TorneoEstandar(
                           var competidores: List[Competidor],
                           var postas: List[Posta],
                           var dragones: List[Dragon]
                         ) extends Torneo(
  competidores,
  postas,
  dragones,
  Primero, // criterioGanador
  PrimeraMitad // criterioSiguienteRonda
)