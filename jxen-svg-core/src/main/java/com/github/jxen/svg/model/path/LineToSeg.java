package com.github.jxen.svg.model.path;

import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;

/**
 * {@code LineToSeg} class represents line to segment of SVG path.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class LineToSeg extends PathSeg {

	private static final double EQUALITY_PRECISION = 1e-3;

	private final Point point;

	/**
	 * @param type     type
	 * @param point    point
	 * @param endPoint end point
	 */
	public LineToSeg(PathSegType type, Point point, Point endPoint) {
		super(type, endPoint);
		this.point = point;
		checkPoints(type, point, endPoint);
	}

	@Override
	public LineToSeg transform(Transform transform, Point refOld, Point refNew) {
		Point endPoint = (isRelative() ? point.toAbs(refOld) : point).transform(transform);
		return isRelative() ? getRel(endPoint.toRel(refNew), endPoint) : getAbs(endPoint, refNew);
	}

	@Override
	public LineToSeg toAbs(Point ref) {
		return isRelative() ? getAbs(point.toAbs(ref), ref) : this;
	}

	@Override
	public LineToSeg toRel(Point ref) {
		return isRelative() ? this : getRel(point.toRel(ref), ref);
	}

	@Override
	protected StringBuilder prepare(StringBuilder builder, FormatHelper helper) {
		if (getType() == PathSegType.H_ABS || getType() == PathSegType.H_REL) {
			helper.add(point.getX(), builder);
		} else if (getType() == PathSegType.V_ABS || getType() == PathSegType.V_REL) {
			helper.add(point.getY(), builder);
		} else {
			helper.add(point, builder);
		}
		return builder;
	}

	private LineToSeg getAbs(Point value, Point refNew) {
		if (Math.abs(value.getX() - refNew.getX()) < EQUALITY_PRECISION) {
			return new LineToSeg(PathSegType.V_ABS, value, value);
		}
		if (value.getY() == refNew.getY()) {
			return new LineToSeg(PathSegType.H_ABS, value, value);
		}
		return new LineToSeg(PathSegType.L_ABS, value, value);
	}

	private LineToSeg getRel(Point value, Point endPoint) {
		if (value.getX() == 0) {
			return new LineToSeg(PathSegType.V_REL, value, endPoint);
		}
		if (value.getY() == 0) {
			return new LineToSeg(PathSegType.H_REL, value, endPoint);
		}
		return new LineToSeg(PathSegType.L_REL, value, endPoint);
	}
}
