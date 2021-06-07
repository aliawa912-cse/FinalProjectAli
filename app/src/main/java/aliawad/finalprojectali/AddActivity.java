package aliawad.finalprojectali;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 *
 */

public class AddActivity extends AppCompatActivity {
    private EditText etTitle,etCategrm,etLink;
    private ImageView ImImageView;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etTitle=findViewById(R.id.etTitle);
        etCategrm=findViewById(R.id.etCategrm);
        etLink=findViewById(R.id.etLink);
        ImImageView=findViewById(R.id.IvImageView);
        btnSave=findViewById(R.id.btnSave);
    }
    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if(resultCode != RESULT_CANCELED) {
                    switch (requestCode) {
                        case 0:
                            if (resultCode == RESULT_OK && data != null) {
                                Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                                ImageView.setImageBitmap(selectedImage);
                            }

                            break;
                        case 1:
                            if (resultCode == RESULT_OK && data != null) {
                                Uri selectedImage =  data.getData();
                                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                                if (selectedImage != null) {
                                    Cursor cursor = getContentResolver().query(selectedImage,
                                            filePathColumn, null, null, null);
                                    if (cursor != null) {
                                        cursor.moveToFirst();

                                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                        String picturePath = cursor.getString(columnIndex);
                                        ImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                        cursor.close();
                                    }
                                }

                            }
                            break;
                    }
                }
            }
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}