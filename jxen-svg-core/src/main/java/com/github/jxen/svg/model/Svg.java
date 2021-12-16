package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.model.type.ValueType;
import com.github.jxen.svg.model.type.ViewBox;

/**
 * {@code Svg} class represents root SVG element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("svg")
public final class Svg extends Element<Svg> {

  @Attr(name = "width", type = ValueType.LENGTH)
  private Length width;

  @Attr(name = "height", type = ValueType.LENGTH)
  private Length height;

  @Attr(name = "viewBox", type = ValueType.VIEW_BOX)
  private ViewBox viewBox;

  @Attr(name = "version", type = ValueType.STRING)
  private String version;

  /**
   * Initializes element.
   */
  public Svg() {
    super(Svg.class);
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
   * @return the viewBox
   */
  public ViewBox getViewBox() {
    return viewBox;
  }

  /**
   * Sets the value.
   *
   * @param viewBox the viewBox to set
   */
  public void setViewBox(ViewBox viewBox) {
    this.viewBox = viewBox;
  }

  /**
   * Provides the value.
   *
   * @return the version
   */
  public String getVersion() {
    return version;
  }

  /**
   * Sets the value.
   *
   * @param version the version to set
   */
  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public void accept(SvgVisitor t) {
    t.visit(this);
  }
}
