require_relative 'document'

class Alumno
  attr_reader :nombre, :legajo, :estado
  def initialize(nombre, legajo, telefono, estado)
    @nombre = nombre
    @legajo = legajo
    @telefono = telefono
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

unEstado = Estado.new(3, 5, true)
unAlumno = Alumno.new("Matias","123456-8", "1234567890", unEstado)


=begin
documento_automatico = Document.serialize(unAlumno)
puts documento_automatico.xml
=end

=begin
nombre="Matias" legajo="123456-8">
	<estado finales_rendidos=3 es_regular=true materias_aprobadas=5/>
</Alumno>

=end
#TODO que los nombres empiezen en minuscula


documento_manual = Document.new do
  alumno nombre: unAlumno.nombre, legajo: unAlumno.legajo do
    estado finales_rendidos: unAlumno.estado.finales_rendidos,
           materias_aprobadas: unAlumno.estado.materias_aprobadas,
           es_regular: unAlumno.estado.es_regular
  end
end
documento_manual.xml
