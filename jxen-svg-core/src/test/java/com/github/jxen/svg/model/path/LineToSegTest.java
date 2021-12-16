package com.github.jxen.svg.model.path;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.jxen.svg.model.geometry.Point;
import com.github.jxen.svg.transform.Transform;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class LineToSegTest {

  @Test
  void testAbsL1() {
    Point refOld = new Point(1, 1);
    PathSeg seg = PathSegType.L_ABS.create(Arrays.asList(1.0, 1.0), refOld);
    Transform transform = Transform.of(1, 0, 1, 1, 1, 1);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.L_ABS, transformed.getType());
    assertEquals("3,2", transformed.toString());
  }

  @Test
  void testAbsL2() {
    Point refOld = new Point(1, 1);
    PathSeg seg = PathSegType.L_ABS.create(Arrays.asList(1.0, 1.0), refOld);
    Transform transform = Transform.of(1, 0, -1, 1, 0, 0);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.V_ABS, transformed.getType());
    assertEquals("1", transformed.toString());
  }

  @Test
  void testAbsL3() {
    Point refOld = new Point(1, 1);
    PathSeg seg = PathSegType.L_ABS.create(Arrays.asList(1.0, 1.0), refOld);
    Transform transform = Transform.of(1, -1, 0, 1, 0, 0);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.H_ABS, transformed.getType());
    assertEquals("1", transformed.toString());
  }

  @Test
  void testRelL1() {
    Point refOld = new Point(1, 1);
    PathSeg seg = PathSegType.L_REL.create(Arrays.asList(1.0, 1.0), refOld);
    Transform transform = Transform.of(1, 0, 1, 1, 1, 1);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.L_REL, transformed.getType());
    assertEquals("5,3", transformed.toString());
  }

  @Test
  void testRelL2() {
    Point refOld = new Point(1, 1);
    PathSeg seg = PathSegType.L_REL.create(Arrays.asList(1.0, 1.0), refOld);
    Transform transform = Transform.of(1, 0, -1, 1, 0, 0);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.V_REL, transformed.getType());
    assertEquals("2", transformed.toString());
  }

  @Test
  void testRelL3() {
    Point refOld = new Point(1, 1);
    PathSeg seg = PathSegType.L_REL.create(Arrays.asList(1.0, 1.0), refOld);
    Transform transform = Transform.of(1, -1, 0, 1, 0, 0);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.H_REL, transformed.getType());
    assertEquals("2", transformed.toString());
  }

  @Test
  void testAbsH1() {
    Point refOld = new Point(1, 1);
    PathSeg seg = PathSegType.H_ABS.create(Collections.singletonList(1.0), refOld);
    Transform transform = Transform.of(1, 0, 1, 1, 1, 1);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.L_ABS, transformed.getType());
    assertEquals("3,2", transformed.toString());
  }

  @Test
  void testRelH1() {
    Point refOld = new Point(1, 1);
    PathSeg seg = PathSegType.H_REL.create(Collections.singletonList(1.0), refOld);
    Transform transform = Transform.of(1, 0, 1, 1, 1, 1);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.L_REL, transformed.getType());
    assertEquals("4,2", transformed.toString());
  }

  @Test
  void testAbsV1() {
    Point refOld = new Point(1, 1);
    PathSeg seg = PathSegType.V_ABS.create(Collections.singletonList(1.0), refOld);
    Transform transform = Transform.of(1, 0, 1, 1, 1, 1);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.L_ABS, transformed.getType());
    assertEquals("3,2", transformed.toString());
  }

  @Test
  void testRelV1() {
    Point refOld = new Point(1, 1);
    PathSeg seg = PathSegType.V_REL.create(Collections.singletonList(1.0), refOld);
    Transform transform = Transform.of(1, 0, 1, 1, 1, 1);
    PathSeg transformed = seg.transform(transform, refOld, new Point(0, 0));
    assertEquals(PathSegType.L_REL, transformed.getType());
    assertEquals("4,3", transformed.toString());
  }
}
