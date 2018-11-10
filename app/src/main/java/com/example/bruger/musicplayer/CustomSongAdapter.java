package com.example.bruger.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomSongAdapter extends BaseAdapter {

    public CustomSongAdapter(Context context, int layout, ArrayList<Song> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    private Context context;
    private int layout;
    private ArrayList<Song> arrayList;
    private MediaPlayer mediaPlayer;
    private Song currentSong;

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Store our views in a private class
    private class ViewHolder {
        TextView txtName, txtSinger;
        ImageView playB, stopB;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //create an instance of viewHolder class
        final ViewHolder viewHolder;

        if (view == null) {
            //create a new viewHolder object
            viewHolder = new ViewHolder();

            // inflate (create) another copy of our custom layout
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);

            viewHolder.txtName = (TextView) view.findViewById(R.id.songName_text);
            viewHolder.txtSinger = (TextView) view.findViewById(R.id.singer_text);
            viewHolder.playB = (ImageView) view.findViewById(R.id.play_png);
            viewHolder.stopB = (ImageView) view.findViewById(R.id.stop_png);

            //setTag is used as a multiple object holder (in this case our views)
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Song song = arrayList.get(position);

        // make changes to our custom layout and its subviews
        viewHolder.txtName.setText(song.getName());
        viewHolder.txtSinger.setText(song.getSinger());
        Log.d("view", "Created the views");
        try {
            // play music
            viewHolder.playB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // MediaPlayer has not been initialized OR clicked song is not the currently loaded song
                    if (currentSong == null || song != currentSong) {
                        Log.d("csong", "Clicked on an audio file");

                        // Sets the currently loaded song
                        currentSong = song;
                        mediaPlayer = MediaPlayer.create(context, song.getSong());
                        Log.d("song", "Created the song");

                        Toast.makeText(context, "Playing: " + song.getSinger() + " " + song.getName(), Toast.LENGTH_LONG).show();
                    }

                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        Log.d("song", "Pause song");
                        viewHolder.playB.setImageResource(R.drawable.play);
                    } else {
                        Log.d("song", "Started song");
                        mediaPlayer.start();
                        viewHolder.playB.setImageResource(R.drawable.pause);
                    }
                }
            });

            // stop
            viewHolder.stopB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // If currently loaded song is set the MediaPlayer must be initialized
                    if (currentSong != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        currentSong = null; // Set back currently loaded song
                        Log.d("song", "Stopped song");
                    }
                    viewHolder.playB.setImageResource(R.drawable.play);
                }
            });
        } catch (Exception e) {
            Log.d("song", "ERROR:" + e.toString());
        }
        return view;
    }
}
