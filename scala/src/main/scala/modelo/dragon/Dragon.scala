package modelo.dragon

import modelo.competidor.Vikingo

case class Dragon (
                    velocidadBase: Double = 60.0,
                    danioBase: Double,
                    peso: Double,
                    barbarosidadNecesariaParaMontarlo: Double
                  ) {
  def puedeSerMontadoPor(vikingo: Vikingo): Boolean = {
    vikingo.barbarosidad > barbarosidadNecesariaParaMontarlo
  }

  def pesoMaximoCarga(): Double = {
    peso * 0.2
  }

  def danio(): Double = danioBase

  def velocidad(): Double = {
    velocidadBase - peso
  }
}
