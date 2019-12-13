package com.github.jxen.svg.style;

import com.github.jxen.svg.api.SvgException;

/**
 * {@code Display} enum represents display style item.
 *
 * @author Denis Murashev
 *
 * @since SVG Core 0.1
 */
public enum Display {

	/**
	 * Inline.
	 */
	INLINE("inline"),

	/**
	 * Block.
	 */
	BLOCK("block"),

	/**
	 * List item.
	 */
	LIST_ITEM("list-item"),

	/**
	 * Run-in.
	 */
	RUN_IN("run-in"),

	/**
	 * Compact.
	 */
	COMPACT("compact"),

	/**
	 * Marker.
	 */
	MARKER("marker"),

	/**
	 * Table.
	 */
	TABLE("table"),

	/**
	 * Inline table.
	 */
	INLINE_TABLE("inline-table"),

	/**
	 * Table row group.
	 */
	TABLE_ROW_GROUP("table-row-group"),

	/**
	 * Table header group.
	 */
	TABLE_HEADER_GROUP("table-header-group"),

	/**
	 * Table footer group.
	 */
	TABLE_FOOTER_GROUP("table-footer-group"),

	/**
	 * Table row.
	 */
	TABLE_ROW("table-row"),

	/**
	 * Table column group.
	 */
	TABLE_COLUMN_GROUP("table-column-group"),

	/**
	 * Table column.
	 */
	TABLE_COLUMN("table-column"),

	/**
	 * Table cell.
	 */
	TABLE_CELL("table-cell"),

	/**
	 * Table caption.
	 */
	TABLE_CAPTION("table-caption"),

	/**
	 * None.
	 */
	NONE("none");

	private final String name;

	Display(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * @param value value
	 * @return parsed object
	 */
	public static Display parse(String value) {
		for (Display display : values()) {
			if (display.name.equals(value)) {
				return display;
			}
		}
		throw new SvgException("Unsupported display type:" + value);
	}
}
