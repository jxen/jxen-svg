package com.github.jxen.svg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.jxen.svg.model.type.Length;
import org.junit.jupiter.api.Test;

class RectTest {

  @Test
  void testCopy() {
    Rect rect = getRect();
    Rect copy = rect.copy();
    assertEquals(0.0, copy.getX().getValue());
    assertEquals(0.0, copy.getY().getValue());
    assertEquals(100.0, copy.getWidth().getValue());
    assertEquals(100.0, copy.getHeight().getValue());
    assertEquals(0.0, copy.getRx().getValue());
    assertEquals(0.0, copy.getRy().getValue());
    assertEquals("1", copy.getId());
  }

  static Rect getRect() {
    Rect rect = new Rect();
    rect.setX(new Length(0));
    rect.setY(new Length(0));
    rect.setWidth(new Length(100));
    rect.setHeight(new Length(100));
    rect.setRx(new Length(0));
    rect.setRy(new Length(0));
    rect.setId("1");
    return rect;
  }
}
