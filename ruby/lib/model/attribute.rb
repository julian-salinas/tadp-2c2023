# frozen_string_literal: true

class Attribute
  attr_accessor :name, :value, :ignore

  def initialize(name, value, ignore = false)
    @name = name
    @value = value
    @ignore = ignore
    self
  end

end
