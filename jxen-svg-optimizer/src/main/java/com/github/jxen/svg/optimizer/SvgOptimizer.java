package com.github.jxen.svg.optimizer;

import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.style.Style;

/**
 * {@code SvgOptimizer} class does SVG model optimizations.
 *
 * @author Denis Murashev
 *
 * @since SVG Optomizer 0.1
 */
public class SvgOptimizer {

	/**
	 * Optimizes SVG tree.
	 *
	 * @param root root element
	 * @return optimized element
	 */
	public Element<?> optimize(Element<?> root) {
		root.setEffective(Style.getDefault());
		root.accept(new EffectiveVisitor());
		root.accept(new TransformVisitor());
		root.accept(new StyleVisitor());
		FindIdVisitor idVisitor = new FindIdVisitor();
		root.accept(idVisitor);
		root.accept(new RemoveIdVisitor(idVisitor.getWhiteList()));
		root.accept(new RedundantVisitor());
		return root;
	}
}
