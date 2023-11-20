package modelo.torneo

import modelo.Competidor
import modelo.dragon.Dragon
import modelo.posta.Posta

case class Torneo(
                   var competidores: List[Competidor],
                   var postas: List[Posta],
                   var dragones: List[Dragon],
                   val criterioGanador: CriterioGanador,
                   val criterioSiguienteRonda: CriterioSiguienteRonda
                   ) {

  def ganador(competidores: List[Competidor]): Competidor = {
    this.criterioGanador(competidores)
  }

  def iniciar() = {
    ???
  }

  def siguienteRonda() = {
    val siguientePosta = siguientePosta()
    siguientePosta match {
      case Some(siguientePosta) => ???
      case None => ???
    }
  }

  def siguientePosta(): Option[Posta] = {
    postas match {
      case head :: tail =>
        // Si hay al menos una posta en la lista, devolver la primera y actualizar la lista
        postas = tail
        Some(head)
      case Nil =>
        // Si no hay postas en la lista, devolver None
        None
    }
  }

  def elegirDragones() = ???
}
