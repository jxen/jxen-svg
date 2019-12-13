package com.github.jxen.svg.model;

import com.github.jxen.svg.annotation.Tag;
import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.style.Style;

/**
 * {@code Title} class represents SVG title element.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
@Tag("title")
public class Title extends Element<Title> {

	/**
	 * Initializes element.
	 */
	public Title() {
		super(Title.class);
		setStyle(Style.getDefault());
	}

	@Override
	public void accept(SvgVisitor t) {
		t.visit(this);
	}
}
