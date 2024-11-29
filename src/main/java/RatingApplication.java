import model.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RatingApplication {

    public static void main(String[] args) {
        UserService userService = new UserService();
        List<User> userList = new ArrayList<>();
        Random random = new Random();

        String[] names = arrayNames();

        for (int i = 0; i < 1500; i++) {
            for (int j = 0; j < names.length; j++) {
                userList.add(new User(names[j], random.nextInt(150000) + 1));
            }
        }

        userService.createFile(userList);
    }

    private static String[] arrayNames(){

        return new String[]{
                "Alice", "Bob", "Charlie", "David", "Eve",
                "Frank", "Grace", "Heidi", "Ivan", "Judy",
                "Karen", "Liam", "Mona", "Nina", "Olga",
                "Paul", "Quinn", "Rachel", "Sam", "Tina",
                "Uma", "Vera", "Walter", "Xavier", "Yara",
                "Zara", "Amy", "Ben", "Cathy", "Dana",
                "Ed", "Fiona", "Gina", "Hank", "Iris",
                "Jack", "Kate", "Linda", "Mike", "Nancy"
        };
    }
}