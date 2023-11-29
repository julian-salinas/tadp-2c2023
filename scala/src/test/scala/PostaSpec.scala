import modelo.competidor.{Arma, Competidor, Vikingo}
import modelo.posta.{Carrera, Combate, Pesca}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class PostaSpec extends AnyFreeSpec {
  "Tres vikingos compiten en una pesca" - {
    val vikingoPesado: Vikingo = Vikingo(300, 10, 10, Some(Arma(40)), 0)
    val vikingoNi: Vikingo = vikingoPesado.copy(peso = 85)
    val vikingoLiviano: Vikingo = vikingoPesado.copy(peso = 60)
    val vikingos: List[Vikingo] = List(vikingoLiviano, vikingoPesado, vikingoNi)

    "Gana el vikingo pesado" in {
      val resultado: List[Competidor] = (new Pesca()).competir(vikingos)
      resultado shouldEqual List(vikingoPesado.copy(hambre=5), vikingoNi.copy(hambre=5), vikingoLiviano.copy(hambre=5))
    }
  }

  "Tres vikingos compiten en una carrera" - {
    val vikingoRapido: Vikingo = Vikingo(100, 20, 10, None, 0)
    val vikingoLento: Vikingo = vikingoRapido.copy(velocidad = 5)
    val vikingoNormal: Vikingo = vikingoRapido.copy(velocidad = 15)
    val vikingos: List[Vikingo] = List(vikingoLento, vikingoNormal, vikingoRapido)

    "Gana el vikingo rápido" in {
      val resultado: List[Competidor] = (new Carrera(distanciaKm = 10)).competir(vikingos)
      resultado shouldEqual List(vikingoRapido.copy(hambre=10), vikingoNormal.copy(hambre=10), vikingoLento.copy(hambre=10))
    }
  }

  "Dos vikingos compiten en un combate" - {
    val vikingoFuerte: Vikingo = Vikingo(200, 15, 10, Some(Arma(30)), 0)
    val vikingoDebil: Vikingo = Vikingo(150, 10, 5, Some(Arma(20)), 0)
    val vikingos: List[Vikingo] = List(vikingoFuerte, vikingoDebil)

    "Gana el vikingo fuerte" in {
      val resultado: List[Competidor] = (new Combate()).competir(vikingos)
      resultado shouldEqual List(vikingoFuerte.copy(hambre=10), vikingoDebil.copy(hambre=10))
    }
  }

  "Tres vikingos participan en una pesca" - {
    val vikingoPesado: Vikingo = Vikingo(300, 10, 10, Some(Arma(40)), 0)
    val vikingoNi: Vikingo = vikingoPesado.copy(peso = 85)
    val vikingoLiviano: Vikingo = vikingoPesado.copy(peso = 60)
    val vikingos: List[Competidor] = List(vikingoLiviano, vikingoPesado, vikingoNi)

    "Incrementa el hambre en 5 para cada competidor" in {
      val resultado: List[Competidor] = Pesca().competir(vikingos)
      resultado.foreach(_.hambre shouldEqual (vikingoPesado.hambre + 5))
    }
  }

  "Tres vikingos participan en una carrera" - {
    val vikingoRapido: Vikingo = Vikingo(100, 20, 10, None, 0)
    val vikingoLento: Vikingo = vikingoRapido.copy(velocidad = 5)
    val vikingoNormal: Vikingo = vikingoRapido.copy(velocidad = 15)
    val vikingos: List[Vikingo] = List(vikingoLento, vikingoNormal, vikingoRapido)

    "Incrementa el hambre en 1 para cada competidor" in {
      val resultado: List[Competidor] = Carrera(distanciaKm = 1).competir(vikingos)
      resultado.foreach(_.hambre shouldEqual (vikingoRapido.hambre + 1))
    }
  }

  "Dos vikingos participan en un combate" - {
    val vikingoFuerte: Vikingo = Vikingo(200, 15, 10, Some(Arma(30)), 0)
    val vikingoDebil: Vikingo = Vikingo(150, 10, 5, Some(Arma(20)), 0)
    val vikingos: List[Vikingo] = List(vikingoFuerte, vikingoDebil)

    "Incrementa el hambre en 10 para cada competidor" in {
      val resultado: List[Competidor] = Combate().competir(vikingos)
      resultado.foreach(_.hambre shouldEqual (vikingoFuerte.hambre + 10))
    }
  }
}