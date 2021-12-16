package com.github.jxen.svg.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.model.geometry.Point;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PolygonParserTest {

  @Test
  void testParse() {
    List<Point> points = new PolygonParser().parse("1e0,0 0.5,2 +2,-3E+0");
    assertEquals(Arrays.asList(new Point(1, 0), new Point(0.5, 2), new Point(2, -3)), points);
  }

  @Test
  void testParseFailure1() {
    assertThrows(SvgException.class, () -> new PolygonParser().parse("a"));
  }

  @Test
  void testParseFailure2() {
    assertThrows(SvgException.class, () -> new PolygonParser().parse("1ee1"));
  }
}
