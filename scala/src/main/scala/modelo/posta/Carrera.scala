package modelo.posta


// Es una clase porque su efecto depende de la longitud de la misma, y pasa lo mismo que en las otras
case class Carrera(
                    requiereMontura: Boolean = false,
                    distanciaKm: Int
                  ) extends Posta(
  criterioPuntaje = competidor => competidor.velocidad(),
  efectos = competidor => competidor.incrementarHambrePosta(distanciaKm),
  criterioAdmision = competidor => competidor.esJinete || !requiereMontura
) {}
