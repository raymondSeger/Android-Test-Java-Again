package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class ExoPlayerActivity extends AppCompatActivity {
    SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);

        player = new SimpleExoPlayer.Builder(ExoPlayerActivity.this).build();

        // components https://exoplayer.dev/ui-components.html
        StyledPlayerView playerView = (StyledPlayerView) findViewById(R.id.player_view);

        /*
        // play one video
        // Build the media item.
        MediaItem mediaItem = MediaItem.fromUri("android.resource://" + getPackageName() + "/" + R.raw.sample_video);
        // Set the media item to be played.
        player.setMediaItem(mediaItem);
        // Prepare the player.
        player.prepare();
        // Start the playback.
        player.play();
        */

        // play multiple items
        // Build the media items.
        MediaItem firstItem = MediaItem.fromUri("android.resource://" + getPackageName() + "/" + R.raw.sample_video);
        MediaItem secondItem = MediaItem.fromUri("android.resource://" + getPackageName() + "/" + R.raw.main_activity_video);
        // Add the media items to be played.
        player.addMediaItem(firstItem);
        player.addMediaItem(secondItem);
        // Prepare the player.
        player.prepare();
        // Start the playback.
        player.play();

        // Bind the player to the view.
        playerView.setPlayer(player);
    }
}