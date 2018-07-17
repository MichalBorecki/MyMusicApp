package pl.kabeonet.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Song> allSongs = new ArrayList<>();
    Button songBtn;
    Button artistBtn;
    Button albumBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allSongs.add(new Song(R.drawable.kortez_moj_dom_cover, getString(R.string.kortez), getString(R.string.moj_dom), getString(R.string.pierwsza)));
        allSongs.add(new Song(R.drawable.kortez_moj_dom_cover, getString(R.string.kortez), getString(R.string.moj_dom), getString(R.string.dobrze_ze_cie_mam)));
        allSongs.add(new Song(R.drawable.kortez_moj_dom_cover, getString(R.string.kortez), getString(R.string.moj_dom), getString(R.string.we_dwoje)));
        allSongs.add(new Song(R.drawable.kortez_moj_dom_cover, getString(R.string.kortez), getString(R.string.moj_dom), getString(R.string.film_przed_snem)));
        allSongs.add(new Song(R.drawable.kortez_moj_dom_cover, getString(R.string.kortez), getString(R.string.moj_dom), getString(R.string.dobry_moment)));
        allSongs.add(new Song(R.drawable.kortez_moj_dom_cover, getString(R.string.kortez), getString(R.string.moj_dom), getString(R.string.nic_tu_po_mnie)));
        allSongs.add(new Song(R.drawable.kortez_moj_dom_cover, getString(R.string.kortez), getString(R.string.moj_dom), getString(R.string.dziwny_sen)));
        allSongs.add(new Song(R.drawable.kortez_moj_dom_cover, getString(R.string.kortez), getString(R.string.moj_dom), getString(R.string.trudny_wiek)));
        allSongs.add(new Song(R.drawable.kortez_moj_dom_cover, getString(R.string.kortez), getString(R.string.moj_dom), getString(R.string.wyjdz_ze_mna_na_deszcz)));

        allSongs.add(new Song(R.drawable.organek_czarna_madonna_cover, getString(R.string.organek), getString(R.string.czarna_madonna), getString(R.string.introdukcja)));
        allSongs.add(new Song(R.drawable.organek_czarna_madonna_cover, getString(R.string.organek), getString(R.string.czarna_madonna), getString(R.string.rilke)));
        allSongs.add(new Song(R.drawable.organek_czarna_madonna_cover, getString(R.string.organek), getString(R.string.czarna_madonna), getString(R.string.mississipi_w_ogniu)));
        allSongs.add(new Song(R.drawable.organek_czarna_madonna_cover, getString(R.string.organek), getString(R.string.czarna_madonna), getString(R.string.get_it_right)));
        allSongs.add(new Song(R.drawable.organek_czarna_madonna_cover, getString(R.string.organek), getString(R.string.czarna_madonna), getString(R.string.son_of_a_gun)));
        allSongs.add(new Song(R.drawable.organek_czarna_madonna_cover, getString(R.string.organek), getString(R.string.czarna_madonna), getString(R.string.wiosna)));
        allSongs.add(new Song(R.drawable.organek_czarna_madonna_cover, getString(R.string.organek), getString(R.string.czarna_madonna), getString(R.string.hkdk)));
        allSongs.add(new Song(R.drawable.organek_czarna_madonna_cover, getString(R.string.organek), getString(R.string.czarna_madonna), getString(R.string.ki_czort)));

        songBtn = findViewById(R.id.song_btn);
        artistBtn = findViewById(R.id.artist_btn);
        albumBtn = findViewById(R.id.album_btn);

        songBtn.setOnClickListener(this);
        artistBtn.setOnClickListener(this);
        albumBtn.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.song_btn:
                songBtn.setTextColor(getResources().getColor(R.color.textColor));
                songBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                Intent songsIntent = new Intent(MainActivity.this, SongActivity.class);
                songsIntent.putParcelableArrayListExtra("songsToChoose", allSongs);
                songsIntent.putParcelableArrayListExtra("allSongs", allSongs);
                startActivity(songsIntent);
                break;
            case R.id.artist_btn:
                artistBtn.setTextColor(getResources().getColor(R.color.textColor));
                artistBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                Intent artistsIntent = new Intent(MainActivity.this, ArtistActivity.class);
                artistsIntent.putParcelableArrayListExtra("songsToChoose", allSongs);
                artistsIntent.putParcelableArrayListExtra("allSongs", allSongs);
                startActivity(artistsIntent);
                break;
            case R.id.album_btn:
                albumBtn.setTextColor(getResources().getColor(R.color.textColor));
                albumBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                Intent albumsIntent = new Intent(MainActivity.this, AlbumActivity.class);
                albumsIntent.putParcelableArrayListExtra("songsToChoose", allSongs);
                albumsIntent.putParcelableArrayListExtra("allSongs", allSongs);
                startActivity(albumsIntent);
                break;
        }
    }
}