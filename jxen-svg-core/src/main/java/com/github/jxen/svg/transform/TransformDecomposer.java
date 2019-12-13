package com.github.jxen.svg.transform;

/**
 * {@code TransformDecomposer} interface is general contract for affine transformation decomposition.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public interface TransformDecomposer {

	/**
	 * Decompose given transformation.
	 *
	 * @param transform transform to be decomposed
	 * @return decomposition
	 */
	TransformDecomposition decompose(Transform transform);
}
