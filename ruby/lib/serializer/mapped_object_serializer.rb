# frozen_string_literal: true
require_relative '../model/mapped_object'
class MappedObjectSerializer

  def self.serialize(mapped_object)
    tag_name = mapped_object.name
    new_tag = Tag.with_label(tag_name.to_s.first_to_lowercase)
    mapped_object.attributes.each { |attribute|
      new_tag.with_attribute(attribute.name, attribute.value)
    }
    mapped_object.child_or_children.each { |child|
      if TypeUtils.is_primitive? child
        new_tag.with_child(child)
      else
        new_tag.with_child(MappedObjectSerializer.serialize(child))
      end
    }
    new_tag
  end

end

class String
  def first_to_lowercase
    self[0].downcase + self[1..-1]
  end
end