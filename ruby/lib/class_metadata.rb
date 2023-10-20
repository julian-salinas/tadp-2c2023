# frozen_string_literal: true

class ClassMetadata
  attr_reader :given_name
  alias original_initialize initialize

  def attr_reader(*attributes)
    attributes.each do |attribute|
      define_method(attribute) do
        instance_variable_get("@#{attribute}")
      end
      puts "attr_reader: #{attribute}" # comportamiento extra
    end
  end

  def attr_accessor(*attributes)
    attributes.each do |attribute|
      define_method(attribute) do
        instance_variable_get("@#{attribute}")
      end

      define_method("#{attribute}=") do |value|
        instance_variable_set("@#{attribute}", value)
      end

      puts "attr_accessor: #{attribute}" # comportamiento extra
    end
  end
end
