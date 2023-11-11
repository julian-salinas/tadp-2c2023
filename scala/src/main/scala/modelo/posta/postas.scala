package modelo.posta

import modelo.{Competidor, Jinete, Vikingo}

class Posta(val competidores: List[Competidor], val efectoCompetidores: List[Vikingo => Vikingo], val puntajeCompetidor: (Competidor) => (Double)) {
  private def aplicarEfectoACompetidores(): List[Competidor] = {
    competidores.map {
      case vikingo: Vikingo => efectoCompetidores.foldLeft(vikingo)((vikingo, efecto) => efecto(vikingo))
      case jinete: Jinete => jinete.incrementarHambre(0.05)
    }
  }

  private def obtenerGanador(): Option[Competidor] = {
    val competidoresConPuntaje = competidores.map(competidor => (puntajeCompetidor(competidor), competidor))

    val competidoresOrdenados = competidoresConPuntaje.sortBy {
      case (puntaje, _) => puntaje
    }

    competidoresOrdenados.map(tupla => tupla._1) match {
      case head::tail if tail.contains(head) => None
      case _ => Some(competidoresOrdenados.last._2)
    }
  }

  def celebrar(): Option[Competidor] = {
    val ganador = obtenerGanador()
    aplicarEfectoACompetidores()
    ganador
  }
}

//TODO: Hacer tests <3

class Pesca(competidores: List[Competidor]) extends Posta(
  competidores = competidores,
  efectoCompetidores = List(competidor => competidor.incrementarHambre(0.05)),
  puntajeCompetidor = competidor =>  competidor.cantidadDePescadoQuePuedeTransportar()
)

class Combate(competidores: List[Competidor]) extends Posta(
  competidores = competidores,
  efectoCompetidores = List(competidor => competidor.incrementarHambre(0.10)),
  puntajeCompetidor = competidor =>  competidor.danio()
)

class Carrera(competidores: List[Competidor], kilometros: Int) extends Posta(
  competidores = competidores,
  efectoCompetidores = List(competidor => competidor.incrementarHambre(0.01 * kilometros)),
  puntajeCompetidor = competidor =>  competidor.velocidad()
)
