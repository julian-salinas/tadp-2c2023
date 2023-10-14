# frozen_string_literal: true


class Ignore

  def serialize_object(thing)
    thing.ignore= true
  end

  def apply_over_root(thing)
    thing.ignore= true
  end

  def apply_over_attribute(attribute)
    attribute.ignore= true
  end
end
