# frozen_string_literal: true

require_relative 'mapped_object'

class Root < MappedObject
  attr_accessor :ignore
  def initialize(name, ignore = false)
    @ignore = ignore
    super(name)
  end
end
