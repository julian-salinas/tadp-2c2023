import modelo.MejorMontura
import modelo.competidor.{Arma, Vikingo}
import modelo.dragon.Dragon
import modelo.posta.{Carrera, Combate, Pesca}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class MejorMonturaSpec extends AnyFreeSpec {

  "MejorMontura" - {

    val dragonDylan: Dragon = Dragon(60, 1, 10, 50)
    val dragonConan: Dragon = Dragon(70000, 2, 2, 70)

    val aumentoDeCarnes: Vikingo = Vikingo(200, 15, 100, Some(Arma(30)), 0)
    val javierDelay: Vikingo = Vikingo(180, 12, 100, Some(Arma(25)), 0)

    "en una carrera" in {
      val resultado = MejorMontura(aumentoDeCarnes, List(dragonDylan, dragonConan), Carrera(distanciaKm = 1))
      resultado shouldEqual Some(aumentoDeCarnes.montar(dragonConan))
    }

    "en un combate" in {
      val resultado = MejorMontura(aumentoDeCarnes, List(dragonDylan, dragonConan), Combate())
      resultado shouldEqual Some(aumentoDeCarnes.montar(dragonConan))
    }

    "en una pesca" in {
      val resultado = MejorMontura(javierDelay, List(dragonDylan, dragonConan), Pesca())
      resultado shouldEqual Some(javierDelay)
    }

    "en una carrera decidiendo entre Conan y 10 clones más de Conan" in {
      val clonesConan: List[Dragon] = List.fill(10)(dragonConan.copy(peso=200))
      val resultado = MejorMontura(javierDelay, clonesConan :+ dragonConan, Carrera(distanciaKm = 1))
      resultado shouldEqual Some(javierDelay.montar(dragonConan))
    }
  }
}
