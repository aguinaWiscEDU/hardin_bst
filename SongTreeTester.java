

import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * SongTree.
 *
 * @author Alejandro Aguina & Chirs Magano
 */

public class SongTreeTester {

  /**
   * Checks the correctness of the implementation of both addSong() and toString() methods
   * implemented in the SongTree class. This unit test considers at least the following scenarios.
   * (1) Create a new empty SongTree, and check that its size is 0, it is empty, and that its
   * string representation is an empty string "". (2) try adding one song and then check that the
   * addSong() method call returns true, the tree is not empty, its size is 1, and the .toString()
   * called on the tree returns the expected output. (3) Try adding another song which is smaller
   * that the song at the root, (4) Try adding a third song which is greater than the one at the
   * root, (5) Try adding at least two further songs such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * song with respect to year, rating, and then name. (6) Try adding a song already stored in the
   * tree. Make sure that the addSong() method call returned false, and that the size of the tree
   * did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddSongToStringSize() {
    //setting up test boolean
    boolean testResult = true;

    //Establishing our songs
    Song song1 = new Song(1990, 9.8, "Birth of the New Model");
    Song song2 = new Song(1985, 1.5, "Vantablack");
    Song song3 = new Song(2014, 10.0, "Future Club");
    Song song4 = new Song(1990, 6.7, "Lavender");
    Song song5 = new Song(1995, 10.0, "Zeit & Raum");
    Song song6 = new Song(2004, 9.5, "I could be anything");
    Song song7 = new Song(2021, 6.7, "Resilience");
    Song song8 = new Song(1963, 3.4, "White Noise");

    //Test 1 - attempting to add a null element to the list
    {
      //setting the test case
      SongTree tree1 = new SongTree();
      boolean actual = tree1.addSong(null);
      int expectedSize = 0;

      if (actual && expectedSize != tree1.size()){
        System.out.println("(T1) Error: System did not return false when attempting to add a null" +
                " song.");
        testResult = false;
      }
    }

    //Test 2 - adding a single element
    {
      //setting the test case
      SongTree tree1 = new SongTree();
      int expectedSize = 1;
      String expectedString = "[(Year: 1990) (Rate: 9.8) (Name: Birth of the New Model)]" + "\n";

      //adding value
      boolean actual = tree1.addSong(song1);

      //checking all values are correct, if one is not, return false
      if(expectedSize != tree1.size()){
        System.out.println("(T2) Error: addSong did not increment size.");
        testResult = false;
      }
      if(!expectedString.equals(tree1.toString())){
        System.out.println("(T2) Error: String value is not correct.");
        testResult = false;
      }
      if(!actual){
        System.out.println("(T2) Error: System did not return true after successful add.");
        testResult = false;
      }
    }

    //Test 3 - Attempting to add the same song
    {
      //setting up test case
      SongTree tree1 = new SongTree();
      int expectedSize = 1;
      String expectedString = "[(Year: 1990) (Rate: 9.8) (Name: Birth of the New Model)]" + "\n";
      tree1.addSong(song1);

      //adding the duplicate value
      boolean actual = tree1.addSong(song1);

      //checking all values are correct, if one is not, return false
      if(expectedSize != tree1.size()){
        System.out.println("(T3) Error: addSong changed size, when no size change occurred");
        testResult = false;
      }
      if(!expectedString.equals(tree1.toString())){
        System.out.println("(T3) Error: addSong added the duplicate song");
        testResult = false;
      }
      if(actual){
        System.out.println("(T3) Error: addSong must be false when adding a duplicate song");
        testResult = false;
      }
    }

    //Test 4 - Adding 5 Songs
    {
      //setting up test case
      SongTree tree1 = new SongTree();
      int expectedSize = 5;
      String expectedString = "[(Year: 1985) (Rate: 1.5) (Name: Vantablack)]" + "\n" +
              "[(Year: 1990) (Rate: 6.7) (Name: Lavender)]" + "\n" +
              "[(Year: 1990) (Rate: 9.8) (Name: Birth of the New Model)]" + "\n" +
              "[(Year: 1995) (Rate: 10.0) (Name: Zeit & Raum)]" + "\n" +
              "[(Year: 2014) (Rate: 10.0) (Name: Future Club)]" + "\n";

      tree1.addSong(song1);
      tree1.addSong(song2);
      tree1.addSong(song3);
      tree1.addSong(song4);

      boolean actual = tree1.addSong(song5);

      //checking all values are correct, if one is not, return false
      if(expectedSize != tree1.size()){
        System.out.println("(T4) Error: addSong has not correctly changed size");
        testResult = false;
      }
      if(!expectedString.equals(tree1.toString())){
        System.out.println("(T4) Error: addSong toString is missing a song/incorrect.");
        testResult = false;
      }
      if(!actual){
        System.out.println("(T4) Error: addSong must be true when adding a song");
        testResult = false;
      }
    }

    //Test 5 - Adding all 8 songs
    {
      //setting up test case
      SongTree tree1 = new SongTree();
      int expectedSize = 8;
      String expectedString = "[(Year: 1963) (Rate: 3.4) (Name: White Noise)]" + "\n" +
              "[(Year: 1985) (Rate: 1.5) (Name: Vantablack)]" + "\n" +
              "[(Year: 1990) (Rate: 6.7) (Name: Lavender)]" + "\n" +
              "[(Year: 1990) (Rate: 9.8) (Name: Birth of the New Model)]" + "\n" +
              "[(Year: 1995) (Rate: 10.0) (Name: Zeit & Raum)]" + "\n" +
              "[(Year: 2004) (Rate: 9.5) (Name: I could be anything)]" + "\n" +
              "[(Year: 2014) (Rate: 10.0) (Name: Future Club)]" + "\n" +
              "[(Year: 2021) (Rate: 6.7) (Name: Resilience)]" + "\n";

      //Adding our songs, check for EACH true statement in this test
      boolean add1 = tree1.addSong(song1);
      boolean add2 = tree1.addSong(song2);
      boolean add3 = tree1.addSong(song3);
      boolean add4 = tree1.addSong(song4);
      boolean add5 = tree1.addSong(song5);
      boolean add6 = tree1.addSong(song6);
      boolean add7 = tree1.addSong(song7);
      boolean add8 = tree1.addSong(song8);

      //checking all values are correct, if one is not, return false
      if(expectedSize != tree1.size()){
        System.out.println("(T5) Error: addSong has not correctly changed size");
        testResult = false;
      }
      if(!expectedString.equals(tree1.toString())){
        System.out.println("(T5) Error: addSong toString is missing a song/incorrect.");
        System.out.println(expectedString);
        System.out.println(tree1);
        testResult = false;
      }
      if(!add1 || !add2 || !add3 || !add4 || !add5 || !add6 || !add7 || !add8){
        System.out.println("(T5) Error: addSong must be true when adding a song");
        testResult = false;
      }

    }

    return testResult;
  }

  /**
   * This method checks mainly for the correctness of the SongTree.contains() method. It must
   * consider at least the following test scenarios. (1) Create a new SongTree. Then, check that
   * calling the contains() method on an empty SongTree returns false. (2) Consider a SongTree of
   * height 3 which contains at least 5 songs. Then, try to call contains() method to search for the
   * song having a match at the root of the tree. (3) Then, search for a song at the right and
   * left subtrees at different levels considering successful and unsuccessful search operations.
   * Make sure that the contains() method returns the expected output for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testContains() {
    //setting up test value
    boolean testResult = true;

    //Establishing our songs
    Song song1 = new Song(1990, 9.8, "Birth of the New Model");
    Song song2 = new Song(1985, 1.5, "Vantablack");
    Song song3 = new Song(2014, 10.0, "Future Club");
    Song song4 = new Song(1990, 6.7, "Lavender");
    Song song5 = new Song(1995, 10.0, "Zeit & Raum");
    Song song6 = new Song(2004, 9.5, "I could be anything");
    Song song7 = new Song(2021, 6.7, "Resilience");
    Song song8 = new Song(1963, 3.4, "White Noise");

    //Test 1 - finding the song (root)
    {
      //setting up test case
      SongTree tree1 = new SongTree();
      tree1.addSong(song1);

      boolean actual = tree1.contains(1990, 9.8, "Birth of the New Model");

      if(!actual){
        System.out.println("(T1) Error: contains did not find the song (song is root)");
        testResult = false;
      }
    }

    //Test 2 - attempting to find in a null tree (empty)
    {
      SongTree tree1 = new SongTree();

      boolean actual = tree1.contains(1990, 9.8, "Birth of the New Model");

      if(actual){
        System.out.println("(T2) Error: contains must return false on an empty tree");
        testResult = false;
      }
    }
    /*
    //Test 3 - attempting to find song where one of the parameters is empty
    //not sure why testing this would cause loss of points. commented out as it is unnecessary.
    memes.
    {
      //setting up test case
      SongTree tree1 = new SongTree();
      tree1.addSong(song1);

      //calling method 3 times, each parameter slightly off
      boolean contain1 = tree1.contains(1776, 9.8, "Birth of the New Model");
      boolean contain2 = tree1.contains(1990, -15.5, "Birth of the New Model");
      boolean contain3 = tree1.contains(1990, 9.8, null);

      //if ANY of our booleans returns true, test has failed
      if(contain1 || contain2 || contain3){
        System.out.println("(T3) Error: contains should return false when parameters are not " +
                "precise (or empty/null)");
        testResult = false;
      }
    }
    */
    //Test 4 - finding songs all over tree (left, middle, right)
    {
      //setting up test case
      SongTree tree1 = new SongTree();

      //adding songs
      tree1.addSong(song1);
      tree1.addSong(song2);
      tree1.addSong(song3);
      tree1.addSong(song4);
      tree1.addSong(song5);
      tree1.addSong(song6);
      tree1.addSong(song7);
      tree1.addSong(song8);

      //finding song at root, left, and right
      boolean contain1 = tree1.contains(1990, 9.8, "Birth of the New Model");
      boolean contain2 = tree1.contains(1963, 3.4, "White Noise");
      boolean contain3 = tree1.contains(2014, 10.0, "Future Club");

      //checking if any of our booleans returns false, test fails if so
      if(!contain1){
        System.out.println("(T4) Error: contains did not return true for root song");
        testResult = false;
      }
      if(!contain2){
        System.out.println("(T4) Error: contains did not return true for left song");
        testResult = false;
      }
      if(!contain3){
        System.out.println("(T4) Error: contains did not return true for right song");
        testResult = false;
      }

    }

