package com.github.jxen.svg.style;

import com.github.jxen.svg.api.SvgException;

/**
 * {@code Overflow} enum represents overflow style item.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public enum Overflow {

	/**
	 * Visible.
	 */
	VISIBLE("visible"),

	/**
	 * Hidden.
	 */
	HIDDEN("hidden"),

	/**
	 * Scroll.
	 */
	SCROLL("scroll"),

	/**
	 * Auto.
	 */
	AUTO("auto");

	private final String name;

	Overflow(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * @param value value
	 * @return parsed object
	 */
	public static Overflow parse(String value) {
		for (Overflow display : values()) {
			if (display.name.equals(value)) {
				return display;
			}
		}
		throw new SvgException("Unsupported display type");
	}
}
