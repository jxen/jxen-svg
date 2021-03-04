package com.github.jxen.svg.api;

import com.github.jxen.svg.model.Circle;
import com.github.jxen.svg.model.Defs;
import com.github.jxen.svg.model.Desc;
import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.model.Ellipse;
import com.github.jxen.svg.model.G;
import com.github.jxen.svg.model.Line;
import com.github.jxen.svg.model.Metadata;
import com.github.jxen.svg.model.Path;
import com.github.jxen.svg.model.Polygon;
import com.github.jxen.svg.model.Rect;
import com.github.jxen.svg.model.Svg;
import com.github.jxen.svg.model.Title;
import com.github.jxen.svg.model.Use;

/**
 * {@code SvgVisitor} interface is general contract for implementing Visitor design pattern
 * that can be applied to the SVG elements.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public interface SvgVisitor {

	/**
	 * @param circle circle
	 */
	default void visit(Circle circle) {
		process(circle);
	}

	/**
	 * @param defs defs
	 */
	default void visit(Defs defs) {
		process(defs);
	}

	/**
	 * @param desc desc
	 */
	default void visit(Desc desc) {
		process(desc);
	}

	/**
	 * @param ellipse ellipse
	 */
	default void visit(Ellipse ellipse) {
		process(ellipse);
	}

	/**
	 * @param g g
	 */
	default void visit(G g) {
		process(g);
	}

	/**
	 * @param line line
	 */
	default void visit(Line line) {
		process(line);
	}

	/**
	 * @param metadata metadata
	 */
	default void visit(Metadata metadata) {
		process(metadata);
	}

	/**
	 * @param path path
	 */
	default void visit(Path path) {
		process(path);
	}

	/**
	 * @param polygon polygon
	 */
	default void visit(Polygon polygon) {
		process(polygon);
	}

	/**
	 * @param rect rect
	 */
	default void visit(Rect rect) {
		process(rect);
	}

	/**
	 * @param svg svg
	 */
	default void visit(Svg svg) {
		process(svg);
	}

	/**
	 * @param title title
	 */
	default void visit(Title title) {
		process(title);
	}

	/**
	 * @param use use
	 */
	default void visit(Use use) {
		process(use);
	}

	/**
	 * Default processing of element.
	 *
	 * @param element element to be processed
	 */
	default void process(Element<?> element) {
		element.stream().forEach(e -> e.accept(this));
	}
}
