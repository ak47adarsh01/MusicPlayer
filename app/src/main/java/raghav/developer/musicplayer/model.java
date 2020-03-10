package raghav.developer.musicplayer;

public class model {

  public String songName, artist, songURL, coverURL;

  public model() {
  }

  public model(String songName, String artist, String songURL, String coverURL) {
    this.songName = songName;
    this.artist = artist;
    this.songURL = songURL;
    this.coverURL = coverURL;
  }

  public String getSongName() {
    return songName;
  }

  public void setSongName(String songName) {
    this.songName = songName;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getSongURL() {
    return songURL;
  }

  public void setSongURL(String songURL) {
    this.songURL = songURL;
  }

  public String getCoverURL() {
    return coverURL;
  }

  public void setCoverURL(String coverURL) {
    this.coverURL = coverURL;
  }
}