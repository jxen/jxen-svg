package com.github.jxen.svg.format;

import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.model.geometry.Ellipse;
import com.github.jxen.svg.model.geometry.Point;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * {@code FormatHelper} class helps to build formatted data.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class FormatHelper {

	private static final String ZERO = "0";

	private final DecimalFormat decimalFormat;

	/**
	 * @param format format for numbers
	 */
	public FormatHelper(String format) {
		this.decimalFormat = new DecimalFormat(format);
		this.decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
	}

	/**
	 * @param point      point
	 * @param appendable appendable
	 * @return current instance of builder
	 */
	public Appendable add(Point point, Appendable appendable) {
		add(point.getX(), appendable);
		comma(appendable);
		add(point.getY(), appendable);
		return appendable;
	}

	/**
	 * @param ellipse    ellipse
	 * @param appendable appendable
	 * @return current instance of builder
	 */
	public Appendable add(Ellipse ellipse, Appendable appendable) {
		add(ellipse.getRx(), appendable);
		comma(appendable);
		add(ellipse.getRy(), appendable);
		space(appendable);
		add(ellipse.getAngle(), appendable);
		return appendable;
	}

	/**
	 * @param value      String value
	 * @param appendable appendable
	 * @return current instance of builder
	 */
	public Appendable add(String value, Appendable appendable) {
		try {
			return appendable.append(value);
		} catch (IOException e) {
			throw new SvgException("Error formatting data", e);
		}
	}

	/**
	 * @param value      double value
	 * @param appendable appendable
	 * @return current instance of builder
	 */
	public Appendable add(double value, Appendable appendable) {
		String format = decimalFormat.format(value);
		if ("-0".equals(format)) {
			format = ZERO;
		}
		return add(format, appendable);
	}

	/**
	 * @param value      boolean value
	 * @param appendable appendable
	 * @return current instance of builder
	 */
	public Appendable add(boolean value, Appendable appendable) {
		return add(value ? "1" : ZERO, appendable);
	}

	/**
	 * @param appendable appendable
	 * @return current instance of builder
	 */
	public Appendable comma(Appendable appendable) {
		return add(",", appendable);
	}

	/**
	 * @param appendable appendable
	 * @return current instance of builder
	 */
	public Appendable space(Appendable appendable) {
		return add(" ", appendable);
	}
}
