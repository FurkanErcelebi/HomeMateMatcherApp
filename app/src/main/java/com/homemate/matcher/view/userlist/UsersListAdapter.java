package com.homemate.matcher.view.userlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.homemate.matcher.R;
import com.homemate.matcher.models.BriefUser;

import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder> {

    private final Context context;

    private final List<BriefUser> userList;

    public UsersListAdapter(Context context, List<BriefUser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UsersListAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListAdapter.UserViewHolder holder, int position) {
        BriefUser briefUser = userList.get(position);
        holder.idText.setText(briefUser.getId());
        holder.idText.setVisibility(View.INVISIBLE);
        holder.userNames.setText(briefUser.getFirstName() + " " + briefUser.getLastName());
        String userStatus = "";
        switch (briefUser.getStatus()){
            case NOT_IN_SEARCH:
                userStatus = "Aktif değil";
                break;
            case IN_SEARCH_OF_MATE:
                userStatus = "Ev arıyor";
                break;
            case IN_SEARCH_OF_HOME:
                userStatus = "Ev arkadaşı aroyor";
                break;
        }
        holder.userStatus.setText("Status: " + userStatus);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }



    public static class UserViewHolder extends RecyclerView.ViewHolder
                                        implements View.OnClickListener{

        private final TextView idText;
        private final TextView userNames;
        private final TextView userStatus;

        private final Context context;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.userId);
            userNames = itemView.findViewById(R.id.userNames);
            userStatus = itemView.findViewById(R.id.userStatus);
            context = itemView.getContext();
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            TextView userIdView = view.findViewById(R.id.userId);
            String userId = (String) userIdView.getText();
            Intent intent = new Intent(context, UserDetailActivity.class);
            intent.putExtra("firebase_id", userId);
            context.startActivity(intent);
        }
    }

}
