package pl.kabeonet.mymusicapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import pl.kabeonet.mymusicapp.databinding.MusicListBinding;

public class ArtistActivity extends AppCompatActivity {
    static boolean ARTISTS_ACTIVE;
    MusicListBinding bind;
    ArrayList<Song> allSongs;
    ArrayList<Song> songsToChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARTISTS_ACTIVE = true;
        bind = DataBindingUtil.setContentView(this, R.layout.music_list);
        allSongs = getIntent().getParcelableArrayListExtra("allSongs");
        songsToChoose = getIntent().getParcelableArrayListExtra("songsToChoose");

        Collections.sort(songsToChoose, new Comparator<Song>() {
            public int compare(Song s1, Song s2) {
                return s1.getArtistName().compareTo(s2.getArtistName());
            }
        });

        for (int i = 0; i < songsToChoose.size(); i++) {
            while (songsToChoose.get(i).getArtistName().equals(songsToChoose.get(i + 1).getArtistName())) {
                songsToChoose.remove(i + 1);
                if (i == songsToChoose.size() - 1) {
                    break;
                }
            }
            songsToChoose.get(i).setCoverId(0);
            songsToChoose.get(i).setSongTitle("");
            songsToChoose.get(i).setAlbumTitle("");
        }

        bind.list.setAdapter(new MusicAdapter(this, songsToChoose));

        bind.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent playerIntent = new Intent(ArtistActivity.this, PlayerActivity.class);
                String artistName = songsToChoose.get(position).getArtistName();
                playerIntent.putExtra("flag", "artist");
                playerIntent.putExtra("clickedItem", artistName);
                playerIntent.putParcelableArrayListExtra("allSongs", allSongs);
                startActivity(playerIntent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        ARTISTS_ACTIVE = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        ARTISTS_ACTIVE = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ARTISTS_ACTIVE = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ARTISTS_ACTIVE = true;
    }
}