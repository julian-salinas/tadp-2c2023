# frozen_string_literal: true

require_relative 'attribute'
require_relative '../mapper/children_mapper'

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

  alias push add_child

  private def method_missing(method, *args, **kwargs, &block)
    # buscar el atributo al que se deseó acceder en la lista de atributos usando el name, en caso de no encontrarlo retornar el error
    attribute = @attributes.find { |attribute| attribute.name == method.to_s }
    if attribute.nil?
      super
    else
      attribute.value
    end
  end

  def self.create(name, *args, **kwargs, &block)
    mapped_object = new(name)
    kwargs.each do |key, value|
      mapped_object.add_attribute(key, value)
    end
    children_mapper = ChildrenMapper.new
    children_mapper.map(*args, &block)
    mapped_object.child_or_children = children_mapper.children
    mapped_object
  end

end