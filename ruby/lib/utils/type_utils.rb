# frozen_string_literal: true

class TypeUtils
  def self.is_primitive?(type)
    primitive_types = [Numeric, TrueClass, FalseClass, String]
    primitive_types.any?{ |primitive| type.class <= primitive }
  end
end
