# frozen_string_literal: true

require_relative '../../../utils/block_name_extractor'
require_relative '../../../utils/type_utils'
require_relative '../../root'
require_relative 'custom_children_builder'

class CustomBlockToObjectMapper

  attr_accessor :context, :root

  def initialize(root)
    @root = root
  end

  def map(&block)
    instance_eval(&block)
  end

  private def method_missing(name, *args, **kwargs, &block)
    context = @root.original_object
    if context.respond_to? name
      return context.send(name, *args, **kwargs, &block)
    end

    mapped_object = Root.new(name, context)

    kwargs.each do |key, value|
      mapped_object.add_attribute(key, value)
    end

    root_children = CustomChildrenBuilder.new(context).map(&block).children
    mapped_object.child_or_children = root_children
    @root.add_child(mapped_object)
  end

end
