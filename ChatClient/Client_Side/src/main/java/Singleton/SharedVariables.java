package Singleton;

import Data.User;

public class SharedVariables {
    public static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SharedVariables.user = user;
    }
}
