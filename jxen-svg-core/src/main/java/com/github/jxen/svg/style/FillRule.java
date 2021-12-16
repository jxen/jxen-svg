package com.github.jxen.svg.style;

import com.github.jxen.svg.api.SvgException;

/**
 * {@code FillRule} enum represents fillrule style item.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public enum FillRule {

  /**
   * Non zero.
   */
  NONZERO("nonzero"),

  /**
   * Even odd.
   */
  EVENODD("evenodd");

  private final String name;

  FillRule(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  /**
   * Provides instance.
   *
   * @param value value
   * @return parsed object
   */
  public static FillRule parse(String value) {
    for (FillRule display : values()) {
      if (display.name.equals(value)) {
        return display;
      }
    }
    throw new SvgException("Unsupported display type");
  }
}
