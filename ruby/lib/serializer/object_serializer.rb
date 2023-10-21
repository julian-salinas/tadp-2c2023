require_relative '../utils/type_utils'
require_relative '../mapper/public_attributes_extractor'
require_relative '../model/document'
require_relative '../utils/fold'
require_relative '../model/root'

# atributo -> Attribute
# raíz -> MappedObject

class ObjectSerializer
  def serialize_object(thing)
    root = create_root(thing)
    puts "Por serializar #{thing}"
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
      ObjectSerializer.new.serialize_attribute(root, attribute, root.original_object.class.metadata.serializers[attribute.name])
    end
  end
  
  def serialize_attribute(root, attribute, attribute_serializers)
    if TypeUtils.is_primitive? attribute.value or attribute.value.nil?
      serialize_primitive_attr(root, attribute, attribute_serializers)
    elsif TypeUtils.is_list? attribute.value
      serialize_attribute_list(root, attribute, attribute_serializers)
    else
      serialize_non_primitive_attr(root, attribute, attribute_serializers)
    end
  end

  private def serialize_primitive_attr(root, attribute, attribute_serializers)
    final_attribute = apply_attribute_serializers(attribute_serializers, attribute)
    if final_attribute.value.nil? then return root.add_attribute(final_attribute.name, final_attribute.value) end
    if should_ignore? final_attribute then return end
    root.add_attribute(final_attribute.name, final_attribute.value)
  end

  private def serialize_attribute_list(root, attribute, attribute_serializers)
    attribute.value.each { |list_element|
      if TypeUtils.is_primitive? list_element
        primitive_child = generate_root_from_primitive(list_element)
        final_attribute = apply_attribute_serializers(attribute_serializers, Attribute.new("_", primitive_child))
        add_final_attribute(root, final_attribute)
      else
        serialize_attribute(root, Attribute.new(list_element.class.name, list_element), attribute_serializers)
      end
    }
  end

  private def generate_root_from_primitive(element)
    Root.new(element.class.name, element).add_child(element)
  end

  private def serialize_non_primitive_attr(root, attribute, attribute_serializers)
    attribute.value = ObjectSerializer.new.serialize_object(attribute.value)
    if attribute.value.nil? then return root.add_attribute(attribute.name, nil) end
    final_attribute = apply_attribute_serializers(attribute_serializers, attribute)
    if should_ignore? final_attribute then return end
    add_final_attribute(root, final_attribute)
  end

  private def add_final_attribute(root, final_attribute)
    if final_attribute.value.is_a? Root
      root.add_child(final_attribute.value)
    elsif final_attribute.nil?
      root.add_child(nil)
    else
      root.add_attribute(final_attribute.name, final_attribute.value)
    end
  end

  private def should_ignore?(object)
    object.ignore? || (object.value.respond_to?(:ignore?) && object.value.ignore?)
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
