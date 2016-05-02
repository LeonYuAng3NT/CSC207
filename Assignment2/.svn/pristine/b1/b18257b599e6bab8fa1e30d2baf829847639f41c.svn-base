package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import a2a.foundation.Directory;
import a2a.foundation.File;
import a2a.foundation.FileSystem;
import a2a.operator.PathNavigator;

/**
 * A path navigator test, uses to test if the methods are working as
 * expected.
 * 
 * @author Yu Ang Zhang
 */
public class PathNavigatorTest {
  
  private FileSystem fileSystem;
  private String[] passed;
  private String[] failed;
  private PathNavigator navigator;

  /**
   * Set up PathNavigator test.
   */
  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    Directory root = fileSystem.getRoot();
    // Initialization of 
   Directory folder1 = new Directory("folder1","/folder1/", root );
   Directory folder2 = new Directory("folder2","/folder2/", root );
   Directory folder3 = new Directory("folder3","/folder3/", root );
   Directory foldera = new Directory("foldera","/folder1/foldera/",  folder1);
   Directory folderb = new Directory("folderb","/folder1/folderb/",  folder1);  
   File file = new File("file","", "/folder1/file");
    fileSystem.getCurrDir().getContent().add(folder1);
    fileSystem.getCurrDir().getContent().add(folder2);
    fileSystem.getCurrDir().getContent().add(folder3);
    ((Directory) fileSystem.getCurrDir().getContent().get(0)).getContent().add(
        foldera);
    ((Directory) fileSystem.getCurrDir().getContent().get(0)).getContent().add(
        folderb);
    ((Directory) fileSystem.getCurrDir().getContent().get(0)).getContent().add(
        file);
    passed = new String[]{"mv /folder1/foldera folder3", "mv folder1 folder3",
        "mv folder1/file folder3","mv folder2 folder4"};
    failed = new String[]{"mv /folderb","mv folder1 foldera","mv folder1 file",
        "mv file1 file2"};
    this.navigator =  new PathNavigator();

}
 

  /**
   * Test if the instance can be successfully created.
   */
  @Test
  public void testPathNavigator() {
    try {
      new PathNavigator();
    } catch (Exception e) {
      fail("Failed to create an instance of path navigator.");
    }
  }

  /**
   * Test if the method successfully set up or throw
   * exception in suitable cases.
   * 
   * @throws Exception
   */
  @Test
  public void testSetUp() throws Exception {

    try {
      PathNavigator pathNavigator = new PathNavigator();
      for (String command : passed) {
        pathNavigator.setUp(command, fileSystem);
        
      }

    } catch (Exception e) {
      fail("Unhandled exception have been thrown.");
    }

    // Test cases that should be failed
    try {
      PathNavigator pathNavigator = new PathNavigator();
      for (String command : failed) {
        pathNavigator.setUp(command, fileSystem);
      }
      fail("Exception should be thrown.");
    } catch (Exception e) {
      // Empty catch statement
    }
  }

  /**
   * Test if the execute method for NetworkProtocol is working as expected.
   * 
   * @throws Exception
   */
  @Test
  public void testExecute() throws Exception {
    try{
      assertEquals(3, fileSystem.getCurrDir().getContent().size());
        navigator.setUp(passed[0], fileSystem);
        navigator.execute();
        // the directory has been moved
        assertTrue(((Directory)fileSystem.getCurrDir().getContent().get(0))
            .getContent().size() == 2);
        navigator = new PathNavigator();
        navigator.setUp(passed[1], fileSystem);
        navigator.execute();
        // the directory folder 1 has been moved
        assertTrue(fileSystem.getCurrDir().getContent().size() == 2);
        //check whether folder 1 has been added to folder 3
        assertTrue(((Directory)fileSystem.getCurrDir().getContent().get(1))
            .getContent().size() == 2);
        navigator = new PathNavigator();
        navigator.setUp(passed[3], fileSystem);
        navigator.execute();
        // the directory folder 2 has been renamed to folder 4
        assertTrue(fileSystem.getCurrDir().getContent().size() == 2);
        String newName =((Directory)fileSystem.getCurrDir().getContent().get(1))
        .getName();
        // checking the name
        assertEquals(newName,"folder4");
        navigator = new PathNavigator();

    }catch(Exception e){
      fail("Unexpected exception have been thrown");
    }
  }
}
