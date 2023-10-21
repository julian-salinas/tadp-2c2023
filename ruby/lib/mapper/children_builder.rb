# frozen_string_literal: true

require_relative './block_to_object_mapper'
require_relative '../model/mapped_object'

class ChildrenBuilder

  attr_reader :children

  def initialize(&block)
    @children = []
    ret = instance_eval(&block)
    if TypeUtils.is_primitive? ret
      @children.push(ret)
    end
    self
  end

  private def method_missing(name, *args, **kwargs, &block)
    puts "children_builder #{name}, #{args}, #{kwargs}, #{block}"
    mapped_object = MappedObject.new(name)

    kwargs.each do |key, value|
      mapped_object.add_attribute(key, value)
    end

    mapped_object.child_or_children = ChildrenBuilder.new(&block).children

    @children.push(mapped_object)
  end

end



