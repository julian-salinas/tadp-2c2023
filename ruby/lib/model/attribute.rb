# frozen_string_literal: true

class Attribute
  attr_accessor :name, :value

  def initialize(name, value)
    @name = name
    @value = value
    self
  end
end
