package es.dprimenko.redsocial.models;

import java.util.List;

/**
 * Created by usuario on 3/02/17.
 */

public class User implements Comparable<User>{

    private String mUsername;
    private String mPassword;
    private String mEmail;
    private List<User> mFriends;
    private String mAccount;

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public List<User> getmFriends() {
        return mFriends;
    }

    public void setmFriends(List<User> mFriends) {
        this.mFriends = mFriends;
    }

    public String getmAccount() {
        return mAccount;
    }

    public void setmAccount(String mAccount) {
        this.mAccount = mAccount;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public User() {
    }

    public User(String mUsername, String mPassword, String mEmail, List<User> mFriends, String mAccount) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.mEmail = mEmail;
        this.mFriends = mFriends;
        this.mAccount = mAccount;
    }

    @Override
    public int compareTo(User user) {
        return this.getmUsername().compareToIgnoreCase(user.getmUsername());
    }
}
