package modelo.torneo

import modelo.dragon.Dragon
import modelo.posta.Posta

class TorneoEliminacion(
                         postas: List[Posta],
                         competidoresEliminadosPorRonda: Int
                       ) extends Torneo(
  postas,
  criterioGanador = PrimeroGanador,
  criterioSiguienteRonda = UltimosNSeQuedanAfuera(competidoresEliminadosPorRonda)
) {}

class TorneoInverso(
                     postas: List[Posta],
                   ) extends Torneo(
  postas,
  criterioGanador = UltimoGanador,
  criterioSiguienteRonda = UltimaMitadPasaDeRonda
){}

class TorneoConVeto(
                   postas: List[Posta],
                   criterioDragonesDisponibles: Dragon => Boolean
                   ) extends Torneo(
  postas,
  criterioGanador = PrimeroGanador,
  criterioSiguienteRonda = PrimeraMitadPasaDeRonda,
  criterioDragonesDisponibles
) {}

class TorneoConHandicap(
                       postas: List[Posta]
                       ) extends Torneo(
  postas,
  criterioGanador = PrimeroGanador,
  criterioSiguienteRonda = PrimeraMitadPasaDeRonda,
  criterioEleccionDeMonturas = _.reverse
){}
