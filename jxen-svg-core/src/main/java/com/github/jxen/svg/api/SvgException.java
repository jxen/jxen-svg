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
   * Initializes with given values.
   *
   * @param message message
   * @param cause   cause
   */
  public SvgException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Initializes with given value.
   *
   * @param message message
   */
  public SvgException(String message) {
    super(message);
  }

  /**
   * Initializes with given values.
   *
   * @param cause cause
   */
  public SvgException(Throwable cause) {
    super(cause);
  }
}
