package modelo.dragon

import modelo.Vikingo
import modelo.dragon.Dragon.dragonSoportaPesoDelVikingo

case class Dragon (
                    velocidadBase: Double = 60.0,
                    danioBase: Double,
                    peso: Double,
                    requisitosParaSerMontado: List[(Dragon, Vikingo) => Boolean] = List(dragonSoportaPesoDelVikingo)
                    //^ TODO: Esto recuerdo que estaba planteado distinto, all yours ^
                  ) {
  def puedeSerMontadoPor(vikingo: Vikingo): Boolean = {
    requisitosParaSerMontado.forall(_(this, vikingo))
  }

  def pesoMaximoCarga(): Double = {
    peso * 0.2
  }

  def danio(): Double = danioBase

}

object Dragon {
  def dragonSoportaPesoDelVikingo(dragon: Dragon, vikingo: Vikingo): Boolean = dragon.pesoMaximoCarga() >= vikingo.peso
  def barbarosidadSuperiorA(barbarosidadMinima: Int, dragon: Dragon, vikingo: Vikingo): Boolean = vikingo.barbarosidad >= barbarosidadMinima
  def danioDeDragonEsSuperiorAlDelVikingo(dragon: Dragon, vikingo: Vikingo): Boolean = dragon.danio > vikingo.danio
}



