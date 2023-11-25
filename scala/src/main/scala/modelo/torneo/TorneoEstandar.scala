package modelo.torneo

import modelo.posta.Posta

class TorneoEstandar(
    postas: List[Posta],
  ) extends Torneo(
    postas,
    PrimeroGanador,
    PrimeraMitadPasaDeRonda
  ) {

}