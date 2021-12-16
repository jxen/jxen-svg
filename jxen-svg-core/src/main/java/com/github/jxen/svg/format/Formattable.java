package com.github.jxen.svg.format;

/**
 * {@code Formattable} interface is a contract for object that can be formatted.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public interface Formattable {

  /**
   * Formats object using given format parameter.
   *
   * @param format format
   * @return formatted value
   */
  String format(String format);
}
