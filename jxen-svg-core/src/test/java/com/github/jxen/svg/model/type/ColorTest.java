package com.github.jxen.svg.model.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.jxen.svg.api.SvgException;
import org.junit.jupiter.api.Test;

class ColorTest {

  @Test
  void testOf() {
    Color color = Color.of(255, 0, 0);
    assertEquals(Color.parse("red"), color);
  }

  @Test
  void testOfFailureCase1() {
    assertThrows(SvgException.class, () -> Color.of(-1, 0, 0));
  }

  @Test
  void testOfFailureCase2() {
    assertThrows(SvgException.class, () -> Color.of(1000, 0, 0));
  }

  @Test
  void testOfFailureCase3() {
    assertThrows(SvgException.class, () -> Color.of(0, -1, 0));
  }

  @Test
  void testOfFailureCase4() {
    assertThrows(SvgException.class, () -> Color.of(0,1000, 0));
  }

  @Test
  void testOfFailureCase5() {
    assertThrows(SvgException.class, () -> Color.of(0,0, -1));
  }

  @Test
  void testOfFailureCase6() {
    assertThrows(SvgException.class, () -> Color.of(0,0,1000));
  }

  @Test
  void testParse() {
    assertEquals(Color.parse("red"), Color.parse("red"));
  }

  @Test
  void testParseHexLong() {
    Color color = Color.parse("#ff0000");
    assertEquals(Color.parse("red"), color);
  }

  @Test
  void testParseHexShort() {
    Color color = Color.parse("#f00");
    assertEquals(Color.parse("red"), color);
  }

  @Test
  void testParseHexFailure() {
    assertThrows(SvgException.class, () -> Color.parse("#0"));
  }

  @Test
  void testParseRgb() {
    Color color = Color.parse("rgb(100%,0,0)");
    assertEquals(Color.parse("red"), color);
  }

  @Test
  void testParseRgbFailure() {
    assertThrows(SvgException.class, () -> Color.parse("rgb()"));
  }
}
