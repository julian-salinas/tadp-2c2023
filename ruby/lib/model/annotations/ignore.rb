# frozen_string_literal: true



class Ignore

  def apply_over_root(thing)
    thing.ignore = true
    thing
  end

  def apply_over_attribute(attribute)
    attribute.ignore = true
    attribute
  end
end
