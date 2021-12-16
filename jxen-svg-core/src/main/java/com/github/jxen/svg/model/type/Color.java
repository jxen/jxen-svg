package com.github.jxen.svg.model.type;

import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.format.Formattable;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code Color} class represents color model.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public final class Color implements Formattable {

  /**
   * None color.
   */
  public static final Color NONE = new Color(-1, -1, -1);

  /**
   * Black color.
   */
  public static final Color BLACK = new Color(0, 0, 0);

  private static final Logger LOG = LogManager.getLogger(Color.class);

  private static final int MAX = 0xFF;

  private final int red;
  private final int green;
  private final int blue;

  private Color(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Provides color for given value.
   *
   * @param red   red
   * @param green green
   * @param blue  blue
   * @return {@code Color}
   */
  public static Color of(int red, int green, int blue) {
    if (red < 0 || red > MAX) {
      throw new SvgException("Unsupported value for red color: " + red);
    }
    if (green < 0 || green > MAX) {
      throw new SvgException("Unsupported value for green color: " + green);
    }
    if (blue < 0 || blue > MAX) {
      throw new SvgException("Unsupported value for blue color: " + blue);
    }
    return new Color(red, green, blue);
  }

  /**
   * Parses color from string.
   *
   * @param value value to be parsed
   * @return {@code Color}
   */
  public static Color parse(String value) {
    if (value.charAt(0) == '#') {
      return parseHex(value.substring(1));
    }
    if (value.startsWith("rgb")) {
      int start = value.indexOf('(');
      int end = value.lastIndexOf(')');
      return parseRgb(value.substring(start + 1, end).trim());
    }
    if (value.startsWith("url")) {
      // TODO: implement URL support
      LOG.warn("URL is not supported yet");
      return BLACK;
    }
    return Optional.ofNullable(NamedColor.of(value)).map(c -> c.value)
        .orElseThrow(() -> new SvgException("Unknown color: " + value));
  }

  private static Color parseHex(String value) {
    final int radix = 16;
    final int longValue = 6;
    if (value.length() == longValue) {
      int i = 0;
      int red = Integer.parseInt(value.substring(i, i + 2), radix);
      i += 2;
      int green = Integer.parseInt(value.substring(i, i + 2), radix);
      i += 2;
      int blue = Integer.parseInt(value.substring(i, i + 2), radix);
      return of(red, green, blue);
    }
    final int shortValue = 3;
    if (value.length() == shortValue) {
      final int factor = 17;
      int i = 0;
      int red = factor * Integer.parseInt(value.substring(i++, i), radix);
      int green = factor * Integer.parseInt(value.substring(i++, i), radix);
      int blue = factor * Integer.parseInt(value.substring(i++, i), radix);
      return of(red, green, blue);
    }
    throw new SvgException("Unsupported color value: " + value);
  }

  private static Color parseRgb(String value) {
    String[] split = value.split(",");
    final int colors = 3;
    if (split.length != colors) {
      throw new SvgException("Unsupported RGB value: " + value);
    }
    int red = parseRgbItem(split[0].trim());
    int green = parseRgbItem(split[1].trim());
    int blue = parseRgbItem(split[2].trim());
    return of(red, green, blue);
  }

  private static int parseRgbItem(String value) {
    if (value.endsWith("%")) {
      final int hundred = 100;
      return MAX * Integer.parseInt(value.substring(0, value.length() - 1)) / hundred;
    }
    return Integer.parseInt(value);
  }

  @Override
  public String format(String format) {
    NamedColor named = NamedColor.of(this);
    final int maxShortName = 7;
    if (named != null && named.name.length() <= maxShortName) {
      return named.name;
    }
    return toString();
  }

  @Override
  public String toString() {
    return String.format("#%02x%02x%02x", red, green, blue);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = prime + blue;
    result = prime * result + green;
    return prime * result + red;
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
    Color other = (Color) obj;
    return blue == other.blue && green == other.green && red == other.red;
  }

  private enum NamedColor {

    NONE("none", Color.NONE),

    ALICEBLUE("aliceblue", new Color(240, 248, 255)),

    ANTIQUEWHITE("antiquewhite", new Color(250, 235, 215)),

    AQUA("aqua", new Color(0, 255, 255)),

    AQUAMARINE("aquamarine", new Color(127, 255, 212)),

    AZURE("azure", new Color(240, 255, 255)),

    BEIGE("beige", new Color(245, 245, 220)),

    BISQUE("bisque", new Color(255, 228, 196)),

    BLACK("black", Color.BLACK),

    BLANCHEDALMOND("blanchedalmond", new Color(255, 235, 205)),

    BLUE("blue", new Color(0, 0, 255)),

    BLUEVIOLET("blueviolet", new Color(138, 43, 226)),

    BROWN("brown", new Color(165, 42, 42)),

    BURLYWOOD("burlywood", new Color(222, 184, 135)),

    CADETBLUE("cadetblue", new Color(95, 158, 160)),

    CHARTREUSE("chartreuse", new Color(127, 255, 0)),

    CHOCOLATE("chocolate", new Color(210, 105, 30)),

    CORAL("coral", new Color(255, 127, 80)),

    CORNFLOWERBLUE("cornflowerblue", new Color(100, 149, 237)),

    CORNSILK("cornsilk", new Color(255, 248, 220)),

    CRIMSON("crimson", new Color(220, 20, 60)),

    CYAN("cyan", new Color(0, 255, 255)),

    DARKBLUE("darkblue", new Color(0, 0, 139)),

    DARKCYAN("darkcyan", new Color(0, 139, 139)),

    DARKGOLDENODD("darkgoldenrod", new Color(184, 134, 11)),

    DARKGRAY("darkgray", new Color(169, 169, 169)),

    DARKGREEN("darkgreen", new Color(0, 100, 0)),

    DARKGREY("darkgrey", new Color(169, 169, 169)),

    DARKKHAKI("darkkhaki", new Color(189, 183, 107)),

    DARKMAGENTA("darkmagenta", new Color(139, 0, 139)),

    DARKOLIVEGREEN("darkolivegreen", new Color(85, 107, 47)),

    DARKORANGE("darkorange", new Color(255, 140, 0)),

    DARKORCHID("darkorchid", new Color(153, 50, 204)),

    DARKRED("darkred", new Color(139, 0, 0)),

    DARKSALMON("darksalmon", new Color(233, 150, 122)),

    DARKSEAGREEN("darkseagreen", new Color(143, 188, 143)),

    DARKSLATEBLUE("darkslateblue", new Color(72, 61, 139)),

    DARKSLATEGRAY("darkslategray", new Color(47, 79, 79)),

    DARKSLATEGREY("darkslategrey", new Color(47, 79, 79)),

    DARKTURQUOISE("darkturquoise", new Color(0, 206, 209)),

    DARKVIOLET("darkviolet", new Color(148, 0, 211)),

    DEEPPINK("deeppink", new Color(255, 20, 147)),

    DEEPSKYBLUE("deepskyblue", new Color(0, 191, 255)),

    DIMGRAY("dimgray", new Color(105, 105, 105)),

    DIMGREY("dimgrey", new Color(105, 105, 105)),

    DODGERBLUE("dodgerblue", new Color(30, 144, 255)),

    FIREBRICK("firebrick", new Color(178, 34, 34)),

    FLORALWHITE("floralwhite", new Color(255, 250, 240)),

    FORESTGREEN("forestgreen", new Color(34, 139, 34)),

    FUCHSIA("fuchsia", new Color(255, 0, 255)),

    GAINSBORO("gainsboro", new Color(220, 220, 220)),

    GHOSTWHITE("ghostwhite", new Color(248, 248, 255)),

    GOLD("gold", new Color(255, 215, 0)),

    GOLDENROD("goldenrod", new Color(218, 165, 32)),

    GRAY("gray", new Color(128, 128, 128)),

    GREY("grey", new Color(128, 128, 128)),

    GREEN("green", new Color(0, 128, 0)),

    GREENYELLOW("greenyellow", new Color(173, 255, 47)),

    HONEYDEW("honeydew", new Color(240, 255, 240)),

    HOTPINK("hotpink", new Color(255, 105, 180)),

    INDIANRED("indianred", new Color(205, 92, 92)),

    INDIGO("indigo", new Color(75, 0, 130)),

    IVORY("ivory", new Color(255, 255, 240)),

    KHAKI("khaki", new Color(240, 230, 140)),

    LAVENDER("lavender", new Color(230, 230, 250)),

    LAVENDERBLUSH("lavenderblush", new Color(255, 240, 245)),

    LAWNGREEN("lawngreen", new Color(124, 252, 0)),

    LEMONCHIFFON("lemonchiffon", new Color(255, 250, 205)),

    LIGHTBLUE("lightblue", new Color(173, 216, 230)),

    LIGHTCORAL("lightcoral", new Color(240, 128, 128)),

    LIGHTCYAN("lightcyan", new Color(224, 255, 255)),

    LIGHTGOLDENRODYELLOW("lightgoldenrodyellow", new Color(250, 250, 210)),

    LIGHTGRAY("lightgray", new Color(211, 211, 211)),

    LIGHTGREEN("lightgreen", new Color(144, 238, 144)),

    LIGHTGREY("lightgrey", new Color(211, 211, 211)),

    LIGHTPINK("lightpink", new Color(255, 182, 193)),

    LIGHTSALMON("lightsalmon", new Color(255, 160, 122)),

    LIGHTSEEGREEN("lightseagreen", new Color(32, 178, 170)),

    LIGHTSKYBLUE("lightskyblue", new Color(135, 206, 250)),

    LIGHTSLATEGRAY("lightslategray", new Color(119, 136, 153)),

    LIGHTSLATEGREY("lightslategrey", new Color(119, 136, 153)),

    LIGHTSTEELBLUE("lightsteelblue", new Color(176, 196, 222)),

    LIGHTYELLOW("lightyellow", new Color(255, 255, 224)),

    LIME("lime", new Color(0, 255, 0)),

    LIMEGREEN("limegreen", new Color(50, 205, 50)),

    LINEN("linen", new Color(250, 240, 230)),

    MAGENTA("magenta", new Color(255, 0, 255)),

    MAROON("maroon", new Color(128, 0, 0)),

    MEDIUMAQUAMARINE("mediumaquamarine", new Color(102, 205, 170)),

    MEDIUMBLUE("mediumblue", new Color(0, 0, 205)),

    MEDIUMORCHID("mediumorchid", new Color(186, 85, 211)),

    MEDIUMPURPLE("mediumpurple", new Color(147, 112, 219)),

    MEDIUMSEAGREEN("mediumseagreen", new Color(60, 179, 113)),

    MEDIUMSLATEBLUE("mediumslateblue", new Color(123, 104, 238)),

    MEDIUMSPRINGGREEN("mediumspringgreen", new Color(0, 250, 154)),

    MEDIUMTURQUOISE("mediumturquoise", new Color(72, 209, 204)),

    MEDIOMVIOLETRED("mediumvioletred", new Color(199, 21, 133)),

    MIDNIGHTBLUE("midnightblue", new Color(25, 25, 112)),

    MINTCREAM("mintcream", new Color(245, 255, 250)),

    MISTYROSE("mistyrose", new Color(255, 228, 225)),

    MOCCASIN("moccasin", new Color(255, 228, 181)),

    NAVAJOWHITE("navajowhite", new Color(255, 222, 173)),

    NAVY("navy", new Color(0, 0, 128)),

    OLDLACE("oldlace", new Color(253, 245, 230)),

    OLIVE("olive", new Color(128, 128, 0)),

    OLIVEDRAB("olivedrab", new Color(107, 142, 35)),
    ORANGE("orange", new Color(255, 165, 0)),

    ORANGERED("orangered", new Color(255, 69, 0)),

    ORCHID("orchid", new Color(218, 112, 214)),

    PALEGOLDENROD("palegoldenrod", new Color(238, 232, 170)),

    PALEGREEN("palegreen", new Color(152, 251, 152)),

    PALETURQUOISE("paleturquoise", new Color(175, 238, 238)),

    PALEVIOLETRED("palevioletred", new Color(219, 112, 147)),

    PAPAYAWHIP("papayawhip", new Color(255, 239, 213)),

    PEACHPUFF("peachpuff", new Color(255, 218, 185)),

    PERU("peru", new Color(205, 133, 63)),

    PINK("pink", new Color(255, 192, 203)),

    PLUM("plum", new Color(221, 160, 221)),

    POWDERBLUE("powderblue", new Color(176, 224, 230)),

    PURPLE("purple", new Color(128, 0, 128)),

    RED("red", new Color(255, 0, 0)),

    ROSYBROWN("rosybrown", new Color(188, 143, 143)),

    ROYALBLUE("royalblue", new Color(65, 105, 225)),

    SADDLEBROWN("saddlebrown", new Color(139, 69, 19)),

    SALMON("salmon", new Color(250, 128, 114)),

    SANDYBROWN("sandybrown", new Color(244, 164, 96)),

    SEAGREEN("seagreen", new Color(46, 139, 87)),

    SEASHELL("seashell", new Color(255, 245, 238)),

    SIENNA("sienna", new Color(160, 82, 45)),

    SILVER("silver", new Color(192, 192, 192)),

    SKYBLUE("skyblue", new Color(135, 206, 235)),

    SLATEBLUE("slateblue", new Color(106, 90, 205)),

    SLATEGRAY("slategray", new Color(112, 128, 144)),

    SLATEGREY("slategrey", new Color(112, 128, 144)),

    SNOW("snow", new Color(255, 250, 250)),

    SPRINGGREEN("springgreen", new Color(0, 255, 127)),

    STEELBLUE("steelblue", new Color(70, 130, 180)),

    TAN("tan", new Color(210, 180, 140)),

    TAL("teal", new Color(0, 128, 128)),

    THISTLE("thistle", new Color(216, 191, 216)),

    TOMATO("tomato", new Color(255, 99, 71)),

    TURQUOISE("turquoise", new Color(64, 224, 208)),

    VIOLET("violet", new Color(238, 130, 238)),

    WHEAT("wheat", new Color(245, 222, 179)),

    WHITE("white", new Color(255, 255, 255)),

    WHITESMOKE("whitesmoke", new Color(245, 245, 245)),

    YELLOW("yellow", new Color(255, 255, 0)),

    YELLOWGREEN("yellowgreen", new Color(154, 205, 50));

    private static final Map<String, NamedColor> BY_NAME = Stream.of(values())
        .collect(Collectors.toMap(
            e -> e.name, e -> e));

    private static final Map<Color, NamedColor> BY_COLOR = Stream.of(values())
        .collect(Collectors.toMap(
            e -> e.value, e -> e, (u, v) -> u.name.length() <= v.name.length() ? u : v));

    private final String name;
    private final Color value;

    NamedColor(String name, Color value) {
      this.name = name;
      this.value = value;
    }

    private static NamedColor of(String name) {
      return BY_NAME.get(name);
    }

    private static NamedColor of(Color color) {
      return BY_COLOR.get(color);
    }
  }
}
