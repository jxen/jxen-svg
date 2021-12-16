package com.github.jxen.svg.model.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.jxen.svg.transform.Transform;
import org.junit.jupiter.api.Test;

class EllipseTest {

  private static final float EPSILON = 1e-5f;

  @Test
  void testTranslate() {
    Ellipse ellipse = new Ellipse(20, 10, 0);
    Ellipse actual = ellipse.transform(Transform.translate(10, 10));
    assertEquals(ellipse, actual);
  }

  @Test
  void testScale() {
    Ellipse ellipse = new Ellipse(20, 10, 45);
    Ellipse actual = ellipse.transform(Transform.scale(2, 2));
    assertEquals(40, actual.getRx());
    assertEquals(20, actual.getRy());
  }

  @Test
  void testRotate() {
    Ellipse ellipse = new Ellipse(20, 10, 0);
    Ellipse actual = ellipse.transform(Transform.rotate(45));
    assertEquals(45, actual.getAngle(), EPSILON);
  }
}
