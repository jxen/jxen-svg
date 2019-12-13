package com.github.jxen.svg.model.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.jxen.math.linear.Column;
import com.github.jxen.svg.api.SvgException;
import com.github.jxen.svg.transform.Transform;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class PointTest {

	private static final double SQRT2 = Math.sqrt(2);

	@Test
	void testRotate1() {
		Transform rotate = Transform.rotate(45, 10, 10);
		Point point = new Point(0, 10);
		Point actual = point.transform(rotate);
		double x = 10 - 10 / SQRT2;
		assertEquals(x, actual.getX(), 1e-5);
		assertEquals(x, actual.getY(), 1e-5);
	}

	@Test
	void testFromFailure() {
		assertThrows(SvgException.class, () -> Point.from(Column.of(1.0)));
	}

	@Test
	void testFormat() {
		assertEquals("1,1", new Point(1.0, 1.0).format("#.#"));
	}

	@Test
	void testToString() {
		Locale.setDefault(Locale.US);
		assertEquals("(1.000000, 1.000000)", new Point(1.0, 1.0).toString());
	}

	@Test
	void testHashCode() {
		assertEquals(-33553471, new Point(1.0, 1.0).hashCode());
	}

	@Test
	void testNotEqualsCase1() {
		assertNotEquals(new Point(0.0, 1.0), new Point(1.0, 1.0));
	}

	@Test
	void testNotEqualsCase2() {
		assertNotEquals(new Point(1.0, 0.0), new Point(1.0, 1.0));
	}

	@Test
	void testNotEqualsCase3() {
		assertNotEquals(new Point(1.0, 1.0), new Object());
	}
}
