package modelo.dragon

import modelo.competidor.Vikingo

class NadderMortifero(
                            velocidadBase: Double,
                            peso: Double,
                            barbarosidadNecesariaParaMontarlo: Double
                          ) extends Dragon(
  velocidadBase,
  150,
  peso,
  barbarosidadNecesariaParaMontarlo) {

  override def puedeSerMontadoPor(vikingo: Vikingo): Boolean = {
    super.puedeSerMontadoPor(vikingo)
    // todo: && vikingo.danio < danio o algo así
  }
}
