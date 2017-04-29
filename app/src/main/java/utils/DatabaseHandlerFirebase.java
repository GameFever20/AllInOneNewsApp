package utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.NumberPicker;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import app.news.allinone.craftystudio.allinonenewsapp.NewsListActivity;

/**
 * Created by bunny on 22/04/17.
 */

public class DatabaseHandlerFirebase {

    private DatabaseReference mDatabase;

    private DataBaseHandlerNewsListListner databaseNewsListListner;


    public DatabaseHandlerFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();


    }

    public void addNewsListListner(DataBaseHandlerNewsListListner dataBaseHandlerNewsListListner) {

        this.databaseNewsListListner = dataBaseHandlerNewsListListner;

    }

    public void getNewsList(int limit) {

        DatabaseReference myRef = mDatabase.child("NewsMetaInfo");

        Query myref2 = myRef.limitToLast(limit);

        myref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<NewsMetaInfo> newsMetaInfoArrayList = new ArrayList<NewsMetaInfo>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NewsMetaInfo newsMetaInfo = snapshot.getValue(NewsMetaInfo.class);


                    newsMetaInfoArrayList.add(newsMetaInfo);
                }

                /*Reversing Array list element */
                ArrayList<NewsMetaInfo> newsMetaInfoArrayListFinal = new ArrayList<NewsMetaInfo>();
                for (int i = newsMetaInfoArrayList.size() - 1; i >= 0; i--) {
                    downloadImageFromFireBase(newsMetaInfoArrayList.get(i));
                    newsMetaInfoArrayListFinal.add(newsMetaInfoArrayList.get(i));
                }

                if (databaseNewsListListner != null) {
                    databaseNewsListListner.onNewsList(newsMetaInfoArrayListFinal);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (databaseNewsListListner != null) {
                    databaseNewsListListner.onCancel();
                }

            }
        });


    }

    public void getNewsMetaInfo(String pushKeyId){
        DatabaseReference myRef = mDatabase.child("NewsMetaInfo/"+pushKeyId);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<NewsMetaInfo> newsMetaInfoArrayList = new ArrayList<NewsMetaInfo>();
                NewsMetaInfo newsMetaInfo = dataSnapshot.getValue(NewsMetaInfo.class);
                newsMetaInfoArrayList.add(newsMetaInfo);

                if (databaseNewsListListner != null) {
                    databaseNewsListListner.onNewsList(newsMetaInfoArrayList);
                }
                downloadImageFromFireBase(newsMetaInfo);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getNewsListMore(String lastPushKeyId , int limit){

        DatabaseReference myRef = mDatabase.child("NewsMetaInfo");

        Query myref2 = myRef.orderByKey().equalTo(lastPushKeyId).limitToLast(limit);
        myref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<NewsMetaInfo> newsMetaInfoArrayList = new ArrayList<NewsMetaInfo>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NewsMetaInfo newsMetaInfo = snapshot.getValue(NewsMetaInfo.class);

                    downloadImageFromFireBase(newsMetaInfo);
                    newsMetaInfoArrayList.add(newsMetaInfo);
                }
                if (databaseNewsListListner != null) {
                    databaseNewsListListner.ongetNewsListMore(newsMetaInfoArrayList);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                if (databaseNewsListListner != null) {
                    databaseNewsListListner.onCancel();
                }
            }
        });


    }


    public void getNewsInfo(String pushKeyId) {

        DatabaseReference myRef = mDatabase.child("NewsInfo/" + pushKeyId);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                NewsInfo newsInfo = dataSnapshot.getValue(NewsInfo.class);
                databaseNewsListListner.onNewsInfo(newsInfo);


                if (databaseNewsListListner != null) {

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (databaseNewsListListner != null) {
                    databaseNewsListListner.onCancel();
                }

            }
        });


    }

    public void downloadImageFromFireBase(final NewsMetaInfo newsMetaInfo) {
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference riversRef = mStorageRef.child("NewsMetaInfo/newsImage" + newsMetaInfo.getNewsPushKeyId() + ".jpg");


        File localFile = null;
        try {
            localFile = File.createTempFile("images", ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final File finalLocalFile = localFile;
        riversRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...
                        newsMetaInfo.setNewsImage(BitmapFactory.decodeFile(finalLocalFile.getPath()));
                        newsMetaInfo.setNewsImageLocalPath(finalLocalFile.getPath());
                        databaseNewsListListner.onNewsImageFetched(true);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
            }
        });


    }


    public interface DataBaseHandlerNewsListListner {
        public void onNewsList(ArrayList<NewsMetaInfo> newsMetaInfoArrayList);

        public void onCancel();

        public void onNoticePost(boolean isSuccessful);

        void onNewsImageFetched(boolean isFetchedImage);

        public void onNewsInfo(NewsInfo newsInfo);

        public void ongetNewsListMore(ArrayList<NewsMetaInfo> newsMetaInfoArrayListMore);
    }

}
