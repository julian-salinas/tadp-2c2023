import modelo.{Arma, Dragon, Item, Jinete, Vikingo}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.freespec.AnyFreeSpec

class VikingoSpec extends AnyFreeSpec {
  "Un vikingo intenta montar un dragon" - {

    val vikingoPesado: Vikingo = Vikingo(10, 10, 10, Arma(40), 0)
    val vikingoLiviano: Vikingo = vikingoPesado.copy(peso = 1)
    val dragon: Dragon = Dragon(60, 1, 10)

    "Cumpliendo sus requisitos" in {
      vikingoLiviano.montar(dragon) shouldBe a [Jinete]
    }
    "No cumpliendo sus requisitos" in {
      vikingoPesado.montar(dragon) should not be a [Jinete]
    }
  }

}
