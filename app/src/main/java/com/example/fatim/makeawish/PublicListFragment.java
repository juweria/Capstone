package com.example.fatim.makeawish;
import android.content.Intent;
import android.support.v4.app.Fragment; import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PublicListFragment extends Fragment {
    public DatabaseReference mDatabase;
    FirebaseUser user;
    ListView items_list;
    ArrayList<String> all_items_list = new ArrayList<>();
    Button profile;
    Button add;
    String friends;
    int friendsNumber;
    TextView friendsNumberText;

    //   private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.publiclistlayout, viewGroup, false);
        friendsNumberText=(TextView) view.findViewById(R.id.Public_FriendsNumber_TextView);
        items_list = (ListView) view.findViewById(R.id.Profile_publicItems_ListView);


        add=(Button)view.findViewById(R.id.publicList_add_button);
        profile=(Button)view.findViewById(R.id.customlayout_decline_button);

        //displaying the public list's items
        user = FirebaseAuth.getInstance().getCurrentUser();
        String username[] = user.getEmail().split("@");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Users").child(username[0]).child("Lists").child("Public").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                all_items_list = new ArrayList<>();

                // Get Post object and use the values to update the UI
                for (DataSnapshot n : dataSnapshot.getChildren()) {
//                    if (n.getKey().equals("username") || n.getKey().equals("email"))
//                        continue;
                    Item item = n.getValue(Item.class);
                    all_items_list.add(item.getName());
                    ArrayAdapter<String> adapter1 = (new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, all_items_list));
                    items_list.setAdapter(adapter1);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(null, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddingItem.class));

            }
        });

        mDatabase.child("Users").child(username[0]).child("friends").addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                friends=dataSnapshot.getValue(String.class);
                for(int i=0;i<friends.length();i++){
                    if(friends.charAt(i)== ','){
                        friendsNumber++;
                    }
                }
                friendsNumberText.setText(friendsNumber+1+" friends");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(null, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
//
        friendsNumberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent ( getActivity(),displayFriends.class));
            }
        });
        return view;
    }
}
