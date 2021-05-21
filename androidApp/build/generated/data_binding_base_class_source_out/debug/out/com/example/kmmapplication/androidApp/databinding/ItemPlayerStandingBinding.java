// Generated by view binder compiler. Do not edit!
package com.example.kmmapplication.androidApp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.kmmapplication.androidApp.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemPlayerStandingBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final MaterialTextView playerName;

  @NonNull
  public final MaterialTextView playerRating;

  @NonNull
  public final MaterialTextView playerStanding;

  private ItemPlayerStandingBinding(@NonNull MaterialCardView rootView,
      @NonNull MaterialTextView playerName, @NonNull MaterialTextView playerRating,
      @NonNull MaterialTextView playerStanding) {
    this.rootView = rootView;
    this.playerName = playerName;
    this.playerRating = playerRating;
    this.playerStanding = playerStanding;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemPlayerStandingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemPlayerStandingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_player_standing, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemPlayerStandingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.playerName;
      MaterialTextView playerName = rootView.findViewById(id);
      if (playerName == null) {
        break missingId;
      }

      id = R.id.playerRating;
      MaterialTextView playerRating = rootView.findViewById(id);
      if (playerRating == null) {
        break missingId;
      }

      id = R.id.playerStanding;
      MaterialTextView playerStanding = rootView.findViewById(id);
      if (playerStanding == null) {
        break missingId;
      }

      return new ItemPlayerStandingBinding((MaterialCardView) rootView, playerName, playerRating,
          playerStanding);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
