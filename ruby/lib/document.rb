require_relative 'tag'
require_relative 'block_to_object_mapper'
require_relative 'utils/type_helper'
require_relative 'utils/object_mapper'
require_relative 'utils/block_name_extractor'

class Document
  attr_accessor :root_tag

  def initialize(&block)
    if block_given?
      @root_tag = Document.initialize_from_block(&block)
      self
    end
  end

  def self.initialize_from_block(&block)
    document = new
    puts document
    new_tag = document.create_tag_with_name(BlockNameExtractor.extract(&block))
    attributes_map = ObjectMapper.map_public_attributes(BlockToObjectMapper.map(&block))
    add_attributes_to_tag(new_tag, attributes_map)
  end


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
    TypeHelper.is_primitive?(type)
  end

  def self.serialize(thing)
    document = new
    new_tag = document.create_tag_with_name(thing.class.name)
    attributes_map = ObjectMapper.map_public_attributes(thing)
    document.root_tag = add_attributes_to_tag(new_tag, attributes_map)
    document
  end


end

