package com.github.jxen.svg.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.model.Svg;
import org.junit.jupiter.api.Test;

class SvgReaderTest {

  @Test
  void testRead() {
    SvgReader reader = new SvgReader();
    Element<?> element = reader.read(getClass().getResourceAsStream("/test.svg"));
    assertTrue(element instanceof Svg);
  }
}
