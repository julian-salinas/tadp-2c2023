package modelo

import modelo.competidor.{Competidor, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.Posta
import modelo.torneo.{CriterioGanador, Primero}

case object MejorMontura extends (
  (
    Vikingo,
    List[Dragon],
    Posta
  ) => Option[Competidor]) { //Option ya que podría ocurrir que ninguno pueda participar

  def apply(vikingo: Vikingo,
            dragones: List[Dragon],
            posta: Posta
           ): Option[Competidor] = {
    val posiblesOpciones: List[Competidor] = dragones.map(vikingo.montar).filter(posta.puedeParticipar) :+ vikingo
    posta.ordenarSegunResultado(posiblesOpciones).headOption
  }
}