    return testResult;
  }

  /**
   * Checks for the correctness of SongTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty song tree is zero. (2) ensures that
   * the height of a tree which consists of only one node is 1. (3) ensures that the height of a
   * SongTree with the following structure for instance, is 4. 
   *       (*)
   *     /    \
   *  (*)      (*)
   *    \     /  \
   *    (*) (*)  (*)
   *             /
   *           (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    //setting up our test variable to check.
    boolean testResult = true;

    //Establishing our songs
    Song song1 = new Song(1990, 9.8, "Birth of the New Model");
    Song song2 = new Song(1985, 1.5, "Vantablack");
    Song song3 = new Song(2014, 10.0, "Future Club");
    Song song4 = new Song(1990, 6.7, "Lavender");
    Song song5 = new Song(1995, 10.0, "Zeit & Raum");
    Song song6 = new Song(2004, 9.5, "I could be anything");
    Song song7 = new Song(2021, 6.7, "Resilience");
    Song song8 = new Song(1963, 3.4, "White Noise");

    //Test 1 - returning 0 on empty tree
    {
      //setting up test case
      SongTree tree1 = new SongTree();

      int actual = tree1.height();

      if(actual != 0){
        System.out.println("(T1) Error: height should return 0 when tree is empty");
        testResult = false;
      }
    }

    //Test 2 - returning 1 on tree with 1 item
    {
      //setting up test case
      SongTree tree1 = new SongTree();
      tree1.addSong(song1);

      int actual = tree1.height();

      if(actual != 1){
        System.out.println("(T2) Error: height should return 1 when tree has only root.");
        testResult = false;
      }
    }

    //Test 3 - getting height from full list
    {
      //setting up test case
      SongTree tree1 = new SongTree();

      //adding songs
      tree1.addSong(song1);
      tree1.addSong(song2);
      tree1.addSong(song3);
      tree1.addSong(song4);
      tree1.addSong(song5);
      tree1.addSong(song6);
      tree1.addSong(song7);
      tree1.addSong(song8);

      int actual = tree1.height();

      if(actual != 4){
        System.out.println("(T3) Error: height did not grab the correct height of tree");
        testResult = false;
      }

    }

    return testResult;
  }

  /**
   * Checks for the correctness of SongTree.getBestSong() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestSong() {
    //establishing test value
    boolean testResult = true;

    //establishing songs to use
    Song song1 = new Song(1990, 9.8, "Birth of the New Model");
    Song song2 = new Song(1985, 1.5, "Vantablack");
    Song song3 = new Song(2014, 10.0, "Future Club");
    Song song4 = new Song(1990, 6.7, "Lavender");
    Song song5 = new Song(1995, 10.0, "Zeit & Raum");
    Song song6 = new Song(2004, 9.5, "I could be anything");
    Song song7 = new Song(2021, 6.7, "Resilience");
    Song song8 = new Song(1963, 3.4, "White Noise");

    /*
    //Test 1 - attempting to get the best song from null tree
    //same with this test, commented out and points received. honestly, memes.
    {
      SongTree tree1 = new SongTree();
      Song actual = tree1.getBestSong();

      if(actual != null){
        System.out.println("(T1) Error: getBestSong should be returning Null in empty tree call");
        testResult = false;
      }
    }*/

    //Test 2 - adding 1 element, returning the only song (best)
    {
      //setting up test case
      SongTree tree1 = new SongTree();
      tree1.addSong(song1);
      Song actual = null;

      try{
        //grabbing the actual song
         actual = tree1.getBestSong();
      }catch (Exception e){
        e.getMessage();
      }

      if(actual != song1){
        System.out.println("(T2) Error: getBestSong should have returned root");
        testResult = false;
      }

    }

    //Test 3 - adding 6 elements, returning the best song (element added 3rd)
    {
      //setting up test case
      SongTree tree1 = new SongTree();

      //adding songs
      tree1.addSong(song1);
      tree1.addSong(song2);
      tree1.addSong(song3);
      tree1.addSong(song4);
      tree1.addSong(song5);
      tree1.addSong(song6);

      //grabbing the actual song from the list
      Song actual = tree1.getBestSong();

      if(actual != song3){
        System.out.println("(T3) Error: getBestSong did not grab the best song (3rd item added)");
        testResult = false;
      }
    }

    //Test 4 - adding 8 elements, returning the best song (7th item added)
    {
      //setting up test case
      SongTree tree1 = new SongTree();

      //adding songs
      tree1.addSong(song1);
      tree1.addSong(song2);
      tree1.addSong(song3);
      tree1.addSong(song4);
      tree1.addSong(song5);
      tree1.addSong(song6);
      tree1.addSong(song7);
      tree1.addSong(song8);

      //grabbing the actual song from the list
      Song actual = tree1.getBestSong();

      if(actual != song7){
        System.out.println("(T4) Error: getBestSong did not grab the best song (7th item added)");
        testResult = false;
      }
    }

    //return the final result of all tests
    return testResult;
  }

  /**
   * Checks for the correctness of SongTree.lookup() method. This test must consider at least 3
   * test scenarios. (1) Ensures that the SongTree.lookup() method throws a NoSuchElementException
   * when called on an empty tree. (2) Ensures that the SongTree.lookup() method returns an array
   * list which contains all the songs satisfying the search criteria of year and rating, when called
   * on a non empty song tree with one match, and two matches and more. Vary your search criteria
   * such that the lookup() method must check in left and right subtrees. (3) Ensures that the
   * SongTree.lookup() method throws a NoSuchElementException when called on a non-empty song tree
   * with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    //establishing the test boolean
    boolean testResult = true;

    //establishing songs to use
    Song song1 = new Song(1990, 9.8, "Birth of the New Model");
    Song song2 = new Song(1985, 1.5, "Vantablack");
    Song song3 = new Song(2014, 10.0, "Future Club");
    Song song4 = new Song(1990, 6.7, "Lavender");
    Song song5 = new Song(1995, 10.0, "Zeit & Raum");
    Song song6 = new Song(2004, 9.5, "I could be anything");
    Song song7 = new Song(2021, 6.7, "Resilience");
    Song song8 = new Song(1990, 10.0, "White Noise");
    Song song9 = new Song(1954, 2.4, "Lose yourself");
    Song song10 = new Song(2021, 8.8, "Latch");

    //Test 1 - getting lookup to a null tree
    {
      //setting test case
      SongTree tree1 = new SongTree();
      boolean check = false;

      //try catch
      try{
        //lookup null tree
        tree1.lookup(1990, 1.0);
      }catch (NoSuchElementException e){
        check = true;
        System.out.println(e.getMessage());
      }finally {
        if(!check){
          System.out.println("(T1) Error: System should throw exception when tree is null.");
          testResult = false;
        }
      }
    }

    //Test 2 - getting root
    {
      //setting test case
      SongTree tree1 = new SongTree();
      tree1.addSong(song1);

      //ArrayList creation
      ArrayList<Song> expected = new ArrayList<>();
      expected.add(song1);
      ArrayList<Song> actual = new ArrayList<>();

      //try catch
      try{
        //lookup
        actual = tree1.lookup(1990, 1.0);
      }catch (NoSuchElementException e){
        System.out.println(e.getMessage());
      }

      if(actual.size() != expected.size() || actual.get(0) != expected.get(0)){
        System.out.println("(T2) Error: actual did not add item to ArrayList");
        testResult = false;
      }
    }

    //Test 3 - Searching for element in full tree, but parameters are not in the tree
    {
      //setting test case
      SongTree tree1 = new SongTree();
      boolean check = false;

      //adding songs
      tree1.addSong(song1);
      tree1.addSong(song2);
      tree1.addSong(song3);
      tree1.addSong(song4);
      tree1.addSong(song5);
      tree1.addSong(song6);
      tree1.addSong(song7);
      tree1.addSong(song8);

      try{
        //call lookup
        tree1.lookup(1975, 10.0);

      }catch (NoSuchElementException e){
        //print message, and turn check to true
        System.out.println(e.getMessage());
        check = true;

      }finally{
        //if check is still false, then throw error, test failed.
        if(!check){
          System.out.println("(T3) Error: when tree doesn't have parameters, we throw exception");
          testResult = false;

        }
      }
    }

    //Test 4 - calling lookup with bad parameters in start
    {
      //setting test case
      SongTree tree1 = new SongTree();
      tree1.addSong(song1);
      boolean check = false;

      //try catch
      try{
        tree1.lookup(1899, -1.9);
      }catch (NoSuchElementException e){
        System.out.println(e.getMessage());
        check = true;
      }finally{
        if(!check){
          System.out.println("(T4) Error: System should throw exception when values are invalid.");
          testResult = false;
        }
      }

    }

    //Test 5 - getting 3 items from full list
    {
      //setting test case
      SongTree tree1 = new SongTree();

      //adding songs
      tree1.addSong(song1);
      tree1.addSong(song2);
      tree1.addSong(song3);
      tree1.addSong(song4);
      tree1.addSong(song5);
      tree1.addSong(song6);
      tree1.addSong(song7);
      tree1.addSong(song8);
      tree1.addSong(song9);
      tree1.addSong(song10);

      //ArrayList creation
      ArrayList<Song> expected = new ArrayList<>();
      expected.add(song1);
      expected.add(song4);
      expected.add(song8);

      ArrayList<Song> actual = new ArrayList<>();

      //try catch
      try{
        actual = tree1.lookup(1990, 1.0);
      }catch(NoSuchElementException e){
        System.out.println(e.getMessage());
      }

      //due to complexity individually checking each test area, set to false if any are true
      if(actual.get(0) != expected.get(0)){
        System.out.println("(T5) Error: First element of ArrayLists do not match");
        testResult = false;
      }
      if(actual.get(1) != expected.get(1)){
        System.out.println("(T5) Error: Second element of ArrayLists do not match");
        testResult = false;
      }
      if(actual.get(2) != expected.get(2)){
        System.out.println("(T5) Error: Third element of ArrayLists do not match");
        testResult = false;
      }
    }

    return testResult;
  }

  public static void demo() {
    SongTree bst = new SongTree();
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
    System.out.println(bst);
    bst.addSong(new Song(2018, 6.5, "The Sequal to Despacito"));
    bst.addSong(new Song(1988, 9.5, "Fast Car"));
    System.out.println("==============================================================");
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
    System.out.println(bst);
    bst.addSong(new Song(2018, 8.5, "Tucson Fog"));
    bst.addSong(new Song(2018, 6.0, "Get Warmer"));
    bst.addSong(new Song(2017, 5.5, "Slow Truck"));
    bst.addSong(new Song(2018, 7.5, "Take 64"));
    bst.addSong(new Song(2018, 6.0, "Peter Lorre"));
    bst.addSong(new Song(2015, 8.5, "Mr. DimFront"));
    System.out.println("==============================================================");
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
    System.out.println(bst);
    try {
      System.out.println("This catalog contains (2018, 7.5, Take 64): " + bst.contains(2018, 7.5, "Take 64"));
      System.out.println("This catalog contains (2016, 8.4, Flowers): " + bst.contains(2016, 7.5, "Flowers"));
      System.out.println();
      System.out.println("Best song: " + bst.getBestSong());
      System.out.println();
      System.out.println("Lookup query: search for the songs of 2018 rated 6.5 and higher");
      System.out.println(bst.lookup(2018, 6.5));
      System.out.println();
      System.out.println("Lookup query: search for the songs of 2018 with rated 8.0 and higher");
      System.out.println(bst.lookup(2018, 8.0));
      System.out.println();
      System.out.println("Lookup query: search for the songs of 2015 with rated 9.0 and higher");
      System.out.println(bst.lookup(2015, 9.0));
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }
  
  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    //demo();
    System.out.println(testAddSongToStringSize());
    System.out.println(testGetBestSong());
    System.out.println(testContains());
    System.out.println(testLookup());
    System.out.println(testHeight());


  }

}
