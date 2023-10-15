# frozen_string_literal: true


class Ignore

  def serialize_object(thing)
    thing.ignore= true
  end

  def apply_over_root(thing)
    thing.ignore= true
  end

  def apply_over_attribute(attribute, getter = nil)
    attribute_to_modify = getter ? attribute.send(getter) : attribute
    attribute_to_modify.ignore= true
  end
end
