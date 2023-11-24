package modelo.posta

import modelo.competidor.Competidor
import modelo.posta.Posta.criterioAdmisionNulo

// Clase ya que puede cambiar el criterio de admisión como se enuncia al final de la hoja 3/5 del enunciado

case class Combate(
                    criterioAdmision: Competidor => Boolean = criterioAdmisionNulo
                  ) extends Posta(
  criterioPuntaje = competidor => competidor.danio(),
  efectos = competidor => competidor.incrementarHambre(10)
) {}