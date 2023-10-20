# frozen_string_literal: true

require_relative 'attribute'

class MappedObject

  attr_reader :attributes
  attr_accessor :child_or_children, :name

  def initialize(name)
    @name = name
    @attributes = []
    @child_or_children = []
  end

  def add_attribute(name, value)
    @attributes.push(Attribute.new name, value)
    self
  end

  def add_child(child)
    @child_or_children.push(child)
    self
  end

  private def method_missing(method, *args, **kwargs, &block)
    # buscar el atributo al que se deseó acceder en la lista de atributos usando el name, en caso de no encontrarlo retornar el error
    attribute = @attributes.find { |attribute| attribute.name == method.to_s }
    if attribute.nil?
      super
    else
      attribute.value
    end
  end

end
