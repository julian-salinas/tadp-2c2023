package modelo.dragon

import modelo.competidor.Vikingo
import modelo.dragon.RequisitosMontura.RequisitoMontura

class NadderMortifero(
                            velocidadBase: Double,
                            peso: Double,
                            barbarosidadNecesariaParaMontarlo: Double,
                            requisitosAdicionalesParaMontarlo: List[RequisitoMontura]
                          ) extends Dragon(
  velocidadBase,
  150,
  peso,
  barbarosidadNecesariaParaMontarlo,
  requisitosAdicionalesParaMontarlo = RequisitosMontura.VikingoHaceMenosDanioQueDragon :: requisitosAdicionalesParaMontarlo) {
}
