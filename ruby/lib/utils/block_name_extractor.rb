# frozen_string_literal: true

class BlockNameExtractor
  def self.extract(&block)
    instance_eval(&block)
  end

  private def self.method_missing(name, *args)
    name
  end
end