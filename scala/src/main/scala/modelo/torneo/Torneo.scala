package modelo.torneo

import modelo.Competidor
import modelo.dragon.Dragon
import modelo.posta.Posta

abstract case class Torneo(
                   var competidores: List[Competidor],
                   var postas: List[Posta],
                   var dragones: List[Dragon],
                   val criterioGanador: CriterioGanador,
                   val criterioSiguienteRonda: CriterioSiguienteRonda
                   ) {

  def ganador(competidores: List[Competidor]): Competidor = {
    criterioGanador(competidores)
  }

  def iniciar() = {
    // todo: que todos elijan montura
    // todo: Filtrar a los que no podrían jugar la posta
    // todo: Jugar la posta
    // todo: Cansar a todos
  }

  def siguientePosta(): Option[Posta] = {
    postas match {
      case head :: tail =>
        // Si hay al menos una posta en la lista, devolver la primera y actualizar la lista
        postas = tail
        Some(head)
      case Nil =>
        None
    }
  }

  def elegirMonturas() = ???
}
