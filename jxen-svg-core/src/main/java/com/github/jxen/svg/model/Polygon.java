package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.model.type.ValueType;
import java.util.List;

/**
 * {@code Polygon} class represents SVG polygon element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("polygon")
public final class Polygon extends Element<Polygon> {

  @Attr(name = "points", type = ValueType.POLYGON)
  private List<Point> points;

  /**
   * Initializes element.
   */
  public Polygon() {
    super(Polygon.class);
  }

  /**
   * Provides the value.
   *
   * @return the points
   */
  public List<Point> getPoints() {
    return points;
  }

  /**
   * Sets the value.
   *
   * @param points the points to set
   */
  public void setPoints(List<Point> points) {
    this.points = points;
  }

  @Override
  public void accept(SvgVisitor t) {
    t.visit(this);
  }
}
