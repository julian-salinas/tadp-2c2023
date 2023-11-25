package modelo.competidor

import modelo.torneo.GanadorTorneo

case class Equipo(nombre: String) extends ((List[Vikingo]) => List[Vikingo]) with GanadorTorneo {
  def apply(vikingos: List[Vikingo]): List[Vikingo] = {
    vikingos.map(v => v.copy(equipo = Some(this)))
  }
}
