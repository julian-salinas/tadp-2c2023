require_relative 'tag'
require_relative 'utils/object_mapper'

class Document
  attr_writer :root_tag

  def xml
    @root_tag.xml
  end

  # Serialización automática

  def create_tag_with_name(name)
    Tag.with_label(name)
  end

  def self.generate_tag(parent_name, parent_value)
    attributes_map = ObjectMapper.map_public_attributes(parent_value)
    new_tag = Tag.with_label(parent_name)
    add_attributes_to_tag(new_tag, attributes_map)
    new_tag
  end

  def self.add_attributes_to_tag(tag, attributes_map)
    attributes_map.each do |name, value|
      add_to_tag(tag, name, value)
    end
    tag
  end

  def self.add_to_tag(new_tag, name, value)
    # todo: parte 3: que onda los children?
    if is_attribute?(value)
      new_tag.with_attribute(name, value)
    else new_tag.with_child(
      generate_tag name, value
    )
    end
  end

  def self.is_attribute?(type)
    primitive_attributes = [Numeric, TrueClass, FalseClass, String]
    primitive_attributes.any?{ |primitive| type.class <= primitive }
  end

  def self.serialize(thing)
    document = new
    new_tag = document.create_tag_with_name(thing.class.name)
    attributes_map = ObjectMapper.map_public_attributes(thing)
    document.root_tag = add_attributes_to_tag(new_tag, attributes_map)
    document
  end

  def method_missing(tag_name, attributes = {}, &block)
    new_tag = Tag.with_label(tag_name.to_s)
    add_attributes_to_tag(new_tag, attributes)
    @root_tag.with_child(new_tag)
    if block_given?
      previous_tag = @root_tag
      @root_tag = new_tag
      instance_eval(&block)
      @root_tag = previous_tag
    end
  end

  def respond_to_missing?(msg_name, includ_privete=false)

  end
end
