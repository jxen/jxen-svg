package com.github.jxen.svg.transform;

import com.github.jxen.math.linear.Matrix;
import com.github.jxen.svg.format.FormatHelper;
import java.util.List;

class TranslateHelper implements TransformHelper {

  @Override
  public Transform create(List<Double> values) {
    if (values.size() == 1) {
      return Transform.translate(values.get(0), 0);
    }
    return Transform.translate(values.get(0), values.get(1));
  }

  @Override
  public boolean test(Matrix matrix) {
    if (matrix.isZero(0, 2) && matrix.isZero(1, 2)) {
      return false;
    }
    return matrix.isOne(0, 0) && matrix.isZero(1, 0) && matrix.isZero(0, 1) && matrix.isOne(1, 1);
  }

  @Override
  public String toString(Transform transform) {
    Matrix m = transform.toMatrix();
    FormatHelper helper = format();
    StringBuilder builder = new StringBuilder();
    helper.add(m.get(0, 2), builder);
    if (!m.isZero(1, 2)) {
      helper.comma(builder);
      helper.add(m.get(1, 2), builder);
    }
    return builder.toString();
  }
}
