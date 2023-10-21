# frozen_string_literal: true

require_relative '../utils/block_name_extractor'
require_relative '../utils/type_utils'
require_relative '../model/mapped_object'
require_relative './children_mapper'

class BlockToObjectMapper

  attr_reader :root
  def initialize(root)
    @root = root
  end

  def map(*args, &block)
    instance_exec(*args, &block)
  end

  private def method_missing(name, *args, **kwargs, &block)
    mapped_object = MappedObject.create(name, *args, **kwargs, &block)
    root.add_child(mapped_object)
  end

  def self.map_block(&block)
    aux_root = Root.new("auxiliar_root")
    BlockToObjectMapper.new(aux_root).map(&block)
    aux_root.tail
  end
end
