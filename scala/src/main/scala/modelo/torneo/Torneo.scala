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
                   criterioDragonesDisponibles: Dragon => Boolean = _ => true,
                   criterioEleccionDeMonturas: List[Vikingo] => List[Vikingo] = identity // Por defecto, no hace ningún cambio
                   ) {
  def iniciarTorneo(vikingos: List[Vikingo], dragones: List[Dragon] = List()): Option[GanadorTorneo] = {
    desarrollarTorneo(vikingos, dragones.filter(criterioDragonesDisponibles), postas)
  }

  /*
    [Correción] Lo que están tratando de hacer es tomar una lista de postas y, arracando de los vikingos iniciales,
      llegar a los vikingos luego de aplicar todas las postas. Esto se puede hacer con un fold, con eso no necesitan
      preocuparse por la recursividad y solo tiene que escribir el codigo de ejecutar la posta con los vikingos.
   */
  @tailrec
  private def desarrollarTorneo(vikingos: List[Vikingo], dragones: List[Dragon], postas: List[Posta]): Option[GanadorTorneo] = {
    postas match {
      case posta :: demasPostas =>
        if (quedaUltimoParticipanteEnPie(vikingos)) {
          return Some(vikingos.head) //Queda un solo vikingo, gana él
        }
        val ganadores = competir(vikingos, dragones, posta)
        ganadores match {
          case Some(competidores) => desarrollarTorneo(competidores, dragones, demasPostas)
          case _ => None
        }
      case Nil =>
        criterioGanador(vikingos)
    }
  }

  private def competir(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): Option[List[Vikingo]] = {
    val participantes = elegirMonturas(vikingos, dragones, posta)
    if (ningunCompetidorSeBancariaEstaPosta(vikingos, posta)) {
      return None // No hay ganador y finaliza el torneo
    }
    val resultado = posta.competir(participantes)
    Some(criterioSiguienteRonda(obtenerVikingos(resultado)))
  }

  private def quedaUltimoParticipanteEnPie(vikingos: List[Vikingo]): Boolean = vikingos.length == 1

  private def ningunCompetidorSeBancariaEstaPosta(vikingos: List[Competidor], posta: Posta): Boolean = {
    !vikingos.exists(_.puedePermitirseParticiparEn(posta))
  }

  private def obtenerVikingos(competidores: List[Competidor]) = {
    competidores.map {
      case jinete: Jinete => jinete.vikingo
      case vikingo: Vikingo => vikingo
    }
  }

  private def elegirMonturas(vikingos: List[Vikingo], dragones: List[Dragon], posta: Posta): List[Competidor] = {
    var dragonesMutable = dragones
    criterioEleccionDeMonturas(vikingos).map(vikingo => {
      val maybeDragon: Option[Dragon] = MejorMontura(vikingo, dragonesMutable, posta)
      dragonesMutable = dragonesMutable.filter(!maybeDragon.getOrElse(false).equals(_))
      Try(maybeDragon.map(vikingo.montar).get).recover(_ => vikingo).get
    })
  }
}
