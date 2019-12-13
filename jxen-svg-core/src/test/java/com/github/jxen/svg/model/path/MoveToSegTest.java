package com.github.jxen.svg.model.path;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class MoveToSegTest {

	@Test
	void testAbs() {
		Point refOld = new Point(1, 1);
		PathSeg seg = PathSegType.M_ABS.create(Arrays.asList(1.0, 1.0), refOld);
		Transform transform = Transform.of(1, 0, 1, 1, 1, 1);
		PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
		assertEquals(PathSegType.M_ABS, transformed.getType());
		assertEquals("3,2", transformed.toString());
	}

	@Test
	void testRel() {
		Point refOld = new Point(1, 1);
		PathSeg seg = PathSegType.M_REL.create(Arrays.asList(1.0, 1.0), refOld);
		Transform transform = Transform.of(1, 0, 1, 1, 1, 1);
		PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
		assertEquals(PathSegType.M_REL, transformed.getType());
		assertEquals("5,3", transformed.toString());
	}
}
