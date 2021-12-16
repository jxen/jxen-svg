package com.github.jxen.svg.model.path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class ArcSegTest {

  @Test
  void testAbs() {
    Point refOld = new Point(0, 0);
    PathSeg seg = PathSegType.A_ABS.create(Arrays.asList(10.0, 20.0, -0.00001, 1.0, 0.0, 20.0, 10.0), refOld);
    Transform transform = Transform.of(1, 0, 0, 1, 10, 10);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.A_ABS, transformed.getType());
    assertEquals("10,20 0 1 0 30,20", transformed.toString());
  }

  @Test
  void testRel() {
    Point refOld = new Point(10, 10);
    PathSeg seg = PathSegType.A_REL.create(Arrays.asList(10.0, 20.0, 0.0, 0.0, 0.0, 20.0, 10.0), refOld);
    Transform transform = Transform.of(1, 0, 0, 1, 10, 10);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.A_REL, transformed.getType());
    assertEquals("10,20 0 0 0 40,30", transformed.toString());
  }

  @Test
  void testToAbsCase1() {
    PathSeg seg = PathSegType.A_ABS.create(Arrays.asList(10.0, 20.0, 0.0, 0.0, 0.0, 20.0, 10.0), new Point(0, 0));
    assertSame(seg, seg.toAbs(new Point(0, 0)));
  }

  @Test
  void testToAbsCase2() {
    PathSeg seg = PathSegType.A_REL.create(Arrays.asList(10.0, 20.0, 0.0, 0.0, 0.0, 20.0, 10.0), new Point(0, 0));
    assertEquals("10,20 0 0 0 20,10", seg.toAbs(new Point(0, 0)).toString());
  }

  @Test
  void testToRelCase1() {
    PathSeg seg = PathSegType.A_REL.create(Arrays.asList(10.0, 20.0, 0.0, 0.0, 0.0, 20.0, 10.0), new Point(0, 0));
    assertSame(seg, seg.toRel(new Point(0, 0)));
  }

  @Test
  void testToRelCase2() {
    PathSeg seg = PathSegType.A_ABS.create(Arrays.asList(10.0, 20.0, 0.0, 0.0, 0.0, 20.0, 10.0), new Point(0, 0));
    assertEquals("10,20 0 0 0 20,10", seg.toRel(new Point(0, 0)).toString());
  }
}
