# frozen_string_literal: true

require_relative '../../../mapper/block_to_object_mapper'
require_relative '../../root'

class CustomChildrenBuilder

  attr_reader :children, :context

  def initialize(context)
    @context = context
    @children = []
  end

  def map(&block)
    child = instance_eval(&block)
    if TypeUtils.is_primitive? child
      @children.push(child)
    end
    self
  end

end



