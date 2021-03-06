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
package operator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The CoAuthorListGenerator generates the list or all co-authors combined and
 * sort them before printing it in string representation
 *
 * @author Yu Ang Zhang
 * 
 */
public class CoAuthorListGenerator {

  private List<String> coAuthorList;

  /**
   * Construct the CoAuthorListGenerator
   * 
   */
  public CoAuthorListGenerator() {
    coAuthorList = new ArrayList<String>();
  }

  /**
   * A generic Comparator object that is used for sorting the coAuthorList in
   * alphabetical order
   * 
   */
  private static Comparator<String> alphabeticalOrder =
      new Comparator<String>(){
        public int compare(String str1, String str2) {
          int res = str1.compareTo(str2);
          return res;
        }
      };

  /**
   * Produce the string representation of all co-authors in all scholars' page
   * 
   * @return the string representation of all co-authors
   */
  public String produceAllCoAuthors() throws Exception {
    String result =
        "7. Co-Author list sorted (Total: " + coAuthorList.size() + "):\n";
    this.coAuthorList.sort(alphabeticalOrder);
    for (int i = 0; i < coAuthorList.size(); i++) {
      result += coAuthorList.get(i) + "\n";
    }
    return result;

  }

  /**
   * Get the List of all Co-Authors
   * 
   * @return the collection type of CoAuthors' list
   */
  public List<String> getCoAuthorList() {
    return coAuthorList;
  }
}
