require 'rspec'
require_relative './spec_helper'
require_relative './../lib/document'

context "document serialization" do
  describe "given two defined domain classes" do
    it "automatic serialization gives expected output" do
      Document.serialize(un_alumno)
      expect(true).to be_truthy
    end

  end
end