package modelo

import modelo.dragon.Dragon
import modelo.posta.Posta
import modelo.torneo.{CriterioGanador, Primero}

case object MejorMontura extends (
  (
    Competidor,
    List[Dragon],
    Posta,
    CriterioGanador
  ) => Competidor) {

  def apply(competidor: Competidor,
            dragones: List[Dragon],
            posta: Posta,
            criterioGanador: CriterioGanador = Primero): Competidor = {
    val posiblesOpciones: List[Competidor] = dragones.map(competidor.montar)
    val opcionesConCompetidor: List[Competidor] = posiblesOpciones :+ competidor
    val resultadoPosta = posta(opcionesConCompetidor)
    criterioGanador(resultadoPosta)
  }
}


