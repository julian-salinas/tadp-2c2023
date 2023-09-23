require_relative 'tag'
require_relative 'utils/object_mapper'

class Document
  attr_writer :root_tag
  def self.serialize(thing)
    document = new
    document.root_tag = document.generate_tag(thing.class.name, thing)
    document
  end

  def xml
    @root_tag.xml
  end

  def generate_tag(parent_name, parent_value)
    attributes_map = ObjectMapper.map_public_attributes(parent_value)
    new_tag = Tag.with_label(parent_name)
    attributes_map.each do |name, value|
      add_to_tag(new_tag, name, value)
    end
    new_tag
  end

  def add_to_tag(new_tag, name, value)
    # todo: parte 3: que onda los children?
    if is_attribute?(value)
      new_tag.with_attribute(name, value)
    else new_tag.with_child(
      generate_tag name, value
    )
    end
  end

  def is_attribute?(type)
    primitive_attributes = [Numeric, TrueClass, FalseClass, String]
    primitive_attributes.any?{ |primitive| type.class <= primitive }
  end
end
