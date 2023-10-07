require_relative "../lib/mapper/block_to_object_mapper"
require_relative "../lib/mapper/public_attributes_extractor"
require_relative './spec_helper'

describe BlockToObjectMapper do
  context "given an object represented by a block composed of " do
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
      it "maps to an object containing a single child" do
        # <alumno>5</alumno>
        block = proc do
          alumno { 5 }
        end
        mapped_object = BlockToObjectMapper.map &block
        expect(mapped_object.child).to eq 5
      end
    end
    describe "1 message with 2 nested objects each containing a primitive value" do
      it "maps to an object containing a two children with their respective values" do
        # <alumno>5</alumno>
        block = proc do
          alumno do
            materias_aprobadas { 5 }
            estado { "regular" }
          end
        end
        mapped_object = BlockToObjectMapper.map &block
        expect(mapped_object.respond_to? materias_aprobadas).to be_truthy
        expect(mapped_object.materias_aprobadas).to eq 5
        expect(mapped_object.respond_to? estado).to be_truthy
        expect(mapped_object.estado).to eq "regular"
      end
    end
  end
end
