import modelo.competidor.{Arma, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.{Carrera, Combate, Pesca}
import modelo.torneo.{PrimeraMitadPasaDeRonda, PrimeroGanador, Torneo, TorneoConHandicap, TorneoConVeto, TorneoEliminacion, TorneoEstandar, TorneoInverso}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class TorneoSpec extends AnyFreeSpec {

  "Torneo estandar" - {
    val laCabra = Vikingo(100, 200, 5, Some(Arma(5.0)))
    val remisero = Vikingo(75, 175, 3, None)

    val dragonDylan: Dragon = Dragon(60, 1, 1000, 50)
    val dragonConan: Dragon = Dragon(70000, 2, 2000, 70)

    val pesca = Pesca()

    val torneo: Torneo = new TorneoEstandar(List(pesca))

    "Ganador en condiciones normales" in {
      torneo.iniciarTorneo(List(laCabra, remisero), List(dragonDylan, dragonConan)) shouldBe Some(laCabra.copy(hambre = 5.0))
    }

  }

  "TorneoConVeto" - {
    val dragonDylan: Dragon = Dragon(1000, 1, 600, 50, "dylan")
    val dragonConan: Dragon = Dragon(2000, 2, 700, 70, "conan")
    val dragonVetado: Dragon = Dragon(800, 10, 2000, 1, "vetado")
    val vikingo1: Vikingo = Vikingo(100, 20, 100, Some(Arma(20)), nombre = "juli")
    val vikingo2: Vikingo = Vikingo(80, 25, 100, None, nombre = "agus")
    val vikingo3: Vikingo = Vikingo(120, 18, 100, Some(Arma(30)), nombre = "santi")

    val carrera = Carrera(distanciaKm = 10)
    val torneoVeto = new TorneoConVeto(List(carrera), _.barbarosidadNecesariaParaMontarlo > 30)

    "Ganador con veto" in {
      torneoVeto.iniciarTorneo(List(vikingo1, vikingo2, vikingo3), List(dragonDylan, dragonConan, dragonVetado)).get shouldBe vikingo1.copy(hambre=10.0)
    }
  }

  "TorneoConHandicap" - {
    val dragonDylan: Dragon = Dragon(1000, 1, 600, 50, "dylan")
    val dragonConan: Dragon = Dragon(2000, 2, 700, 70, "conan")

    val vikingo1: Vikingo = Vikingo(100, 20, 30, Some(Arma(20)))
    val vikingo2: Vikingo = Vikingo(80, 25, 40, None)
    val vikingo3: Vikingo = Vikingo(120, 18, 35, Some(Arma(30)))

    val pesca = Pesca()
    val torneoHandicap = new TorneoConHandicap(List(pesca))

    "Ganador con handicap" in {
      torneoHandicap.iniciarTorneo(List(vikingo1, vikingo2, vikingo3), List(dragonDylan, dragonConan)) shouldBe Some(vikingo3.copy(hambre=5))
    }
  }

  "TorneoEliminacion" - {
    val vikingo1: Vikingo = Vikingo(100, 20, 120, Some(Arma(20)))
    val vikingo2: Vikingo = Vikingo(80, 25, 120, None)
    val vikingo3: Vikingo = Vikingo(120, 18, 150, Some(Arma(30)))
    val vikingo4: Vikingo = Vikingo(90, 22, 120, Some(Arma(25)))
    val vikingo5: Vikingo = Vikingo(90, 150, 141, None)

    val combate = Combate(barbarosidadMinima = 100)
    val carrera = Carrera(distanciaKm = 2)

    val torneoEliminacion = new TorneoEliminacion(List(combate, carrera), competidoresQuePasanPorRonda = 2)

    "Ganador elimnando los ultimos 2 por cada ronda" in {
      torneoEliminacion.iniciarTorneo(List(vikingo1, vikingo2, vikingo3, vikingo4, vikingo5), List()) shouldBe Some(vikingo5.copy(hambre=12.0))
    }
  }

  "TorneoInverso" - {
    val dragonDylan: Dragon = Dragon(60, 1, 1000, 50)
    val dragonConan: Dragon = Dragon(70000, 2, 2000, 70)

    val vikingo1: Vikingo = Vikingo(100, 20, 30, Some(Arma(20)))
    val vikingo2: Vikingo = Vikingo(80, 25, 40, None)
    val vikingo3: Vikingo = Vikingo(120, 18, 35, Some(Arma(30)))

    val carrera = Carrera(distanciaKm = 10)
    val torneoInverso = new TorneoInverso(List(carrera))

    "Ganador en condiciones normales" in {
      torneoInverso.iniciarTorneo(List(vikingo1, vikingo2, vikingo3), List(dragonDylan, dragonConan)) shouldBe Some(vikingo3.copy(hambre=10.0))
    }
  }
}
