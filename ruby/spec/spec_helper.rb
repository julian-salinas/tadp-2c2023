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

class AlumnoQueUsaInline

  ✨Inline✨ {|campo| campo.upcase }
  attr_reader :nombre, :legajo
  def initialize(nombre, legajo, telefono, estado)
    @nombre = nombre
    @legajo = legajo
    @telefono = telefono
    @estado = estado
  end

  ✨Inline✨ {|estado| estado.es_regular }
  def estado
    @estado
  end
end

✨Inline✨ {|estado| estado.es_regular } # Esto existe para que rompa
class EstadoInlineado
  attr_reader :finales_rendidos, :materias_aprobadas, :es_regular
  def initialize(finales_rendidos, materias_aprobadas, es_regular)
    @finales_rendidos = finales_rendidos
    @es_regular = es_regular
    @materias_aprobadas = materias_aprobadas
  end
end
