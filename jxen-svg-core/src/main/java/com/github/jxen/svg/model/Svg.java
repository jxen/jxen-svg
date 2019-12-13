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
public class Svg extends Element<Svg> {

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
	 * @return the width
	 */
	public Length getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Length width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public Length getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Length height) {
		this.height = height;
	}

	/**
	 * @return the viewBox
	 */
	public ViewBox getViewBox() {
		return viewBox;
	}

	/**
	 * @param viewBox the viewBox to set
	 */
	public void setViewBox(ViewBox viewBox) {
		this.viewBox = viewBox;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
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
