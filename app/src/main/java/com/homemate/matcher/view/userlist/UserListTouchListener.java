package com.homemate.matcher.view.userlist;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class UserListTouchListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener userDetailListener;
    public UserListTouchListener(Context applicationContext,
                                 OnItemClickListener onItemClickListener) {
        this.userDetailListener = onItemClickListener;
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
