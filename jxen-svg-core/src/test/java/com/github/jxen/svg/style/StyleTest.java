package com.github.jxen.svg.style;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.model.type.Color;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.model.type.Percent;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class StyleTest {

  @Test
  void testSetStrokeWidth() {
    Style style = new Style();
    style.setStrokeWidth(null);
    assertEquals(Color.NONE, style.getStroke());
  }

  @Test
  void testGetNull() {
    Style style = new Style();
    style.set("unknown", Color.BLACK);
    assertNull(style.get("unknown"));
  }

  @Test
  void testGetSet() {
    Style style = new Style();
    style.set("color", Color.BLACK);
    assertEquals(Color.BLACK, style.get("color"));
  }

  @Test
  void testParseItem() {
    Style style = new Style();
    style.parseItem("color", "#f00");
    assertEquals(Color.parse("red"), style.getColor());
  }

  @Test
  void testParseItemUnsupported() {
    Style style = new Style();
    style.parseItem("unsupported", "value");
    assertEquals("unsupported:value", style.toString());
  }

  @Test
  void testParseItemFailure1() {
    Style style = new Style();
    assertThrows(SvgException.class, () -> style.parseItem(null, ""));
  }

  @Test
  void testParseItemFailure2() {
    Style style = new Style();
    assertThrows(SvgException.class, () -> style.parseItem("", null));
  }

  @Test
  void testParseItemFailure3() {
    Style style = new Style();
    assertThrows(SvgException.class, () -> style.parseItem("color", "unknown"));
  }

  @Test
  void testToStringEnpty() {
    Style style = new Style();
    assertEquals("", style.toString());
  }

  @Test
  void testToString() {
    Style style = new Style();
    style.setColor(Color.BLACK);
    assertEquals("color:black", style.toString());
  }

  @Test
  void testCopy() {
    Style style = Style.getDefault().copy();
    assertEquals(Color.NONE, style.getStroke());
  }

  @Test
  void testIsNameSupported() {
    assertFalse(Style.isNameSupported("test"));
  }

  @Test
  void testNames() {
    assertFalse(Style.names().collect(Collectors.toSet()).contains("unknown"));
  }

  @Test
  void testIsEmptyNull() {
    assertTrue(Style.isEmpty(null));
  }

  @Test
  void testIsEmpty() {
    assertTrue(Style.isEmpty(new Style()));
  }

  @Test
  void testIsEmptyFalse() {
    Style style = new Style();
    style.setColor(Color.BLACK);
    assertFalse(Style.isEmpty(style));
  }

  @Test
  void testProperties() {
    Style style = new Style();
    style.setDisplay(Display.INLINE);
    assertEquals(Display.INLINE, style.getDisplay());
    style.setEnableBackground("none");
    assertEquals("none", style.getEnableBackground());
    style.setFill(Color.BLACK);
    assertEquals(Color.BLACK, style.getFill());
    style.setFillOpacity(new Percent(1));
    assertEquals(1.0, style.getFillOpacity().getValue());
    style.setFillRule(FillRule.NONZERO);
    assertEquals(FillRule.NONZERO, style.getFillRule());
    style.setMarker("none");
    assertEquals("none", style.getMarker());
    style.setMarkerEnd("none");
    assertEquals("none", style.getMarkerEnd());
    style.setMarkerMid("none");
    assertEquals("none", style.getMarkerMid());
    style.setMarkerStart("none");
    assertEquals("none", style.getMarkerStart());
    style.setOpacity(new Percent(1));
    assertEquals(1.0, style.getOpacity().getValue());
    style.setOverflow(Overflow.AUTO);
    assertEquals(Overflow.AUTO, style.getOverflow());
    style.setStroke(Color.BLACK);
    assertEquals(Color.BLACK, style.getStroke());
    style.setStrokeDasharray(DashArray.DEFAULT);
    assertEquals(DashArray.DEFAULT, style.getStrokeDasharray());
    style.setStrokeDashoffset(new Length(1));
    assertEquals(new Length(1), style.getStrokeDashoffset());
    style.setStrokeLinecap(Linecap.ROUND);
    assertEquals(Linecap.ROUND, style.getStrokeLinecap());
    style.setStrokeLinejoin(Linejoin.MITER);
    assertEquals(Linejoin.MITER, style.getStrokeLinejoin());
    style.setStrokeMiterlimit(1.0);
    assertEquals(1.0, style.getStrokeMiterlimit());
    style.setStrokeOpacity(new Percent(1));
    assertEquals(new Percent(1), style.getStrokeOpacity());
    style.setStrokeWidth(new Length(1));
    assertEquals(new Length(1), style.getStrokeWidth());
    style.setVisibility(Visibility.VISIBLE);
    assertEquals(Visibility.VISIBLE, style.getVisibility());
  }
}
