package com.github.jxen.svg.format;

import com.github.jxen.svg.transform.Transform;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code PathFormatter} class is responsible for {@link Transform} objects formatting.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class TransformFormatter implements Formatter {

	@Override
	public String format(Object value) {
		@SuppressWarnings("unchecked")
		List<Transform> transforms = (List<Transform>) value;
		return String.join("",
			transforms.stream()
				.filter(v -> !v.toMatrix().isIdentity())
				.map(v -> String.valueOf(v))
				.collect(Collectors.joining("", "", "")));
	}
}
