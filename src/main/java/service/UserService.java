package service;

import model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    public void createFile(List<User> userList) {
        try (Workbook wb = new HSSFWorkbook();
             FileOutputStream file = new FileOutputStream("rew.xls")) {

            Sheet sheet = wb.createSheet("Лист Пользователей");

            addUserByRating(userList, sheet);

            wb.write(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUserByRating(List<User> userList, Sheet sheet) {
        List<User> list = userList.stream().sorted(Comparator.comparingInt(User::getRatingPoints).reversed())
                .toList();

        int count = 0;
        for (User user : list) {
            Row row = sheet.createRow(count);

            Cell cellName = row.createCell(0);
            Cell cellRating = row.createCell(1);

            cellName.setCellValue(user.getName());
            cellRating.setCellValue(user.getRatingPoints());

            count++;
        }
    }
}
