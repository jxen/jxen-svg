package com.github.jxen.svg.style;

import com.github.jxen.svg.format.Formattable;
import com.github.jxen.svg.model.type.Length;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code DashArray} class represents dasharray style item.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public final class DashArray implements Formattable {

	static final DashArray DEFAULT = new DashArray(null);

	private static final String NONE = "none";

	private final List<Length> array;

	private DashArray(List<Length> array) {
		this.array = array;
	}

	/**
	 * @param value value
	 * @return parsed object
	 */
	public static DashArray parse(String value) {
		if (value.isEmpty() || NONE.equals(value)) {
			return DEFAULT;
		}
		String[] items = value.split("[\\s,]");
		return new DashArray(Stream.of(items).filter(s -> !s.trim().isEmpty())
				.map(Length::parse)
				.collect(Collectors.toList()));
	}

	@Override
	public String format(String format) {
		if (array == null) {
			return NONE;
		}
		return array.stream().map(e -> e.format(format)).collect(Collectors.joining(" "));
	}

	@Override
	public String toString() {
		return format("#.###");
	}
}
