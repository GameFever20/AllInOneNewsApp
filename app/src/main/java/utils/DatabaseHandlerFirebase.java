package utils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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

        DatabaseReference myRef = mDatabase.child("NewsInfo");

        Query myref2 = myRef.limitToLast(limit);

        myref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<NewsInfo> newsInfoArrayList = new ArrayList<NewsInfo>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NewsInfo newsInfo = snapshot.getValue(NewsInfo.class);

                    newsInfo.resolveHashmap();
                    newsInfoArrayList.add(newsInfo);
                }


                if (databaseNewsListListner != null) {
                    databaseNewsListListner.onNewsList(newsInfoArrayList);
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


    public interface DataBaseHandlerNewsListListner {
        public void onNewsList(ArrayList<NewsInfo> newsInfoArrayList);

        public void onCancel();

        public void onNoticePost(boolean isSuccessful);
    }

}
