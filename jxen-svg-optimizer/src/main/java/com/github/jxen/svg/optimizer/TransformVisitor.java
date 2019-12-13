package com.github.jxen.svg.optimizer;

import com.github.jxen.svg.api.SvgVisitor;
import com.github.jxen.svg.model.Circle;
import com.github.jxen.svg.model.Element;
import com.github.jxen.svg.model.Line;
import com.github.jxen.svg.model.Path;
import com.github.jxen.svg.model.Polygon;
import com.github.jxen.svg.model.Rect;
import com.github.jxen.svg.model.Use;
import com.github.jxen.svg.transform.Transform;
import java.util.ArrayList;
import java.util.List;

class TransformVisitor implements SvgVisitor {

	private final CircleTransformApplier circleTransformApplier = new CircleTransformApplier();
	private final LineTransformApplier lineTransformApplier = new LineTransformApplier();
	private final PathTransformApplier pathTransformApplier = new PathTransformApplier();
	private final PolygonTransformApplier poligonTransformApplier = new PolygonTransformApplier();
	private final RectTransformApplier rectTransformApplier = new RectTransformApplier();
	private final UseTransformApplier useTransformApplier = new UseTransformApplier();

	@Override
	public void visit(Circle circle) {
		circleTransformApplier.apply(circle);
	}

	@Override
	public void visit(Line line) {
		lineTransformApplier.apply(line);
	}

	@Override
	public void visit(Path path) {
		pathTransformApplier.apply(path);
	}

	@Override
	public void visit(Polygon poligon) {
		poligonTransformApplier.apply(poligon);
	}

	@Override
	public void visit(Rect rect) {
		rectTransformApplier.apply(rect);
	}

	@Override
	public void visit(Use use) {
		useTransformApplier.apply(use);
	}

	@Override
	public void process(Element<?> element) {
		element.stream().forEach(e -> {
			List<Transform> transform = element.getTransform();
			if (transform != null) {
				if (e.getTransform() != null) {
					transform = new ArrayList<>(transform);
					transform.addAll(e.getTransform());
				}
				e.setTransform(transform);
			}
			e.accept(this);
		});
		element.setTransform(null);
	}
}
