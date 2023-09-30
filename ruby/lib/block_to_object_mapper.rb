# frozen_string_literal: true

require_relative './utils/block_name_extractor'

class BlockToObjectMapper

  def self.map(&block)
    obj = new
    obj.instance_eval(&block)
    obj
  end

  private def method_missing(name, *args, **kwargs, &block)
    kwargs.each do |key, value|
      puts "key: #{key}, value: #{value}"
      instance_variable_set("@#{key}".to_sym, value)
      self.define_singleton_method(key) { value }
    end

    if block_given?
      if TypeHelper.is_primitive? block.call.class
        # pending: esto sería un child
      else
        instance_variable_set("@#{BlockNameExtractor.extract(&block)}", BlockToObjectMapper.map(&block))
        self.define_singleton_method(BlockNameExtractor.extract(&block).to_s) { BlockToObjectMapper.map(&block) }
      end
    end

  end

end
