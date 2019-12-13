package com.github.jxen.svg.spi;

/**
 * {@code SvgService} interface is service provider for SVG Tools project.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public interface SvgService {

	/**
	 * @return precision used in calculations
	 */
	default double precision() {
		return Constants.PRECISION;
	}
}
