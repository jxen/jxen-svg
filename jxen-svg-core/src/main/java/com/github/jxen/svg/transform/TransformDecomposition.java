package com.github.jxen.svg.transform;

import java.util.Collections;
import java.util.List;

/**
 * {@code TransformDecomposition} class contains affine transformation decomposition.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public abstract class TransformDecomposition {

  private final List<Transform> transforms;

  /**
   * Initializes with given values.
   *
   * @param transforms transforms
   */
  public TransformDecomposition(List<Transform> transforms) {
    this.transforms = Collections.unmodifiableList(transforms);
  }

  /**
   * Provides transformations.
   *
   * @return the transforms
   */
  public final List<Transform> getTransforms() {
    return transforms;
  }

  /**
   * Provides scale.
   *
   * @return scale part
   */
  public final Transform scale() {
    return transforms.get(scaleIndex());
  }

  abstract int scaleIndex();

  /**
   * Provides skew X.
   *
   * @return skewX part
   */
  public final Transform skewX() {
    return transforms.get(skewXIndex());
  }

  abstract int skewXIndex();

  /**
   * Provides rotate.
   *
   * @return rotate part
   */
  public final Transform rotate() {
    return transforms.get(rotateIndex());
  }

  abstract int rotateIndex();

  /**
   * Provides translate.
   *
   * @return translate part
   */
  public final Transform translate() {
    return transforms.get(translateIndex());
  }

  abstract int translateIndex();
}
