package modelo

import modelo.competidor.{Competidor, Jinete, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.Posta

case object MejorMontura extends (
  (
    Vikingo,
    List[Dragon],
    Posta
  ) => Option[Dragon]) { //Option ya que podría ocurrir que ninguno pueda participar

  def apply(vikingo: Vikingo,
            dragones: List[Dragon],
            posta: Posta
           ): Option[Dragon] = {
    val posiblesOpciones: List[Competidor] = dragones.map(vikingo.montar).filter(posta.puedeParticipar) :+ vikingo
    val maybeMejorOpcion = posta.ordenarSegunResultado(posiblesOpciones).headOption
    maybeMejorOpcion match {
      case Some(Jinete(_, dragon)) => Some(dragon)
      case _ => None
    }
  }
}


