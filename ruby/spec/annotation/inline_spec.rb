require 'rspec'
require_relative '../spec_helper'

RSpec.describe Inline do
  context "serialize an object using inline attributes" do
    describe "given an inline attribute for the class" do
      before(:each) do
        @un_estado = Estado.new(3, 5, true)
        @un_alumno = AlumnoQueUsaInline.new("Matias","123456-7", @un_estado)
      end

      it "automatic serialization gives expected output" do
        doc = Document.serialize(@un_alumno)
        xml_doc = doc.xml
        puts xml_doc
        expected_document = '<alumnoQueUsaInline nombre="MATIAS" legajo="123456-7" estado=true/>'
        expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
      end
    end

    describe "cant apply inline annotation to root" do
      before(:each) do
        @un_estado = EstadoInlineado.new(3, 5, true)
        @un_alumno = AlumnoQueUsaInline.new("Matias","123456-7", @un_estado)
      end

      it "automatic serialization gives expected output" do
        expect { Document.serialize(@un_alumno) }.to raise_error("Inline annotation can only be applied to attributes")
      end

    end
  end
end
