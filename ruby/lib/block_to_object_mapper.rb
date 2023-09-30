# frozen_string_literal: true

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
      # do something
      # instance_variable_set("@child".to_sym, BlockToObjectMapper.map(&block))
    end

  end

end
