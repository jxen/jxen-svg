package com.github.jxen.svg.api;

/**
 * {@code SvgException} class is general exception for SVG Tools project.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public class SvgException extends RuntimeException {

	private static final long serialVersionUID = 4072429213572700936L;

	/**
	 * @param message message
	 * @param cause   cause
	 */
	public SvgException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message message
	 */
	public SvgException(String message) {
		super(message);
	}

	/**
	 * @param cause cause
	 */
	public SvgException(Throwable cause) {
		super(cause);
	}
}
