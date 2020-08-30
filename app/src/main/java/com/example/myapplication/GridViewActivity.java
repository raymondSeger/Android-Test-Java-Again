package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.objects.Interest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class GridViewActivity extends AppCompatActivity {

    public Set<String> chosen_interest;
    private GridView interests_gridview;

    @Override
    public void onBackPressed() {
        // don't let user go back
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        // set
        GridView interests_gridview         = (GridView) findViewById(R.id.interests_gridview);
        ArrayList<Interest> interests_data  = new ArrayList<Interest>();
        chosen_interest                     = new HashSet<>();

        // show the dialog
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Attention")
                .setContentText("Choose at least one interest that you love to follow")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();

        // !TODO replace with real data from Cloud
        // TEST prepare the gridview data
        Interest interest_1     = new Interest(1, R.drawable.interest_icon_percintaan , "Percintaan");
        Interest interest_2     = new Interest(2, R.drawable.interest_icon_hewan_kesayangan , "Hewan Kesayangan");
        Interest interest_3     = new Interest(3, R.drawable.interest_icon_film_interest , "Film");
        Interest interest_4     = new Interest(4, R.drawable.interest_icon_kecantikan , "Kecantikan");
        Interest interest_5     = new Interest(5, R.drawable.interest_icon_buku , "Buku");
        Interest interest_6     = new Interest(6, R.drawable.interest_icon_fitness_kesehatan , "Fitness & Kesehatan");
        Interest interest_7     = new Interest(7, R.drawable.interest_icon_olahraga , "Olahraga");
        Interest interest_8     = new Interest(8, R.drawable.interest_icon_travel , "Travel");
        Interest interest_9     = new Interest(9, R.drawable.interest_icon_teknologi , "Teknologi");
        Interest interest_10    = new Interest(10, R.drawable.interest_icon_pendidikan , "Pendidikan");
        Interest interest_11    = new Interest(11, R.drawable.interest_icon_fotografi , "Fotografi");
        Interest interest_12    = new Interest(12, R.drawable.interest_icon_komik , "Komik");
        Interest interest_13    = new Interest(13, R.drawable.interest_icon_musik , "Musik");
        Interest interest_14    = new Interest(14, R.drawable.interest_icon_berita , "Berita");
        Interest interest_15    = new Interest(15, R.drawable.interest_icon_anime , "Anime");
        Interest interest_16    = new Interest(16, R.drawable.interest_icon_seni_desain , "Seni & Design");
        Interest interest_17    = new Interest(17, R.drawable.interest_icon_otomotif , "Otomatif");
        Interest interest_18    = new Interest(18, R.drawable.interest_icon_kutipan , "Kutipan");
        Interest interest_19    = new Interest(19, R.drawable.interest_icon_mode , "Mode");
        Interest interest_20    = new Interest(20, R.drawable.interest_icon_bisnis , "Bisnis");
        Interest interest_21    = new Interest(21, R.drawable.interest_icon_cosplay , "Cosplay");
        Interest interest_22    = new Interest(22, R.drawable.interest_icon_lain_lain , "Lain - Lain");
        Interest interest_23    = new Interest(23, R.drawable.interest_icon_lapak , "Lapak");
        Interest interest_24    = new Interest(24, R.drawable.interest_icon_kuis , "Kuis");
        Interest interest_25    = new Interest(25, R.drawable.interest_icon_kebudayaan , "Kebudayaan");
        Interest interest_26    = new Interest(26, R.drawable.interest_icon_mainan , "Mainan");
        Interest interest_27    = new Interest(27, R.drawable.interest_icon_humor , "Humor");
        Interest interest_28    = new Interest(28, R.drawable.interest_icon_selebriti , "Selebriti");
        Interest interest_29    = new Interest(29, R.drawable.interest_icon_game , "Game");
        Interest interest_30    = new Interest(30, R.drawable.interest_icon_kuliner , "Kuliner");
        Interest interest_31    = new Interest(31, R.drawable.interest_icon_ponsel , "Ponsel");
        interests_data.add(interest_1);
        interests_data.add(interest_2);
        interests_data.add(interest_3);
        interests_data.add(interest_4);
        interests_data.add(interest_5);
        interests_data.add(interest_6);
        interests_data.add(interest_7);
        interests_data.add(interest_8);
        interests_data.add(interest_9);
        interests_data.add(interest_10);
        interests_data.add(interest_11);
        interests_data.add(interest_12);
        interests_data.add(interest_13);
        interests_data.add(interest_14);
        interests_data.add(interest_15);
        interests_data.add(interest_16);
        interests_data.add(interest_17);
        interests_data.add(interest_18);
        interests_data.add(interest_19);
        interests_data.add(interest_20);
        interests_data.add(interest_21);
        interests_data.add(interest_22);
        interests_data.add(interest_23);
        interests_data.add(interest_24);
        interests_data.add(interest_25);
        interests_data.add(interest_26);
        interests_data.add(interest_27);
        interests_data.add(interest_28);
        interests_data.add(interest_29);
        interests_data.add(interest_30);
        interests_data.add(interest_31);

        // set the gridview with the data
        interests_gridview.setAdapter( new InterestAdapter(getApplicationContext(), interests_data) );


    }

    // The adapter used by Interest-GridView
    public class InterestAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<Interest> all_interests;

        public InterestAdapter(Context c, ArrayList<Interest> interests) {
            mContext        = c;
            all_interests   = interests;
        }

        public Set<String> getChosenInterest() {
            return chosen_interest;
        }

        public int getCount() {
            return all_interests.size();
        }

        public Object getItem(int position) {
            return all_interests.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = null;

            if (convertView == null) {
                LayoutInflater inflater     = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView                 = (View) inflater.inflate( R.layout.layout_for_interest, null);

                final LinearLayout main_layout  = (LinearLayout) convertView.findViewById(R.id.main_layout);
                ImageView interest_image        = (ImageView) convertView.findViewById(R.id.interest_image);
                TextView interest_name          = (TextView) convertView.findViewById(R.id.interest_name);

                interest_image.setImageResource( all_interests.get(position).getImageDrawableId() );
                interest_name.setText( all_interests.get(position).getName() );

                // current position
                final Interest current_interest     = all_interests.get(position);

                // set the bgColor
                if( chosen_interest.contains( String.valueOf( current_interest.getId() ) ) ) {
                    main_layout.setBackgroundColor(Color.parseColor("#bdc3c7"));
                } else {
                    main_layout.setBackgroundColor(Color.parseColor("#ffffff"));
                }

                // set click handler
                main_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String current_selected_interest_id = String.valueOf( current_interest.getId() );
                        if ( chosen_interest.contains(current_selected_interest_id) ) {
                            chosen_interest.remove(current_selected_interest_id);
                            Toast.makeText(getApplicationContext(), "You have removed layout_for_interest with ID: " + chosen_interest.toString(), Toast.LENGTH_LONG).show();
                            main_layout.setBackgroundColor(Color.parseColor("#ffffff"));
                        } else {
                            chosen_interest.add( String.valueOf(current_interest.getId()) );
                            Toast.makeText(getApplicationContext(), "You have chosen layout_for_interest with ID: " + chosen_interest.toString(), Toast.LENGTH_LONG).show();
                            main_layout.setBackgroundColor(Color.parseColor("#bdc3c7"));
                        }

                        // if there is at least 1 chosen layout_for_interest, allow user to move to the Main Page
                        if( chosen_interest.size() > 0 ) {

                        } else {

                        }
                    }
                });
            }

            return convertView;
        }

    }

}