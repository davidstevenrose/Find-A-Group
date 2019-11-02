package io.github.davidstevenrose;

public enum TagCollection {
  GAMING("Gaming"),
  SPORTS("Sports"),
  FITNESS("Fitness"),
  READING("Reading"),
  STUDY("Study"), FUN("Fun"),
  MOVIES("Movies"),
  ART("Art");

  private String name;

  TagCollection(String s){
    name = s;
  }

  public String getName(){
    return name;
  }

}
