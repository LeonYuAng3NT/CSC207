package test;


import org.junit.*;

import windchill.BadInputException;
import windchill.MyTempConverter;

public class TestWindchillCalculations {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testWindChill() {
    long expected = -11;
    long actual = MyTempConverter.windChill(5, 0);
    Assert.assertEquals(expected, actual); //expect a “deprecated” warning
    expected = 3;
    actual = MyTempConverter.windChill(10, 15);
    Assert.assertEquals(expected, actual); //expect a “deprecated” warning
  }
  @Test (expected = BadInputException.class)
  public void testWindchillLowSpeed() throws BadInputException {
  long actual = MyTempConverter.windChill(4, 10);
  }
}
