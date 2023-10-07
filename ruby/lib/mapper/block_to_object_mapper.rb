# frozen_string_literal: true

require_relative '../utils/block_name_extractor'
require_relative '../utils/type_utils'

class BlockToObjectMapper

  def self.map(&block)
    obj = new
    obj.instance_eval(&block)
    obj
  end

  private def method_missing(name, *args, **kwargs, &block)
    puts "method_missing - block_to_object_mapper: (#{name}, #{args}, #{kwargs}, #{block})"
    kwargs.each do |key, value|
      instance_variable_set("@#{key}".to_sym, value)
      self.define_singleton_method(key) { value }
    end

    if block_given?
      if TypeUtils.is_primitive? block.call
        # =begin
           # puts "name: #{name}"
        # CHILD FINAL
        # puts "child final: #{BlockNameExtractor.extract(&block)}"
        # instance_variable_set("@#{name}", block.call)
        # puts "asignando #{block.call} a @#{name}"
        # self.define_singleton_method(name) { block.call }
        # child = ObjectMapper.map_public_attributes(BlockToObjectMapper.map(&block))
      else
        # CHILD OBJETO
        puts "child objetoso: #{BlockNameExtractor.extract(&block)}"
        instance_variable_set("@#{BlockNameExtractor.extract(&block)}", BlockToObjectMapper.map(&block))
        self.define_singleton_method(BlockNameExtractor.extract(&block).to_s) { BlockToObjectMapper.map(&block) }
      end
    else
      #
    end

  end

end
