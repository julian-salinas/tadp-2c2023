require_relative 'spec_helper'

describe Label do
  context "serialize an object using labels" do
    describe "given a label for the class" do
      before(:each) do
        @un_estado = Estado.new(3, 5, true)
        @un_alumno = AlumnoEstudiante.new("Carlos","123456-8", @un_estado)
      end

      it "automatic serialization gives expected output" do
        doc = Document.serialize(@un_alumno)
        xml_doc = doc.xml
        puts xml_doc
        expected_document = '<estudiante nombre="Carlos" legajo="123456-8"><estado finales_rendidos=3 es_regular=true materias_aprobadas=5/></estudiante>'
        expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
      end
    end

    describe "given a label for each attribute except one" do
      before(:each) do
        @un_estado = Estado.new(3, 5, true)
        @un_alumno = AlumnoReBasado.new("Carlos","123456-8", @un_estado)
      end

      it "automatic serialization gives expected output" do
        doc = Document.serialize(@un_alumno)
        xml_doc = doc.xml
        puts xml_doc
        expected_document = '<pibardo nombre="Carlos" numerito-que-no-se-acuerda="123456-8"><esto-no-se-pregunta finales_rendidos=3 es_regular=true materias_aprobadas=5/></pibardo>'
        expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
      end
    end
  end
end