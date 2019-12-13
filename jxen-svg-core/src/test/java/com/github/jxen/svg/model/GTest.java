package com.github.jxen.svg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GTest {

	@Test
	void testCopy() {
		G g = new G();
		g.setId("1");
		g.add(RectTest.getRect());
		G copy = g.copy();
		assertEquals("1", copy.getId());
		assertEquals(1, (Long) g.stream().count());
	}
}
