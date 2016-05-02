package a2a.operator;

import a2a.exceptions.*;
import a2a.foundation.Directory;
import a2a.foundation.FileSystem;

import java.util.regex.Pattern;

/**
 * The Path Checker uses to check the validity of the path.
 * It provides different ways to check the path.
 *
 * @author Yi Jian Wang
 * @author Yu Ang Zhang
 */
public class PathChecker {

  private FileSystem fileSystem;

  /**
   * Construct a PathChecker.
   *
   * @param fileSystem The file system uses to check the path.
   */
  public PathChecker(FileSystem fileSystem) {

    this.fileSystem = fileSystem;
  }

  /**
   * The purpose of this method is uses to serve other function
   * to check the path. If one other function will create new file
   * or directory in a specified location, then it will check the
   * validity of this process.
   *
   * @param pathString The string of the path
   * @throws PathDoesNotExistException  if the path does not exist
   * @throws NameConflictException      if the target name is occupied
   * @throws InvalidTargetNameException if the target name is invalid
   */
  public void inspectSinglePath(String pathString)
      throws PathDoesNotExistException, NameConflictException,
      InvalidTargetNameException {

    // Split the pathString to array and search the path
    PathAnalyzer analyzer = new PathAnalyzer(pathString);
    String[] path = analyzer.convert();
    Searcher searcher = new Searcher(path, fileSystem);
    Pattern ch = Pattern.compile("^[^!@$&*()?:\\[\\]\"<>'`|={}/\\\\,;]+$");

    boolean locationExist = searcher.searchLocation() != null;
    boolean dirIsOccupied = searcher.searchTarget(false) != null;

    if (!locationExist) {
      throw new PathDoesNotExistException(pathString);
    } else if (dirIsOccupied) {
      throw new NameConflictException();
    }

    // Check the format of the name of the new target
    String targetName = path[path.length - 1];
    if (!ch.matcher(targetName).matches() || targetName.equals(".")
        || targetName.equals("..")) {
      throw new InvalidTargetNameException(targetName);
    }
  }

  /**
   * The purpose of this method is use to inspect two path.
   * <p>
   * Both paths must at least have a valid location.
   * Both path cannot be same if the target is a directory.
   * <p>
   * The target of the first path must exist.
   * If the type of the first path is a directory, then the
   * second path must be a directory. If the target of second
   * path does not exist, then it must not conflict with other
   * type of the target with the same name.
   * If the type of the first path is a file, then the second
   * path can be a file no matter the target is exist or not.
   *
   * @param firstPath  The first path of the address
   * @param secondPath The second path of the address
   * @return The boolean array of the inspect result
   */
  public boolean[] inspectDoublePath(String firstPath, String secondPath)
      throws TypeNotMatchException, PathDoesNotExistException,
      ConflictPathException {

    PathAnalyzer first = new PathAnalyzer(firstPath);
    PathAnalyzer second = new PathAnalyzer(secondPath);
    Searcher firstSearcher = new Searcher(first.convert(), fileSystem);
    Searcher secondSearcher = new Searcher(second.convert(), fileSystem);

    boolean secondLocationIsExist = secondSearcher.searchLocation() != null;
    boolean firstIsFile = firstSearcher.searchTarget(true) != null;
    boolean firstIsDir = firstSearcher.searchTarget(false) != null;
    boolean secondIsFile = secondSearcher.searchTarget(true) != null;
    boolean secondIsDir = secondSearcher.searchTarget(false) != null;

    if ((!firstIsFile && !firstIsDir) || !secondLocationIsExist) {
      throw new PathDoesNotExistException();
    } else if (firstIsDir && secondIsDir && !isValid(firstSearcher,
        secondSearcher)) {
      throw new ConflictPathException();
    } else if (firstIsDir && secondIsFile) {
      throw new TypeNotMatchException();
    }
    return new boolean[] {firstIsFile, secondIsFile || secondIsDir,
        secondIsDir, secondIsFile};
  }

  /**
   * The purpose of this method is use to check if the location of
   * both path is logically correct.
   * The parent directory cannot be put in its children directory.
   * The parent directory cannot be put in itself.
   *
   * @return True if the path is logically correct
   */
  private boolean isValid(Searcher first, Searcher second) {

    String name = ((Directory) first.searchTarget(false)).getName();
    Directory curr = (Directory) second.searchTarget(false);

    // The are the same directory
    if (first.searchTarget(false) == second.searchTarget(false)) {
      return false;
    }

    // Check the parent
    while (!curr.getName().equals("/")) {
      curr = curr.getParent();
      if (curr.getName().equals(name)) {
        return false;
      }
    }
    return true;
  }
}
