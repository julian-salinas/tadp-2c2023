import modelo.competidor.{Arma, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.{Combate, Pesca}
import modelo.torneo.{PrimeraMitad, Primero, Torneo, TorneoEstandar}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class TorneoSpec extends AnyFreeSpec {

  "Torneo estandar" - {
    val laCabra = Vikingo(100, 200, 5, Some(Arma(5.0)))
    val remisero = Vikingo(75, 175, 3, None)

    val dragonDylan: Dragon = Dragon(60, 1, 1000, 50)
    val dragonConan: Dragon = Dragon(70000, 2, 2000, 70)

    val pesca = Pesca()
    val combate = Combate(4)

    val torneo: Torneo = new TorneoEstandar(List(pesca))

    "Ganador en condiciones normales" in {
      torneo.iniciarTorneo(List(laCabra, remisero), List(dragonDylan, dragonConan)) shouldBe Some(laCabra.copy(hambre = 5.0))
    }
  }

}
