package modelo.dragon

import modelo.competidor.Vikingo

/* [Correción] Este modelo no contempla la capacidad de los dragones deberian tener de definir sus condiciones
    de montura al definirlos, adicional a la condicion obligatoria o las condiciones de la especie.

 */
case class Dragon (
                    velocidadBase: Double = 60.0,
                    danioBase: Double,
                    peso: Double,
                    barbarosidadNecesariaParaMontarlo: Double
                  ) {
  def puedeSerMontadoPor(vikingo: Vikingo): Boolean = {
    vikingo.barbarosidad > barbarosidadNecesariaParaMontarlo && vikingo.peso <= pesoMaximoCarga()
  }

  def pesoMaximoCarga(): Double = {
    peso * 0.2
  }

  def danio(): Double = danioBase

  def velocidad(): Double = {
    velocidadBase - peso
  }
}
