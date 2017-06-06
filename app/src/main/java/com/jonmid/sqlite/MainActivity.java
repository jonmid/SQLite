package com.jonmid.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.jonmid.sqlite.Adapters.AdapterListPost;
import com.jonmid.sqlite.Adapters.AdapterUser;
import com.jonmid.sqlite.Data.DataManager;
import com.jonmid.sqlite.Models.ListPost;
import com.jonmid.sqlite.Models.Post;
import com.jonmid.sqlite.Models.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button create, listbtn;
    ListView lista;
    DataManager dataManager;
    List<ListPost> userList;
    AdapterListPost adapterListPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = (Button) findViewById(R.id.id_btn_create);
        listbtn = (Button) findViewById(R.id.id_btn_list);
        lista = (ListView) findViewById(R.id.id_lv_mylist);
        dataManager = new DataManager(this);
        dataManager.open();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createData();
            }
        });

        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList = dataManager.findAll();
                adapterListPost = new AdapterListPost(getApplicationContext(), userList);
                lista.setAdapter(adapterListPost);
            }
        });
    }

    private void createData(){
        User user = new User();
        user.setName("jonmid");
        user.setEmail("jamideros@hotmail.com");

        Post post = new Post();
        post.setTitle("AAA");
        post.setStatus(true);

        dataManager.createUserPost(user, post);
    }
}
