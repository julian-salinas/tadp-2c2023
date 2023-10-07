# frozen_string_literal: true

require_relative '../utils/block_name_extractor'
require_relative '../utils/type_utils'
require_relative '../model/mapped_object'

class BlockToObjectMapper

  attr_accessor :children

  def initialize
    #@children = []
  end

  def self.map(&block)
    mapper = new
    mapper.instance_eval(&block)
  end




  private def method_missing(name, *args, **kwargs, &block)
    @mapped_object = MappedObject.new(name)

    kwargs.each do |key, value|
      @mapped_object.add_attribute(key, value)
    end

    @mapped_object.add_child(BlockToObjectMapper.map(&block))

    # mapped_object

=begin
    if TypeUtils.is_primitive? block.call # Si el bloque retorna un tipo primitivo...
      # CHILD FINAL (items)
      instance_variable_set("@#{name}", block.call)
      self.define_singleton_method(:"#{name}") { block.call }
    else
      # CHILD OBJETO
      instance_variable_set("@#{BlockNameExtractor.extract(&block)}", BlockToObjectMapper.map(&block))
      self.define_singleton_method(BlockNameExtractor.extract(&block).to_s) { BlockToObjectMapper.map(&block) }
    end
=end

  end

end
