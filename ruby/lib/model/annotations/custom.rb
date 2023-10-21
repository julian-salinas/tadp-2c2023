# frozen_string_literal: true

require_relative '../../mapper/block_to_object_mapper'

class Custom
  attr_reader :block

  def initialize(&block)
    @block = block
  end

  def apply_over_root(root)
    BlockToObjectMapper.new(root).map(root.original_object, &@block) # Tiene efecto sobre root
    delete_attributes(root.original_object)
    root
  end

  def apply_over_attribute(attribute)
    throw "Custom annotation can only be applied to attributes"
  end

  private def delete_attributes(object)
    object.instance_variables.each do |var|
      object.remove_instance_variable(var)
    end
  end

end
