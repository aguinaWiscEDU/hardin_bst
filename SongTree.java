
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This creates a BST of Songs to hold. It allows the user to add, search for contents, get the
 * height, get a list of songs, and uses recursive methods to assist in the implementation of
 * methods.
 *
 * @author Alejandro Aguina
 */
public class SongTree {
  private BSTNode<Song> root; // root of this song BST
  private int size; // size of this song tree


  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this SongTree is empty, false otherwise
   */
  public boolean isEmpty() {
    //creating private var to store value of isEmpty return
    boolean emptyVal;

    //check whether root or size is null/0, true if either condition = true
    if(root == null || size == 0){
      emptyVal = true;
    }
    //else, tree is not empty
    else{
      emptyVal = false;
    }

    //return our T/F result
    return emptyVal;
  }

  /**
   * Returns the number of songs stored in this BST.
   * 
   * @return the size of this SongTree
   */
  public int size() {
    return this.size;
  }


  /**
   * Adds a new song to this SongTree
   * 
   * @param newSong a new song to add to this BST.
   * @return true if the newSong was successfully added to this BST, and returns false if there is
   *         a match with this song already stored in this BST.
   */
  public boolean addSong(Song newSong) {
    //creating a new boolean for our return value
    boolean isAdded = false;

    //if song = null - return false
    if(newSong == null){
      return false;
    }

    //if isEmpty returns true, we'll set the root
    if (isEmpty()) {
      BSTNode<Song> newRoot = new BSTNode<>(newSong);

      //set the root of this tree to newSong, increment size, and return true.
      root = newRoot;
      size++;
      isAdded = true;

    } else {
      //Using a boolean method to call our helper, then used to check if add was successful
      boolean methodCheck = addSongHelper(newSong, root);

      //if the check is successful, we'll increment our size, and set isAdded to true
      if(methodCheck){
        size++;
        isAdded = true;
      }

    }

    //finally, we return our boolean isAdded result
    return isAdded;
  }

  /**
   * Recursive helper method to add a new song to a SongTree rooted at current.
   * 
   * @param current  The "root" of the subtree we are inserting new song into.
   * @param newSong The song to be added to a BST rooted at current.
   * @return true if the newSong was successfully added to this SongTree, false otherwise
   */
  protected static boolean addSongHelper(Song newSong, BSTNode<Song> current) {
    //Objective, add a new song, while still connecting to node its attached to.
    //base case - if current equals null, we add our newSong to it.
    if(current == null){
      current = new BSTNode<>(newSong);
      return true;
    }

    //base case - if compareTo = 0, return false
    if(newSong.compareTo(current.getData()) == 0){
      return false;
    }

    //param 1 - if our compareTo < 0, enter the left side of the tree
    if(newSong.compareTo(current.getData()) < 0){
      //If current's left is null, set our newSong
      if(current.getLeft() == null){
        //set left node of this to newSong
        current.setLeft(new BSTNode<>(newSong));
        return true;
      }
      //else, we recursively call addSong
      else{
        return addSongHelper(newSong, current.getLeft());
      }
    }

    //param 2 - if our compareTo > 0, enter the right side of the tree
    if(newSong.compareTo(current.getData()) > 0){
      //If current's right is null, set our newSong
      if(current.getRight() == null){
        current.setRight(new BSTNode<>(newSong));
        return true;
      }
      //else, we recursively call addSong
      else{
        return addSongHelper(newSong, current.getRight());
      }
    }

    return false;
  }

  /**
   * Returns a String representation of all the songs stored within this BST in the increasing
   * order, separatingd by a newline "\n". For instance
   * 
   * "[(Year: 1988) (Rate: 7.0) (Name: Light Years)]" + "\n" + 
   * "[(Year: 2015) (Rate: 6.5) (Name: Cinderella)]" + "\n"
   * 
   * @return a String representation of all the songs stored within this BST sorted in an
   *         increasing order with respect to the result of Song.compareTo() method (year, rating,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a SongTree is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current song within this BST (root of a subtree)
   * @return a String representation of all the songs stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Song.compareTo() method (year, rating,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Song> current) {
    //creating a string variable to hold our data
    String list = "";

    //Base Case 1 - current is null, return ""
    if(current == null){
      return "";
    }

    //Step 1 - calling our left subtree, and getting their data
    list += toStringHelper(current.getLeft());

    //Step 2 - getting the current value in string formation
    list += current.getData().toString() + "\n";

    //Step 3 - calling our right subtree, and getting their data
    list += toStringHelper(current.getRight());

    // return the list
    return list;
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   * 
   * @param current pointer to the current BSTNode within a SongTree (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Song> current) {
    //Base Case 1 - if current = null return currHeight at 0
    if(current == null){
      return 0;
    }

    //We will return 1 + the Max of right/left subtree
    return 1 + Math.max(heightHelper(current.getRight()), heightHelper(current.getLeft()));
  }

  /**
   * Checks whether this SongTree contains a song given its name, production year, and rating.
   * 
   * @param year   year of production of the song to search
   * @param rating rating of the song to search
   * @param name   name of the song to search
   * @return true if there is a match with this song in this BST, and false otherwise
   */
  public boolean contains(int year, double rating, String name) {
    //if ANY item is null, return false
    if(year < 1900 || year > 2021 ||  rating < 0.0 || rating > 10.0 || name == null){
      return false;
    }

    //if the root is null, return false
    if(root == null){
      return false;
    }

    //create a temp value to hold our parameters
    Song targetSong = new Song(year, rating, name);

    //call recursive helper method, return its result
    return containsHelper(targetSong, root);
  }

