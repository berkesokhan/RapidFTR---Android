package com.rapidftr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.rapidftr.R;
import com.rapidftr.utils.PhotoCaptureHelper;

public class ViewPhotoActivity extends RapidFtrActivity {

    protected PhotoCaptureHelper photoCaptureHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoCaptureHelper = new PhotoCaptureHelper(getContext());
        this.initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getIntent().getBooleanExtra("enabled", false)) {
            getMenuInflater().inflate(R.menu.image_menu, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.set_as_primary:
                Intent intent = new Intent();
                intent.putExtra("file_name", getIntent().getStringExtra("file_name"));
                setResult(RESULT_OK, intent);
                finish();
        }
        return true;
    }

    protected ImageView getImageView() {
        return (ImageView) findViewById(R.id.photo);
    }

    public void initialize() {
        setContentView(R.layout.activity_view_photo);
        String fileName = getIntent().getStringExtra("file_name");

        try {
            getImageView().setImageBitmap(photoCaptureHelper.loadPhoto(fileName));
        } catch (Exception e) {
            makeToast(R.string.photo_view_error);
        }
    }

}
