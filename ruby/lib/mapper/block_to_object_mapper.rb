# frozen_string_literal: true

require_relative '../utils/block_name_extractor'
require_relative '../utils/type_utils'
require_relative '../model/mapped_object'
require_relative './children_builder'

class BlockToObjectMapper

  attr_reader :root
  def initialize(root)
    @root = root
  end

  def map(*args, &block)
    instance_exec(*args, &block)
  end

  private def method_missing(name, *args, **kwargs, &block)
    mapped_object = MappedObject.new(name)
    kwargs.each do |key, value|
      mapped_object.add_attribute(key, value)
    end

    mapped_object.child_or_children = ChildrenBuilder.new(*args, &block).children
    root.add_child(mapped_object)
  end

  def self.map_block(&block)
    auxiliar_root = Root.new("auxiliar_root")
    BlockToObjectMapper.new(auxiliar_root).map(&block)
    auxiliar_root.child_or_children[0]
  end
end
