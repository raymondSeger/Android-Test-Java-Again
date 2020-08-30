package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.tiancaicc.springfloatingactionmenu.OnMenuActionListener;
import com.tiancaicc.springfloatingactionmenu.SpringFloatingActionMenu;

public class SpringFloatingActionMenuActivity extends AppCompatActivity {

    // tumblr style FAB - Begin
    private static int[] frameAnimRes = new int[]{
            R.mipmap.compose_anim_1,
            R.mipmap.compose_anim_2,
            R.mipmap.compose_anim_3,
            R.mipmap.compose_anim_4,
            R.mipmap.compose_anim_5,
            R.mipmap.compose_anim_6,
            R.mipmap.compose_anim_7,
            R.mipmap.compose_anim_8,
            R.mipmap.compose_anim_9,
            R.mipmap.compose_anim_10,
            R.mipmap.compose_anim_11,
            R.mipmap.compose_anim_12,
            R.mipmap.compose_anim_13,
            R.mipmap.compose_anim_14,
            R.mipmap.compose_anim_15,
            R.mipmap.compose_anim_15,
            R.mipmap.compose_anim_16,
            R.mipmap.compose_anim_17,
            R.mipmap.compose_anim_18,
            R.mipmap.compose_anim_19
    };
    private SpringFloatingActionMenu springFloatingActionMenu;
    private int frameDuration = 20;
    private AnimationDrawable frameAnim;
    private AnimationDrawable frameReverseAnim;
    // tumblr style FAB - End

    @Override
    public void onBackPressed() {
        // cannot go back. To make things simpler
        if (springFloatingActionMenu.isMenuOpen()) {
            springFloatingActionMenu.hideMenu();
        } else {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_floating_action_menu);

        // create Tumblr style FAB button
        set_tumble_style_fab();

    }

    private void createFabFrameAnim() {
        frameAnim = new AnimationDrawable();
        frameAnim.setOneShot(true);
        Resources resources = getResources();
        for (int res : frameAnimRes) {
            frameAnim.addFrame(resources.getDrawable(res), frameDuration);
        }
    }

    private void createFabReverseFrameAnim() {
        frameReverseAnim = new AnimationDrawable();
        frameReverseAnim.setOneShot(true);
        Resources resources = getResources();
        for (int i = frameAnimRes.length - 1; i >= 0; i--) {
            frameReverseAnim.addFrame(resources.getDrawable(frameAnimRes[i]), frameDuration);
        }
    }

    public void set_tumble_style_fab() {
        // create Fab Animation for Icon
        createFabFrameAnim();

        // create Fab Animation Reverse for Icon
        createFabReverseFrameAnim();

        // prepare the FAB button
        final FloatingActionButton fab = new FloatingActionButton(this);
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        fab.setImageDrawable(frameAnim);
        // fab.setImageResource(android.R.drawable.ic_dialog_email);
        fab.setColorPressedResId(R.color.fbutton_color_orange);
        fab.setColorNormalResId(R.color.fbutton_color_sun_flower);
        fab.setColorRippleResId(R.color.text_color);
        fab.setShadow(true);

        springFloatingActionMenu = new SpringFloatingActionMenu.Builder(this)
                .fab(fab)
                .addMenuItem(R.color.red_btn_bg_color, R.mipmap.compose_anim_1, "Text", R.color.text_color, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fab.performClick();
                        Toast.makeText(getBaseContext(),"text 1", Toast.LENGTH_SHORT).show();
                    }
                })
                .addMenuItem(R.color.red_btn_bg_color, R.mipmap.compose_anim_2, "Link", R.color.text_color, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        frameAnim.stop();
                        frameReverseAnim.start();
                        Toast.makeText(getBaseContext(),"text 2", Toast.LENGTH_SHORT).show();
                    }
                })
                .addMenuItem(R.color.red_btn_bg_color, R.mipmap.compose_anim_3, "Photo", R.color.text_color, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        frameAnim.stop();
                        frameReverseAnim.start();
                        Toast.makeText(getBaseContext(),"text 3", Toast.LENGTH_SHORT).show();
                    }
                })
                .addMenuItem(R.color.red_btn_bg_color, R.mipmap.compose_anim_4, "Video", R.color.text_color, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        frameAnim.stop();
                        frameReverseAnim.start();
                        Toast.makeText(getBaseContext(),"text 4", Toast.LENGTH_SHORT).show();
                    }
                })
                .addMenuItem(R.color.red_btn_bg_color, R.mipmap.compose_anim_5, "Audio", R.color.text_color, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        frameAnim.stop();
                        frameReverseAnim.start();
                        Toast.makeText(getBaseContext(),"text 5", Toast.LENGTH_SHORT).show();
                    }
                })
                .addMenuItem(R.color.red_btn_bg_color, R.mipmap.compose_anim_6, "Polls", R.color.text_color, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        frameAnim.stop();
                        frameReverseAnim.start();
                        Toast.makeText(getBaseContext(),"text 6", Toast.LENGTH_SHORT).show();
                    }
                })
                .animationType(SpringFloatingActionMenu.ANIMATION_TYPE_BLOOM)
                .revealColor(R.color.fbutton_color_pomegranate)
                .gravity(Gravity.RIGHT | Gravity.BOTTOM)
                .onMenuActionListner(new OnMenuActionListener() {
                    @Override
                    public void onMenuOpen() {
                        fab.setImageDrawable(frameAnim);
                        frameReverseAnim.stop();
                        frameAnim.start();
                    }

                    @Override
                    public void onMenuClose() {
                        fab.setImageDrawable(frameReverseAnim);
                        frameAnim.stop();
                        frameReverseAnim.start();
                    }
                })
                .build();
   }

}