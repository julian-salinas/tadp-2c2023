# Define un módulo para agregar funcionalidad de etiquetado a clases e instancias
module LabelDecorator
  def self.included(base)
    base.extend(ClassMethods)
  end

  module ClassMethods
    attr_reader :label

    def ✨Label✨(label)
      @label = label
    end
  end
end

# Define la clase Alumno con la funcionalidad de etiquetado
class Alumno
  include LabelDecorator
  attr_reader :nombre, :legajo, :estado, :telefono

  def initialize(nombre, legajo, telefono, estado)
    @nombre = nombre
    @legajo = legajo
    @telefono = telefono
    @estado = estado
  end

  ✨Label✨("estudiante")
  def to_xml
    "<#{self.class.label} nombre=\"#{@nombre}\" legajo=\"#{@legajo}\" celular=\"#{@telefono}\">\n" +
      "  #{estado.to_xml}\n" +
      "</#{self.class.label}>"
  end
end

# Define la clase Estado con la funcionalidad de etiquetado
class Estado
  include LabelDecorator
  attr_reader :finales_rendidos, :materias_aprobadas, :es_regular

  def initialize(finales_rendidos, materias_aprobadas, es_regular)
    @finales_rendidos = finales_rendidos
    @es_regular = es_regular
    @materias_aprobadas = materias_aprobadas
  end

  ✨Label✨("estado")
  def to_xml
    "<#{self.class.label} es_regular=#{@es_regular} finales_rendidos=#{@finales_rendidos} materias_aprobadas=#{@materias_aprobadas} />"
  end
end

# Crear una instancia de Alumno y generar el XML
alumno = Alumno.new("Matias", "123456-7", "1234567890", Estado.new(3, 5, true))
puts alumno.to_xml
