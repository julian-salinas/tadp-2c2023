# frozen_string_literal: true

require_relative 'default'
require_relative '../../utils/type_utils'
require_relative "../../model/attribute"

class Inline
  attr_reader :block
  def initialize(&block)
    @block = block
  end

  def apply_over_root(root)
    throw "Inline annotation can only be applied to attributes"
  end

  def apply_over_attribute(attribute)
    Attribute.new(attribute.name, @block.call(attribute.value))
  end
end
