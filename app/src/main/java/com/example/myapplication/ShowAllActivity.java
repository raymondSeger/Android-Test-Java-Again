package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.objects.ActivityObject;
import com.example.myapplication.objects.Employee;
import com.example.myapplication.objects.ExampleItem;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.generateViewId;

public class ShowAllActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        // retrieve secret message
        String message = (String) view.getTag();

        /*
        // show a message with the button's ID
        Toast toast = Toast.makeText(ShowAllActivity.this, "You clicked button " + message + " " + view.getId(), Toast.LENGTH_LONG);
        toast.show();
        */

        Class<?> c = null;
        try {
            c               = Class.forName("com.example.myapplication." + message);
            Intent intent   = new Intent(getBaseContext(), c);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*
        // get the parent layout and remove the clicked button
        LinearLayout parentLayout = (LinearLayout) view.getParent();
        parentLayout.removeView(view);
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        LinearLayout linear_layout = (LinearLayout) findViewById(R.id.linear_layout);

        ArrayList<ActivityObject> the_list  = new ArrayList<>();
        the_list.add( new ActivityObject("GetLocationDataActivity", "1. Get Location Data") );
        the_list.add( new ActivityObject("GetSetPreferencesActivity", "2. Get Set Preferences") );
        the_list.add( new ActivityObject("GetStringActivity", "3. Get String") );
        the_list.add( new ActivityObject("GoToOtherActivity", "4. Go To Other Activity") );
        the_list.add( new ActivityObject("JsonActivity", "5. Json") );
        the_list.add( new ActivityObject("NotificationActivity", "6. Notification") );
        the_list.add( new ActivityObject("PlayMusicActivity", "7. Play Music") );
        the_list.add( new ActivityObject("SecondaryActivity", "8. Load Web View") );
        the_list.add( new ActivityObject("TryIntentsActivity", "9. Try Intents") );
        the_list.add( new ActivityObject("VibrateActivity", "10. Vibrate") );
        the_list.add( new ActivityObject("ThirdActivity", "11. Video View") );
        the_list.add( new ActivityObject("PinnedShortcutActivity", "12. Pinned Shortcut") );
        the_list.add( new ActivityObject("PictureInPictureActivity", "13. Picture In Picture") );
        the_list.add( new ActivityObject("CopyPasteActivity", "14. Copy Paste") );
        the_list.add( new ActivityObject("SnackbarActivity", "15. Snackbar") );
        the_list.add( new ActivityObject("AppBarActivity", "16. App Bar") );
        the_list.add( new ActivityObject("FragmentsActivity", "17. Fragments") );
        the_list.add( new ActivityObject("SettingActivity", "18. Setting") );
        the_list.add( new ActivityObject("ShowModalBottomActivity", "19. Show Modal Bottom") );
        the_list.add( new ActivityObject("TextToSpeechActivity", "20. Text To Speech") );
        the_list.add( new ActivityObject("RecordVoicePlayVoiceActivity", "21. Record Voice Play Voice") );
        the_list.add( new ActivityObject("PhotoActivity", "22. Photo") );
        the_list.add( new ActivityObject("PickTimeAndDateActivity", "23. Pick Time And Date") );
        the_list.add( new ActivityObject("MenuContextActivity", "24. Menu Context") );
        the_list.add( new ActivityObject("DialogActivity", "25. Dialog") );
        the_list.add( new ActivityObject("AppIntroActivity", "26. App Intro") );
        the_list.add( new ActivityObject("GridViewActivity", "27. Grid View") );
        the_list.add( new ActivityObject("SpringFloatingActionMenuActivity", "28. Spring Floating Action Menu") );
        the_list.add( new ActivityObject("RecyclerViewActivity", "29. Recycler View") );
        the_list.add( new ActivityObject("SwipeRefreshLayoutActivity", "30. Swipe Refresh Layout") );
        the_list.add( new ActivityObject("TabbedActivity", "31. Tabbed Activity") );
        the_list.add( new ActivityObject("ViewModelActivity", "32. View Model") );
        the_list.add( new ActivityObject("NavigationBottomActivity", "33. Navigation Bottom") );
        the_list.add( new ActivityObject("ScanQRActivity", "34. Scan QR") );
        the_list.add( new ActivityObject("ConfigurationChangedActivity", "35. Configuration Changed") );
        the_list.add( new ActivityObject("AnimationActivity", "36. Animation") );
        the_list.add( new ActivityObject("ColorStateActivity", "37. Color State") );
        the_list.add( new ActivityObject("StringFormatterActivity", "38. String Formatter") );
        the_list.add( new ActivityObject("GetMetaDataActivity", "39. Get Meta Data") );
        the_list.add( new ActivityObject("GoogleSignInActivity", "40. Google Sign In") );
        the_list.add( new ActivityObject("PlayMusicTwoActivity", "41. Play Music Two") );
        the_list.add( new ActivityObject("ServiceActivity", "42. Service") );
        the_list.add( new ActivityObject("GradientActivity", "43. Gradient") );
        the_list.add( new ActivityObject("PressBackAndRateMeActivity", "44. Press Back And Rate Me") );
        the_list.add( new ActivityObject("ModalBottomActivity", "45. Modal Bottom") );
        the_list.add( new ActivityObject("SharedElementActivity", "46. Shared Element") );
        the_list.add( new ActivityObject("AutoCompleteTextViewActivity", "47. Auto Complete Text View") );
        the_list.add( new ActivityObject("CustomAutoCompleteTextViewActivity", "48. Custom Auto Complete Text View") );
        the_list.add( new ActivityObject("TextInputLayoutActivity", "49. Text Input Layout") );
        the_list.add( new ActivityObject("AnimationDrawableActivity", "50. Animation Drawable") );
        the_list.add( new ActivityObject("CircularRevealActivity", "51. Circular Reveal") );
        the_list.add( new ActivityObject("CustomButtonActivity", "52. Custom Button") );
        the_list.add( new ActivityObject("YoyoActivity", "53. Yoyo") );
        the_list.add( new ActivityObject("MultiAutoCompleteTextViewActivity", "54. Multi Auto Complete Text View") );
        the_list.add( new ActivityObject("ImageSliderActivity", "55. Image Slider") );
        the_list.add( new ActivityObject("PicassoImageSliderActivity", "56. Picasso Image Slider") );
        the_list.add( new ActivityObject("BottomSheetActivity", "57. Bottom Sheet") );
        the_list.add( new ActivityObject("DodgeInsetEdgesActivity", "58. Dodge Inset Edges") );
        the_list.add( new ActivityObject("SeekBarActivity", "59. Seek Bar") );
        the_list.add( new ActivityObject("SpinnerActivity", "60. Spinner") );
        the_list.add( new ActivityObject("SpinnerCustomObjectActivity", "61. Spinner Custom Object") );
        the_list.add( new ActivityObject("TextWatcherActivity", "62. Text Watcher") );
        the_list.add( new ActivityObject("ImeActivity", "63. Ime") );
        the_list.add( new ActivityObject("ClickableSpanActivity", "64. Clickable Span") );
        the_list.add( new ActivityObject("SpannableStringActivity", "65. Spannable String") );
        the_list.add( new ActivityObject("SpannableStringColorActivity", "66. Spannable String Color") );
        the_list.add( new ActivityObject("FadingRandomizedTextViewActivity", "67. Fading Randomized Text View") );
        the_list.add( new ActivityObject("IncludeActivity", "68. Include") );
        the_list.add( new ActivityObject("CustomSpinnerActivity", "69. Custom Spinner") );
        the_list.add( new ActivityObject("SendDataBetweenFragmentsActivity", "70. Send Data Between Fragments") );
        the_list.add( new ActivityObject("CreateFragmentWithDataActivity", "71. Create Fragment With Data") );
        the_list.add( new ActivityObject("FragmentWithAnimationActivity", "72. Fragment With Animation") );
        the_list.add( new ActivityObject("ColorSeekBarActivity", "73. Color Seek Bar") );
        the_list.add( new ActivityObject("ViewFlipperActivity", "74. View Flipper") );
        the_list.add( new ActivityObject("SlidrActivity", "75. Slidr") );
        the_list.add( new ActivityObject("Slidr2Activity", "76. Slidr 2") );
        the_list.add( new ActivityObject("CircularProgressBarActivity", "77. Circular Progress Bar") );
        the_list.add( new ActivityObject("CircularLoadingBetterActivity", "78. Circular Loading Better") );
        the_list.add( new ActivityObject("HideKeyboardActivity", "79. Hide Keyboard") );
        the_list.add( new ActivityObject("IntentAnimationActivity", "80. Intent Animation") );
        the_list.add( new ActivityObject("SlideAnimationActivity", "81. Slide Animation") );
        the_list.add( new ActivityObject("SlideAnimationActivity2", "82. Slide Animation 2") );
        the_list.add( new ActivityObject("RecyclerViewActivity2", "83. Recycler View 2") );
        the_list.add( new ActivityObject("RecyclerViewWithSearchViewActivity", "84. Recycler View With Search View") );
        the_list.add( new ActivityObject("RecyclerViewWithEditTextSearchActivity", "85. Recycler View With Edit Text Search") );
        the_list.add( new ActivityObject("SaveTextFileActivity", "86. Save Text File") );
        the_list.add( new ActivityObject("OkHttpActivity", "87. OkHttp") );
        the_list.add( new ActivityObject("VolleyActivity", "88. Volley") );
        the_list.add( new ActivityObject("KenBurnsViewActivity", "89. KenBurns View") );
        the_list.add( new ActivityObject("BroadcastReceiverTimeTickActivity", "90. Broadcast Receiver Time Tick") );
        the_list.add( new ActivityObject("ChooseColorActivity", "91. Choose Color") );
        the_list.add( new ActivityObject("CalendarAddActivity", "92. Calendar Add") );
        the_list.add( new ActivityObject("ChronometerActivity", "93. Chronometer") );
        the_list.add( new ActivityObject("CountDownTimerActivity", "94. Count Down Timer") );
        the_list.add( new ActivityObject("SortingArrayActivity", "95. Sorting Array") );
        the_list.add( new ActivityObject("SharedViewModelActivity", "96. Shared View Model") );
        the_list.add( new ActivityObject("RecyclerViewWithPicassoActivity", "97. Recycler View With Picasso") );
        the_list.add( new ActivityObject("BundleDataForActivity", "98. Bundle Data For") );
        the_list.add( new ActivityObject("ToastyActivity", "99. Toasty") );
        the_list.add( new ActivityObject("AlerterActivity", "100. Alerter") );
        the_list.add( new ActivityObject("GSONSharedPreferencesActivity", "101. GSON Shared Preferences") );
        the_list.add( new ActivityObject("StyleableToastActivity", "102. Styleable Toast") );
        the_list.add( new ActivityObject("RandomizedColorActivity", "103. Randomized Color") );
        the_list.add( new ActivityObject("InsertNewContactActivity", "104. Insert New Contact") );
        the_list.add( new ActivityObject("NavigationDrawerActivity", "105. Navigation Drawer") );
        the_list.add( new ActivityObject("FlowingDrawerActivity", "106. Flowing Drawer") );
        the_list.add( new ActivityObject("PostDelayedActivity", "107. Post Delayed") );
        the_list.add( new ActivityObject("AsyncTaskActivity", "108. Async Task") );
        the_list.add( new ActivityObject("AlarmManagerActivity", "109. Alarm Manager") );
        the_list.add( new ActivityObject("IntentServiceActivity", "110. Intent Service ") );
        the_list.add( new ActivityObject("RegisterBroadcastActivity", "111. Register Broadcast") );
        the_list.add( new ActivityObject("ImplicitBroadcastReceiverActivity", "112. Implicit Broadcast Receiver") );
        the_list.add( new ActivityObject("ImplicitBroadcastSenderActivity", "113. Implicit Broadcast Sender") );
        the_list.add( new ActivityObject("AnimatorExampleActivity", "114. Animator Example") );
        the_list.add( new ActivityObject("StartImplicitIntentActivity", "115. Start Implicit Intent ") );
        the_list.add( new ActivityObject("DeepLinkActivity", "116. Deep Link") );
        the_list.add( new ActivityObject("DetectGestureActivity", "117. Detect Gesture Activity") );
        the_list.add( new ActivityObject("AnimatorActivity", "118. Animator") );
        the_list.add( new ActivityObject("ViewPagerActivity", "119. View Pager") );
        the_list.add( new ActivityObject("ViewPager2Activity", "120. View Pager 2") );
        the_list.add( new ActivityObject("CrossfadeActivity", "121. Crossfade") );
        the_list.add( new ActivityObject("TableLayoutActivity", "122. Table Layout") );
        the_list.add( new ActivityObject("BluetoothOnOrOffActivity", "123. Bluetooth On Or Off") );
        the_list.add( new ActivityObject("GridLayoutActivity", "124. Grid Layout") );
        the_list.add( new ActivityObject("StyleOnViewActivity", "125. Style On View") );
        the_list.add( new ActivityObject("DragAndDropActivity", "126. Drag And Drop") );
        the_list.add( new ActivityObject("LocationActivity", "127. Location") );
        the_list.add( new ActivityObject("RecordVideoActivity", "128. Record Video") );
        the_list.add( new ActivityObject("MotionTagActivity", "129. Motion Tag") );
        the_list.add( new ActivityObject("FirebaseRealTimeDatabaseActivity", "130. Firebase Real Time Database") );
        the_list.add( new ActivityObject("RemoteConfigActivity", "131. Remote Config") );
        the_list.add( new ActivityObject("FirebaseCloudMessagingActivity", "132. Firebase Cloud Messaging") );
        the_list.add( new ActivityObject("TryBackButtonActivity", "133. Try Back Button") );
        the_list.add( new ActivityObject("TestBackOnFragmentActivity", "134. Test Back On Fragment") );
        the_list.add( new ActivityObject("ContentProviderActivity", "135. Content Provider") );
        the_list.add( new ActivityObject("MLTextRecognitionActivity", "136. ML Text Recognition") );
        the_list.add( new ActivityObject("MLKitIdentifyLanguageActivity", "137. ML Kit Identify Language") );
        the_list.add( new ActivityObject("MLKitTranslateTextActivity", "138. ML Kit Translate Text") );
        the_list.add( new ActivityObject("MLKitSmartReplyActivity", "139. ML Kit Smart Reply") );
        the_list.add( new ActivityObject("MLKitDetectFaceActivity", "140. ML Kit Detect Face") );
        the_list.add( new ActivityObject("MLKitBarcodeScannerActivity", "141. ML Kit Barcode Scanner") );
        the_list.add( new ActivityObject("CameraActivity", "142. Camera") );
        the_list.add( new ActivityObject("CameraXActivity", "143. Camera X") );
        the_list.add( new ActivityObject("MLKitImageLabelActivity", "144. ML Kit Image Label ") );
        the_list.add( new ActivityObject("MLKitObjectDetectionActivity", "145. ML Kit Object Detection") );
        the_list.add( new ActivityObject("SavePictureToGalleryActivity", "146. Save Picture To Gallery") );
        the_list.add( new ActivityObject("FileSaveReadDeleteActivity", "147. File Save Read Delete") );
        the_list.add( new ActivityObject("SensorActivity", "148. Sensor") );
        the_list.add( new ActivityObject("ChangeFragmentActivity", "149. Change Fragment") );
        the_list.add( new ActivityObject("CallPhoneAndDialSimpleVersionActivity", "150. Call Phone And Dial Simple Version") );
        the_list.add( new ActivityObject("ExoPlayerActivity", "151. ExoPlayer") );
        the_list.add( new ActivityObject("WallpaperActivity", "152. Wallpaper") );
        the_list.add( new ActivityObject("LiveWallpaperActivity", "153. Live Wallpaper") );
        the_list.add( new ActivityObject("Notification2Activity", "154. Notification 2") );
        the_list.add( new ActivityObject("ShowDialogToChooseNumberActivity", "155. Show Dialog To Choose Number") );
        the_list.add( new ActivityObject("AskForAppearOnTopActivity", "156. Ask For Appear On Top") );
        the_list.add( new ActivityObject("AppearOnTopActivity", "157. Appear On Top") );
        the_list.add( new ActivityObject("MapsActivity", "158. Maps") );
        the_list.add( new ActivityObject("CirclePageIndicatorActivity", "159. Circle Page Indicator") );
        the_list.add( new ActivityObject("FirebaseAuthUIActivity", "160. Firebase Auth UI") );
        the_list.add( new ActivityObject("GetAllContactActivity", "161. Get All Contact") );
        the_list.add( new ActivityObject("ReadWriteSMSActivity", "162. Read Write SMS") );
        the_list.add( new ActivityObject("ReadWriteCallLogsActivity", "163. Read Write Call Logs") );
        the_list.add( new ActivityObject("RingToneActivity", "164. Ringtone") );
        the_list.add( new ActivityObject("InterceptCallActivity", "165. Intercept Call") );
        the_list.add( new ActivityObject("AnyChartActivity", "166. Any Chart") );
        the_list.add( new ActivityObject("MpAndroidChartActivity", "167. MP Android Chart") );
        // the_list.add( new ActivityObject("MyWallpaperPreferencesActivity", "154. My Wallpaper Preferences") );
        // the_list.add( new ActivityObject("DetailRecyclerViewWithPicassoActivity", "Detail Recycler View With Picasso") );
        // the_list.add( new ActivityObject("BubbleActivity", "Bubble") );
        // the_list.add( new ActivityObject("SaveDataToExternalStorageActivity", "8. Save Data To External Storage") );
        // the_list.add( new ActivityObject("ReceiveTextActivity", "Receive Text") );
        // the_list.add( new ActivityObject("SetBroadcastContextActivity", "Set Broadcast Context") );
        // the_list.add( new ActivityObject("FingerprintActivity", "Fingerprint") );
        // the_list.add( new ActivityObject("SharedElementActivity2", "Shared Element 2") );
        // the_list.add( new ActivityObject("GSONActivity", "GSON") );
        // the_list.add( new ActivityObject("WidgetActivity", "Widget") );
        // the_list.add( new ActivityObject("WidgetActivity2", "Widget 2") );
        // the_list.add( new ActivityObject("ExampleAppWidgetConfig", "ExampleAppWidgetConfig") );
        // the_list.add( new ActivityObject("WidgetResizeActivity", "Widget Resize") );
        // the_list.add( new ActivityObject("ExampleAppWidgetConfig2", "ExampleAppWidgetConfig2") );
        // the_list.add( new ActivityObject("HandlerThreadActivity", "Handler Thread") );
        // the_list.add( new ActivityObject("LooperThreadActivity", "Looper Thread") );
        // the_list.add( new ActivityObject("ThreadAndRunnableActivity", "Thread And Runnable") );
        // the_list.add( new ActivityObject("JobSchedulerActivity", "Job Scheduler") );
        // the_list.add( new ActivityObject("JobIntentServiceActivity", "Job Intent Service") );
        // the_list.add( new ActivityObject("ExampleAppWidgetConfig4", "Example App Widget Config 4") );
        // the_list.add( new ActivityObject("ExampleAppWidgetConfig5", "Example App Widget Config 5") );
        // the_list.add( new ActivityObject("ImplicitIntentHandleActivity", "Implicit Intent Handle") );
        // the_list.add( new ActivityObject("DatabaseActivity", "Database") );
        // the_list.add( new ActivityObject("FirebaseAnalyticsActivity", "Firebase Analytics") );
        // the_list.add( new ActivityObject("FirestoreActivity", "Firestore") );
        // the_list.add( new ActivityObject("FirebaseCrashlyticsActivity", "Firebase Crashlytics") );
        // the_list.add( new ActivityObject("FirebaseInAppMessagingActivity", "Firebase In App Messaging") );
        // the_list.add( new ActivityObject("GetCurrentPhoneNumberActivity", "Get Current Phone Number") );
        // the_list.add( new ActivityObject("BackgroundLocationActivity", "Background Location") );
        // the_list.add( new ActivityObject("ShowAllActivity", "Show All") );

        for (ActivityObject the_activity: the_list) {
            String activity_class_name      = the_activity.activity_class_name;
            String activity_text_to_show    = the_activity.activity_text_to_show;

            Button button = new Button(this);
            button.setText(activity_text_to_show);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(15, 15, 15, 0);
            button.setLayoutParams(params);

            // R.id won't be generated for us, so we need to create one
            button.setId(generateViewId());
            // enclose message
            button.setTag(activity_class_name);
            // add our event handler (less memory than an anonymous inner class)
            button.setOnClickListener( this );
            // add generated button to view
            linear_layout.addView(button);
        }

    }
}