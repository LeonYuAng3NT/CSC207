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

import a2a.exceptions.ArgumentDeclareException;
import a2a.exceptions.InvalidTargetNameException;
import a2a.exceptions.NameConflictException;
import a2a.exceptions.PathDoesNotExistException;
import a2a.foundation.File;
import a2a.foundation.FileSystem;
import a2a.operator.NetworkProtocol;
import a2a.operator.Searcher;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The Network Protocol Test uses to test if methods are working as expected.
 *
 * @author Yi Jian Wang
 * @author Yu Ang Zhang
 * @author Yiming Huang
 */
public class NetworkProtocolTest {

  private NetworkProtocol networkProtocol;
  private FileSystem fileSystem;

  /**
   * Set up NetworkProtocol test.
   */
  @Before public void setUp() {
    fileSystem = new FileSystem();
    networkProtocol = new NetworkProtocol();
  }

  /**
   * Test if the instance can be successfully created.
   */
  @Test public void testNetworkProtocol() {
    try {
      networkProtocol = new NetworkProtocol();
    } catch (Exception e) {
      fail("Failed to create an instance of network protocol.");
    }
  }

  /**
   * Test if the method successfully set up or throw exception in suitable
   * cases.
   */
  @Test public void testSetUp() {
    try {
      networkProtocol.setUp("get http://www.cs.cmu.edu/~spok/grimmtmp/073.txt",
          fileSystem);
      networkProtocol.setUp(
          "get http://www.cs.cmu.edu/~spok/grimmtmp/073.txt > output.txt",
          fileSystem);
    } catch (Exception e) {
      fail("Unhandled exception have been thrown.");
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
      networkProtocol.setUp("get", fileSystem);
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
      networkProtocol.setUp("get http://www.cs.cmu.edu/~spok/grimmtmp/073.txt"
          + " > /PATHNOTEXIST/out.txt", fileSystem);
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
      networkProtocol
          .setUp("get http://www.cs.cmu.edu/~spok/grimmtmp/073.txt" + " > /",
              fileSystem);
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
      networkProtocol.setUp(
          "get http://www.cs.cmu.edu/~spok/grimmtmp/073.txt" + " > ?#.txt",
          fileSystem);
    } catch (InvalidTargetNameException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out MalformedURLException.
   *
   * @throws MalformedURLException if the input url is invalid
   */
  @Test(expected = MalformedURLException.class)
  public void testSetUpWithMalformedURLException()
      throws MalformedURLException {
    try {
      networkProtocol.setUp("get fajfij", fileSystem);
    } catch (MalformedURLException m) {
      throw m;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the method can throw out MalformedURLException.
   *
   * @throws IOException if target website does not exist
   */
  @Test(expected = IOException.class) public void testSetUpWithIOException()
      throws IOException {
    try {
      networkProtocol.setUp("get http://daosdjoisadjoiaaaaaa.com", fileSystem);
    } catch (IOException i) {
      throw i;
    } catch (Exception e) {
      fail("Unhandled Exception have been thrown.");
    }
  }

  /**
   * Test if the execute method for NetworkProtocol is working as expected.
   */
  @Test public void testExecute() {

    try {
      networkProtocol.setUp("get http://www.cs.cmu.edu/~spok/grimmtmp/073.txt",
          fileSystem);
      networkProtocol.execute();
      Searcher searcher = new Searcher(new String[] {"073.txt"}, fileSystem);
      String actual = ((File) searcher.searchTarget(true)).getName();
      assertEquals("073.txt", actual);

    } catch (Exception e) {
      fail("Unhandled exception have been thrown.");
    }
  }
}
