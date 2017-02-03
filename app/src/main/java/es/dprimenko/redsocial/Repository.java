package es.dprimenko.redsocial;

import java.util.ArrayList;
import java.util.List;

import es.dprimenko.redsocial.models.User;

/**
 * Created by dprimenko on 3/02/17.
 */
public class Repository {

    public static final String FACEBOOK = "facebook";
    public static final String GMAIL = "gmail";
    public static final String TWITTER = "twitter";

    private List<User> users;
    private List<User> tempFriendsGmail;
    private List<User> tempFriendsFacebook;
    private List<User> tempFriendsTwitter;
    private User userSelected;

    private static Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {
        users = new ArrayList<>();
        tempFriendsGmail = new ArrayList<>();
        tempFriendsFacebook = new ArrayList<>();
        tempFriendsTwitter = new ArrayList<>();

        tempFriendsGmail.add(new User("gbartolocano", "test", "bartolobano@gmail.com", new ArrayList<User>(), GMAIL));
        tempFriendsGmail.add(new User("gdavidprimenko", "test", "davidprimenko@gmail.com", new ArrayList<User>(), GMAIL));
        tempFriendsGmail.add(new User("dprimenko", "test", "dprimenkoo@gmail.com", new ArrayList<User>(), GMAIL));
        tempFriendsGmail.add(new User("auser", "test", "user@gmail.com", new ArrayList<User>(), GMAIL));

        tempFriendsFacebook.add(new User("fcarloscano", "test", "fcarloscano@gmail.com", new ArrayList<User>(), FACEBOOK));
        tempFriendsFacebook.add(new User("barroso", "test", "barroso@gmail.com", new ArrayList<User>(), FACEBOOK));
        tempFriendsFacebook.add(new User("amaria", "test", "maria@gmail.com", new ArrayList<User>(), FACEBOOK));
        tempFriendsFacebook.add(new User("fjavieraranda", "test", "javi@gmail.com", new ArrayList<User>(), FACEBOOK));

        tempFriendsTwitter.add(new User("tdaviddado", "test", "tdaviddado@gmail.com", new ArrayList<User>(), TWITTER));
        tempFriendsTwitter.add(new User("tjesus", "test", "jesus@gmail.com", new ArrayList<User>(), TWITTER));
        tempFriendsTwitter.add(new User("agp", "test", "agp@gmail.com", new ArrayList<User>(), TWITTER));

        addUser(new User("usuariog", "gmail", "usuariog@gmail.com", tempFriendsGmail, GMAIL));
        addUser(new User("usuariof", "face", "usuariof@gmail.com", tempFriendsFacebook, FACEBOOK));
        addUser(new User("usuariot", "twit", "usuariot@gmail.com", tempFriendsTwitter, TWITTER));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addFriendToUser(User newFriend) {
        users.get(users.indexOf(userSelected)).getmFriends().add(newFriend);
    }

    public boolean friendExists(String email) {
        boolean result = false;

        for (User user:userSelected.getmFriends()) {
            if (email.equals(user.getmEmail())) {
                result = true;
            }
        }

        return result;
    }

    public User getUserSelected() {
        return userSelected;
    }

    public boolean login(String cuenta, String username, String password) {

        boolean result = false;

        for (User user:users) {
            if (user.getmUsername().equals(username) && user.getmPassword().equals(password) && user.getmAccount().equals(cuenta)) {
                userSelected = user;
                result = true;
            }
        }

        return result;
    }

    public List<User> getUsers() {
        return users;
    }
}
