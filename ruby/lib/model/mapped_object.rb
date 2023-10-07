# frozen_string_literal: true

require_relative 'attribute'

class MappedObject

  attr_reader :name, :attributes
  attr_accessor :child_or_children

  def initialize(name)
    @name = name
    @attributes = []
    @child_or_children = [] # Analizar cambios
  end

  def add_attribute(name, value)
    @attributes.push(Attribute.new name, value)
    self
  end

  def add_child(child)
    puts "Agregando child #{child} a #{self}"
    @child_or_children.push(child)
    self
  end

end

