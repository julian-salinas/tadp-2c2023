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

class Estado
  attr_reader :finales_rendidos, :materias_aprobadas, :es_regular
  def initialize(finales_rendidos, materias_aprobadas, es_regular)
    @finales_rendidos = finales_rendidos
    @es_regular = es_regular
    @materias_aprobadas = materias_aprobadas
  end
end

✨Label✨("Estudiante")
class AlumnoEstudiante < Alumno
end

✨Label✨("Pibardo")
class AlumnoReBasado < Alumno
  ✨Label✨("numerito-que-no-se-acuerda")
  attr_reader :legajo

  ✨Label✨("esto-no-se-pregunta")
  attr_reader :estado
end

class AlumnoQueUsaInline < Alumno

  ✨Inline✨ {|campo| campo.upcase }
  attr_reader :nombre, :legajo

  ✨Inline✨ {|estado| estado.es_regular }
  def estado
    @estado
  end
end

✨Inline✨ {|estado| estado.es_regular } # Esto existe para que rompa
class EstadoInlineado < Estado
end

class AlumnoConEstadoCustom < Alumno
  ✨Label✨("situacion")
  attr_reader :estado
end

✨Custom✨ do |estado|
  regular { estado.es_regular }
  pendientes { estado.materias_aprobadas - estado.finales_rendidos }
end
class EstadoCustom < Estado
end

✨Label✨("ya-no-se-que-poner")
✨Custom✨ do |alumno|
  sunombre { alumno.nombre }
  sulegajo { alumno.legajo }
end
class AlumnoTodoRancio < Alumno
end

