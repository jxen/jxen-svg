package com.github.jxen.svg.style;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.jxen.svg.api.SvgException;
import org.junit.jupiter.api.Test;

class VisibilityTest {

  @Test
  void testParse() {
    assertEquals(Visibility.VISIBLE, Visibility.parse("visible"));
  }

  @Test
  void testParseFailure() {
    assertThrows(SvgException.class, () -> Visibility.parse("error"));
  }
}
