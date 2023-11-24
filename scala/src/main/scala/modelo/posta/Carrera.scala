package modelo.posta

import modelo.competidor.Competidor
import modelo.posta.Posta.criterioAdmisionNulo

// Es una clase porque su efecto depende de la longitud de la misma, y pasa lo mismo que en las otras
case class Carrera(
                    criterioAdmision: Competidor => Boolean = criterioAdmisionNulo,
                    distanciaKm: Int
                  ) extends Posta(
  criteiroPuntaje = competidor => competidor.velocidad(),
  efectos = competidor => competidor.incrementarHambre(distanciaKm)
) {}
