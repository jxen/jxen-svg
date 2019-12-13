package com.github.jxen.svg.optimizer;

import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.Use;
import java.util.HashSet;
import java.util.Set;

class FindIdVisitor implements SvgVisitor {

	private final Set<String> whiteList = new HashSet<>();

	@Override
	public void visit(Use use) {
		whiteList.add(use.getHref().substring(1));
		process(use);
	}

	/**
	 * @return the white list
	 */
	public Set<String> getWhiteList() {
		return whiteList;
	}
}
