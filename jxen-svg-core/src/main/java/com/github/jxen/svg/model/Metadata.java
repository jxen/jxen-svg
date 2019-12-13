package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.style.Style;

/**
 * {@code Metadata} class represents SVG metadata element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("metadata")
public class Metadata extends Element<Metadata> {

	/**
	 * Initializes element.
	 */
	public Metadata() {
		super(Metadata.class);
		setStyle(Style.getDefault());
	}

	@Override
	public void accept(SvgVisitor t) {
		t.visit(this);
	}
}
