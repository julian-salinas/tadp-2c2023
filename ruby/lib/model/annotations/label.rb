# frozen_string_literal: true

require_relative '../../utils/type_utils'
require_relative 'default'
require_relative '../attribute'

class Label
  attr_reader :name

  def initialize(name)
    @name = name
  end

  def apply_over_root(root)
    root.name = @name
  end

  def apply_over_attribute(attribute, getter = nil)
    attribute_to_modify = getter ? attribute.send(getter) : attribute
    attribute_to_modify.name = @name
  end
end
