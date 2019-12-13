package com.github.jxen.svg.model.geometry;

import com.github.jxen.math.linear.Column;
import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.format.Formattable;
import com.github.jxen.svg.transform.Transform;

/**
 * {@code Point} class represents 2d point used in SVG models.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class Point implements Formattable {

	private final double x;
	private final double y;

	/**
	 * @param x x
	 * @param y y
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param column Column
	 * @return point
	 */
	public static Point from(Column column) {
		final int size = 3;
		if (column.size() != size) {
			throw new SvgException("Cannot create Point from Column: " + column);
		}
		return new Point(column.get(0), column.get(1));
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return Column instance
	 */
	public Column toColumn() {
		return Column.of(x, y, 1);
	}

	/**
	 * Transforms current point according given matrix.
	 *
	 * @param transform transformation to be applied
	 * @return resulting point
	 */
	public Point transform(Transform transform) {
		return from(transform.toMatrix().multiply(toColumn()));
	}

	/**
	 * Converts to absolute coordinates.
	 *
	 * @param ref reference point
	 * @return corrected point
	 */
	public Point toAbs(Point ref) {
		return new Point(x + ref.x, y + ref.y);
	}

	/**
	 * Converts to relative coordinates.
	 *
	 * @param ref reference point
	 * @return corrected point
	 */
	public Point toRel(Point ref) {
		return new Point(x - ref.x, y - ref.y);
	}

	@Override
	public String format(String format) {
		StringBuilder builder = new StringBuilder();
		FormatHelper helper = new FormatHelper(format);
		helper.add(x, builder);
		helper.comma(builder);
		helper.add(y, builder);
		return builder.toString();
	}

	@Override
	public String toString() {
		return String.format("(%f, %f)", x, y);
	}

	@Override
	public int hashCode() {
		long temp = Double.doubleToLongBits(x);
		final int prime = 31;
		final int bits = 32;
		int result = prime + (int) (temp ^ (temp >>> bits));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> bits));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Point other = (Point) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}
}
