# frozen_string_literal: true

require_relative '../../utils/type_utils'
require_relative 'default'
require_relative '../attribute'
require_relative '../mapped_object'
require_relative '../root'

class Label
  attr_reader :name

  def initialize(name)
    @name = name
  end

  def apply_over_root(root)
    root.name = @name
    root
  end

  def apply_over_attribute(attribute)
    value = attribute.value
    if value.is_a? Root
      value.name = @name
      Attribute.new(value.class.name, attribute.value)
    else
      Attribute.new(@name, value)
    end
  end
end
