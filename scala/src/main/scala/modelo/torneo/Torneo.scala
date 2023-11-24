package modelo.torneo

import modelo.MejorMontura
import modelo.competidor.{Competidor, Jinete, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.Posta

import scala.util.Try

abstract class Torneo(
                   vikingos: List[Vikingo],
                   postas: List[Posta],
                   dragones: List[Dragon],
                   criterioGanador: CriterioGanador,
                   criterioSiguienteRonda: CriterioSiguienteRonda
                   ) {

  def iniciar() = {
    // todo: que todos elijan montura
    // todo: Filtrar a los que no podrían jugar la posta (usar competidor.puedePermitirseParticiparEn)
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

  private def desarrollarPosta(vikingos: List[Vikingo], dragones: List[Dragon], postas: List[Posta]): Torneo = {
    postas match {
      case head :: tail => {
        val resultado = head.aplicar(elegirMonturas(vikingos, dragones, head))
        val nuevosVikingos = resultado.map {
          case jinete: Jinete => jinete.vikingo
          case vikingo: Vikingo => vikingo
        }
        desarrollarPosta(nuevosVikingos, dragones, tail)
      }
    }

    //todo: mitades

  }

  def elegirMonturas(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Competidor] = {
    var dragonesMutable = dragones
    vikingos.map(vikingo => {
      val maybeDragon: Option[Dragon] = MejorMontura(vikingo, dragonesMutable, posta)
      dragonesMutable = dragonesMutable.filter(maybeDragon.getOrElse(false).equals(_))
      Try(maybeDragon.map(vikingo.montar).get).recover(_ => vikingo).get
    })
  }
}
