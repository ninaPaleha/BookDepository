package ru.rsue.paleha.bookdepository;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class PhotoFragment extends DialogFragment {
    private static final String ARG_PATH = "путь";

    static PhotoFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PATH, path);

        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getArguments() != null;
        String path = getArguments().getString(ARG_PATH);
        @SuppressLint("InflateParams") View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_photo, null);
        ImageView photoView = v.findViewById(R.id.fragment_photo_image_view);

        Activity activity = getActivity();
        if (activity != null) {
            Bitmap bitmap = PictureUtils.getScaledBitmap(path, activity);
            photoView.setImageBitmap(bitmap);
        }

        return new AlertDialog.Builder(activity)
                .setView(v)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}