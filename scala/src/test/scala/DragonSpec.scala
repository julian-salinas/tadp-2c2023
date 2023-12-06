import modelo.competidor.{Arma, SistemaDeVuelo, Vikingo}
import modelo.dragon.RequisitosMontura.{RequisitoMontura, SinRequisitosAdicionales, VikingoDebeTenerSistemaDeVuelo}
import modelo.dragon.{Chimuelo, Dragon, FuriaNocturna, Gronckle, NadderMortifero}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class DragonSpec extends AnyFreeSpec {

  "Dragon" - {

    "puedeSerMontadoPor" - {

      "debería devolver true para un vikingo que cumple con los requisitos" in {
        val vikingo = Vikingo(barbarosidad = 70, velocidad = 1, peso = 100, hambre = 0, item = Some(Arma(10)))
        val dragon = Dragon(barbarosidadNecesariaParaMontarlo = 50, peso = 1000, danioBase = 30)

        dragon.puedeSerMontadoPor(vikingo) shouldBe true
      }

      "debería devolver false para un vikingo que no cumple con los requisitos" in {
        val vikingo = Vikingo(barbarosidad = 40, velocidad = 1, peso = 90, hambre = 0, item = Some(Arma(10)))
        val dragon = Dragon(barbarosidadNecesariaParaMontarlo = 50, peso = 1000, danioBase = 30)

        dragon.puedeSerMontadoPor(vikingo) shouldBe false
      }

    }

  }

  "FuriaNocturna" - {

    "velocidad" - {

      "debería ser el triple de la velocidad base del Dragon" in {
        val furiaNocturna = new FuriaNocturna(velocidadBase = 50, danioBase = 20, peso = 10, barbarosidadNecesariaParaMontarlo = 80)
        furiaNocturna.velocidad() shouldBe 120
      }

    }

  }

  "Gronckle" - {

    "danio" - {

      "debería ser el peso multiplicado por 5" in {
        val gronckle = new Gronckle(danioBase = 0, peso = 500, barbarosidadNecesariaParaMontarlo = 40, pesoQuePuedeSoportar = 200)
        gronckle.danio() shouldBe 2500
      }

    }

    "velocidad" - {

      "debería ser la mitad de la velocidad base del Dragon" in {
        val gronckle = new Gronckle(velocidadBase = 110, danioBase = 0, peso = 10, barbarosidadNecesariaParaMontarlo = 40, pesoQuePuedeSoportar = 200)
        gronckle.velocidad() shouldBe 50
      }

    }

  }

  "NadderMortifero" - {

    "requisitosAdicionalesParaMontarlo" - {

      "debería incluir el requisito de que el vikingo haga menos daño que el dragón" in {
        val nadderMortifero = new NadderMortifero(velocidadBase = 70, peso = 8000, barbarosidadNecesariaParaMontarlo = 60, requisitosAdicionalesParaMontarlo = List(VikingoDebeTenerSistemaDeVuelo))
        val vikingo = Vikingo(barbarosidad = 65, velocidad = 1,peso = 75, hambre = 0, item = Some(SistemaDeVuelo))

        nadderMortifero.puedeSerMontadoPor(vikingo) shouldBe true
      }

      "para montar a chimuelo es necesario un sistema de vuelo" in {
        val vikingo = Vikingo(barbarosidad = 650, velocidad = 1,peso = 10, hambre = 0, item = Some(SistemaDeVuelo))

        Chimuelo.puedeSerMontadoPor(vikingo) shouldBe true
      }

      "no puede montar a chimuelo sin sistema de vuelo" in {
        val vikingo = Vikingo(barbarosidad = 650, velocidad = 1,peso = 10, hambre = 0, item = Some(Arma(10)))

        Chimuelo.puedeSerMontadoPor(vikingo) shouldBe false
      }

      "no deberia poder montar al dragon si el vikingo hace más daño que el dragón" in {
        val nadderMortifero = new NadderMortifero(velocidadBase = 70, peso = 8000, barbarosidadNecesariaParaMontarlo = 60, requisitosAdicionalesParaMontarlo = List(VikingoDebeTenerSistemaDeVuelo))
        val vikingo = Vikingo(barbarosidad = 65, velocidad = 1,peso = 75, hambre = 0, item = Some(Arma(80)))

        nadderMortifero.puedeSerMontadoPor(vikingo) shouldBe false
      }

    }

  }

}
