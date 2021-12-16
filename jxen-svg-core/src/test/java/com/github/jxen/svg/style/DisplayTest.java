package com.github.jxen.svg.style;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.jxen.svg.api.SvgException;
import org.junit.jupiter.api.Test;

class DisplayTest {

  @Test
  void testParse() {
    assertEquals(Display.INLINE, Display.parse("inline"));
  }

  @Test
  void testParseFailure() {
    assertThrows(SvgException.class, () -> Display.parse("error"));
  }
}
