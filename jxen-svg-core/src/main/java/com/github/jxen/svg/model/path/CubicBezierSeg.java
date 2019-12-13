package com.github.jxen.svg.model.path;

import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;

/**
 * {@code CubicBezierSeg} class represents cubic Bezier segment of SVG path.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class CubicBezierSeg extends PathSeg {

	private final Point point1;
	private final Point point2;
	private final Point point;

	/**
	 * @param type     type
	 * @param point1   1st point
	 * @param point2   2nd point
	 * @param point    3rd point
	 * @param endPoint end point
	 */
	public CubicBezierSeg(PathSegType type, Point point1, Point point2, Point point, Point endPoint) {
		super(type, endPoint);
		this.point1 = point1;
		this.point2 = point2;
		this.point = point;
		checkPoints(type, point, endPoint);
	}

	@Override
	public PathSeg transform(Transform transform, Point refOld, Point refNew) {
		if (isRelative()) {
			Point end = point.toAbs(refOld).transform(transform);
			return new CubicBezierSeg(getType(),
					point1.toAbs(refOld).transform(transform).toRel(refNew),
					point2.toAbs(refOld).transform(transform).toRel(refNew),
					end.toRel(refNew), end);
		}
		Point end = point.transform(transform);
		return new CubicBezierSeg(getType(), point1.transform(transform), point2.transform(transform), end, end);
	}

	@Override
	public PathSeg toAbs(Point ref) {
		PathSegType type = getType() == PathSegType.C_ABS || getType() == PathSegType.C_REL
				? PathSegType.C_ABS : PathSegType.S_ABS;
		if (isRelative()) {
			Point p = point.toAbs(ref);
			return new CubicBezierSeg(type, point1.toAbs(ref), point2.toAbs(ref), p, p);
		}
		return this;
	}

	@Override
	public PathSeg toRel(Point ref) {
		PathSegType type = getType() == PathSegType.C_ABS || getType() == PathSegType.C_REL
				? PathSegType.C_REL : PathSegType.S_REL;
		return isRelative() ? this
				: new CubicBezierSeg(type, point1.toRel(ref), point2.toRel(ref), point.toRel(ref), ref);
	}

	@Override
	protected StringBuilder prepare(StringBuilder builder, FormatHelper helper) {
		if (getType() == PathSegType.C_ABS || getType() == PathSegType.C_REL) {
			helper.add(point1, builder);
			helper.space(builder);
		}
		helper.add(point2, builder);
		helper.space(builder);
		helper.add(point, builder);
		return builder;
	}
}
