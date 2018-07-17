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

public class SongActivity extends AppCompatActivity {
    MusicListBinding bind;
    ArrayList<Song> allSongs;
    ArrayList<Song> songsToChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bind = DataBindingUtil.setContentView(this, R.layout.music_list);
        allSongs = getIntent().getParcelableArrayListExtra("allSongs");
        songsToChoose = getIntent().getParcelableArrayListExtra("songsToChoose");

        Collections.sort(songsToChoose, new Comparator<Song>() {
            public int compare(Song s1, Song s2) {
                return s1.getSongTitle().compareTo(s2.getSongTitle());
            }
        });

        bind.list.setAdapter(new MusicAdapter(this, songsToChoose));

        bind.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent playerIntent = new Intent(SongActivity.this, PlayerActivity.class);
                String songTitle = songsToChoose.get(position).getSongTitle();
                playerIntent.putExtra("flag", "allSongs");
                playerIntent.putExtra("clickedItem", songTitle);
                playerIntent.putParcelableArrayListExtra("allSongs", allSongs);
                startActivity(playerIntent);
            }
        });
    }
}
