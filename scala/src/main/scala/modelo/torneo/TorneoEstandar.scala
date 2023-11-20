package modelo.torneo

import modelo.dragon.Dragon
import modelo.posta.Posta

case class TorneoEstandar(postas: List[Posta],
                          dragones: List[Dragon])
  extends Torneo(postas, dragones, Primero, PrimeraMitad) {

}
