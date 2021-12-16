package com.github.jxen.svg.model.path;

import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;

/**
 * {@code MoveToSeg} class represents move to segment of SVG path.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class MoveToSeg extends PathSeg {

  private final Point point;

  /**
   * Initializes with given values.
   *
   * @param type     type
   * @param point    point
   * @param endPoint end point
   */
  public MoveToSeg(PathSegType type, Point point, Point endPoint) {
    super(type, endPoint);
    this.point = point;
    checkPoints(type, point, endPoint);
  }

  @Override
  public MoveToSeg transform(Transform transform, Point refOld, Point refNew) {
    Point endPoint = (isRelative() ? point.toAbs(refOld) : point).transform(transform);
    return new MoveToSeg(getType(), isRelative() ? endPoint.toRel(refNew) : endPoint, endPoint);
  }

  @Override
  public MoveToSeg toAbs(Point ref) {
    if (isRelative()) {
      Point p = point.toAbs(ref);
      return new MoveToSeg(PathSegType.M_ABS, p, p);
    }
    return this;
  }

  @Override
  public MoveToSeg toRel(Point ref) {
    return isRelative() ? this : new MoveToSeg(PathSegType.M_REL, point.toRel(ref), ref);
  }

  @Override
  protected StringBuilder prepare(StringBuilder builder, FormatHelper helper) {
    helper.add(point, builder);
    return builder;
  }
}
