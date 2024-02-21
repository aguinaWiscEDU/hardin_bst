
/**
 * This class models a song
 * 
 * 
 *
 */
public class Song implements Comparable<Song> {
  // Note: DO NOT MAKE ANY CHANGE TO THIS CLASS AND DO NOT SUBMIT IT TO GRADESCOPE

  private double rating; // users rating of this song in the scale from 0.0 to 10.0
  private int year; // year of production of this song
  private String name; // name of this song

  /**
   * Creates a new Song with given attributes
   * 
   * @param rating rating average of a song by viewers
   * @param year   the year of production
   * @param name   name of the song
   * @throws IllegalArgumentException if year is less than 1900, if rating is out of the range
   *            from 0.0 to 10.0 or if name is null or an empty string
   */
  public Song(int year, double rating, String name) {
    if (year < 1900)
      throw new IllegalArgumentException("Invalid year of production.");
    if (rating < 0.0 || rating > 10.0)
      throw new IllegalArgumentException("Invalid rate. The rate must be in the scale of 10.");
    if (name == null || name.length() == 0)
      throw new IllegalArgumentException("Invalid name");
    this.year = year;
    this.rating = rating;
    this.name = name;
  }

  /**
   * Gets the rating of this song
   * 
   * @return the rating of song
   */
  public double getRating() {
    return rating;
  }

  /**
   * Gets the year when this song was produced
   *
   * @return the year of the production of this song
   */
  public int getYear() {
    return year;
  }

  /**
   * Gets the name of this song
   * 
   * @return the name of this song
   */
  public String getName() {
    return name;
  }

  /**
   * Compares two songs for ordering with respect to their ratings, years, and names
   * 
   * @return 0 if otherSong has the same year, same rate, and same name as this song; an integer
   *         less then 0 if this song is older than the otherSong, or if they were produced in the
   *         same year but this song has a lower rating, or if they have the same year and same
   *         rating but this song has a lower lexical order than otherSong; otherwise returns an
   *         integer greater than 0
   * 
   */
  @Override
  public int compareTo(Song otherSong) {
    if (this.year == otherSong.year) {
      if (this.rating == otherSong.rating) {
        return this.name.compareTo(otherSong.name);
      } else if (this.rating < otherSong.rating) {
        return -1;
      } else {
        return 1;
      }
    }
    return this.year - otherSong.year;
  }

  /**
   * Checks whether this song equals to the other song
   * 
   * @param obj other object to compare @ return true if this song is equal to the input argument
   * obj and false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    return (obj != null && obj instanceof Song && compareTo((Song) obj) == 0);
  }

  /**
   * Returns a String representation of the song
   * 
   * @return the song as a String in the format
   */
  public String toString() {
    return "[(Year: " + year + ") (Rate: " + rating + ") (Name: " + name + ")]";
  }

}
