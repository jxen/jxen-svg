package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;

/**
 * {@code Defs} class represents SVG defs element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("defs")
public class Defs extends Element<Defs> {

	/**
	 * Initializes element.
	 */
	public Defs() {
		super(Defs.class);
	}

	@Override
	public void accept(SvgVisitor t) {
		t.visit(this);
	}
}
