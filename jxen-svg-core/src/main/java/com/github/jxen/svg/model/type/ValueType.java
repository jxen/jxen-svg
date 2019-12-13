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
	STYLE(new StyleParser(), Object::toString),

	/**
	 * Transform.
	 */
	TRANSFORM(new TransformParser(), new TransformFormatter()),

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
	PATH(new PathParser(), new PathFormatter()),

	/**
	 * Polygon.
	 */
	POLYGON(new PolygonParser(), new PolygonFormatter(Constants.DEFAULT_FORMAT)),

	/**
	 * View box.
	 */
	VIEW_BOX(new ViewBoxParser(), v -> ((ViewBox) v).format(Constants.DEFAULT_FORMAT));

	private final Parser<?> parser;
	private final Formatter formatter;

	ValueType(Parser<?> parser, Formatter formatter) {
		this.parser = parser;
		this.formatter = formatter;
	}

	ValueType(Parser<?> parser) {
		this(parser, null);
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
	}
}
