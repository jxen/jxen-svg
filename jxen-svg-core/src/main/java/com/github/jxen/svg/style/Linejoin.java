package com.github.jxen.svg.style;

import com.github.jxen.svg.api.SvgException;

/**
 * {@code Linejoin} enum represents linejoin style item.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public enum Linejoin {

  /**
   * Miter.
   */
  MITER("miter"),

  /**
   * Round.
   */
  ROUND("round"),

  /**
   * Bevel.
   */
  BEVEL("bevel");

  private final String name;

  Linejoin(String name) {
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
  public static Linejoin parse(String value) {
    for (Linejoin display : values()) {
      if (display.name.equals(value)) {
        return display;
      }
    }
    throw new SvgException("Unsupported linejoin type");
  }
}
