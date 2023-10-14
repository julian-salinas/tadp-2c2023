require_relative '../model/annotations/label'
require_relative '../model/annotations/custom'
require_relative '../model/annotations/default'
require_relative '../model/annotations/ignore'
# require_relative '../model/annotations/inline'
# require_relative '../model/annotations/custom'

class AnnotationsRepository
  @@annotations = []

  def self.push (name, *args, &block)
    if Object.const_defined?(name)
      @@annotations.push( Object.const_get(name).new(*args, &block) )
    else
      raise "No annotation defined with the name #{name}"
    end
  end

  def self.pop
    @@annotations.pop
  end

  def self.has_annotations?
    @@annotations.length > 0
  end

  def self.get_annotations
    @@annotations
  end

  def self.clear
    @@annotations = []
  end

end
