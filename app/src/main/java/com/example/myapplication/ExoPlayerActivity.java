package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.media.session.MediaButtonReceiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class ExoPlayerActivity extends AppCompatActivity {
    SimpleExoPlayer player;
    private MediaSessionCompat mediaSession;
    private MediaSessionConnector mediaSessionConnector;
    private MediaControllerCompat controller;
    private MediaMetadataCompat mediaMetadata;
    private MediaDescriptionCompat description;

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

        mediaSession = new MediaSessionCompat(this, getPackageName());
        mediaSessionConnector = new MediaSessionConnector(mediaSession);

        // onStart() or onResume() according to API level
        mediaSessionConnector.setPlayer(player);
        mediaSession.setActive(true);

        // onPause() or onStop() according to API level
        // mediaSessionConnector.setPlayer(null, null, null);
        // mediaSession.setActive(false);

        // Bind the player to the view.
        playerView.setPlayer(player);

        // notification using exoplayer
        controller      = mediaSession.getController();
        mediaMetadata   = controller.getMetadata();
        description     = mediaMetadata.getDescription();

        // notification simple version
        // notification1();

        // notification simple version
        notification2();

    }

    private void notification2() {
        // add 2 more optional paramaters
        /* notificationListener= */
        /* customActionReceiver */
        PlayerNotificationManager playerNotificationManager = new PlayerNotificationManager(
                this,
                "channel_name_2",
                1,
                new PlayerNotificationManager.MediaDescriptionAdapter() {
                    @Override
                    public CharSequence getCurrentContentTitle(Player player) {
                        return "title";
                    }

                    @Nullable
                    @Override
                    public PendingIntent createCurrentContentIntent(Player player) {
                        return null;
                    }

                    @Nullable
                    @Override
                    public CharSequence getCurrentContentText(Player player) {
                        return "content";
                    }

                    @Nullable
                    @Override
                    public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback callback) {
                        return BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.flag_afghanistan);
                    }
                });

        playerNotificationManager.setColor(R.color.red_btn_bg_color); // doesn't work
        playerNotificationManager.setColorized(true); // doesn't work
        playerNotificationManager.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        playerNotificationManager.setMediaSessionToken(mediaSession.getSessionToken());
        playerNotificationManager.setPriority(NotificationCompat.PRIORITY_HIGH);
        playerNotificationManager.setUseChronometer(true);
        playerNotificationManager.setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE);
        playerNotificationManager.setSmallIcon(R.drawable.interest_icon_mainan);
        playerNotificationManager.setPlayer(player);
        playerNotificationManager.setUseNextAction(false);
        playerNotificationManager.setUseNextActionInCompactView(false);
        playerNotificationManager.setUsePlayPauseActions(false);
        playerNotificationManager.setUsePreviousAction(false);
        playerNotificationManager.setUsePreviousActionInCompactView(false);
        playerNotificationManager.setUseStopAction(false);
        playerNotificationManager.setRewindIncrementMs(0); // remove back
        playerNotificationManager.setFastForwardIncrementMs(0); // remove fast forward
        // if you want to remove the seek bar, use https://stackoverflow.com/questions/60837124/how-to-remove-seek-bar-from-exoplayer
    }

    private void notification1() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), "channel_name_2");

        builder
                // Add the metadata for the currently playing track
                .setContentTitle("Title")
                .setContentText("Subtitle")
                .setSubText("Description")

                // Enable launching the player by clicking the notification
                .setContentIntent(controller.getSessionActivity())

                // Stop the service when the notification is swiped away. Remove this makes the notification to be removable
                .setDeleteIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(getBaseContext(), PlaybackStateCompat.ACTION_STOP))

                // Make the transport controls visible on the lockscreen
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

                // Add an app icon and set its accent color
                // Be careful about the color
                .setSmallIcon(R.drawable.flag_afghanistan)
                .setLargeIcon(BitmapFactory.decodeResource(getBaseContext().getResources(),R.drawable.flag_afghanistan))
                .setColor( getResources().getColor(R.color.appintro_title_color) )

                // Add a pause button
                /*
                .addAction(new NotificationCompat.Action(
                        R.drawable.exo_icon_pause, "Paused",
                        MediaButtonReceiver.buildMediaButtonPendingIntent(getBaseContext(), PlaybackStateCompat.ACTION_PLAY_PAUSE)))
                */
                // Take advantage of MediaStyle features
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                                .setMediaSession(mediaSession.getSessionToken())
                                .setShowActionsInCompactView(0)
                                // Add a cancel button
                                .setShowCancelButton(false)
                        /*
                        .setCancelButtonIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(getBaseContext(),
                                PlaybackStateCompat.ACTION_STOP))
                         */
                );

        // Display the notification and place the service in the foreground
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(88, builder.build());
    }
}