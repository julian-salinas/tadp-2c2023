# frozen_string_literal: true

require_relative 'default'

class Custom
  attr_reader :block
  def initialize(&block)
    @block = block
  end

  def apply_over_root(root)
    # Probablemente hay que usar BlockToObjectMapper
    throw "not implemented"
  end

  def apply_over_attribute(attribute)
    throw "Custom annotation can only be applied to attributes"
  end
end
