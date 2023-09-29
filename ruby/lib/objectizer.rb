# frozen_string_literal: true

class Objectizer

  def self.toObject(&block)
    obj = new
    obj.instance_eval(&block)
    obj
  end

  private def method_missing(name, *args, &block)
    args.each do |key, value|
      puts key
      puts value
      instance_variable_set("@#{key}".to_sym, value)
    end
  end

end
