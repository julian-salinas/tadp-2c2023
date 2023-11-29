import modelo.competidor.{Arma, Astrid, Hipo, Jinete, Patan, Patapez, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.{Carrera, Pesca}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.freespec.AnyFreeSpec

class VikingoSpec extends AnyFreeSpec {
  "Un vikingo intenta montar un dragon" - {

    val vikingoPesado: Vikingo = Vikingo(10, 10, 10, Some(Arma(40)), 0)
    val vikingoLiviano: Vikingo = vikingoPesado.copy(barbarosidad=100, peso = 1)
    val dragon: Dragon = Dragon(60, 1, 10, 10)

    "Cumpliendo sus requisitos" in {
      vikingoLiviano.montar(dragon) shouldBe a [Jinete]
    }
    "No cumpliendo sus requisitos" in {
      vikingoPesado.montar(dragon) should not be a [Jinete]
    }
  }

  "Hipo, Astrid, Patan y Patapez" - {

    "Hipo" - {
      "debería tener velocidad correcta" in {
        Hipo.velocidad shouldBe 20
      }

      "debería tener danio correcto" in {
        Hipo.danio() shouldBe 30
      }

      "debería poder montar un dragón" in {
        Hipo.montar(Dragon(60, 1, 1000, 10)) shouldBe a [Jinete]
      }
    }

    "Astrid" - {
      "debería tener danio correcto" in {
        Astrid.danio() shouldBe 80
      }

      "no debería poder montar un dragón" in {
        Astrid.montar(Dragon(60, 1, 10, 10)) should not be a [Jinete]
      }
    }

    "Patan" - {
      "debería tener danio correcto" in {
        Patan.danio() shouldBe 170
      }

      "no debería poder montar un dragón" in {
        Patan.montar(Dragon(60, 1, 10, 10)) should not be a [Jinete]
      }
    }

    "Patapez" - {
      "debería tener danio correcto" in {
        Patapez.danio() shouldBe 150
      }

      "debería poder montar un dragón" in {
        Patapez.montar(Dragon(10, 1, 600, 10)) shouldBe a [Jinete]
      }

      "no debería poder montar un dragón" in {
        Patapez.montar(Dragon(10, 1, 599, 10)) should not be a [Jinete]
      }

      "debería incrementar correctamente el hambre en una pesca" in {
        val pesca = new Pesca()
        val nuevoPatapez = pesca.competir(List(Patapez)).head
        nuevoPatapez.hambre shouldBe 0 // (5 * 2) - 10
      }

      "debería incrementar correctamente el hambre en una carrera" in {
        val carrera = new Carrera(distanciaKm = 40)
        val nuevoPatapez = carrera.competir(List(Patapez)).head
        nuevoPatapez.hambre shouldBe 70 // (40 * 2) - 10
      }

      "debería permitirse participar en una posta" in {
        val posta = Carrera(distanciaKm = 20)
        Patapez.puedePermitirseParticiparEn(posta) shouldBe true
      }
    }
  }
}
