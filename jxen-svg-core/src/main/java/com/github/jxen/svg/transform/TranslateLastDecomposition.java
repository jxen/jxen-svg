package com.github.jxen.svg.transform;

import java.util.List;

/**
 * {@code TransformDecomposition} class contains affine transformation decomposition (translate transform last).
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class TranslateLastDecomposition extends TransformDecomposition {

	private static final int SCALE = 0;
	private static final int SKEW_X = 1;
	private static final int ROTATE = 2;
	private static final int TRANSLATE = 3;

	/**
	 * @param transforms transforms
	 */
	public TranslateLastDecomposition(List<Transform> transforms) {
		super(transforms);
	}

	@Override
	int scaleIndex() {
		return SCALE;
	}

	@Override
	int skewXIndex() {
		return SKEW_X;
	}

	@Override
	int rotateIndex() {
		return ROTATE;
	}

	@Override
	int translateIndex() {
		return TRANSLATE;
	}
}
