# frozen_string_literal: true

require_relative '../../utils/type_utils'
require_relative 'default'

class Label
  attr_reader :name

  def initialize(name)
    @name = name
  end

  def apply_over_root(root)
    root.name = @name
  end

  def apply_over_attribute(attribute)
    attribute.name = @name
  end
end
