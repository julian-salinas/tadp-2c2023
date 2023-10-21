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

  def initialize(root = nil, &block)
    if not block_given? and root.nil? then raise StandardError.new("You must provide a block or a root to create a document") end
    document_root = if root.nil? then BlockToObjectMapper.map_block(&block) else root end
    @root_tag = MappedObjectSerializer.serialize(document_root)
  end

  def xml
    @root_tag.xml
  end

  # Serialización automática
  def self.serialize(thing)
    root = ObjectSerializer.new.serialize_object(thing)
    Document.new(root)
  end

end
