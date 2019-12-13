package com.github.jxen.svg.parser;

import com.github.jxen.svg.api.SvgException;
import java.util.function.Consumer;

class Splitter {

	private static final String ERROR_WRONG_SYMBOL = "Unexpected symbol: ";

	private final String value;
	private final Consumer<String> consumer;
	private int index;
	private int start;
	private boolean minus;
	private boolean period;
	private boolean exp;

	Splitter(String value, Consumer<String> consumer) {
		this.value = value;
		this.consumer = consumer;
	}

	void split() {
		while (index < value.length()) {
			char ch = value.charAt(index);
			if (isCommand(ch)) {
				processBlock();
				consumer.accept(String.valueOf(ch));
			} else if (isSkipped(ch)) {
				processBlock();
			} else if (ch == '-' || ch == '+') {
				processSign(ch);
			} else if (ch == '.') {
				processPeriod();
			} else if (ch == 'E' || ch == 'e') {
				processExp(ch);
			} else if (isDigit(ch)) {
				index++;
			} else {
				throw new SvgException(ERROR_WRONG_SYMBOL + ch);
			}
		}
		processBlock();
	}

	/**
	 * Checks if given character is command marker.
	 *
	 * @param ch character
	 * @return result of check
	 */
	boolean isCommand(char ch) {
		return false;
	}

	private boolean isSkipped(char ch) {
		return "\t\r\n, ".indexOf(ch) >= 0;
	}

	private boolean isDigit(char ch) {
		return "1234567890".indexOf(ch) >= 0;
	}

	private void processBlock() {
		if (start < index) {
			consumer.accept(value.substring(start, index));
		}
		index++;
		start = index;
		minus = false;
		period = false;
		exp = false;
	}

	private void processSign(char ch) {
		if (minus && index - start == 1) {
			throw new SvgException(ERROR_WRONG_SYMBOL + ch);
		}
		if (exp) {
			index++;
		} else {
			processBlock();
			start--;
		}
		minus = true;
	}

	private void processPeriod() {
		if (period && index - start == 1) {
			throw new SvgException(ERROR_WRONG_SYMBOL + '.');
		}
		if (period) {
			processBlock();
			start--;
		} else {
			index++;
		}
		period = true;
	}

	private void processExp(char ch) {
		if (exp) {
			throw new SvgException(ERROR_WRONG_SYMBOL + ch);
		}
		exp = true;
		minus = false;
		index++;
	}
}
