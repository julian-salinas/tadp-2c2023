package modelo.dragon

class Gronckle(
                     velocidadBase: Double = 60.0,
                     danioBase: Double,
                     peso: Double,
                     barbarosidadNecesariaParaMontarlo: Double,
                     pesoQuePuedeSoportar: Double
                   ) extends Dragon(
  velocidadBase,
  danioBase,
  peso,
  barbarosidadNecesariaParaMontarlo) {

  override def danio(): Double = {
    peso * 5
  }

  override def velocidad(): Double = {
    super.velocidad() / 2
  }

  override def pesoMaximoCarga(): Double = pesoQuePuedeSoportar
}
