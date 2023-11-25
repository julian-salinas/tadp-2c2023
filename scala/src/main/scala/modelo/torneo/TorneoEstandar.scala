package modelo.torneo

import modelo.competidor.{Competidor, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.Posta

class TorneoEstandar(
    postas: List[Posta],
  ) extends Torneo(
    postas,
    PrimeroGanador,
    PrimeraMitadPasaDeRonda
  ) {

}