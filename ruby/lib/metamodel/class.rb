require_relative '../repository/annotations_repository'
require_relative '../model/metadata'

class Class
  attr_accessor :metadata

  private def get_annotations
    annotations = AnnotationsRepository.get_annotations
    AnnotationsRepository.clear
    annotations
  end

  private def apply_annotations(thing, annotations, key)
    annotations.each do |annotation|
      thing.metadata.add_annotation(key, annotation)
    end
  end

  def inherited(new_class)
    new_class.metadata = Metadata.new
    annotations = get_annotations
    apply_annotations new_class, annotations, "root"
  end

  def method_added(method_name)
    annotations = get_annotations
    apply_annotations self, annotations, method_name.to_s
  end

  alias :old_attr_reader :attr_reader
  def attr_reader(*attributes)
    add_annotations_to_multiple(*attributes)
    old_attr_reader(*attributes)
  end

  alias :old_attr_accessor :attr_accessor
  def attr_accessor(*attributes)
    add_annotations_to_multiple(*attributes)
    old_attr_accessor(*attributes)
  end

  private def add_annotations_to_multiple(*attributes)
    annotations = get_annotations
    attributes.each do |attribute|
      apply_annotations self, annotations, attribute.to_s
    end
  end
end
