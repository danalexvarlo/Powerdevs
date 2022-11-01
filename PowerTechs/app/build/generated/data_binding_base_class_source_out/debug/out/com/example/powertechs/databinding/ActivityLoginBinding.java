// Generated by view binder compiler. Do not edit!
package com.example.powertechs.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.powertechs.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button Binicio;

  @NonNull
  public final TextView Brecuperar;

  @NonNull
  public final TextView Bregistro;

  @NonNull
  public final EditText editTextTextPersonName;

  @NonNull
  public final EditText editTextTextPersonName2;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final TextView textView;

  private ActivityLoginBinding(@NonNull RelativeLayout rootView, @NonNull Button Binicio,
      @NonNull TextView Brecuperar, @NonNull TextView Bregistro,
      @NonNull EditText editTextTextPersonName, @NonNull EditText editTextTextPersonName2,
      @NonNull ImageView imageView2, @NonNull TextView textView) {
    this.rootView = rootView;
    this.Binicio = Binicio;
    this.Brecuperar = Brecuperar;
    this.Bregistro = Bregistro;
    this.editTextTextPersonName = editTextTextPersonName;
    this.editTextTextPersonName2 = editTextTextPersonName2;
    this.imageView2 = imageView2;
    this.textView = textView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Binicio;
      Button Binicio = ViewBindings.findChildViewById(rootView, id);
      if (Binicio == null) {
        break missingId;
      }

      id = R.id.Brecuperar;
      TextView Brecuperar = ViewBindings.findChildViewById(rootView, id);
      if (Brecuperar == null) {
        break missingId;
      }

      id = R.id.Bregistro;
      TextView Bregistro = ViewBindings.findChildViewById(rootView, id);
      if (Bregistro == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName;
      EditText editTextTextPersonName = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName2;
      EditText editTextTextPersonName2 = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName2 == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ActivityLoginBinding((RelativeLayout) rootView, Binicio, Brecuperar, Bregistro,
          editTextTextPersonName, editTextTextPersonName2, imageView2, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
