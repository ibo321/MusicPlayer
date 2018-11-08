package com.example.bruger.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
    private Boolean clickedPlaying = true;

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

    private class ViewHolder {
        TextView txtName, txtSinger;
        ImageView playB, stopB;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.songName_text);
            viewHolder.txtSinger = (TextView) convertView.findViewById(R.id.singer_text);
            viewHolder.playB = (ImageView) convertView.findViewById(R.id.play_png);
            viewHolder.stopB = (ImageView) convertView.findViewById(R.id.stop_png);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Song song = arrayList.get(position);

        viewHolder.txtName.setText(song.getName());
        viewHolder.txtSinger.setText(song.getSinger());


        // play music
        viewHolder.playB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedPlaying) {
                    //get song you clicked on
                    mediaPlayer = MediaPlayer.create(context, song.getSong());

                    //set boolean to false so it can get ANOTHER song when clicked
                    clickedPlaying = false;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    viewHolder.playB.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    viewHolder.playB.setImageResource(R.drawable.pause);
                }
            }
        });

        // stop
        viewHolder.stopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clickedPlaying) {
                    mediaPlayer.stop();
                    mediaPlayer.release();

                    clickedPlaying = true;
                }
                viewHolder.playB.setImageResource(R.drawable.play);
            }
        });
        return convertView;
    }
}
