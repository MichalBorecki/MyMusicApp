package pl.kabeonet.mymusicapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import pl.kabeonet.mymusicapp.databinding.ActivityPlayerBinding;

public class PlayerActivity extends AppCompatActivity {

    boolean isPlaying;
    boolean isRepeated;
    boolean isShuffled;
    int indexOfPlayingSong;
    ActivityPlayerBinding bind;
    String songPlaying;
    ArrayList<Song> allSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_player);
        indexOfPlayingSong = 0;

        allSongs = getIntent().getParcelableArrayListExtra("allSongs");

        songPlaying = getIntent().getExtras().getString("clickedItem");
        getSupportActionBar().setTitle(getString(R.string.song_playing, songPlaying));
        isPlaying = true;
        isShuffled = false;
        isRepeated = false;

        final String checkFlag = getIntent().getStringExtra("flag");

        switch (checkFlag) {
            case "allSongs":
                String songTitle = getIntent().getExtras().getString("clickedItem");
                for (int i = 0; i < allSongs.size(); i++) {
                    if (songTitle.equals(allSongs.get(i).getSongTitle())) {
                        indexOfPlayingSong = i;
                    }
                }
                sortSongs(allSongs);
                break;
            case "artist":
                String artist = getIntent().getExtras().getString("clickedItem");
                for (int i = -1; i < allSongs.size() - 1; i++) {
                    while (!artist.equals(allSongs.get(i + 1).getArtistName())) {
                        allSongs.remove(i + 1);
                        if (i == allSongs.size() - 1) {
                            break;
                        }
                    }
                }
                sortSongs(allSongs);
                break;
            case "album":
                String album = getIntent().getExtras().getString("clickedItem");
                for (int i = -1; i < allSongs.size() - 1; i++) {
                    while (!album.equals(allSongs.get(i + 1).getAlbumTitle())) {
                        allSongs.remove(i + 1);
                        if (i == allSongs.size() - 1) {
                            break;
                        }
                    }
                }
                sortSongs(allSongs);
                break;
        }

        bind.include.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                songPlaying = allSongs.get(position).getSongTitle();
                getSupportActionBar().setTitle(getString(R.string.song_playing, songPlaying));
            }
        });

        MusicAdapter musicAdapter = new MusicAdapter(this, allSongs);
        bind.include.list.setBackground(null);
        bind.include.list.setAdapter(musicAdapter);
        bind.include.list.setSelection(indexOfPlayingSong);

        bind.pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    bind.pauseBtn.setImageResource(R.drawable.ic_play_turq);
                    getSupportActionBar().setTitle(getString(R.string.song_paused, songPlaying));
                    isPlaying = false;
                } else {
                    bind.pauseBtn.setImageResource(R.drawable.ic_pause_turq);
                    getSupportActionBar().setTitle(getString(R.string.song_playing, songPlaying));
                    isPlaying = true;
                }
            }
        });

        bind.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bind.backBtn.setImageResource(R.drawable.ic_back_turq);
                onBackPressed();
            }
        });

        bind.shuffleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allSongs.size() == 1) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.cannot_shuffle, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (isShuffled) {
                        bind.shuffleBtn.setImageResource(R.drawable.ic_shuffle_gray);
                        sortSongs(allSongs);
                        isShuffled = false;
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.songs_alphabetically, Toast.LENGTH_SHORT);
                        toast.show();
                        bind.include.list.invalidateViews();
                    } else {
                        bind.shuffleBtn.setImageResource(R.drawable.ic_shuffle_turq);
                        Collections.shuffle(allSongs);
                        isShuffled = true;
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.songs_randomly, Toast.LENGTH_SHORT);
                        toast.show();
                        bind.include.list.invalidateViews();
                    }
                }
            }
        });

        bind.repeatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRepeated) {
                    bind.repeatBtn.setImageResource(R.drawable.ic_repeat_turq);
                    isRepeated = true;
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.repeat_song, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    bind.repeatBtn.setImageResource(R.drawable.ic_repeat_gray);
                    isRepeated = false;
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.not_repeat_song, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        bind.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bind.homeBtn.setImageResource(R.drawable.ic_home_turq);
                Intent mainActivity = new Intent(PlayerActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }

    private static void sortSongs(ArrayList<Song> allSongs) {
        Collections.sort(allSongs, new Comparator<Song>() {
            public int compare(Song s1, Song s2) {
                return s1.getSongTitle().compareTo(s2.getSongTitle());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
