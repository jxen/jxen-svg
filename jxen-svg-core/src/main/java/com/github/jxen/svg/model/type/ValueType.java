package com.github.jxen.svg.model.type;

import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.format.Formatter;
import com.github.jxen.svg.format.PathFormatter;
import com.github.jxen.svg.format.PolygonFormatter;
import com.github.jxen.svg.format.TransformFormatter;
import com.github.jxen.svg.parser.Parser;
import com.github.jxen.svg.parser.PathParser;
import com.github.jxen.svg.parser.PolygonParser;
import com.github.jxen.svg.parser.StyleParser;
import com.github.jxen.svg.parser.TransformParser;
import com.github.jxen.svg.parser.ViewBoxParser;

/**
 * {@code ValueType} enumeration contains possible value types.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public enum ValueType {

  /**
   * Style.
   */
  STYLE(Constants.STYLE_PARSER, Object::toString),

  /**
   * Transform.
   */
  TRANSFORM(Constants.TRANSFORM_PARSER, new TransformFormatter()),

  /**
   * String.
   */
  STRING(String::trim, String::valueOf),

  /**
   * Number.
   */
  NUMBER(Double::parseDouble,
      v -> new FormatHelper(Constants.DEFAULT_FORMAT).add((Double) v, new StringBuilder()).toString()),

  /**
   * Length.
   */
  LENGTH(Length::parse, v -> ((Length) v).format("#.###")),

  /**
   * Path.
   */
  PATH(Constants.PATH_PARSER, new PathFormatter()),

  /**
   * Polygon.
   */
  POLYGON(Constants.POLYGON_PARSER, new PolygonFormatter(Constants.DEFAULT_FORMAT)),

  /**
   * View box.
   */
  VIEW_BOX(Constants.VIEWBOX_PARSER, v -> ((ViewBox) v).format(Constants.DEFAULT_FORMAT));

  private final Parser<?> parser;
  private final Formatter formatter;

  ValueType(Parser<?> parser, Formatter formatter) {
    this.parser = parser;
    this.formatter = formatter;
  }

  /**
   * Parses givan value.
   *
   * @param value value
   * @return parsed object
   */
  public Object parse(String value) {
    return parser.parse(value);
  }

  /**
   * Formats given object.
   *
   * @param value value
   * @return formatted object
   */
  public String format(Object value) {
    return formatter.format(value);
  }

  private static class Constants {
    private static final String DEFAULT_FORMAT = "#.##";

    private static final Parser<?> PATH_PARSER = new PathParser();
    private static final Parser<?> POLYGON_PARSER = new PolygonParser();
    private static final Parser<?> STYLE_PARSER = new StyleParser();
    private static final Parser<?> TRANSFORM_PARSER = new TransformParser();
    private static final Parser<?> VIEWBOX_PARSER = new ViewBoxParser();
  }
}
