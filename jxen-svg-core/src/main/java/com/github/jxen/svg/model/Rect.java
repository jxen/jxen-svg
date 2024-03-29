package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.model.type.ValueType;

/**
 * {@code Rect} class represents SVG rect element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("rect")
public final class Rect extends Element<Rect> {

  @Attr(name = "x", type = ValueType.LENGTH)
  private Length x;

  @Attr(name = "y", type = ValueType.LENGTH)
  private Length y;

  @Attr(name = "width", type = ValueType.LENGTH)
  private Length width;

  @Attr(name = "height", type = ValueType.LENGTH)
  private Length height;

  @Attr(name = "rx", type = ValueType.LENGTH)
  private Length rx;

  @Attr(name = "ry", type = ValueType.LENGTH)
  private Length ry;

  /**
   * Initializes element.
   */
  public Rect() {
    super(Rect.class);
  }

  /**
   * Provides the value.
   *
   * @return the x
   */
  public Length getX() {
    return x;
  }

  /**
   * Sets the value.
   *
   * @param x the x to set
   */
  public void setX(Length x) {
    this.x = x;
  }

  /**
   * Provides the value.
   *
   * @return the y
   */
  public Length getY() {
    return y;
  }

  /**
   * Sets the value.
   *
   * @param y the y to set
   */
  public void setY(Length y) {
    this.y = y;
  }

  /**
   * Provides the value.
   *
   * @return the width
   */
  public Length getWidth() {
    return width;
  }

  /**
   * Sets the value.
   *
   * @param width the width to set
   */
  public void setWidth(Length width) {
    this.width = width;
  }

  /**
   * Provides the value.
   *
   * @return the height
   */
  public Length getHeight() {
    return height;
  }

  /**
   * Sets the value.
   *
   * @param height the height to set
   */
  public void setHeight(Length height) {
    this.height = height;
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
