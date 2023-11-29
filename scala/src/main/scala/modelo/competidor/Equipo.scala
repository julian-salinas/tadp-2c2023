package modelo.competidor

import modelo.torneo.GanadorTorneo

case class Equipo(nombre: String) extends GanadorTorneo {
  def ficharJugadores(vikingos: List[Vikingo]): List[Vikingo] = {
    vikingos.map(v => v.copy(equipo = Some(this)))
  }
}
