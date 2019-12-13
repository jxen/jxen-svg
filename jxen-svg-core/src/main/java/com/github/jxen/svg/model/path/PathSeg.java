package com.github.jxen.svg.model.path;

import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;

/**
 * {@code PathSeg} class represents abstract segment of SVG path.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public abstract class PathSeg {

	private final PathSegType type;
	private final Point endPoint;

	/**
	 * @param type     type
	 * @param endPoint end point
	 */
	protected PathSeg(PathSegType type, Point endPoint) {
		this.type = type;
		this.endPoint = endPoint;
	}

	/**
	 * @return the type
	 */
	public PathSegType getType() {
		return type;
	}

	/**
	 * @return the endPoint
	 */
	public Point getEndPoint() {
		return endPoint;
	}

	/**
	 * Transforms path segment.
	 *
	 * @param transform transform
	 * @param refOld    reference point at old coordinates
	 * @param refNew    reference point at new coordinates
	 * @return transformed path segment
	 */
	public abstract PathSeg transform(Transform transform, Point refOld, Point refNew);

	/**
	 * Converts to absolute coordinates system.
	 *
	 * @param ref reference point
	 * @return segment in absolute coordinates
	 */
	public abstract PathSeg toAbs(Point ref);

	/**
	 * Converts to relative coordinates system.
	 *
	 * @param ref reference point
	 * @return segment in relative coordinates
	 */
	public abstract PathSeg toRel(Point ref);

	@Override
	public final String toString() {
		return prepare(new StringBuilder(), new FormatHelper("#.##")).toString();
	}

	/**
	 * Prepares data to be converted to String.
	 *
	 * @param builder format builder to be prepared
	 * @param helper  format helper to be used
	 * @return {@link StringBuilder} instance
	 */
	protected abstract StringBuilder prepare(StringBuilder builder, FormatHelper helper);

	boolean isRelative() {
		return type.isRelative();
	}

	static void checkPoints(PathSegType type, Point point1, Point point2) {
		if (!type.isRelative() && !point1.equals(point2)) {
			throw new SvgException("Points should be equal");
		}
	}
}
