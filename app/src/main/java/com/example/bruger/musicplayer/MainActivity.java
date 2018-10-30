package com.example.bruger.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Song> arrayList;
    private CustomSongAdapter adapter;
    private ListView songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get listView
        songList = (ListView) findViewById(R.id.song_ListView);

        arrayList = new ArrayList<>();
        arrayList.add(new Song("- Formation","Beyoncé",R.raw.beyonce_formation));
        arrayList.add(new Song("- Hope You Do","Chris Brown",R.raw.chrisbrown_hopeyoudo));
        arrayList.add(new Song("- Beautiful","Akon ft. Colby'O'Donis",R.raw.akon_beautiful_ft_colbyodonis_kardinaloffishall));
        arrayList.add(new Song("- Don't Matter", "Akon", R.raw.akon_dontmatter));
        arrayList.add(new Song("- Locked Up", "Akon", R.raw.akon_lockedup_ft_stylesp));
        arrayList.add(new Song("- Sweet but Psycho", "Ava Max", R.raw.avamax_sweetbutpsycho));
        arrayList.add(new Song("- Ghetto","Tupac and Biggie ft. Akon Remix", R.raw.tupacbiggieakon_ghetto));
        adapter = new CustomSongAdapter(this, R.layout.custom_song_item, arrayList);
        songList.setAdapter(adapter);
    }
}
