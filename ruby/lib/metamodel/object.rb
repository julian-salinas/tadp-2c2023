require_relative '../repository/annotations_repository'

class Object
  private def is_star_annotation?(str)
    str.start_with?("✨") && str.end_with?("✨")
  end

  private def annotation_name(str)
    if str.frozen?
      str = str.dup
    end
    str[1..-2]
  end

  def method_missing(name, *args, &block)
    if is_star_annotation? name
      AnnotationsRepository.push(annotation_name(name), *args, &block)
    else
      super
    end
  end
end
