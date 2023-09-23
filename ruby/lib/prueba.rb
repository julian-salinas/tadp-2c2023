class Prueba

  def materia
    :tadp
  end
end

require_relative 'tag'
require_relative 'helpers/alumno'

unTag = Tag
  .with_label('alumno')
  .with_attribute('nombre', 'Mati')
  .with_attribute('legajo', '123456-7')
  .with_attribute('edad', 27)
  .with_child(
    Tag
      .with_label('telefono')
      .with_child('12345678')
  )
  .with_child(
    Tag
      .with_label('estado')
      .with_child(
        Tag
          .with_label('value')
          .with_child('regular')
      )
  )
  .with_child(Tag.with_label('no_children'))
  .xml
