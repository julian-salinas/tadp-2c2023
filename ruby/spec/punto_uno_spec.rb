
require_relative 'spec_helper'
require_relative './../lib/document'


describe Document do
  context "document serialization" do
    describe "given two defined domain classes" do

      before(:each) do
        @un_estado = Estado.new(3, 5, true)
        @un_alumno = Alumno.new("Carlos","123456-8", @un_estado)
      end

      it "automatic serialization gives expected output" do
        doc = Document.serialize(@un_alumno)
        xml_doc = doc.xml
        puts xml_doc
        expected_document = '<alumno nombre="Carlos" legajo="123456-8"><estado finales_rendidos=3 es_regular=true materias_aprobadas=5/></alumno>'
        expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
      end

      it "manual serialization gives expected output" do
        documento = Document.new do
          alumno nombre: "Carlos", legajo: "123456-8" do
            telefono { "1234567890" }
            estado es_regular: true do
              finales_rendidos { 3 }
              materias_aprobadas { 5 }
            end
          end
        end
        doc_serializado = documento.xml
        puts doc_serializado
        expected_document = '<alumno nombre="Carlos" legajo="123456-8"><estado finales_rendidos=3 es_regular=true materias_aprobadas=5/></alumno>'
        expect(doc_serializado.gsub(/[\t\n]+/, "")).to eq(expected_document)
      end
    end
  end
end