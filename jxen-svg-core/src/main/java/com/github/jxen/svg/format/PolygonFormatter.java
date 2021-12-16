package com.github.jxen.svg.format;

import com.github.jxen.svg.model.geometry.Point;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code PathFormatter} class is responsible for polygon {@link Point} objects formatting.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class PolygonFormatter implements Formatter {

  private final String format;

  /**
   * Initializes with given value.
   *
   * @param format numeric format
   */
  public PolygonFormatter(String format) {
    this.format = format;
  }

  @Override
  public String format(Object value) {
    @SuppressWarnings("unchecked")
    List<Point> points = (List<Point>) value;
    return points.stream().map(p -> p.format(format)).collect(Collectors.joining(" "));
  }
}
