require_relative "../lib/mapper/block_to_object_mapper"
require_relative "../lib/mapper/object_mapper"
require_relative './spec_helper'

describe BlockToObjectMapper do
  context "given an object represented by" do
    describe "1 message and 2 kwargs" do
      it "maps to an object with 2 attributes" do
        block = proc do
          alumno nombre: "Matias", legajo: "123456-7"
        end
        object = BlockToObjectMapper.map &block
        expect(object.nombre).to eq "Matias"
        expect(object.legajo).to eq "123456-7"
      end
    end
    describe "1 message with a primitive value" do
      it "maps to an object with another object inside" do
        block = proc do
          alumno { 5 }
        end
        obj = {BlockToObjectMapper.map &block}
      end
    end
  end
end
