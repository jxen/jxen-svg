package com.github.jxen.svg.model.type;

import com.github.jxen.svg.format.FormatHelper;
import com.github.jxen.svg.format.Formattable;

/**
 * {@code NumericValue} class represents abstract numeric model.
 *
 * @author Denis Murashev
 *
 * @param <V> value
 * @param <T> type
 *
 * @since SVG Core 0.1
 */
public abstract class NumericValue<V extends NumericValue<V, T>, T extends Enum<T> & NumericValue.Type>
    implements Formattable {

  private final double value;
  private final T type;

  /**
   * Initializes with given values.
   *
   * @param value value
   * @param type  type
   */
  protected NumericValue(double value, T type) {
    this.value = value;
    this.type = type;
  }

  /**
   * Provides the value.
   *
   * @return the value
   */
  public final double getValue() {
    return value;
  }

  /**
   * Provides the value.
   *
   * @return the type
   */
  public final T getType() {
    return type;
  }

  /**
   * Converts value.
   *
   * @param arg new value
   * @return new value
   */
  public final V to(double arg) {
    return newValue(value, type);
  }

  /**
   * Negates value.
   *
   * @return negated value
   */
  public final V negate() {
    return newValue(-value, type);
  }

  /**
   * Adds value.
   *
   * @param arg argument
   * @return sum
   */
  public final V plus(V arg) {
    return newValue(value + arg.getValue() * arg.getType().getFactor(), type);
  }

  /**
   * Subtracts value.
   *
   * @param arg argument
   * @return difference
   */
  public final V minus(V arg) {
    return newValue(value - arg.getValue() * arg.getType().getFactor(), type);
  }

  /**
   * Multiplies value.
   *
   * @param factor factor
   * @return product
   */
  public final V multiply(double factor) {
    return newValue(value * factor, type);
  }

  @Override
  public String format(String format) {
    String text = type.equals(getDefaultType()) ? "" : type.getName();
    StringBuilder builder = new StringBuilder();
    FormatHelper helper = new FormatHelper(format);
    helper.add(value, builder);
    helper.add(text, builder);
    return builder.toString();
  }

  @Override
  public String toString() {
    return value + type.getName();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = prime + (type == null ? 0 : type.hashCode());
    long temp = Double.doubleToLongBits(value);
    final int bits = 32;
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
    @SuppressWarnings("unchecked")
    V other = (V) obj;
    return type.equals(other.getType())
        && Double.doubleToLongBits(value) == Double.doubleToLongBits(other.getValue());
  }

  /**
   * Provides default type.
   *
   * @return default type
   */
  protected abstract T getDefaultType();

  /**
   * Converts value to type.
   *
   * @param value value
   * @param type  type
   * @return new value
   */
  protected abstract V newValue(double value, T type);

  interface Type {

    String getName();

    double getFactor();
  }
}
