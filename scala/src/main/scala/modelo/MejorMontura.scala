package modelo

import modelo.competidor.Competidor
import modelo.dragon.Dragon
import modelo.posta.Posta
import modelo.torneo.{CriterioGanador, Primero}

case object MejorMontura extends (
  (
    Competidor,
    List[Dragon],
    Posta
  ) => Option[Competidor]) { //Option ya que podría ocurrir que ninguno pueda participar

  def apply(competidor: Competidor,
            dragones: List[Dragon],
            posta: Posta
           ): Option[Competidor] = {
    val posiblesOpciones: List[Competidor] = dragones.map(competidor.montar).filter(posta.puedeParticipar)
    val opcionesConCompetidor: List[Competidor] = posiblesOpciones :+ competidor
    posta.ordenarSegunResultado(opcionesConCompetidor).headOption

  }
}


