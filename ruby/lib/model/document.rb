require_relative '../metamodel/object'
require_relative '../metamodel/class'
require_relative '../tag'
require_relative '../utils/type_utils'
require_relative '../utils/block_name_extractor'
require_relative '../mapper/public_attributes_extractor'
require_relative '../mapper/block_to_object_mapper'
require_relative '../serializer/mapped_object_serializer'
require_relative '../serializer/object_serializer'

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
    root = ObjectSerializer.new.serialize_object(thing)
    document = Document.new
    document.root_tag = MappedObjectSerializer.serialize(root)
    document
  end

end
