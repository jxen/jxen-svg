package com.github.jxen.svg.transform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.jxen.math.linear.Matrix;
import org.junit.jupiter.api.Test;

class TranslateFirstDecomposerTest {

  private final TranslateFirstDecomposer decomposer = new TranslateFirstDecomposer();

  @Test
  void testIdentity() {
    Transform transform = Transform.of(Matrix.identity(3));
    TransformDecomposition decomposition = decomposer.decompose(transform);
    assertTrue(decomposition.scale().toMatrix().isIdentity());
    assertTrue(decomposition.skewX().toMatrix().isIdentity());
    assertTrue(decomposition.rotate().toMatrix().isIdentity());
    assertTrue(decomposition.translate().toMatrix().isIdentity());
  }

  @Test
  void testScale() {
    Transform transform = Transform.scale(2, 2);
    TransformDecomposition decomposition = decomposer.decompose(transform);
    Transform scale = decomposition.scale();
    assertFalse(scale.toMatrix().isIdentity());
    assertEquals(transform, scale);
    assertTrue(decomposition.skewX().toMatrix().isIdentity());
    assertTrue(decomposition.rotate().toMatrix().isIdentity());
    assertTrue(decomposition.translate().toMatrix().isIdentity());
  }

  @Test
  void testSkewX() {
    Transform transform = Transform.skewX(45);
    TransformDecomposition decomposition = decomposer.decompose(transform);
    assertTrue(decomposition.scale().toMatrix().isIdentity());
    Transform skewX = decomposition.skewX();
    assertFalse(skewX.toMatrix().isIdentity());
    assertEquals(transform, skewX);
    assertTrue(decomposition.rotate().toMatrix().isIdentity());
    assertTrue(decomposition.translate().toMatrix().isIdentity());
  }

  @Test
  void testRotate() {
    Transform transform = Transform.rotate(45);
    TransformDecomposition decomposition = decomposer.decompose(transform);
    assertTrue(decomposition.scale().toMatrix().isIdentity());
    assertTrue(decomposition.skewX().toMatrix().isIdentity());
    Transform rotate = decomposition.rotate();
    assertFalse(rotate.toMatrix().isIdentity());
    assertEquals(transform, rotate);
    assertTrue(decomposition.translate().toMatrix().isIdentity());
  }

  @Test
  void testTranslate() {
    Transform transform = Transform.translate(10, 10);
    TransformDecomposition decomposition = decomposer.decompose(transform);
    assertTrue(decomposition.scale().toMatrix().isIdentity());
    assertTrue(decomposition.skewX().toMatrix().isIdentity());
    assertTrue(decomposition.rotate().toMatrix().isIdentity());
    Transform translate = decomposition.translate();
    assertFalse(translate.toMatrix().isIdentity());
    assertEquals(transform, translate);
  }

  @Test
  void testScaleAndSkewX() {
    Transform transform = Transform.of(2, 0, 2, 2, 0, 0);
    TransformDecomposition decomposition = decomposer.decompose(transform);
    Transform scale = decomposition.scale();
    assertFalse(scale.toMatrix().isIdentity());
    assertEquals(Transform.scale(2, 2), scale);
    Transform skewX = decomposition.skewX();
    assertFalse(skewX.toMatrix().isIdentity());
    assertEquals(Transform.skewX(45), skewX);
    assertTrue(decomposition.rotate().toMatrix().isIdentity());
    assertTrue(decomposition.translate().toMatrix().isIdentity());
  }

  @Test
  void testScaleAndRotate() {
    Transform transform = Transform.of(0, 2, -2, 0, 0, 0);
    TransformDecomposition decomposition = decomposer.decompose(transform);
    Transform scale = decomposition.scale();
    assertFalse(scale.toMatrix().isIdentity());
    assertEquals(Transform.scale(2, 2), scale);
    assertTrue(decomposition.skewX().toMatrix().isIdentity());
    Transform rotate = decomposition.rotate();
    assertFalse(rotate.toMatrix().isIdentity());
    assertEquals(Transform.rotate(90), rotate);
    assertTrue(decomposition.translate().toMatrix().isIdentity());
  }

  @Test
  void testScaleAndTranslate() {
    Transform transform = Transform.of(2, 0, 0, 2, 10, 10);
    TransformDecomposition decomposition = decomposer.decompose(transform);
    Transform scale = decomposition.scale();
    assertFalse(scale.toMatrix().isIdentity());
    assertEquals(Transform.scale(2, 2), scale);
    assertTrue(decomposition.skewX().toMatrix().isIdentity());
    assertTrue(decomposition.rotate().toMatrix().isIdentity());
    Transform translate = decomposition.translate();
    assertFalse(translate.toMatrix().isIdentity());
    assertEquals(Transform.translate(5, 5), translate);
  }

  @Test
  void testSkewXAndRotate() {
    float sqrt2 = (float) Math.sqrt(2);
    Transform transform = Transform.of(1 / sqrt2, 1 / sqrt2, 0, sqrt2, 0, 0);
    TransformDecomposition decomposition = decomposer.decompose(transform);
    assertEquals("scale(1)", decomposition.scale().toString());
    Transform skewX = decomposition.skewX();
    assertFalse(skewX.toMatrix().isIdentity());
    assertEquals(Transform.skewX(45), skewX);
    Transform rotate = decomposition.rotate();
    assertFalse(rotate.toMatrix().isIdentity());
    assertEquals(Transform.rotate(45), rotate);
    assertTrue(decomposition.translate().toMatrix().isIdentity());
  }

  @Test
  void testSkewXAndTranslate() {
    Transform transform = Transform.of(1, 0, 1, 1, 10, 10);
    TransformDecomposition decomposition = decomposer.decompose(transform);
    assertTrue(decomposition.scale().toMatrix().isIdentity());
    Transform skewX = decomposition.skewX();
    assertFalse(skewX.toMatrix().isIdentity());
    assertEquals(Transform.skewX(45), skewX);
    assertTrue(decomposition.rotate().toMatrix().isIdentity());
    Transform translate = decomposition.translate();
    assertFalse(translate.toMatrix().isIdentity());
    assertEquals(Transform.translate(0, 10), translate);
  }

  @Test
  void testRotateAndTranslate() {
    Transform transform = Transform.of(0, 1, -1, 0, -10, 10);
    TransformDecomposition decomposition = decomposer.decompose(transform);
    assertTrue(decomposition.scale().toMatrix().isIdentity());
    assertTrue(decomposition.skewX().toMatrix().isIdentity());
    Transform rotate = decomposition.rotate();
    assertFalse(rotate.toMatrix().isIdentity());
    assertEquals(Transform.rotate(90), rotate);
    Transform translate = decomposition.translate();
    assertFalse(translate.toMatrix().isIdentity());
    assertEquals(Transform.translate(10, 10), translate);
  }
}
