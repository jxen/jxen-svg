package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.model.type.ValueType;

/**
 * {@code Ellipse} class represents SVG ellipse element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("ellipse")
public final class Ellipse extends Element<Ellipse> {

  @Attr(name = "cx", type = ValueType.LENGTH)
  private Length cx;

  @Attr(name = "cy", type = ValueType.LENGTH)
  private Length cy;

  @Attr(name = "rx", type = ValueType.LENGTH)
  private Length rx;

  @Attr(name = "ry", type = ValueType.LENGTH)
  private Length ry;

  /**
   * Initializes element.
   */
  public Ellipse() {
    super(Ellipse.class);
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

  /**
   * Provides the value.
   *
   * @return the rx
   */
  public Length getRx() {
    return rx;
  }

  /**
   * Sets the value.
   *
   * @param rx the rx to set
   */
  public void setRx(Length rx) {
    this.rx = rx;
  }

  /**
   * Provides the value.
   *
   * @return the ry
   */
  public Length getRy() {
    return ry;
  }

  /**
   * Sets the value.
   *
   * @param ry the ry to set
   */
  public void setRy(Length ry) {
    this.ry = ry;
  }

  @Override
  public void accept(SvgVisitor t) {
    t.visit(this);
  }
}
