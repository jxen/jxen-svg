package com.github.jxen.svg.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.jxen.svg.transform.Transform;
import java.util.List;
import org.junit.jupiter.api.Test;

class TransformParserTest {

	@Test
	void testMatrix1() {
		String value = "matrix(1, 1, -1, 1, 10, 10)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(1, list.size());
		assertEquals(Transform.of(1, 1, -1, 1, 10, 10), list.get(0));
	}

	@Test
	void testTranslate1() {
		String value = "translate(10)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(1, list.size());
		assertEquals(Transform.translate(10, 0), list.get(0));
	}

	@Test
	void testTranslate2() {
		String value = "translate(10, 10)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(1, list.size());
		assertEquals(Transform.translate(10, 10), list.get(0));
	}

	@Test
	void testScale1() {
		String value = "scale(10)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(1, list.size());
		assertEquals(Transform.scale(10, 10), list.get(0));
	}

	@Test
	void testScale2() {
		String value = "scale(10, 10)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(1, list.size());
		assertEquals(Transform.scale(10, 10), list.get(0));
	}

	@Test
	void testRotate1() {
		String value = "rotate(90)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(1, list.size());
		assertEquals(Transform.rotate(90), list.get(0));
	}

	@Test
	void testSkewX() {
		String value = "skewX(45)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(1, list.size());
		assertEquals(Transform.skewX(45), list.get(0));
	}

	@Test
	void testSkewY() {
		String value = "skewY(45)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(1, list.size());
		assertEquals(Transform.skewY(45), list.get(0));
	}

	@Test
	void testRotate2() {
		String value = "rotate(90, 10, 10)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(1, list.size());
		assertEquals(Transform.rotate(90, 10, 10), list.get(0));
	}

	@Test
	void testSeveral1() {
		String value = "translate(10)translate(10, 10)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(2, list.size());
		assertEquals(Transform.translate(10, 0), list.get(0));
		assertEquals(Transform.translate(10, 10), list.get(1));
	}

	@Test
	void testSeveral2() {
		String value = "translate(10) translate(10, 10)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(2, list.size());
		assertEquals(Transform.translate(10, 0), list.get(0));
		assertEquals(Transform.translate(10, 10), list.get(1));
	}

	@Test
	void testSeveral3() {
		String value = "translate(10),translate(10, 10)";
		List<Transform> list = new TransformParser().parse(value);
		assertEquals(2, list.size());
		assertEquals(Transform.translate(10, 0), list.get(0));
		assertEquals(Transform.translate(10, 10), list.get(1));
	}
}
