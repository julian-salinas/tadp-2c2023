class ObjectMapper
  def self.map_attributes(object)
    attributes_map = {}
    object.instance_variables.each do |attr_name|
      attr_value = object.instance_variable_get(attr_name)
      attributes_map[attr_name[1..-1]] = attr_value
    end
    attributes_map
  end
end