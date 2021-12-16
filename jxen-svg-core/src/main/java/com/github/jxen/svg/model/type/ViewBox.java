package com.github.jxen.svg.model.type;

import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.format.Formattable;

/**
 * {@code ViewBox} class represents view box model.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public final class ViewBox implements Formattable {

  private final double x;
  private final double y;
  private final double width;
  private final double height;

  /**
   * Initializes with given values.
   *
   * @param x      min x
   * @param y      min y
   * @param width  width
   * @param height height
   */
  public ViewBox(double x, double y, double width, double height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Provides the value.
   *
   * @return the x
   */
  public double getX() {
    return x;
  }

  /**
   * Provides the value.
   *
   * @return the y
   */
  public double getY() {
    return y;
  }

  /**
   * Provides the value.
   *
   * @return the width
   */
  public double getWidth() {
    return width;
  }

  /**
   * Provides the value.
   *
   * @return the height
   */
  public double getHeight() {
    return height;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    final int bits = 32;
    long temp = Double.doubleToLongBits(height);
    int result = prime + (int) (temp ^ (temp >>> bits));
    temp = Double.doubleToLongBits(width);
    result = prime * result + (int) (temp ^ (temp >>> bits));
    temp = Double.doubleToLongBits(x);
    result = prime * result + (int) (temp ^ (temp >>> bits));
    temp = Double.doubleToLongBits(y);
    return prime * result + (int) (temp ^ (temp >>> bits));
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ViewBox other = (ViewBox) obj;
    return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
        && Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y)
        && Double.doubleToLongBits(width) == Double.doubleToLongBits(other.width)
        && Double.doubleToLongBits(height) == Double.doubleToLongBits(other.height);
  }

  @Override
  public String format(String format) {
    StringBuilder builder = new StringBuilder();
    FormatHelper helper = new FormatHelper(format);
    helper.add(x, builder);
    helper.space(builder);
    helper.add(y, builder);
    helper.space(builder);
    helper.add(width, builder);
    helper.space(builder);
    helper.add(height, builder);
    return builder.toString();
  }

  @Override
  public String toString() {
    return format("#.##");
  }
}
