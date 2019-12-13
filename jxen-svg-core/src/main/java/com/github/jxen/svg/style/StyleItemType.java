package com.github.jxen.svg.style;

import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.format.Formatter;
import com.github.jxen.svg.model.type.Color;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.model.type.Percent;
import com.github.jxen.svg.parser.Parser;
import java.util.Optional;

/**
 * {@code StyleItemType} enum contains all supported style item types.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public enum StyleItemType {

	/**
	 * Display type.
	 */
	DISPLAY(Display.INLINE, Display::parse),

	/**
	 * Fill color type.
	 */
	FILL_COLOR(Color.BLACK, Color::parse, v -> ((Color) v).format("")),

	/**
	 * Fill rule type.
	 */
	FILL_RULE(FillRule.NONZERO, FillRule::parse),

	/**
	 * Opacity type.
	 */
	OPACITY(new Percent(1), Percent::parse, v -> ((Percent) v).format(Constants.LONG_FORMAT)),

	/**
	 * Overflow type.
	 */
	OVERFLOW(Overflow.AUTO, Overflow::parse),

	/**
	 * String type.
	 * TODO: temporary solution
	 */
	STRING(Constants.NONE, v -> Optional.ofNullable(v).orElse(Constants.NONE)),

	/**
	 * Stroke color type.
	 */
	STROKE_COLOR(Color.NONE, Color::parse, v -> ((Color) v).format("")),

	/**
	 * Stroke dash array type.
	 */
	STROKE_DASHARRAY(DashArray.DEFAULT, DashArray::parse, v -> ((DashArray) v).format(Constants.LONG_FORMAT)),

	/**
	 * Stroke dash offset type.
	 */
	STROKE_DASHOFFSET(new Length(0), Length::parse, v -> ((Length) v).format(Constants.LONG_FORMAT)),

	/**
	 * Stroke linecap type.
	 */
	STROKE_LINECAP(Linecap.BUTT, Linecap::parse),

	/**
	 * Stroke line join type.
	 */
	STROKE_LINEJOIN(Linejoin.MITER, Linejoin::parse),

	/**
	 * Stroke miter limit type.
	 */
	STROKE_MITERLIMIT(4.0, Double::parseDouble,
			v -> new FormatHelper("#.##").add((Double) v, new StringBuilder()).toString()),

	/**
	 * Stroke width type.
	 */
	STROKE_WIDTH(new Length(1), Length::parse, v -> ((Length) v).format(Constants.LONG_FORMAT)),

	/**
	 * Visibility type.
	 */
	VISIBILITY(Visibility.VISIBLE, Visibility::parse);

	private final Object defaultValue;
	private final Parser<?> parser;
	private final Formatter formatter;

	StyleItemType(Object defaultValue, Parser<?> parser, Formatter formatter) {
		this.defaultValue = defaultValue;
		this.parser = parser;
		this.formatter = formatter;
	}

	StyleItemType(Object defaultValue, Parser<?> parser) {
		this(defaultValue, parser, Object::toString);
	}

	/**
	 * @return default value of the type
	 */
	public Object getDefault() {
		return defaultValue;
	}

	/**
	 * @param value value
	 * @return parsed object
	 */
	public Object parse(String value) {
		return Optional.ofNullable(value).map(parser::parse).orElse(null);
	}

	/**
	 * @param value value
	 * @return formatted value
	 */
	public String format(Object value) {
		return formatter.format(value);
	}

	private static class Constants {
		private static final String NONE = "none";
		private static final String LONG_FORMAT = "#.###";
	}
}
