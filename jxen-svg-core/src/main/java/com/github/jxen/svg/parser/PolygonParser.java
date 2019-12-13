package com.github.jxen.svg.parser;

import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.model.geometry.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * {@code PolygonParser} class is parser of {@link Point} objects.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class PolygonParser implements Parser<List<Point>> {

	@Override
	public List<Point> parse(String value) {
		Analyzer analyzer = new Analyzer();
		new Splitter(value, analyzer).split();
		return analyzer.getResult();
	}

	private static class Analyzer implements Consumer<String> {

		private final List<Point> points = new ArrayList<>();
		private final List<Double> values = new ArrayList<>();

		@Override
		public void accept(String item) {
			values.add(Double.parseDouble(item));
			if (values.size() == 2) {
				Point point = new Point(values.get(0), values.get(1));
				points.add(point);
				values.clear();
			}
		}

		private List<Point> getResult() {
			if (!values.isEmpty()) {
				throw new SvgException("Inconsistent poligon points data");
			}
			return points;
		}
	}
}
