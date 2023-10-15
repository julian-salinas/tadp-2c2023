def fold_l(ray, base, funcky)
  return base if ray.empty?
  base = funcky.call(base,ray.first)
  fold_l(ray.last(ray.length-1),base,funcky)
end