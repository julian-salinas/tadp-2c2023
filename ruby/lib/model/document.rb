require_relative '../metamodel/object'
require_relative '../metamodel/class'
require_relative '../tag'
require_relative '../utils/type_utils'
require_relative '../utils/block_name_extractor'
require_relative '../mapper/public_attributes_extractor'
require_relative '../mapper/block_to_object_mapper'
require_relative '../serializer/mapped_object_serializer'
require_relative 'annotations/default'

class Document
  attr_accessor :root_tag

  def initialize(&block)
    if block_given?
      root_object = BlockToObjectMapper.map(&block)
      @root_tag = MappedObjectSerializer.serialize(root_object)
      self
    end
  end

  def xml
    @root_tag.xml
  end

  # Serialización automática

  def self.serialize(thing)
    root = Default.new.serialize_object(thing)
    document = Document.new
    document.root_tag = MappedObjectSerializer.serialize(root)
    document
  end

  def to_mapped_object(thing)
    mapped_object = MappedObject.new(thing.name)
    attributes_list = PublicAttributesExtractor.list_public_attributes(thing.value)
    attributes_list.each do |attribute|
      if TypeUtils.is_primitive? attribute.value then
        mapped_object.add_attribute(attribute.name, attribute.value)
      else
        mapped_object.add_child to_mapped_object(attribute)
      end
    end
    mapped_object
  end
end
