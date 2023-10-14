require 'rspec'
require_relative '../lib/metamodel/class'
require_relative '../lib/metamodel/object'

class Alumno
  attr_reader :nombre, :legajo

  def initialize(nombre, legajo, estado)
    @nombre = nombre
    @legajo = legajo
    @estado = estado
  end

  def estado
    @estado
  end

end

✨Label✨("Estudiante")
class AlumnoEstudiante
  attr_reader :nombre, :legajo, :estado
  def initialize(nombre, legajo, estado)
    @nombre = nombre
    @legajo = legajo
    @estado = estado
  end
end

✨Label✨("Pibardo")
class AlumnoReBasado
  attr_reader :nombre

  ✨Label✨("numerito-que-no-se-acuerda")
  attr_reader :legajo

  ✨Label✨("esto-no-se-pregunta")
  attr_reader :estado

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