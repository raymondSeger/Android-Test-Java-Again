package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.nl.smartreply.SmartReply;
import com.google.mlkit.nl.smartreply.SmartReplyGenerator;
import com.google.mlkit.nl.smartreply.SmartReplySuggestion;
import com.google.mlkit.nl.smartreply.SmartReplySuggestionResult;
import com.google.mlkit.nl.smartreply.TextMessage;

import java.util.ArrayList;
import java.util.List;

public class MLKitSmartReplyActivity extends AppCompatActivity {

    private List<TextMessage> conversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml_kit_smart_reply);

        conversation = new ArrayList<>();

        conversation.add(TextMessage.createForLocalUser( "heading out now", System.currentTimeMillis()));
        conversation.add(TextMessage.createForRemoteUser( "Are you coming back soon?", System.currentTimeMillis(), String.valueOf(1)));
        conversation.add(TextMessage.createForRemoteUser( "I'm cooking very tasty cupcake?", System.currentTimeMillis(), String.valueOf(1)));
        conversation.add(TextMessage.createForRemoteUser( "and we will be having pizza party", System.currentTimeMillis(), String.valueOf(1)));
        conversation.add(TextMessage.createForRemoteUser( "you like pizza and hamburger and french fries, right?", System.currentTimeMillis(), String.valueOf(1)));

        SmartReplyGenerator smartReply = SmartReply.getClient();
        smartReply.suggestReplies(conversation)
                .addOnSuccessListener(new OnSuccessListener<SmartReplySuggestionResult>() {
                    @Override
                    public void onSuccess(SmartReplySuggestionResult result) {
                        if (result.getStatus() == SmartReplySuggestionResult.STATUS_NOT_SUPPORTED_LANGUAGE) {
                            // The conversation's language isn't supported, so
                            // the result doesn't contain any suggestions.
                        } else if (result.getStatus() == SmartReplySuggestionResult.STATUS_SUCCESS) {
                            // Task completed successfully
                            for (SmartReplySuggestion suggestion : result.getSuggestions()) {
                                String replyText = suggestion.getText();
                                Toast.makeText(MLKitSmartReplyActivity.this, replyText, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        Toast.makeText(MLKitSmartReplyActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}