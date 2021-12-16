package com.github.jxen.svg.style;

import com.github.jxen.svg.api.SvgException;

/**
 * {@code Visibility} enum represents visibility style item.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public enum Visibility {

  /**
   * Visible.
   */
  VISIBLE("visible"),

  /**
   * Hidden.
   */
  HIDDEN("hidden"),

  /**
   * Collapse.
   */
  COLLAPSE("collapse");

  private final String name;

  Visibility(String name) {
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
  public static Visibility parse(String value) {
    for (Visibility display : values()) {
      if (display.name.equals(value)) {
        return display;
      }
    }
    throw new SvgException("Unsupported display type");
  }
}
