// **********************************************************
// Assignment2:

// Student1:
// CDF user_name: c5zhanim
// UT Student #: 1001322847
// Author: Yu Ang Zhang
//
// Student2:
// CDF user_name: c4huanhf
// UT Student #: 1000076927
// Author: Yiming Huang
//

// Student3:
// CDF user_name: c4wangyk
// UT Student #: 999980579
// Author: Yi Jian Wang
//
// Student4:
// CDF user_name: c4wangzd
// UT Student #: 1001282319
// Author: Yu Wang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package test;

import a2a.exceptions.*;
import a2a.foundation.Directory;
import a2a.foundation.File;
import a2a.foundation.FileSystem;
import a2a.operator.FileCreator;
import a2a.operator.RegexFinder;
import a2a.operator.Searcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The regex finder test uses to test if the methods in the RegexFinder
 * are working as expected.
 *
 * @author Yi Jian Wang
 * @author Yu Ang Zhang
 * @author Yiming Huang
 */
public class RegexFinderTest {

  private RegexFinder finder;
  private FileSystem fileSystem;

  /**
   * Set up the RegexFinder test.
   */
  @Before public void setUp() {
    fileSystem = new FileSystem();
    finder = new RegexFinder();
    FileCreator creator = new FileCreator();
    creator.create("first.txt", "Hello", fileSystem.getRoot());
    creator.create("also.txt", "PHP is the best!", fileSystem.getRoot());
    Directory a = new Directory("a", "/a/", fileSystem.getRoot());
    Directory b = new Directory("b", "/b/", fileSystem.getRoot());
    creator.create("second.txt", "01234", a);
    creator.create("third.txt", "!@#", b);

    fileSystem.getRoot().getContent().add(a);
    fileSystem.getRoot().getContent().add(b);
  }

  /**
   * Test if the instance can be created successfully.
   */
  @Test public void testRegexFinder() {
    try {
      finder = new RegexFinder();
    } catch (Exception e) {
      fail("Failed to create an instance of RegexFinder");
    }
  }

  /**
   * Test if the method successfully set up or throw
   * exception in suitable cases.
   */
  @Test public void testSetUp() {
    try {
      finder.setUp("grep [a-z] first.txt", fileSystem);
      finder.setUp("grep -R [a-z] /", fileSystem);
      finder.setUp("grep -R [0-9] .", fileSystem);
      finder.setUp("grep -R [0-9] ..", fileSystem);
      finder.setUp("grep -R [a-z] /..", fileSystem);
    } catch (Exception e) {
      fail("Failed to create an instance of RegexFinder");
    }
  }

  /**
   * Test if the produce method can produce correct string.
   */
  @Test public void testProduce() {
    try {
      finder.setUp("grep [a-z] first.txt", fileSystem);
      assertEquals("/first.txt/: Hello\n", finder.produce());
      finder.setUp("grep -R [a-z] /", fileSystem);
      assertEquals("/first.txt/: Hello\n/also.txt/: PHP is the best!\n",
          finder.produce());
      finder.setUp("grep -R [0-9] .", fileSystem);
      assertEquals("/a/second.txt/: 01234\n", finder.produce());
      finder.setUp("grep -R [0-9] ..", fileSystem);
      assertEquals("/a/second.txt/: 01234\n", finder.produce());
      finder.setUp("grep -R [a-z] /..", fileSystem);
      assertEquals("/first.txt/: Hello\n/also.txt/: PHP is the best!\n",
          finder.produce());
    } catch (Exception e) {
      fail("Failed to create an instance of RegexFinder");
    }
  }

  /**
   * Test if the method can throw out ArgumentDeclareException.
   *
   * @throws ArgumentDeclareException if argument is not correct
   */
  @Test(expected = ArgumentDeclareException.class)
  public void testSetUpWithArgumentDeclareException()
      throws ArgumentDeclareException {

    try {
      finder.setUp("grep", fileSystem);
    } catch (ArgumentDeclareException a) {
      throw a;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out PathDoesNotExistException.
   *
   * @throws PathDoesNotExistException if output file path is not exist
   */
  @Test(expected = PathDoesNotExistException.class)
  public void testSetUpWithPathDoesNotExistException()
      throws PathDoesNotExistException {

    try {
      finder.setUp("grep [a-z] first.txt > /NOT/result.txt", fileSystem);
    } catch (PathDoesNotExistException p) {
      throw p;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out NameConflictException.
   *
   * @throws NameConflictException if the output file name is conflict
   */
  @Test(expected = NameConflictException.class)
  public void testSetUpWithNameConflictException()
      throws NameConflictException {

    try {
      finder.setUp("grep [a-z] first.txt > a", fileSystem);
    } catch (NameConflictException n) {
      throw n;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out InvalidTargetNameException.
   *
   * @throws InvalidTargetNameException if the output filename is invalid
   */
  @Test(expected = InvalidTargetNameException.class)
  public void testSetUpWithInvalidTargetNameException()
      throws InvalidTargetNameException {

    try {
      finder.setUp("grep [a-z] first.txt > resu!#lt.txt", fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out TypeNotMatchException.
   *
   * @throws TypeNotMatchException if path type does not match requirement
   */
  @Test(expected = TypeNotMatchException.class)
  public void testSetUpWithTypeNotMatchException()
      throws TypeNotMatchException {
    try {
      finder.setUp("grep [a-z] a", fileSystem);
    } catch (TypeNotMatchException t) {
      throw t;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the execute method is working as expected.
   */
  @Test public void testExecute() {

    try {
      finder.setUp("grep -R [a-z] / > result.txt", fileSystem);
      finder.execute();
      Searcher searcher = new Searcher(new String[] {"result.txt"}, fileSystem);
      String actual = ((File) searcher.searchTarget(true)).getContents();
      assertEquals("/first.txt/: Hello\n/also.txt/: PHP is the best!\n",
          actual);

    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }


}


