package com.github.jxen.svg.style;

import com.github.jxen.svg.api.SvgException;

/**
 * {@code Linecap} enum represents linecap style item.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public enum Linecap {

  /**
   * Butt.
   */
  BUTT("butt"),

  /**
   * Round.
   */
  ROUND("round"),

  /**
   * Square.
   */
  SQUARE("square");

  private final String name;

  Linecap(String name) {
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
  public static Linecap parse(String value) {
    for (Linecap display : values()) {
      if (display.name.equals(value)) {
        return display;
      }
    }
    throw new SvgException("Unsupported linecap type");
  }
}
