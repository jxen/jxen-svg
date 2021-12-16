package com.github.jxen.svg.optimizer;

import com.github.jxen.math.linear.Matrix;
import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.style.Style;
import com.github.jxen.svg.transform.Transform;
import java.util.Objects;
import java.util.Optional;

abstract class TransformApplier<T extends Element<T>> {

  void apply(T element) {
    final int matrixSize = 3;
    Transform transform = Transform.of(Matrix.identity(matrixSize));
    if (Objects.nonNull(element.getTransform())) {
      for (Transform t : element.getTransform()) {
        transform = transform.multiply(t);
      }
    }
    transform(element, transform);
    updateStyle(element, transform);
  }

  abstract void transform(T element, Transform transform);

  private void updateStyle(T element, Transform transform) {
    if (transform.toMatrix().isOrthogonal()) {
      return;
    }
    double factor = Math.abs(Math.sqrt(transform.toMatrix().determinant()));
    Style effective = element.getEffective();
    Length value = Optional.ofNullable(effective.getStrokeWidth()).orElse(new Length(1)).multiply(factor);
    Length width = value.getValue() <= 0.0 ? null : value;
    effective.setStrokeWidth(width);
    Style style = Optional.ofNullable(element.getStyle()).orElse(element.getParent().getEffective().copy());
    style.setStrokeWidth(width);
    element.setStyle(style);
  }
}
