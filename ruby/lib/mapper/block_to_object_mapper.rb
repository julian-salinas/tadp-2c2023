# frozen_string_literal: true

require_relative '../utils/block_name_extractor'
require_relative '../utils/type_utils'
require_relative '../model/mapped_object'
require_relative './children_builder'

class BlockToObjectMapper

  def self.map(&block)
    mapper = new
    mapper.instance_eval(&block)
  end

  private def method_missing(name, *args, **kwargs, &block)
    mapped_object = MappedObject.new(name)
    puts "block_to_object_mapper #{name}, #{args}, #{kwargs}, #{block}"
    kwargs.each do |key, value|
      mapped_object.add_attribute(key, value)
    end

    #mapped_object.add_child(BlockToObjectMapper.map(&block))

    mapped_object.child_or_children = ChildrenBuilder.new(&block).children
    mapped_object
  end
end


a = proc do
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

b = BlockToObjectMapper.map(&a)
puts b