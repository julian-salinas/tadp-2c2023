package modelo

case class Vikingo (
                    peso: Double,
                    velocidad: Double,
                    barbarosidad: Int,
                    item: Option[Item],
                    hambre: Double
                  ) extends Competidor {
  require(0 <= hambre && hambre <= 1)

  def montar(dragon: Dragon): Competidor = {
    if(dragon.puedeSerMontadoPor(this)) {
      Jinete(this, dragon)
    } else {
      this
    }
  }

  def incrementarHambre(variacion: Double): Vikingo = this.copy(hambre = Math.min(1, hambre + variacion))

  def danio(): Double = { this.barbarosidad + danioItem}

  private def danioItem: Double = {
    item match {
      case Some(i) => i match {
        case a: Arma => a.danioAdicional
        case _ => 0
    }
      case _ => 0
    }
  }

  override def cantidadDePescadoQuePuedeTransportar(): Double = this.peso + this.barbarosidad * 2
}
