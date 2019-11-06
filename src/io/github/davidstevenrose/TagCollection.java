package io.github.davidstevenrose;

/**
 * List of premade tags
 * @author drose
 */
public enum TagCollection {
  GAMING("Gaming"),
  SPORTS("Sports"),
  FITNESS("Fitness"),
  READING("Reading"),
  STUDY("Study"),
  FUN("Fun"),
  MOVIES("Movies"),
  ART("Art");

  private String name;

  TagCollection(String s) {
    name = s;
  }

  /**
   * Gets the label in CamelCase
   * @return the name
   */
  public String getName() {
    return name;
  }

}
