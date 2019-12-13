package com.github.jxen.svg.model.path;

import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;

/**
 * {@code QuadraticBezierSeg} class represents quadratic Bezier segment of SVG path.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class QuadraticBezierSeg extends PathSeg {

	private final Point point1;
	private final Point point;

	/**
	 * @param type     type
	 * @param point1   1st point
	 * @param point    2nd point
	 * @param endPoint end point
	 */
	public QuadraticBezierSeg(PathSegType type, Point point1, Point point, Point endPoint) {
		super(type, endPoint);
		this.point1 = point1;
		this.point = point;
		checkPoints(type, point, endPoint);
	}

	@Override
	public PathSeg transform(Transform transform, Point refOld, Point refNew) {
		Point p1 = (isRelative() ? point1.toAbs(refOld) : point1).transform(transform);
		Point p = (isRelative() ? point.toAbs(refOld) : point).transform(transform);
		return new QuadraticBezierSeg(getType(), isRelative() ? p1.toRel(refNew) : p1,
				isRelative() ? p.toRel(refNew) : p, p);
	}

	@Override
	public PathSeg toAbs(Point ref) {
		PathSegType type = getType() == PathSegType.Q_ABS || getType() == PathSegType.Q_REL
				? PathSegType.Q_ABS : PathSegType.T_ABS;
		if (isRelative()) {
			Point p = point.toAbs(ref);
			return new QuadraticBezierSeg(type, point1.toAbs(ref), p, p);
		}
		return this;
	}

	@Override
	public PathSeg toRel(Point ref) {
		PathSegType type = getType() == PathSegType.Q_ABS || getType() == PathSegType.Q_REL
				? PathSegType.Q_REL : PathSegType.T_REL;
		return isRelative() ? this : new QuadraticBezierSeg(type, point1.toAbs(ref), point.toAbs(ref), ref);
	}

	@Override
	protected StringBuilder prepare(StringBuilder builder, FormatHelper helper) {
		if (getType() == PathSegType.Q_ABS || getType() == PathSegType.Q_REL) {
			helper.add(point1, builder);
			helper.space(builder);
		}
		helper.add(point, builder);
		return builder;
	}
}
