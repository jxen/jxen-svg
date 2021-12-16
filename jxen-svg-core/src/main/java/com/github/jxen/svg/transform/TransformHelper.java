package com.github.jxen.svg.transform;

import com.github.jxen.math.linear.Matrix;
import com.github.jxen.svg.format.FormatHelper;
import java.util.List;
import java.util.function.Predicate;

interface TransformHelper extends Predicate<Matrix> {

  Transform create(List<Double> values);

  String toString(Transform transform);

  default FormatHelper format() {
    return new FormatHelper("#.##");
  }
}
