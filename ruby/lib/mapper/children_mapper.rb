# frozen_string_literal: true

require_relative './block_to_object_mapper'
require_relative '../model/mapped_object'

class ChildrenMapper

  attr_reader :children

  def initialize
    @children = []
  end

  def map(*args, &block)
    child = instance_exec(*args, &block)
    if TypeUtils.is_primitive? child then @children.push(child) end
  end

  private def method_missing(name, *args, **kwargs, &block)
    mapped_object = MappedObject.create(name, *args, **kwargs, &block)
    @children.push(mapped_object)
  end

end
