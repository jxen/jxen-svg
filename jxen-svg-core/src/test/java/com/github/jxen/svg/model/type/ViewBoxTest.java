package com.github.jxen.svg.model.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class ViewBoxTest {

  @Test
  void testHashCode() {
    assertEquals(-1020389503, new ViewBox(0, 0, 100, 100).hashCode());
  }

  @Test
  void testEqualsCase1() {
    ViewBox viewBox = new ViewBox(0, 0, 100, 100);
    assertEquals(viewBox, viewBox);
  }

  @Test
  void testEqualsCase2() {
    ViewBox viewBox = new ViewBox(0, 0, 100, 100);
    assertNotEquals("", viewBox);
  }

  @Test
  void testEqualsCase3() {
    ViewBox viewBox = new ViewBox(0, 0, 100, 100);
    assertEquals(new ViewBox(0, 0, 100, 100), viewBox);
  }

  @Test
  void testEqualsCase4() {
    ViewBox viewBox = new ViewBox(0, 0, 100, 100);
    assertNotEquals(new ViewBox(10, 0, 100, 100), viewBox);
  }

  @Test
  void testToString() {
    assertEquals("0 0 100 100", new ViewBox(0, 0, 100, 100).toString());
  }
}