  /**
   * Recursive helper method to search whether there is a match with a given song in the subtree
   * rooted at current
   * 
   * @param target  a reference to a song we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected boolean containsHelper(Song target, BSTNode<Song> current) {
    //base case - if current = null
    if(current == null){
      return false;
    }

    //base case - current equals our song, return true
    if(target.compareTo(current.getData()) == 0){
      return true;
    }

    //param 1 - if compareTo is < 0 search left half of tree
    if(target.compareTo(current.getData()) < 0){
      //check if current's left equals target
      if(current.getLeft() != null && target.compareTo(current.getLeft().getData()) == 0){
        return true;
      }
      //if not, recursively call
      else{
        return containsHelper(target, current.getLeft());
      }
    }

    //param 2 - if compareTo is > 0 search right half of tree
    if(target.compareTo(current.getData()) > 0){
      //check if the current's right node equals target
      if(current.getRight() != null && target.compareTo(current.getRight().getData()) == 0){
        return true;
      }
      //else, recursively call
      else{
        return containsHelper(target, current.getRight());
      }
    }

    //all else, return false as we have not found the item in question
    return false;
  }


  /**
   * Gets the best (maximum) song in this BST
   * 
   * @return the best (largest) song (the most recent, highest rated, and having the largest name)
   *         in this SongTree, and null if this tree is empty.
   */
  public Song getBestSong() {
    //iterative, creating Song obj to return, and BSTNode we'll iterate through (root start)
    Song bestSong = null;
    BSTNode<Song> currNode = root;

    //if currNode = null, return null
    if(currNode == null){
      return null;
    }

    //while the currNode's right node is not null, we get the next node
    while(currNode.getRight() != null){
      currNode = currNode.getRight();
    }

    //bestSong is now set to currNode's data
    bestSong = currNode.getData();

    //returning the best song
    return bestSong;
  }


  /**
   * Search for songs given the year and minimum rating as lookup key.
   * 
   * @param year   production year of a song
   * @param rating rating value of a song
   * @return a list of songs whose year equals our lookup year key and having a rating greater or
   *         equal to a given rating.
   * @throws  NoSuchElementException with a descriptive error message if there is no Song found in
   *           this BST having the provided year and a rating greater or equal to the provided
   *           rating
   */
  public ArrayList<Song> lookup(int year, double rating) {
    //if year and rating are empty/not in range of our values, return NoSuchElementException
    if(year < 1900 || year > 2021 || rating < 0.0 || rating > 10.0){
      throw new NoSuchElementException("Entered values are not within this list, please update " +
              "your search parameters.");
    }

    //establishing ArrayList we'll return
    ArrayList<Song> lookupList = new ArrayList<>();

    //calling our recursive help method helper
    lookupHelper(year, rating, root, lookupList);

    //after our helper method, if the list is empty, we throw NoSuchElementException
    if(lookupList.isEmpty()){
      throw new NoSuchElementException("No songs were found with the given year, and minimum " +
              "rating.");
    }

    // else return the list of songs
    return lookupList; // remove this statement. Added to let this code to compile without errors
  }

  /**
   * Recursive helper method to lookup the list of songs given their year of production and a
   * minimum value of rating
   * 
   * @param year      the year we would like to search for a song
   * @param rating    the minimum rating we would like to search for a song
   * @param songList an arraylist which stores the list of songs matching our search criteria
   *                  (exact year of production and having at least the provided rating) within the
   *                  subtree rooted at current
   * @param current   "root" of the subtree we are looking for a match to find within it.
   */
  protected void lookupHelper(int year, double rating, BSTNode<Song> current,
      ArrayList<Song> songList) {
    //base case - if node is null, return
    if(current == null){
      return;
    }

    //param 1 - if current's year matches target, and double rating is >= target, add to list
    if(year == current.getData().getYear() && rating <= current.getData().getRating()){
      songList.add(current.getData());
    }

    //if left is not null, enter and check
    if(current.getLeft() != null){
      lookupHelper(year, rating, current.getLeft(), songList);
    }

    //if right is not null, enter and check
    if(current.getRight() != null){
      lookupHelper(year, rating, current.getRight(), songList);
    }
  }
}
