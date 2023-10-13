require_relative '../../utils/type_utils'
require_relative '../../mapper/public_attributes_extractor'
require_relative '../document'

class Default
  def serialize_object(thing)
    root_object = create_root(thing)
    serialize_attributes(thing, root_object)
    root_object
  end

  def create_root(thing)
    MappedObject.new(thing.class.name)
  end

  def serialize_attributes(thing, root_object)
    public_attributes = PublicAttributesExtractor.list_public_attributes(thing)
    public_attributes.each do |attribute|
      if thing.class.metadata.serializers[attribute.name]
        thing.class.metadata.serializers[attribute.name].serialize_attribute(root_object, attribute)
        next
      end
      Default.new.serialize_attribute(root_object, attribute)
    end
  end

  def serialize_attribute(root_object, attribute)
    if TypeUtils.is_primitive? attribute.value
      root_object.add_attribute(attribute.name, attribute.value)
    else
      child = Document.new.to_mapped_object(attribute)
      root_object.add_child(child)
    end
  end

end
