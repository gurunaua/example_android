package com.example.karads.latihannavigationdrawer;
import com.kosalgeek.android.photoutil.*;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUploadFile extends Fragment implements View.OnClickListener{

    private int CAMERA_REQUEST = 13323;
    private int GALLERY_REQUEST = 2200;

    private ImageView imageView_photo;
    private Button btnOpenGalery;

    private CameraPhoto cameraPhoto;
    private GalleryPhoto galleryPhoto;

    private Uri selectedphoto;

    public FragmentUploadFile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_upload_file, container, false);
        cameraPhoto = new CameraPhoto(getActivity().getApplicationContext());
        galleryPhoto = new GalleryPhoto(getActivity().getApplicationContext());
        imageView_photo = view.findViewById(R.id.image_photo);
        btnOpenGalery = (Button)view.findViewById(R.id.btn_open_galery);
        imageView_photo.setOnClickListener(this);
        btnOpenGalery.setOnClickListener(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.image_photo) {
            try {
                startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
                cameraPhoto.addToGallery();
            }catch (IOException e){
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(),"something wrong when take a photo", Toast.LENGTH_SHORT).show();
            }
        }else if(view.getId()==R.id.btn_open_galery) {
            try {
                startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
                cameraPhoto.addToGallery();
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(),"something wrong when take a photo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK){
            if(requestCode == CAMERA_REQUEST){
                String photoPath = cameraPhoto.getPhotoPath();
                try {
                        Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(400, 300).getBitmap();
                        imageView_photo.setImageBitmap(bitmap); //imageView is your ImageView
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else{
                if(requestCode == GALLERY_REQUEST){
                    Uri uri = data.getData();
                    selectedphoto = uri;
                    galleryPhoto.setPhotoUri(uri);
                    String photoPath = galleryPhoto.getPath();
                    try {
                        Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                        imageView_photo.setImageBitmap(bitmap);
//                        selectedImage = photoPath;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
