  package modelo.competidor

import modelo.dragon.Dragon
import modelo.posta.Posta

  case class Jinete (vikingo: Vikingo, dragon: Dragon) extends Competidor {
    override def incrementarHambre(variacion: Double): Jinete = this.copy(vikingo = vikingo.incrementarHambre(variacion))

    override def cantidadDePescadoQuePuedeTransportar(): Double = dragon.pesoMaximoCarga - vikingo.peso

    override def danio(): Double = { dragon.danio() + vikingo.danio() }

    override def velocidad(): Double = dragon.velocidad() - vikingo.peso

    def hambre(): Double = vikingo.hambre

    override def puedePermitirseParticiparEn(posta: Posta): Boolean = ???
  }
