package util;

import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileOperation {
    // metoda odczytująca dane z pliku i zapusjąca je do listy users
    public static void getDataFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\PROXIMO\\Desktop\\TARR1\\Reservation\\src\\app\\main\\resources\\files\\users.csv"));
        while (scanner.hasNext()){
            String [] user = scanner.nextLine().split("; ");
            InMemoryDB.users.add(new User(user[0], user[1], user[2]));
        }
        System.out.println(InMemoryDB.users);
    }
}
