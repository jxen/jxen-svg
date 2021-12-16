package com.github.jxen.svg.model.path;

import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.model.geometry.Ellipse;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;
import java.util.Iterator;
import java.util.List;

/**
 * {@code ArcSeg} class represents arc segment of SVG path.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class ArcSeg extends PathSeg {

  private final Ellipse ellipse;
  private final boolean largeArc;
  private final boolean sweep;
  private final Point point;

  /**
   * Initializes with given values.
   *
   * @param type     type
   * @param ellipse  ellipse
   * @param largeArc is large arc
   * @param sweep    sweep flag
   * @param point    point
   * @param endPoint end point
   */
  public ArcSeg(PathSegType type, Ellipse ellipse, boolean largeArc, boolean sweep, Point point, Point endPoint) {
    super(type, endPoint);
    this.ellipse = ellipse;
    this.largeArc = largeArc;
    this.sweep = sweep;
    this.point = point;
    checkPoints(type, point, endPoint);
  }

  @Override
  public PathSeg transform(Transform transform, Point refOld, Point refNew) {
    Ellipse e = ellipse.transform(transform);
    Point endPoint = (isRelative() ? point.toAbs(refOld) : point).transform(transform);
    return new ArcSeg(getType(), e, largeArc, transform.toMatrix().determinant() > 0 == sweep,
        isRelative() ? endPoint.toRel(refNew) : endPoint, endPoint);
  }

  @Override
  public PathSeg toAbs(Point ref) {
    return isRelative() ? new ArcSeg(getType(), ellipse, largeArc, sweep, point, point.toAbs(ref)) : this;
  }

  @Override
  public PathSeg toRel(Point ref) {
    return isRelative() ? this : new ArcSeg(getType(), ellipse, largeArc, sweep, point.toRel(ref), point);
  }

  @Override
  protected StringBuilder prepare(StringBuilder builder, FormatHelper helper) {
    helper.add(ellipse, builder);
    helper.space(builder);
    helper.add(largeArc, builder);
    helper.space(builder);
    helper.add(sweep, builder);
    helper.space(builder);
    helper.add(point, builder);
    return builder;
  }

  static ArcSeg create(PathSegType type, List<Double> values, Point ref) {
    Iterator<Double> it = values.iterator();
    Ellipse ellipse = new Ellipse(it.next(), it.next(), it.next());
    boolean largeArc = it.next().intValue() == 1;
    boolean sweep = it.next().intValue() == 1;
    Point point = new Point(it.next(), it.next());
    return new ArcSeg(type, ellipse, largeArc, sweep, point, type == PathSegType.A_ABS ? point.toAbs(ref) : point);
  }
}
