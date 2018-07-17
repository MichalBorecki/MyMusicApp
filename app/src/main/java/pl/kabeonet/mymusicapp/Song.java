package pl.kabeonet.mymusicapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
    private int albumCover;
    private String songTitle;
    private String albumTitle;
    private String artistName;

    protected Song(Parcel in) {
        albumCover = in.readInt();
        artistName = in.readString();
        albumTitle = in.readString();
        songTitle = in.readString();
    }

    public Song(int albumCover, String artistName, String albumTitle, String songTitle) {
        this.albumCover = albumCover;
        this.artistName = artistName;
        this.albumTitle = albumTitle;
        this.songTitle = songTitle;
    }

    public int getCoverId() {
        return this.albumCover;
    }

    public void setCoverId(int cover) {
        this.albumCover = cover;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.albumCover);
        dest.writeString(this.artistName);
        dest.writeString(this.albumTitle);
        dest.writeString(this.songTitle);
    }
}
