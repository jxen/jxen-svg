package com.github.jxen.svg.transform;

import com.github.jxen.math.linear.Matrix;
import com.github.jxen.svg.format.FormatHelper;
import java.util.List;

class SkewXHelper implements TransformHelper {

  @Override
  public Transform create(List<Double> values) {
    return Transform.skewX(values.get(0));
  }

  @Override
  public boolean test(Matrix matrix) {
    if (!matrix.isZero(0, 2) || !matrix.isZero(1, 2)) {
      return false;
    }
    return matrix.isOne(0, 0) && !matrix.isZero(0, 1) && matrix.isZero(1, 0) && matrix.isOne(1, 1);
  }

  @Override
  public String toString(Transform transform) {
    Matrix m = transform.toMatrix();
    double angle = Math.toDegrees(Math.atan(m.get(0, 1)));
    FormatHelper helper = format();
    StringBuilder builder = new StringBuilder();
    helper.add(angle, builder);
    return builder.toString();
  }
}
