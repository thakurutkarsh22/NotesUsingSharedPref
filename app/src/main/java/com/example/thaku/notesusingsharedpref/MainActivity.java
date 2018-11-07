package com.example.thaku.notesusingsharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ArrayList<Note> notes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SharedPreferences sharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE);

//
//        Map<String,?> map = sharedPreferences.getAll();
//
//        Set<String> set = sharedPreferences.getStringSet("key", null);
//        Iterator<String> itr;
//        if(set!=null){
//
//            itr = set.iterator();
//
//            while(itr.hasNext()){
//                Note no = new Note(itr.next().toString(), "ew");
//                notes.add(no);
//            }
//        }

        loadSharedPref();





//        for(Map.Entry<String, ?>  entry: map.entrySet()){
//
//            Log.e("map values", entry.getKey() + ": " + entry.getValue().toString());
//            Note no = new Note(entry.getValue().toString(), "ew");
//
//            notes.add(no);
//
//        }





        final NotesAdapter notesAdapter = new NotesAdapter(notes);
        recyclerView.setAdapter(notesAdapter);

        final EditText editText = findViewById(R.id.etNoteTitle);
        Button btnadd = findViewById(R.id.btnAdd);

//        SharedPreferences sharedPreferences = getsh

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editText.getText().toString();
                notes.add(new Note(title,"e"));
                notesAdapter.notifyDataSetChanged();

            }
        });




    }

    @Override
    protected void onStop() {

        SharedPreferences sharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        for(int i =0 ; i < notes.size() ; i++){

            String s = notes.get(i).getTitle();

            editor.putString("a"+i , s);
            Log.e("TAG", "onStop: "+ s );
        }

        Log.e("TAGs", "onStop: " + sharedPreferences.getAll().size() );
//
//        ArrayList<String> ar = new ArrayList<>();
//
//        for(int i =0 ; i < notes.size() ; i++){
//            ar.add(notes.get(i).getTitle());
//        }
//
//        Set<String> set = new HashSet<String>();
//        set.addAll(ar);
//        editor.putStringSet("key", set);
//        Log.e("TAGs", "onStop: " + sharedPreferences.getAll().size() );


        editor.apply();





        super.onStop();

    }


    private void loadSharedPref (){
        notes.clear();
        ArrayList<Note> sparrayList = new ArrayList<>();

        SharedPreferences sp = getSharedPreferences("mypref", MODE_PRIVATE);

        Log.e("loadsharepref" , sp.getAll().size()+"");
        for(int i=0;i<sp.getAll().size();i++){
            String title = sp.getString("a"+i , null);
            sparrayList.add(new Note(title,"aa"));
        }

        notes = sparrayList;

    }
}
