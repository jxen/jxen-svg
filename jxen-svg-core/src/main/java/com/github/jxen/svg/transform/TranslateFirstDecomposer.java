package com.github.jxen.svg.transform;

import com.github.jxen.math.linear.Matrix;
import java.util.Arrays;

/**
 * {@code TranslateFirstDecomposer} class is responsible for affine transformation decomposition.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class TranslateFirstDecomposer extends AbstractTranslateDecomposer {

  /*
  @Override
  public TranslateFirstDecomposition decompose(Transform transform) {
    Matrix m = transform.toMatrix();
    double alpha = m.isZero(0, 0) ? Math.PI / 2 : Math.atan(m.get(1, 0) / m.get(0, 0));
    double det = m.get(0, 0) * m.get(1, 1) - m.get(0, 1) * m.get(1, 0);
    double hx = (m.get(0, 0) * m.get(0, 1) + m.get(1, 0) * m.get(1, 1)) / det;
    double sx = m.isZero(0, 0) ? m.get(1, 0) / Math.sin(alpha) : m.get(0, 0) / Math.cos(alpha);
    double sy = sx * det / (m.get(0, 0) * m.get(0, 0) + m.get(1, 0) * m.get(1, 0));
    Transform scale = Transform.scale(sx, sy);
    Transform skewX = Transform.skewX(Math.toDegrees(Math.atan(hx)));
    Transform rotate = Transform.rotate(Math.toDegrees(alpha));
    double detX = m.get(1, 1) * m.get(0, 2) - m.get(0, 1) * m.get(1, 2);
    double tx = detX / det;
    double detY = m.get(0, 0) * m.get(1, 2) - m.get(1, 0) * m.get(0, 2);
    double ty = detY / det;
    Transform translate = Transform.translate(tx, ty);
    return new TranslateFirstDecomposition(Arrays.asList(translate, scale, skewX, rotate));
  }
  */

  @Override
  TransformDecomposition addTranslate(Matrix matrix, Transform scale, Transform skewX, Transform rotate) {
    double det = matrix.get(0, 0) * matrix.get(1, 1) - matrix.get(0, 1) * matrix.get(1, 0);
    double detX = matrix.get(1, 1) * matrix.get(0, 2) - matrix.get(0, 1) * matrix.get(1, 2);
    double tx = detX / det;
    double detY = matrix.get(0, 0) * matrix.get(1, 2) - matrix.get(1, 0) * matrix.get(0, 2);
    double ty = detY / det;
    Transform translate = Transform.translate(tx, ty);
    return new TranslateFirstDecomposition(Arrays.asList(translate, scale, skewX, rotate));
  }
}
