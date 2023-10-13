# frozen_string_literal: true

require_relative '../../utils/type_utils'
require_relative 'default'

class Label < Default
  attr_reader :name
  attr_accessor :serializes_attributes

  def initialize(name)
    @name = name
  end

  def create_root(thing)
    MappedObject.new(@name)
  end

  def serialize_attribute(root_object, attribute)
    attribute.name = @name
    super(root_object, attribute)
  end
end
