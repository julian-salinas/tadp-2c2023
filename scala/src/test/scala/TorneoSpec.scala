import modelo.competidor.{Arma, Equipo, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.{Carrera, Combate, Pesca}
import modelo.torneo.{Torneo, TorneoConHandicap, TorneoConVeto, TorneoEliminacion, TorneoEstandar, TorneoInverso, TorneoPorEquipos}
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
    val dragonDylan: Dragon = Dragon(1000, 1, 600, 50)
    val dragonConan: Dragon = Dragon(2000, 2, 700, 70)
    val dragonVetado: Dragon = Dragon(800, 10, 2000, 1)
    val vikingo1: Vikingo = Vikingo(100, 20, 100, Some(Arma(20)))
    val vikingo2: Vikingo = Vikingo(80, 25, 100, None)
    val vikingo3: Vikingo = Vikingo(120, 18, 100, Some(Arma(30)))

    val carrera = Carrera(distanciaKm = 10)
    val torneoVeto = new TorneoConVeto(List(carrera), _.barbarosidadNecesariaParaMontarlo > 30)

    "Ganador con veto" in {
      torneoVeto.iniciarTorneo(List(vikingo1, vikingo2, vikingo3), List(dragonDylan, dragonConan, dragonVetado)).get shouldBe vikingo1.copy(hambre=10.0)
    }
  }

  "TorneoConHandicap" - {
    val dragonDylan: Dragon = Dragon(1000, 1, 600, 50)
    val dragonConan: Dragon = Dragon(2000, 2, 700, 70)

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
    val vikingo1: Vikingo = Vikingo(100, 20, 250, Some(Arma(20)))
    val vikingo2: Vikingo = Vikingo(80, 25, 120, None)
    val vikingo3: Vikingo = Vikingo(120, 18, 300, Some(Arma(30)))
    val vikingo4: Vikingo = Vikingo(90, 22, 120, Some(Arma(25)))
    val vikingo5: Vikingo = Vikingo(90, 150, 210, None)

    val combate = Combate(barbarosidadMinima = 100)
    val carrera = Carrera(distanciaKm = 2)

    val torneoEliminacion = new TorneoEliminacion(List(combate, carrera), competidoresEliminadosPorRonda = 2)

    "Ganador elimnando los ultimos 2 por cada ronda" in {
      torneoEliminacion.iniciarTorneo(List(vikingo1, vikingo2, vikingo3, vikingo4, vikingo5)) shouldBe Some(vikingo5.copy(hambre=12.0))
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

  "Torneo por equipos" - {
    val perritoBarrios: Vikingo = Vikingo(100, 99, 120, Some(Arma(20)))
    val malcomBraida: Vikingo = Vikingo(80, 88, 120, None)
    val augustoBatalla: Vikingo = Vikingo(120, 120, 150, Some(Arma(30)))
    val messi: Vikingo = Vikingo(90, 22, 200, Some(Arma(25)))
    val diMaria: Vikingo = Vikingo(90, 81, 200, None)
    val elDibu: Vikingo = Vikingo(90, 45, 200, None)

    val sanLorenzo = new Equipo("San lorenzo")
    val laScaloneta = new Equipo("La Scaloneta")

    val plantelScaloneta = laScaloneta.ficharJugadores(List(messi, diMaria, elDibu))
    val plantelSanLorenzo = sanLorenzo.ficharJugadores(List(perritoBarrios, malcomBraida, augustoBatalla))

    val carrera = new Carrera(distanciaKm = 10)

    val torneoPorEquipos = new TorneoPorEquipos(List(carrera))

    "una gitana hermosa tiró las cartas dijo que san lorenzo iba a ser campeón" in {
      torneoPorEquipos.iniciarTorneo(List(plantelScaloneta, plantelSanLorenzo).flatten) shouldBe Some(sanLorenzo)
    }
  }

  "Torneo demasiado tryhard, nadie puede hacer ni la primera posta" - {
    val vikingo1: Vikingo = Vikingo(100, 20, 30, None)
    val vikingo2: Vikingo = Vikingo(80, 25, 40, None)
    val vikingo3: Vikingo = Vikingo(120, 18, 35, None)

    val pescaReDificil = Pesca(pesoMinimo = 500)
    val carrera = Carrera(distanciaKm = 1)

    val torneo: Torneo = new TorneoEstandar(List(pescaReDificil, carrera))

    "nadie se la banca, no gana nadie" - {
      torneo.iniciarTorneo(List(vikingo1, vikingo2, vikingo3)) shouldBe None
    }
  }

  "Queda un solo participante, gana ese" - {
    val vikingo1: Vikingo = Vikingo(100, 20, 30, None, 60)
    val vikingo2: Vikingo = Vikingo(80, 25, 40, None, 60)
    val vikingo3: Vikingo = Vikingo(120, 18, 35, None, 0)

    val unaCarrera = Carrera(distanciaKm = 50)
    val otraCarrera = Carrera(distanciaKm = 20)

    val torneo: Torneo = new TorneoEstandar(List(unaCarrera, otraCarrera))

    "gana el unico que sobrevive a la primer carrera" in {
      torneo.iniciarTorneo(List(vikingo1, vikingo2, vikingo3)) shouldBe Some(vikingo3.copy(hambre=50))
    }
  }
}
