package com.github.jxen.svg.optimizer;

import com.github.jxen.svg.model.Use;
import com.github.jxen.svg.transform.Transform;
import java.util.Collections;

class UseTransformApplier extends TransformApplier<Use> {

	@Override
	void transform(Use use, Transform transform) {
		use.setTransform(Collections.singletonList(transform));
	}
}
