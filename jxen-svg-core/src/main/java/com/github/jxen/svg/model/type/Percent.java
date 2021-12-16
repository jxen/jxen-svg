package com.github.jxen.svg.model.type;

import com.github.jxen.svg.api.SvgException;

/**
 * {@code Percent} class represents percent model.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class Percent extends NumericValue<Percent, Percent.PercentType> {

  /**
   * Initializes with given values.
   *
   * @param value value
   * @param type  type
   */
  public Percent(double value, PercentType type) {
    super(value, type);
    type.check(value);
  }

  /**
   * Initializes with given value.
   *
   * @param value value
   */
  public Percent(double value) {
    this(value, PercentType.DEFAULT);
  }

  /**
   * Creates percent from string value.
   *
   * @param value value to be parsed
   * @return parsed value
   */
  public static Percent parse(String value) {
    PercentType type = PercentType.of(value);
    double val = Double.parseDouble(value.substring(0, value.lastIndexOf(type.name)));
    return new Percent(val, type);
  }

  @Override
  protected PercentType getDefaultType() {
    return PercentType.DEFAULT;
  }

  @Override
  protected Percent newValue(double value, PercentType type) {
    return new Percent(value, type);
  }

  enum PercentType implements NumericValue.Type {

    DEFAULT("", 1),

    PERCENT("%", 100);

    private final String name;
    private final double factor;
    private final double max;

    PercentType(String text, double max) {
      this.name = text;
      this.factor = 1.0 / max;
      this.max = max;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public double getFactor() {
      return factor;
    }

    private void check(double value) {
      if (value < 0 || value > max) {
        throw new SvgException("Incorrect percent value: " + value + name);
      }
    }

    static PercentType of(String value) {
      for (PercentType type : values()) {
        if (type == DEFAULT) {
          continue;
        }
        if (value.trim().endsWith(type.name)) {
          return type;
        }
      }
      return DEFAULT;
    }
  }
}
