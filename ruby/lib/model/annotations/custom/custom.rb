# frozen_string_literal: true

require_relative '../default'
require_relative 'custom_block_to_object_mapper'
require_relative 'custom_children_builder'
require_relative '../../../serializer/mapped_object_serializer'

class Custom
  attr_reader :block

  def initialize(&block)
    @block = block
  end

  def apply_over_root(root)
    CustomBlockToObjectMapper.new(root).map(&@block) # Tiene efecto sobre root
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
