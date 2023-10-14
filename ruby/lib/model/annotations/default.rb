require_relative '../../utils/type_utils'
require_relative '../../mapper/public_attributes_extractor'
require_relative '../document'

class Default
  def serialize_object(thing)
    root_object = create_root(thing)
    if thing.class.metadata.has_serializer_for? "root"
      root_serializers = thing.class.metadata.serializers["root"]
      root_serializers.each do |serializer|
        serializer.apply_over_root(root_object) # root_object can be nil
      end
    end
    serialize_attributes(thing, root_object)
    root_object
  end

  def create_root(thing)
    MappedObject.new(thing.class.name) # esto genera problemas, la raiz no es polimorfica con los hijos
  end

  def serialize_attributes(thing, root_object)
    if root_object.nil? then return nil end

    public_attributes = PublicAttributesExtractor.list_public_attributes(thing)
    public_attributes.each do |attribute|
      Default.new.serialize_attribute(root_object, attribute, thing.class.metadata.serializers[attribute.name])
    end
  end

  def serialize_attribute(root_object, attribute, attribute_serializers)

    if TypeUtils.is_primitive? attribute.value

      # Aplicar anotaciones estudiante
      apply_attribute_serializers(attribute_serializers, attribute)

      unless attribute.ignore
        root_object.add_attribute(attribute.name, attribute.value)
      end
    else
      # Aplicar anotaciones estado
      attribute.value = Default.new.serialize_object(attribute.value)

      # Aplicar anotaciones estudiante
      apply_attribute_serializers(attribute_serializers, attribute.value)

      unless attribute.value.ignore
        root_object.add_child(attribute.value)
      end
    end
  end

  private def apply_attribute_serializers(attribute_serializers, attribute)
    unless attribute_serializers.nil?
      attribute_serializers.each do |serializer|
        serializer.apply_over_attribute(attribute)
      end
    end
  end

  private def get_class_serializer_or_nil(clazz)
    clazz.metadata.serializers["root"]
  end

end
