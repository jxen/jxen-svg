package com.github.jxen.svg.parser;

import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.style.Style;

/**
 * {@code StyleParser} class is parser of {@link Style} objects.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class StyleParser implements Parser<Style> {

  @Override
  public Style parse(String value) {
    Style style = new Style();
    for (String item : value.split(";")) {
      int index = item.indexOf(':');
      if (index < 1) {
        throw new SvgException("Unknown style item for: " + item);
      }
      style.parseItem(item.substring(0, index), item.substring(index + 1));
    }
    return style;
  }
}
