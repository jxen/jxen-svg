package com.github.jxen.svg.style;

import com.github.jxen.svg.annotation.StyleItem;
import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.model.type.Color;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.model.type.Percent;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code Style} class is responsible for style of each SVG element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public final class Style {

	private static final Logger LOG = LogManager.getLogger(Style.class);

	private static final Helper HELPER = new Helper();

	@StyleItem(name = "color", type = StyleItemType.STROKE_COLOR)
	private Color color;

	@StyleItem(name = "display", type = StyleItemType.DISPLAY)
	private Display display;

	@StyleItem(name = "enable-background", type = StyleItemType.STRING)
	private String enableBackground;

	@StyleItem(name = "fill", type = StyleItemType.FILL_COLOR)
	private Color fill;

	@StyleItem(name = "fill-opacity", type = StyleItemType.OPACITY)
	private Percent fillOpacity;

	@StyleItem(name = "fill-rule", type = StyleItemType.FILL_RULE)
	private FillRule fillRule;

	@StyleItem(name = "marker", type = StyleItemType.STRING)
	private String marker;

	@StyleItem(name = "marker-end", type = StyleItemType.STRING)
	private String markerEnd;

	@StyleItem(name = "marker-mid", type = StyleItemType.STRING)
	private String markerMid;

	@StyleItem(name = "marker-start", type = StyleItemType.STRING)
	private String markerStart;

	@StyleItem(name = "opacity", type = StyleItemType.OPACITY)
	private Percent opacity;

	@StyleItem(name = "overflow", type = StyleItemType.OVERFLOW)
	private Overflow overflow;

	@StyleItem(name = "stroke", type = StyleItemType.STROKE_COLOR)
	private Color stroke;

	@StyleItem(name = "stroke-dasharray", type = StyleItemType.STROKE_DASHARRAY)
	private DashArray strokeDasharray;

	@StyleItem(name = "stroke-dashoffset", type = StyleItemType.STROKE_DASHOFFSET)
	private Length strokeDashoffset;

	@StyleItem(name = "stroke-linecap", type = StyleItemType.STROKE_LINECAP)
	private Linecap strokeLinecap;

	@StyleItem(name = "stroke-linejoin", type = StyleItemType.STROKE_LINEJOIN)
	private Linejoin strokeLinejoin;

	@StyleItem(name = "stroke-miterlimit", type = StyleItemType.STROKE_MITERLIMIT)
	private Double strokeMiterlimit;

	@StyleItem(name = "stroke-opacity", type = StyleItemType.OPACITY)
	private Percent strokeOpacity;

	@StyleItem(name = "stroke-width", type = StyleItemType.STROKE_WIDTH)
	private Length strokeWidth;

	@StyleItem(name = "visibility", type = StyleItemType.VISIBILITY)
	private Visibility visibility;

	private String unsupportedYet = "";

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the display
	 */
	public Display getDisplay() {
		return display;
	}

	/**
	 * @param display the display to set
	 */
	public void setDisplay(Display display) {
		this.display = display;
	}

	/**
	 * @return the enableBackground
	 */
	public String getEnableBackground() {
		return enableBackground;
	}

	/**
	 * @param enableBackground the enableBackground to set
	 */
	public void setEnableBackground(String enableBackground) {
		this.enableBackground = enableBackground;
	}

	/**
	 * @return the fill
	 */
	public Color getFill() {
		return fill;
	}

	/**
	 * @param fill the fill to set
	 */
	public void setFill(Color fill) {
		this.fill = fill;
	}

	/**
	 * @return the fillOpacity
	 */
	public Percent getFillOpacity() {
		return fillOpacity;
	}

	/**
	 * @param fillOpacity the fillOpacity to set
	 */
	public void setFillOpacity(Percent fillOpacity) {
		this.fillOpacity = fillOpacity;
	}

	/**
	 * @return the fillRule
	 */
	public FillRule getFillRule() {
		return fillRule;
	}

	/**
	 * @param fillRule the fillRule to set
	 */
	public void setFillRule(FillRule fillRule) {
		this.fillRule = fillRule;
	}

	/**
	 * @return the marker
	 */
	public String getMarker() {
		return marker;
	}

	/**
	 * @param marker the marker to set
	 */
	public void setMarker(String marker) {
		this.marker = marker;
	}

	/**
	 * @return the markerEnd
	 */
	public String getMarkerEnd() {
		return markerEnd;
	}

	/**
	 * @param markerEnd the markerEnd to set
	 */
	public void setMarkerEnd(String markerEnd) {
		this.markerEnd = markerEnd;
	}

	/**
	 * @return the markerMid
	 */
	public String getMarkerMid() {
		return markerMid;
	}

	/**
	 * @param markerMid the markerMid to set
	 */
	public void setMarkerMid(String markerMid) {
		this.markerMid = markerMid;
	}

	/**
	 * @return the markerStart
	 */
	public String getMarkerStart() {
		return markerStart;
	}

	/**
	 * @param markerStart the markerStart to set
	 */
	public void setMarkerStart(String markerStart) {
		this.markerStart = markerStart;
	}

	/**
	 * @return the opacity
	 */
	public Percent getOpacity() {
		return opacity;
	}

	/**
	 * @param opacity the opacity to set
	 */
	public void setOpacity(Percent opacity) {
		this.opacity = opacity;
	}

	/**
	 * @return the overflow
	 */
	public Overflow getOverflow() {
		return overflow;
	}

	/**
	 * @param overflow the overflow to set
	 */
	public void setOverflow(Overflow overflow) {
		this.overflow = overflow;
	}

	/**
	 * @return the stroke
	 */
	public Color getStroke() {
		return stroke;
	}

	/**
	 * @param stroke the stroke to set
	 */
	public void setStroke(Color stroke) {
		this.stroke = stroke;
	}

	/**
	 * @return the strokeDasharray
	 */
	public DashArray getStrokeDasharray() {
		return strokeDasharray;
	}

	/**
	 * @param strokeDasharray the strokeDasharray to set
	 */
	public void setStrokeDasharray(DashArray strokeDasharray) {
		this.strokeDasharray = strokeDasharray;
	}

	/**
	 * @return the strokeDashoffset
	 */
	public Length getStrokeDashoffset() {
		return strokeDashoffset;
	}

	/**
	 * @param strokeDashoffset the strokeDashoffset to set
	 */
	public void setStrokeDashoffset(Length strokeDashoffset) {
		this.strokeDashoffset = strokeDashoffset;
	}

	/**
	 * @return the strokeLinecap
	 */
	public Linecap getStrokeLinecap() {
		return strokeLinecap;
	}

	/**
	 * @param strokeLinecap the strokeLinecap to set
	 */
	public void setStrokeLinecap(Linecap strokeLinecap) {
		this.strokeLinecap = strokeLinecap;
	}

	/**
	 * @return the strokeLinejoin
	 */
	public Linejoin getStrokeLinejoin() {
		return strokeLinejoin;
	}

	/**
	 * @param strokeLinejoin the strokeLinejoin to set
	 */
	public void setStrokeLinejoin(Linejoin strokeLinejoin) {
		this.strokeLinejoin = strokeLinejoin;
	}

	/**
	 * @return the strokeMiterlimit
	 */
	public Double getStrokeMiterlimit() {
		return strokeMiterlimit;
	}

	/**
	 * @param strokeMiterlimit the strokeMiterlimit to set
	 */
	public void setStrokeMiterlimit(Double strokeMiterlimit) {
		this.strokeMiterlimit = strokeMiterlimit;
	}

	/**
	 * @return the strokeOpacity
	 */
	public Percent getStrokeOpacity() {
		return strokeOpacity;
	}

	/**
	 * @param strokeOpacity the strokeOpacity to set
	 */
	public void setStrokeOpacity(Percent strokeOpacity) {
		this.strokeOpacity = strokeOpacity;
	}

	/**
	 * @return the strokeWidth
	 */
	public Length getStrokeWidth() {
		return strokeWidth;
	}

	/**
	 * @param strokeWidth the strokeWidth to set
	 */
	public void setStrokeWidth(Length strokeWidth) {
		if (Objects.isNull(strokeWidth)) {
			stroke = Color.NONE;
		}
		this.strokeWidth = strokeWidth;
	}

	/**
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	/**
	 * @param name style item name
	 * @return style item value
	 */
	public Object get(String name) {
		return HELPER.get(this, name);
	}

	/**
	 * @param name  style item name
	 * @param value style item value
	 */
	public void set(String name, Object value) {
		HELPER.set(this, name, value);
	}

	/**
	 * Parses style item and put the value into the style.
	 *
	 * @param name  style item name
	 * @param value style item value
	 */
	public void parseItem(String name, String value) {
		if (name == null) {
			throw new SvgException("Style item name should not be null");
		}
		if (value == null) {
			throw new SvgException("Style item value should not be null");
		}
		HELPER.parseItem(this, name.trim(), value.trim());
	}

	@Override
	public String toString() {
		return HELPER.format(this);
	}

	/**
	 * @return copy of the style
	 */
	public Style copy() {
		return HELPER.copy(this);
	}

	/**
	 * @param name name
	 * @return {@code true} if given name is supported
	 */
	public static boolean isNameSupported(String name) {
		return HELPER.types.containsKey(name);
	}

	/**
	 * @return available style item names as {@link Stream}
	 */
	public static Stream<String> names() {
		return HELPER.types.keySet().stream();
	}

	/**
	 * @return default style
	 */
	public static Style getDefault() {
		return HELPER.getDefault();
	}

	/**
	 * @param style style
	 * @return {@code true} if style is empty
	 */
	public static boolean isEmpty(Style style) {
		if (style == null) {
			return true;
		}
		return HELPER.isEmpty(style);
	}

	private static final class Helper {

		private final Map<String, Field> fields = new HashMap<>();
		private final Map<String, StyleItemType> types = new LinkedHashMap<>();

		private Helper() {
			for (Field field : Style.class.getDeclaredFields()) {
				StyleItem item = field.getAnnotation(StyleItem.class);
				if (item != null) {
					fields.put(item.name(), field);
					field.setAccessible(true);
					types.put(item.name(), item.type());
				}
			}
		}

		private Object get(Style style, String name) {
			Field field = fields.get(name);
			if (field == null) {
				return null;
			}
			try {
				return field.get(style);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new SvgException(e);
			}
		}

		private void set(Style style, String name, Object value) {
			Field field = fields.get(name);
			if (field != null) {
				try {
					field.set(style, value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new SvgException(e);
				}
			}
		}

		private Style getDefault() {
			try {
				Style style = new Style();
				for (Map.Entry<String, StyleItemType> item : types.entrySet()) {
					Field field = fields.get(item.getKey());
					Object value = item.getValue().getDefault();
					if (value != null) {
						field.set(style, value);
					}
				}
				return style;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new SvgException(e);
			}
		}

		private Style copy(Style source) {
			try {
				Style style = new Style();
				for (Map.Entry<String, StyleItemType> item : types.entrySet()) {
					Field field = fields.get(item.getKey());
					Object value = field.get(source);
					if (value != null) {
						field.set(style, value);
					}
				}
				return style;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new SvgException(e);
			}
		}

		private boolean isEmpty(Style style) {
			try {
				for (Map.Entry<String, StyleItemType> item : types.entrySet()) {
					Field field = fields.get(item.getKey());
					Object value = field.get(style);
					if (value != null) {
						return false;
					}
				}
				return style.unsupportedYet.isEmpty();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new SvgException(e);
			}
		}

		private void parseItem(Style style, String name, String value) {
			Field field = fields.get(name);
			StyleItemType type = types.get(name);
			if (field == null || type == null) {
				style.unsupportedYet += String.format("%s:%s;", name, value);
				LOG.warn(() -> "Unsupported style item: " + name + " : " + value);
			} else {
				try {
					field.set(style, type.parse(value));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new SvgException(e);
				}
			}
		}

		private String format(Style style) {
			try {
				StringBuilder builder = new StringBuilder();
				for (Map.Entry<String, StyleItemType> item : types.entrySet()) {
					Field field = fields.get(item.getKey());
					Object value = field.get(style);
					if (value != null) {
						builder.append(item.getKey()).append(':').append(item.getValue().format(value)).append(';');
					}
				}
				builder.append(style.unsupportedYet);
				if (builder.length() > 0) {
					builder.setLength(builder.length() - 1);
				}
				return builder.toString();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new SvgException(e);
			}
		}
	}
}
