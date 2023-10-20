require 'rspec'
require_relative '../spec_helper'

RSpec.describe "Mixed" do
  describe "custom and label work fine together" do
    before(:each) do
      @un_estado = EstadoCustom.new(3, 5, true)
      @un_alumno = AlumnoTodoRancio.new("Carlos","123456-8", @un_estado)
    end

    it "automatic serialization gives expected output" do
      doc = Document.serialize(@un_alumno)
      xml_doc = doc.xml
      puts xml_doc
      expected_document = '<ya-no-se-que-poner><sunombre>"Carlos"</sunombre><sulegajo>"123456-8"</sulegajo></ya-no-se-que-poner>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end
end
