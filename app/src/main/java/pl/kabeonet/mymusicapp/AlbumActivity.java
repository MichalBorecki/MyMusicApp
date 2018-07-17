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

public class AlbumActivity extends AppCompatActivity {
    static boolean ALBUMS_ACTIVE;
    MusicListBinding bind;
    ArrayList<Song> allSongs;
    ArrayList<Song> songsToChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ALBUMS_ACTIVE = true;
        bind = DataBindingUtil.setContentView(this, R.layout.music_list);
        allSongs = getIntent().getParcelableArrayListExtra("allSongs");
        songsToChoose = getIntent().getParcelableArrayListExtra("songsToChoose");

        Collections.sort(songsToChoose, new Comparator<Song>() {
            public int compare(Song s1, Song s2) {
                return s1.getAlbumTitle().compareTo(s2.getAlbumTitle());
            }
        });

        for (int i = 0; i < songsToChoose.size(); i++) {
            while (songsToChoose.get(i).getAlbumTitle().equals(songsToChoose.get(i + 1).getAlbumTitle())) {
                songsToChoose.remove(i + 1);
                if (i == songsToChoose.size() - 1) {
                    break;
                }
            }
            songsToChoose.get(i).setSongTitle("");
        }

        bind.list.setAdapter(new MusicAdapter(this, songsToChoose));

        bind.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent playerIntent = new Intent(AlbumActivity.this, PlayerActivity.class);
                String albumTitle = songsToChoose.get(position).getAlbumTitle();
                playerIntent.putExtra("flag", "album");
                playerIntent.putExtra("clickedItem", albumTitle);
                playerIntent.putParcelableArrayListExtra("allSongs", allSongs);
                startActivity(playerIntent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        ALBUMS_ACTIVE = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        ALBUMS_ACTIVE = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ALBUMS_ACTIVE = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ALBUMS_ACTIVE = true;
    }
}
