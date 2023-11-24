package modelo.torneo

import modelo.MejorMontura
import modelo.competidor.{Competidor, Jinete, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.Posta
import scala.annotation.tailrec
import scala.util.Try

abstract class Torneo(
                   vikingos: List[Vikingo],
                   postas: List[Posta],
                   dragones: List[Dragon],
                   criterioGanador: CriterioGanador,
                   criterioSiguienteRonda: CriterioSiguienteRonda,
                   criterioEleccionDeMonturas: List[Vikingo] => List[Vikingo] = identity // Por defecto, no hace ningún cambio
                   ) {

  // optional[todo]: mejorar este nombre
  def iniciarTorneo(): Unit = {
    desarrollar(vikingos, dragones, postas);
  }

  // todo: mejorar este nombre
  @tailrec // q se yo me dijo el ide que lo agregue
  private def desarrollar(vikingos: List[Vikingo], dragones: List[Dragon], postas: List[Posta]): Vikingo = {
    postas match {
      case head :: tail => {
        val resultado = head.aplicar(elegirMonturas(vikingos, dragones, head))
        val nuevosVikingos = resultado.map {
          case jinete: Jinete => jinete.vikingo
          case vikingo: Vikingo => vikingo
        }
        desarrollar(criterioSiguienteRonda(nuevosVikingos), dragones, tail)
      }
      case Nil =>
        criterioGanador(vikingos)
    }
  }

  private def elegirMonturas(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Competidor] = {
    var dragonesMutable = dragones
    criterioEleccionDeMonturas(vikingos).map(vikingo => {
      val maybeDragon: Option[Dragon] = MejorMontura(vikingo, dragonesMutable, posta)
      dragonesMutable = dragonesMutable.filter(maybeDragon.getOrElse(false).equals(_))
      Try(maybeDragon.map(vikingo.montar).get).recover(_ => vikingo).get
    })
  }
}
