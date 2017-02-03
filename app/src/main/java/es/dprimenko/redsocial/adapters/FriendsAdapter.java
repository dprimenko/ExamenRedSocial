package es.dprimenko.redsocial.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import es.dprimenko.redsocial.R;
import es.dprimenko.redsocial.Repository;
import es.dprimenko.redsocial.models.User;

/**
 * Created by dprimenko on 3/02/17.
 */

public class FriendsAdapter extends ArrayAdapter<User> {

    private FriendHolder holder;

    public FriendsAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void refreshList(List<User> users) {
        clear();
        addAll(users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            view = inflater.inflate(R.layout.item_friend, null);

            holder = new FriendHolder();
            holder.llFriend = (LinearLayout) view.findViewById(R.id.ll_friend);
            holder.txvUsername = (TextView) view.findViewById(R.id.txv_username);
            holder.txvEmail = (TextView) view.findViewById(R.id.txv_email);

            view.setTag(holder);
        } else {
            holder = (FriendHolder) view.getTag();
        }

        User item = getItem(position);

        switch (item.getmAccount()) {
            case Repository.FACEBOOK:
                holder.llFriend.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
                break;
            case Repository.GMAIL:
                holder.llFriend.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_red_light));
                break;
            case Repository.TWITTER:
                holder.llFriend.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_blue_light));
                break;
        }

        holder.txvUsername.setText(item.getmUsername());
        holder.txvEmail.setText(item.getmEmail());

        return view;
    }

    public void sortAsc() {

        List<User> friends = Repository.getInstance().getUserSelected().getmFriends();
        Collections.sort(friends);
        refreshList(friends);
    }

    public void sortDesc() {
        List<User> friends = Repository.getInstance().getUserSelected().getmFriends();
        Collections.sort(friends);
        Collections.reverse(friends);
        refreshList(friends);
    }

    class FriendHolder {
        LinearLayout llFriend;
        TextView txvUsername, txvEmail;
    }
}
