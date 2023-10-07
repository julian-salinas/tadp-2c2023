require 'rspec'
class Alumno
  attr_reader :nombre, :legajo, :estado
  def initialize(nombre, legajo, estado)
    @nombre = nombre
    @legajo = legajo
    @estado = estado
  end
end

class Estado
  attr_reader :finales_rendidos, :materias_aprobadas, :es_regular
  def initialize(finales_rendidos, materias_aprobadas, es_regular)
    @finales_rendidos = finales_rendidos
    @es_regular = es_regular
    @materias_aprobadas = materias_aprobadas
  end
end
