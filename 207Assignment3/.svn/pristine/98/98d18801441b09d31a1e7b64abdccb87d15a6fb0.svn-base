// **********************************************************
// Assignment3:
// CDF user_name: c5zhanim  
//
// Author: Yu Ang Zhang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// *********************************************************
package test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import operator.CoAuthorListGenerator;

public class CoAuthorListGeneratorTest {
  private CoAuthorListGenerator generator;
  /**
   * Initially set up the test
   * 
   */

  @Before
  public void setUp(){
    try{
    generator = new CoAuthorListGenerator();
    generator.getCoAuthorList().add("Leon");
    generator.getCoAuthorList().add("Mary");
    
    }catch (Exception e){
      fail("failed to construct an object");
    }

  }
  /**
   * Test whether the method works as expected
   * 
   */
  
  @Test
  public void testProduceAllCoAuthors() {
    try{
    String result = generator.produceAllCoAuthors();
    System.out.println(result);
    assertEquals(result,"7. Co-Author list sorted (Total: 2):\nLeon\nMary\n");
    }catch( Exception e){
      fail("generator failed to construct");
    }
  }

}
