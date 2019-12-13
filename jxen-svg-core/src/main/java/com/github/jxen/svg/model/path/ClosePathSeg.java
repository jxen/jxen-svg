package com.github.jxen.svg.model.path;

import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;

/**
 * {@code ClosePathSeg} class represents close segment of SVG path.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class ClosePathSeg extends PathSeg {

	/**
	 * @param type     type
	 * @param endPoint end point
	 */
	public ClosePathSeg(PathSegType type, Point endPoint) {
		super(type, endPoint);
	}

	@Override
	public ClosePathSeg transform(Transform transform, Point refOld, Point refNew) {
		return new ClosePathSeg(getType(), refNew);
	}

	@Override
	public ClosePathSeg toAbs(Point ref) {
		return new ClosePathSeg(PathSegType.Z_ABS, ref);
	}

	@Override
	public ClosePathSeg toRel(Point ref) {
		return new ClosePathSeg(PathSegType.Z_REL, ref);
	}

	@Override
	protected StringBuilder prepare(StringBuilder builder, FormatHelper helper) {
		return builder;
	}
}
