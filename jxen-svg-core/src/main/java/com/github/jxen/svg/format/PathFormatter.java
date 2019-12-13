package com.github.jxen.svg.format;

import com.github.jxen.svg.model.path.PathSeg;
import com.github.jxen.svg.model.path.PathSegType;
import java.util.List;

/**
 * {@code PathFormatter} class is responsible for {@link PathSeg} objects formatting.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class PathFormatter implements Formatter {

	@Override
	public String format(Object value) {
		@SuppressWarnings("unchecked")
		List<PathSeg> segments = (List<PathSeg>) value;
		StringBuilder builder = new StringBuilder();
		PathSegType type = null;
		for (PathSeg step : segments) {
			if (type != null) {
				builder.append(' ');
			}
			builder.append(step.getType().toString(step, type));
			type = step.getType();
		}
		return builder.toString();
	}
}
