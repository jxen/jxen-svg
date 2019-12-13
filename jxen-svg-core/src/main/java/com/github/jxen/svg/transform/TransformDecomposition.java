package com.github.jxen.svg.transform;

import java.util.Collections;
import java.util.List;

/**
 * {@code TransformDecomposition} class contains affine transformation decomposition.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public abstract class TransformDecomposition {

	private final List<Transform> transforms;

	/**
	 * @param transforms transforms
	 */
	public TransformDecomposition(List<Transform> transforms) {
		this.transforms = Collections.unmodifiableList(transforms);
	}

	/**
	 * @return the transforms
	 */
	public List<Transform> getTransforms() {
		return transforms;
	}

	/**
	 * @return scale part
	 */
	public Transform scale() {
		return transforms.get(scaleIndex());
	}

	abstract int scaleIndex();

	/**
	 * @return skewX part
	 */
	public Transform skewX() {
		return transforms.get(skewXIndex());
	}

	abstract int skewXIndex();

	/**
	 * @return rotate part
	 */
	public Transform rotate() {
		return transforms.get(rotateIndex());
	}

	abstract int rotateIndex();

	/**
	 * @return translate part
	 */
	public Transform translate() {
		return transforms.get(translateIndex());
	}

	abstract int translateIndex();
}
