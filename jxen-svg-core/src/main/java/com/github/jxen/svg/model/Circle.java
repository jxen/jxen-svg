package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.model.type.ValueType;

/**
 * {@code Circle} class represents SVG circle element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("circle")
public final class Circle extends Element<Circle> {

  @Attr(name = "r", type = ValueType.LENGTH)
  private Length r;

  @Attr(name = "cx", type = ValueType.LENGTH)
  private Length cx;

  @Attr(name = "cy", type = ValueType.LENGTH)
  private Length cy;

  /**
   * Initializes element.
   */
  public Circle() {
    super(Circle.class);
  }

  /**
   * Provides the value.
   *
   * @return the r
   */
  public Length getR() {
    return r;
  }

  /**
   * Sets the value.
   *
   * @param r the r to set
   */
  public void setR(Length r) {
    this.r = r;
  }

  /**
   * Provides the value.
   *
   * @return the cx
   */
  public Length getCx() {
    return cx;
  }

  /**
   * Sets the value.
   *
   * @param cx the cx to set
   */
  public void setCx(Length cx) {
    this.cx = cx;
  }

  /**
   * Provides the value.
   *
   * @return the cy
   */
  public Length getCy() {
    return cy;
  }

  /**
   * Sets the value.
   *
   * @param cy the cy to set
   */
  public void setCy(Length cy) {
    this.cy = cy;
  }

  @Override
  public void accept(SvgVisitor t) {
    t.visit(this);
  }
}
