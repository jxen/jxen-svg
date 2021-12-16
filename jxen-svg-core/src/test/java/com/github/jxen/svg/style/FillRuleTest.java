package com.github.jxen.svg.style;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.jxen.svg.api.SvgException;
import org.junit.jupiter.api.Test;

class FillRuleTest {

  @Test
  void testParse() {
    assertEquals(FillRule.NONZERO, FillRule.parse("nonzero"));
  }

  @Test
  void testParseFailure() {
    assertThrows(SvgException.class, () -> FillRule.parse("error"));
  }
}
