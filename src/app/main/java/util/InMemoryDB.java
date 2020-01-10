package util;

import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface InMemoryDB {
    List<User> users = new ArrayList<>(
            Arrays.asList(
                    new User("m","m", "m@m.pl"),
                    new User("x","x", "x@x.pl"),
                    new User("e","e", "e@e.pl"),
                    new User("r","r", "r@r.pl")
            )
    );
}
