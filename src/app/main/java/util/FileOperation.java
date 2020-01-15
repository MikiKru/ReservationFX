package util;

import model.Event;
import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileOperation {
    private static File file = new File("C:\\Users\\PROXIMO\\Desktop\\TARR1\\Reservation\\src\\app\\main\\resources\\files\\users.csv");
    private static File fileEvent = new File("C:\\Users\\PROXIMO\\Desktop\\TARR1\\Reservation\\src\\app\\main\\resources\\files\\events.csv");

    // metoda odczytująca dane z pliku i zapusjąca je do listy users
    public static void getDataFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            String [] user = scanner.nextLine().split("; ");
            InMemoryDB.users.add(new User(user[0], user[1], user[2]));
        }
        scanner.close();
        System.out.println(InMemoryDB.users);
    }
    // metoda aktualizująca zawartość pliku
    public static void setDataToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (User user : InMemoryDB.users) {
            fileWriter.write(user.getLogin() + "; " + user.getPassword() + "; " + user.getEmial() + "\n");
        }
        fileWriter.close();
    }
    public static void getEventDataFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(fileEvent);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (scanner.hasNext()){
            String [] event = scanner.nextLine().split("; ");
            InMemoryDB.events.add(new Event(event[0], event[1], LocalDateTime.parse(event[2], dtf), event[3].split(","), Integer.valueOf(event[4])));
        }
        scanner.close();
        System.out.println(InMemoryDB.events);
    }
    public static void setEventDataToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(fileEvent);
        for (Event event : InMemoryDB.events) {
            fileWriter.write(
                    event.getName() + "; " +
                            event.getDescription() + "; " +
                    event.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "; " +
                            Arrays.stream(event.getType()).collect(Collectors.joining(",")) + "; " +
                    event.getAvailablePlaces() + "\n");
        }
        fileWriter.close();
    }
}
