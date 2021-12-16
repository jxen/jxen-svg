package com.github.jxen.svg.transform;

import com.github.jxen.math.linear.Matrix;
import com.github.jxen.svg.api.SvgException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * {@code TransformType} enum contains all supported transform types.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public enum TransformType {

  /**
   * General transform.
   */
  MATRIX("matrix", new DefaultHelper(), 6),

  /**
   * Translate transform.
   */
  TRANSLATE("translate", new TranslateHelper(), 2, 1),

  /**
   * Scale transform.
   */
  SCALE("scale", new ScaleHelper(), 2, 1),

  /**
   * Rotate transform.
   */
  ROTATE("rotate", new RotateHelper(), 3, 1),

  /**
   * Skew Y transform.
   */
  SKEW_X("skewX", new SkewXHelper(), 1),

  /**
   * Skew Y transform.
   */
  SKEW_Y("skewY", new SkewYHelper(), 1);

  private final String name;
  private final TransformHelper helper;
  private final Set<Integer> argumentCount;

  TransformType(String name, TransformHelper helper, int... arguments) {
    this.name = name;
    this.helper = helper;
    argumentCount = new HashSet<>();
    for (int i : arguments) {
      argumentCount.add(i);
    }
  }

  /**
   * Converts to String.
   *
   * @param transform transform
   * @return string representation of transform
   */
  public String toString(Transform transform) {
    return String.format("%s(%s)", name, helper.toString(transform));
  }

  /**
   * Creates transform for given name and matrix values.
   *
   * @param name   name
   * @param values value
   * @return {@link Transform}
   */
  public static Transform create(String name, List<Double> values) {
    TransformType type = of(name);
    if (!type.argumentCount.contains(values.size())) {
      throw new SvgException(String.format("Incorrect number of arguments for '%s'", name));
    }
    return type.helper.create(values);
  }

  /**
   * Looks for transform type for given name.
   *
   * @param name name
   * @return transform type
   */
  public static TransformType of(String name) {
    for (TransformType type : values()) {
      if (type.name.equals(name)) {
        return type;
      }
    }
    throw new SvgException("Unsupported transformation " + name);
  }

  /**
   * Looks for transform type for given matrix.
   *
   * @param matrix matrix
   * @return transform type
   */
  public static TransformType of(Matrix matrix) {
    Transform.ensure(matrix);
    TransformType type = TransformType.MATRIX;
    for (TransformType t : values()) {
      if (t.helper.test(matrix)) {
        type = t;
      }
    }
    return type;
  }
}
