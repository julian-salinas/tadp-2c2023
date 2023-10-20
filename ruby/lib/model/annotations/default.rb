require_relative '../../utils/type_utils'
require_relative '../../mapper/public_attributes_extractor'
require_relative '../document'
require_relative '../../utils/fold'
require_relative '../root'

# atributo -> Attribute
# raíz -> MappedObject

class Default
  def serialize_object(thing)
    root = create_root(thing)
    root_serializers = thing.class.metadata.serializers["root"]
    final_root = apply_root_serializers(root_serializers, root)
    serialize_attributes(final_root)
    final_root
  end

  def create_root(thing)
    Root.new(thing.class.name, thing)
  end

  def serialize_attributes(root)
    if root.nil? then return end
    public_attributes = PublicAttributesExtractor.list_public_attributes(root.original_object)
    public_attributes.each do |attribute|
      Default.new.serialize_attribute(root, attribute, root.original_object.class.metadata.serializers[attribute.name])
    end
  end
  
  def serialize_attribute(root, attribute, attribute_serializers)
    if TypeUtils.is_primitive? attribute.value
      final_attribute = apply_attribute_serializers(attribute_serializers, attribute)
      if final_attribute.nil? then return end
      root.add_attribute(final_attribute.name, final_attribute.value)
    else
      attribute.value = Default.new.serialize_object(attribute.value)
      if attribute.value.nil? then return end
      final_attribute = apply_attribute_serializers(attribute_serializers, attribute)
      if final_attribute.nil? then return end
      if final_attribute.value.is_a? Root
        root.add_child(final_attribute.value)
      else
        root.add_attribute(final_attribute.name, final_attribute.value)
      end
    end
  end

  private def apply_attribute_serializers(attribute_serializers, attribute)
    attribute_serializers = attribute_serializers.nil? ? [] : attribute_serializers
    fold_l(attribute_serializers, attribute, lambda{|x,y| y.apply_over_attribute(x)})
  end

  private def apply_root_serializers(root_serializers, root)
    root_serializers = root_serializers.nil? ? [] : root_serializers
    fold_l(root_serializers, root, lambda{|x,y| y.apply_over_root(x)})
  end

end
