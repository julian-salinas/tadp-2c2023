# frozen_string_literal: true

require_relative 'mapped_object'

class Root < MappedObject
  attr_accessor :ignore, :original_object
  def initialize(name, original_object = nil, ignore = false)
    @ignore = ignore
    @original_object = original_object
    super(name)
  end

  def ignore?
    ignore
  end

  def tail
    # desprender la raíz
    child_or_children[0]
  end

end
