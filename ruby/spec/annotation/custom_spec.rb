require 'rspec'
require_relative '../spec_helper'

RSpec.describe Custom do
  context "serialize an object using custom serialization" do
    describe "given a labeled attribute that serializes with custom annotation" do
      before(:each) do
        @un_estado = EstadoCustom.new(5, 7, true)
        @un_alumno = AlumnoConEstadoCustom.new("Matias","123456-7", @un_estado)
      end

      it "automatic serialization gives expected output" do
        doc = Document.serialize(@un_alumno)
        xml_doc = doc.xml
        puts xml_doc
        expected_document = '<alumnoConEstadoCustom nombre="Matias" legajo="123456-7"><situacion><regular>true</regular><pendientes>2</pendientes></situacion></alumnoConEstadoCustom>'
        expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
      end

    end
  end
end
