# frozen_string_literal: true

require 'rspec'
require_relative '../spec_helper'

RSpec.describe 'Ignore' do
  context 'given a class with an ignored attribute' do

    before do
      @un_estado = Estado.new(3, 5, true)
      @un_alumno_sin_estado = AlumnoAtributoEstadoIgnorado.new("Carlos", "123456-8", @un_estado)
    end

    it 'the ignored attribute is not serialized' do
      doc = Document.serialize(@un_alumno_sin_estado)
      xml_doc = doc.xml
      expected_document = '<alumnoAtributoEstadoIgnorado nombre="Carlos" legajo="123456-8"/>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end

  context 'given a class with an attribute from a class with an ignore annotation' do
    before do
      @un_estado_ignorado = EstadoIgnorado.new(3, 5, true)
      @un_alumno = Alumno.new("Carlos", "123456-8", @un_estado_ignorado)
    end

    it 'the attribute containing the ignored class is not serialized' do
      doc = Document.serialize(@un_alumno)
      xml_doc = doc.xml
      expected_document = '<alumno nombre="Carlos" legajo="123456-8"/>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end

  context 'given a class with two reader methods ignored with one annotation' do
    before do
      @un_estado = Estado.new(3, 5, true)
      @un_alumno_con_solo_estado = AlumnoConSoloEstado.new("Carlos", "123456-8", @un_estado)
    end

    it 'only the reader without the ignore annotation is serialized' do
      doc = Document.serialize(@un_alumno_con_solo_estado)
      xml_doc = doc.xml
      expected_document = '<alumnoConSoloEstado><estado finales_rendidos=3 es_regular=true materias_aprobadas=5/></alumnoConSoloEstado>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end

  context 'given a class with an ignored attribute and a nil attribute' do
    before do
      @un_estado = Estado.new(3, 5, true)
      @un_alumno_sin_estado = AlumnoAtributoEstadoIgnorado.new(nil, "123456-8", @un_estado)
    end

    it 'the ignored attribute is not serialized' do
      doc = Document.serialize(@un_alumno_sin_estado)
      xml_doc = doc.xml
      expected_document = '<alumnoAtributoEstadoIgnorado nombre= legajo="123456-8"/>'
      expect(xml_doc.gsub(/[\t\n]+/, "")).to eq(expected_document)
    end
  end

end

# Clases auxiliares

class AlumnoAtributoEstadoIgnorado
  attr_reader :nombre
  attr_reader :legajo
  ✨Ignore✨
  attr_reader :estado

  def initialize(nombre, legajo, estado)
    @nombre = nombre
    @legajo = legajo
    @estado = estado
  end
end

✨Ignore✨
class EstadoIgnorado
  attr_reader :finales_rendidos, :materias_aprobadas, :es_regular
  def initialize(finales_rendidos, materias_aprobadas, es_regular)
    @finales_rendidos = finales_rendidos
    @es_regular = es_regular
    @materias_aprobadas = materias_aprobadas
  end
end

class AlumnoConSoloEstado
  ✨Ignore✨
  attr_reader :nombre, :legajo
  attr_reader :estado

  def initialize(nombre, legajo, estado)
    @nombre = nombre
    @legajo = legajo
    @estado = estado
  end
end
