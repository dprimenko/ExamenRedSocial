package es.dprimenko.redsocial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import es.dprimenko.redsocial.adapters.FriendsAdapter;

/**
 * Created by dprimenko on 3/02/17.
 */

public class ListFriendsActivity extends AppCompatActivity {

    private ListView listFriends;
    private FriendsAdapter adapter;
    private FloatingActionButton fabAddFriend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        listFriends = (ListView) findViewById(R.id.lw_friends);
        adapter = new FriendsAdapter(this, R.layout.item_friend);
        fabAddFriend = (FloatingActionButton) findViewById(R.id.add_friend);

        adapter.refreshList(Repository.getInstance().getUserSelected().getmFriends());
        listFriends.setAdapter(adapter);

        fabAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListFriendsActivity.this, AddFriendActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_sort_asc) {
            adapter.sortAsc();
        } else if (item.getItemId() == R.id.action_sort_desc) {
            adapter.sortDesc();
        }

        return true;
    }
}
