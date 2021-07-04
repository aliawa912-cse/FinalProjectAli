package aliawad.finalprojectali.Data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import aliawad.finalprojectali.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * אדאבתר הוא מעבר בין פיאר ביס לליסת ויו
 * עושה פירמשן בגלל לאשר לו לקחת תמונה או לכנס לתמונות
 */

public class ThingsAdapter extends ArrayAdapter<Thing> {
    public ThingsAdapter(@Nullable Context context) {
        super(context, R.layout.thing_item);
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vitem = LayoutInflater.from(getContext()).inflate(R.layout.thing_item, parent, false);
        TextView Title = vitem.findViewById(R.id.itmTitle);
        TextView Categrm = vitem.findViewById(R.id.itmcab);
        ImageView Image = vitem.findViewById(R.id.itmimageview);
        TextView Link = vitem.findViewById(R.id.itmLink);
        final Thing thing = getItem(position);
        /**
         * POSTION הוא רואה את המקום שלהם מינוס 1
         */
        if(thing.getImage().length()!=0)
              downloadImageUsingPicasso(thing.getImage(),Image);

        Link.setMovementMethod(LinkMovementMethod.getInstance());
        Link.setTextSize(16);
        if(thing.link==null || thing.link.length()==0)
            Link.setCursorVisible(false);
        Title.setText(thing.getTitle());
        Categrm.setText(thing.getCatg());
        Link.setText(thing.getLink());




        return vitem;
    }
    private void downloadImageUsingPicasso(String imageUrL, ImageView toView)
    {
        Picasso.with(getContext())
                .load(imageUrL)
                .centerCrop()
                .error(R.drawable.common_full_open_on_phone)
                .resize(90,90)
                .into(toView);
    }

    private void downloadImageToLocalFile(String fileURL, final ImageView toView) {
        StorageReference httpsReference = FirebaseStorage.getInstance().getReferenceFromUrl(fileURL);
        final File localFile;
        try {
            localFile = File.createTempFile("images", "jpg");


            httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Toast.makeText(getContext(), "downloaded Image To Local File", Toast.LENGTH_SHORT).show();
                    toView.setImageURI(Uri.fromFile(localFile));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    Toast.makeText(getContext(), "onFailure downloaded Image To Local File "+exception.getMessage(), Toast.LENGTH_SHORT).show();
                    exception.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void downloadImageToMemory(String fileURL, final ImageView toView)
    {
        StorageReference httpsReference = FirebaseStorage.getInstance().getReferenceFromUrl(fileURL);
        final long ONE_MEGABYTE = 1024 * 1024;
        httpsReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                toView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 90, 90, false));
                Toast.makeText(getContext(), "downloaded Image To Memory", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Toast.makeText(getContext(), "onFailure downloaded Image To Local File "+exception.getMessage(), Toast.LENGTH_SHORT).show();
                exception.printStackTrace();
            }
        });

    }
}






