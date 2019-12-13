package com.github.jxen.svg.parser;

/**
 * {@code Parser} interface is general parser of some values used inside SVG elements.
 *
 * @author Denis Murashev
 *
 * @param <T> type of parsed object
 *
 * @since SVG Core 0.1
 */

public interface Parser<T> {

	/**
	 * Parses given string value into object of given type.
	 *
	 * @param value value
	 * @return parsed object
	 */
	T parse(String value);
}
