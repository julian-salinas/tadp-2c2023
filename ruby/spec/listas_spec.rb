# frozen_string_literal: true

require 'rspec'

RSpec.describe 'Listas' do

  before(:each) do
    @un_curso = Curso.new("123")
    @otro_curso = Curso.new("445")
    @tadp = Curso.new("3511")
    @un_profesor = Profesor.new([@un_curso, @otro_curso, @tadp])
    @un_profesor_nuevo = Profesor.new([])
    @un_profesor_que_no_cargo_bien_los_cursos = Profesor.new(['123', '445', '3511'])
    @un_profesor_rebelde = Profesor.new([@tadp, '42', Alumno.new('Juan', '123', 'Libre')])
    @un_cursed = Cursed.new([AlumnoTodoRancio.new('Juan', '123', 'Libre'), AlumnoTodoRancio.new('Juan', '123', 'Libre')])
    @cursito = Cursito.new(['pablito', 'jorgito', 'parpadeo automatico desactivado'])
  end

  context 'when serializing an object containing a simple object list' do
    it 'serializes all its children' do
      doc = Document.serialize(@un_profesor)
      xml_doc = doc.xml
      expected_document = '<profesor><curso id="123"/><curso id="445"/><curso id="3511"/></profesor>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end

  context 'when serializing an object containing an empty list' do
    it 'serializes the parent class without children' do
      doc = Document.serialize(@un_profesor_nuevo)
      xml_doc = doc.xml
      expected_document = '<profesor/>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end

  context 'when serializing an object containing a primitive list' do
    it 'serializes all its children' do
      doc = Document.serialize(@un_profesor_que_no_cargo_bien_los_cursos)
      xml_doc = doc.xml
      expected_document = '<profesor><string>"123"</string><string>"445"</string><string>"3511"</string></profesor>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end

  context 'when serializing an object containing a mixed type list' do
    it 'serializes all its children' do
      doc = Document.serialize(@un_profesor_rebelde)
      xml_doc = doc.xml
      expected_document = '<profesor><curso id="3511"/><string>"42"</string><alumno nombre="Juan" legajo="123" estado="Libre"/></profesor>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end

  context 'when serializing a non primitive objects list with annotations' do
    it 'serializes all its children' do
      doc = Document.serialize(@un_cursed)
      xml_doc = doc.xml
      puts xml_doc
      expected_document = '<cursed><ya-no-se-que-poner><sunombre>"Juan"</sunombre><sulegajo>"123"</sulegajo></ya-no-se-que-poner><ya-no-se-que-poner><sunombre>"Juan"</sunombre><sulegajo>"123"</sulegajo></ya-no-se-que-poner></cursed>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end

  context 'when serializing a primitive objects list with annotations' do
    it 'serializes all its children' do
      doc = Document.serialize(@cursito)
      xml_doc = doc.xml
      puts xml_doc
      expected_document = '<cursito><nene>"pablito"</nene><nene>"jorgito"</nene><nene>"parpadeo automatico desactivado"</nene></cursito>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end
end

class Profesor
  attr_reader :cursos

  def initialize(cursos)
    @cursos = cursos
  end

end

class Curso
  attr_reader :id
  def initialize(id)
    @id = id
  end
end

class Cursed
  attr_reader :alumnos
  def initialize(alumnos)
    @alumnos = alumnos
  end
end

class Cursito
  ✨Label✨("nene")
  attr_reader :alumnos
  def initialize(alumnos)
    @alumnos = alumnos
  end
end