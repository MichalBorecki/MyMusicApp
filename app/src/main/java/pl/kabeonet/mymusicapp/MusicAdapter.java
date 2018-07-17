package pl.kabeonet.mymusicapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.graphics.Typeface.BOLD;
import static pl.kabeonet.mymusicapp.AlbumActivity.ALBUMS_ACTIVE;
import static pl.kabeonet.mymusicapp.ArtistActivity.ARTISTS_ACTIVE;

public class MusicAdapter extends ArrayAdapter<Song> {
    public MusicAdapter(Activity context, ArrayList<Song> musicList) {
        super(context, 0, musicList);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listSongView = convertView;
        if (listSongView == null) {
            listSongView = LayoutInflater.from(getContext()).inflate(R.layout.song_view, parent, false);
        }
        Song currentMusicTrack = getItem(position);

        TextView songTitle = listSongView.findViewById(R.id.song_title_view);
        songTitle.setText(currentMusicTrack.getSongTitle());
        TextView artistName = listSongView.findViewById(R.id.artist_name_view);
        artistName.setText(currentMusicTrack.getArtistName());
        ImageView musicImage = listSongView.findViewById(R.id.album_cover_view);
        musicImage.setImageResource(currentMusicTrack.getCoverId());
        TextView albumTitle = listSongView.findViewById(R.id.album_title_view);
        albumTitle.setText(currentMusicTrack.getAlbumTitle());

        Typeface typeface = Typeface.create("monospace", BOLD);
        if (ARTISTS_ACTIVE) {
            artistName.setTypeface(typeface);
            artistName.setTextSize(20);
        }
        if (ALBUMS_ACTIVE) {
            albumTitle.setTypeface(typeface);
            albumTitle.setTextSize(20);
        }
        if (songTitle.getText().equals("")) {
            songTitle.setVisibility(View.GONE);
        }
        if (albumTitle.getText().equals("")) {
            albumTitle.setVisibility(View.GONE);
        }
        return listSongView;
    }
}
