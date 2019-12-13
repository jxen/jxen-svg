package com.github.jxen.svg.parser;

import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.transform.Transform;
import com.github.jxen.svg.transform.TransformType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * {@code TransformParser} class is parser of {@link Transform} objects.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class TransformParser implements Parser<List<Transform>> {

	@Override
	public List<Transform> parse(String value) {
		final char left = '(';
		final char right = ')';
		List<Transform> transforms = new ArrayList<>();
		int start = 0;
		int indexLeft = value.indexOf(left);
		int indexRight = value.indexOf(right);
		while (indexLeft >= 0 && indexRight >= 0) {
			if (indexLeft > indexRight) {
				throw new SvgException("Unparsable transform: " + value);
			}
			final String name = value.substring(start, indexLeft).replaceAll(",", "").trim();
			Analyzer analyzer = new Analyzer();
			new Splitter(value.substring(indexLeft + 1, indexRight), analyzer).split();
			List<Double> values = analyzer.getResult();
			transforms.add(TransformType.create(name, values));
			start = indexRight + 1;
			indexLeft = value.indexOf(left, start);
			indexRight = value.indexOf(right, start);
		}
		return transforms;
	}

	private static class Analyzer implements Consumer<String> {

		private final List<Double> values = new ArrayList<>();

		@Override
		public void accept(String item) {
			values.add(Double.parseDouble(item));
		}

		private List<Double> getResult() {
			return values;
		}
	}
}
