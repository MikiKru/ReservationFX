package util;

import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileOperation {
    private static File file = new File("C:\\Users\\PROXIMO\\Desktop\\TARR1\\Reservation\\src\\app\\main\\resources\\files\\users.csv");

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

}
