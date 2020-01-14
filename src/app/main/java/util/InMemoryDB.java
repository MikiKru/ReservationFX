package util;

import model.Event;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface InMemoryDB {
    List<User> users = new ArrayList<>();
    List<Event> events = new ArrayList<>();
}
