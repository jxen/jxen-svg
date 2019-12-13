package com.github.jxen.svg.optimizer;

import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.Element;
import java.util.Set;

class RemoveIdVisitor implements SvgVisitor {

	private final Set<String> whiteList;

	/**
	 * @param whiteList white list
	 */
	RemoveIdVisitor(Set<String> whiteList) {
		this.whiteList = whiteList;
	}

	@Override
	public void process(Element<?> element) {
		if (!whiteList.contains(element.getId())) {
			element.setId(null);
		}
		SvgVisitor.super.process(element);
	}
}
