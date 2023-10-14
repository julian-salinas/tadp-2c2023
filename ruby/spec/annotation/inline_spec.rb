require_relative '../spec_helper'

describe Inline do
  context "serialize an object using inline attributes" do
    describe "given an inline attribute for the class" do
      before(:each) do
        @un_estado = Estado.new(3, 5, true)
        @un_alumno = AlumnoQueUsaInline.new("Carlos","123456-8", "0303456", @un_estado)
      end

      it "automatic serialization gives expected output" do
        doc = Document.serialize(@un_alumno)
        xml_doc = doc.xml
        puts xml_doc
        expected_document = '<estudiante nombre="Carlos" legajo="123456-8"><estado finales_rendidos=3 es_regular=true materias_aprobadas=5/></estudiante>'
        expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
      end
    end

  end
end
