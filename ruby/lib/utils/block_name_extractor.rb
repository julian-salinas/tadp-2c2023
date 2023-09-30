# frozen_string_literal: true

class BlockNameExtractor
  def self.extract(&block)
    instance_eval(&block)
  end

  private def self.method_missing(name, *args)
    name
  end
end

=begin
BlockNameExtractor.extract do
  alumno nombre: "Matias", legajo: "123456-7" do
    telefono { "1234567890" }
    estado es_regular: true do
      finales_rendidos { 3 }
      materias_aprobadas { 5 }
    end
  end
end
=end