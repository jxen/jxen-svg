package com.github.jxen.svg.model;

import static com.github.jxen.svg.annotation.SvgNamespaces.XLINK_NAMESPACE;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.model.type.ValueType;

/**
 * {@code Use} class represents SVG use element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("use")
public class Use extends Element<Use> {

	@Attr(name = "href", namespace = XLINK_NAMESPACE, type = ValueType.STRING)
	private String href;

	@Attr(name = "x", type = ValueType.LENGTH)
	private Length x;

	@Attr(name = "y", type = ValueType.LENGTH)
	private Length y;

	/**
	 * Initializes element.
	 */
	public Use() {
		super(Use.class);
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the x
	 */
	public Length getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(Length x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Length getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(Length y) {
		this.y = y;
	}

	@Override
	public void accept(SvgVisitor t) {
		t.visit(this);
	}
}
