package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import game.MyConverter;


public class TestMyConverter {

  @Before
  public void setUp() throws Exception {}

  public static double EPSILON = 0.0001;

  @Test
  public void testFeetToMeters() {
    double expected = 0.0508;
    double actual = MyConverter.englishToMeters(0, 2);
    Assert.assertEquals(expected, actual, EPSILON);
    // remember those “deprecated” warnings?
    expected = 0.3556;
    actual = MyConverter.englishToMeters(1, 2);
    Assert.assertEquals(expected, actual, EPSILON);
    // remember those “deprecated” warnings?
  }

}
