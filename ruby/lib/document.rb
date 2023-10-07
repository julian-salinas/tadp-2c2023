require_relative 'tag'
require_relative 'mapper/block_to_object_mapper'
require_relative 'utils/type_utils'
require_relative './mapper/public_attributes_extractor'
require_relative 'utils/block_name_extractor'
require_relative 'serializer/mapped_object_serializer'

class Document
  attr_accessor :root_tag

  def initialize(&block)
    if block_given?
      root_object = BlockToObjectMapper.map(&block)
      @root_tag = MappedObjectSerializer.serialize(root_object)
      self
    end
  end

  def xml
    @root_tag.xml
  end

  # Serialización automática

  def self.serialize(thing)
    document = new
    tag_name = thing.class.name

    root_object = MappedObject.new(tag_name)
    attributes_list = PublicAttributesExtractor.list_public_attributes(thing)
    attributes_list.each do |attribute|
      if TypeUtils.is_primitive? attribute.value then
        root_object.add_attribute(attribute.name, attribute.value) #TODO refactor AGREGAR RECURSIVIDAD
      else
        #todo
      end
    end
    document.root_tag = MappedObjectSerializer.serialize(root_object)
    document
  end


end

doc = Document.new do
  alumno nombre: "Matias", legajo: "123456-7" do
    telefono { "1234567890" }
    estado es_regular: true do
      finales_rendidos { 3 }
      materias_aprobadas { 5 }
    end
    gobierno es_feo: true do
      congreso { 1 }
      dinero { 7 }
    end
  end
end

#puts doc.xml


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

doc2 = Document.serialize(unAlumno)
puts doc2.xml