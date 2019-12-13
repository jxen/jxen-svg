package com.github.jxen.svg.optimizer;

import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.Defs;
import com.github.jxen.svg.model.Desc;
import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.model.Metadata;
import com.github.jxen.svg.model.Title;
import com.github.jxen.svg.style.Style;
import java.util.Optional;

class StyleVisitor implements SvgVisitor {

	@Override
	public void visit(Defs defs) {
		process(defs);
		defs.setStyle(null);
	}

	@Override
	public void visit(Desc desc) {
		desc.setStyle(null);
	}

	@Override
	public void visit(Metadata metadata) {
		metadata.setStyle(null);
	}

	@Override
	public void visit(Title title) {
		title.setStyle(null);
	}

	@Override
	public void process(Element<?> element) {
		element.stream().forEach(c -> c.accept(this));
		Style style = getStyle(element);
		Style effective = element.getEffective();
		Style parent = Optional.ofNullable(element.getParent()).map(Element::getEffective).orElse(Style.getDefault());
		Style.names().forEach(n -> {
			Object item = effective.get(n);
			if (parent.get(n).equals(item)) {
				style.set(n, null);
			} else {
				style.set(n, item);
			}
		});
	}

	private Style getStyle(Element<?> element) {
		Style style = element.getStyle();
		if (style == null) {
			style = new Style();
			element.setStyle(style);
		}
		return style;
	}
}
