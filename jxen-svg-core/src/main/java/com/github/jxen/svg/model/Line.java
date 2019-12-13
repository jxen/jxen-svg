package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Attr;
import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.type.Length;
import com.github.jxen.svg.model.type.ValueType;

/**
 * {@code Line} class represents SVG line element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("line")
public class Line extends Element<Line> {

	@Attr(name = "x1", type = ValueType.LENGTH)
	private Length x1;

	@Attr(name = "y1", type = ValueType.LENGTH)
	private Length y1;

	@Attr(name = "x2", type = ValueType.LENGTH)
	private Length x2;

	@Attr(name = "y2", type = ValueType.LENGTH)
	private Length y2;

	/**
	 * Initializes element.
	 */
	public Line() {
		super(Line.class);
	}

	/**
	 * @return the x1
	 */
	public Length getX1() {
		return x1;
	}

	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(Length x1) {
		this.x1 = x1;
	}

	/**
	 * @return the y1
	 */
	public Length getY1() {
		return y1;
	}

	/**
	 * @param y1 the y1 to set
	 */
	public void setY1(Length y1) {
		this.y1 = y1;
	}

	/**
	 * @return the x2
	 */
	public Length getX2() {
		return x2;
	}

	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(Length x2) {
		this.x2 = x2;
	}

	/**
	 * @return the y2
	 */
	public Length getY2() {
		return y2;
	}

	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(Length y2) {
		this.y2 = y2;
	}

	@Override
	public void accept(SvgVisitor t) {
		t.visit(this);
	}
}
