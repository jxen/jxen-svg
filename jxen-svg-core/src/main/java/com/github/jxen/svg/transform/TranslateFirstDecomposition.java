package com.github.jxen.svg.transform;

import java.util.List;

/**
 * {@code TransformDecomposition} class contains affine transformation decomposition (translate transform last).
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class TranslateFirstDecomposition extends TransformDecomposition {

  private static final int TRANSLATE = 0;
  private static final int SCALE = 1;
  private static final int SKEW_X = 2;
  private static final int ROTATE = 3;

  /**
   * Initializes with given values.
   *
   * @param transforms transforms
   */
  public TranslateFirstDecomposition(List<Transform> transforms) {
    super(transforms);
  }

  @Override
  int scaleIndex() {
    return SCALE;
  }

  @Override
  int skewXIndex() {
    return SKEW_X;
  }

  @Override
  int rotateIndex() {
    return ROTATE;
  }

  @Override
  int translateIndex() {
    return TRANSLATE;
  }
}
