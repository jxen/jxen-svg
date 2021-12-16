package com.github.jxen.svg.model.type;

/**
 * {@code Length} class represents length model.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class Length extends NumericValue<Length, Length.LengthType> {

  private static final double DPI = 96;

  /**
   * Initializes with given values.
   *
   * @param value value
   * @param type  type
   */
  public Length(double value, LengthType type) {
    super(round(value), type);
  }

  /**
   * Initializes with given value.
   *
   * @param value value
   */
  public Length(double value) {
    this(value, LengthType.DEFAULT);
  }

  /**
   * Parses value.
   *
   * @param value value to be parsed
   * @return parsed value
   */
  public static Length parse(String value) {
    LengthType type = LengthType.of(value);
    double val = Double.parseDouble(value.substring(0, value.lastIndexOf(type.name)));
    return new Length(val, type);
  }

  @Override
  protected LengthType getDefaultType() {
    return LengthType.DEFAULT;
  }

  @Override
  protected Length newValue(double value, LengthType type) {
    return new Length(value, type);
  }

  private static double round(double value) {
    final double part = 0.125;
    return Math.round(value / part) * part;
  }

  enum LengthType implements NumericValue.Type {

    DEFAULT("", 1),

    EM("em", Double.NaN),

    EX("ex", Double.NaN),

    PX("px", 1),

    IN("in", DPI),

    CM("cm", DPI / 2.54),

    MM("mm", DPI / 25.4),

    PT("pt", 1.25),

    PC("pc", 15),

    PERCENT("%", Double.NaN);

    private final String name;
    private final double factor;

    LengthType(String text, double factor) {
      this.name = text;
      this.factor = factor;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public double getFactor() {
      return factor;
    }

    private static LengthType of(String value) {
      for (LengthType type : values()) {
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
