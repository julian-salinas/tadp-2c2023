package modelo.posta

// Clase ya que puede cambiar el criterio de admisión como se enuncia al final de la hoja 3/5 del enunciado

case class Combate(
                  barbarosidadMinima: Int = 0
                  ) extends Posta(
  criterioPuntaje = competidor => competidor.danio(),
  efectos = competidor => competidor.incrementarHambrePosta(10),
  criterioAdmision = competidor => competidor.barbarosidad() >= barbarosidadMinima || competidor.tieneArma() //TODO test
) {}