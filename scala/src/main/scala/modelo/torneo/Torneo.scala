package modelo.torneo

import modelo.MejorMontura
import modelo.competidor.{Competidor, Jinete, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.Posta
import scala.annotation.tailrec
import scala.util.Try

abstract class Torneo(
                   postas: List[Posta],
                   criterioGanador: CriterioGanador,
                   criterioSiguienteRonda: CriterioSiguienteRonda,
                   criterioEleccionDeMonturas: List[Vikingo] => List[Vikingo] = identity // Por defecto, no hace ningún cambio
                   ) {

  def iniciarTorneo(vikingos: List[Vikingo], dragones: List[Dragon]): Option[Vikingo] = {
    desarrollarPosta(vikingos, dragones, postas)
  }

  @tailrec
  private def desarrollarPosta(vikingos: List[Vikingo], dragones: List[Dragon], postas: List[Posta]): Option[Vikingo] = {
    postas match {
      case head :: tail =>
        val resultado = head.aplicar(elegirMonturas(vikingos, dragones, head))
        val nuevosVikingos = resultado.map {
          case jinete: Jinete => jinete.vikingo
          case vikingo: Vikingo => vikingo
        }
        desarrollarPosta(criterioSiguienteRonda(nuevosVikingos), dragones, tail)
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
