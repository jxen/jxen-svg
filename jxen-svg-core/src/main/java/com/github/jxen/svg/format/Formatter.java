package com.github.jxen.svg.format;

/**
 * {@code Formatter} interface is a contract for object responsible for other objects formatting.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public interface Formatter {

  /**
   * Formats given object.
   *
   * @param value value
   * @return formatter value
   */
  String format(Object value);
}
