package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.style.Style;

/**
 * {@code Desc} class represents SVG desc element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("desc")
public class Desc extends Element<Desc> {

	/**
	 * Initializes element.
	 */
	public Desc() {
		super(Desc.class);
		setStyle(Style.getDefault());
	}

	@Override
	public void accept(SvgVisitor t) {
		t.visit(this);
	}
}
