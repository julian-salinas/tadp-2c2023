# frozen_string_literal: true

=begin
Entradas del diccionario serializers
  - key: nombre del método/atributo : str
  - valor: instancia de un decorador que va a serializar el valor, ej: label
=end

class Metadata
  attr_accessor :attributes, :given_name, :serializers

  def initialize
    @serializers = Hash.new
  end

  def add_annotation(name, block)
    unless has_serializer_for? name
      @serializers[name] = []
    end
    @serializers[name].push(block)
  end

  def has_serializer_for?(name)
    @serializers[name] != nil
  end

end
