require_relative '../model/attribute'

class PublicAttributesExtractor
  def self.list_public_attributes(object)
    attributes_list = []
    object_methods = object.methods.map {|m| "#{m}"}

    object.instance_variables.each do |variable|
      attr_name = variable[1..-1]
      attr_value = object.instance_variable_get(variable)
      if object_methods.any? { |method| method == attr_name }
        attributes_list.push(Attribute.new(attr_name, attr_value))
      end
    end
    attributes_list
  end

end