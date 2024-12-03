package service;

import model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private static final String FILE_NAME = "users.xls";
    private static final String SHEET_NAME = "Лист Пользователей";

    public void createFile(List<User> userList) {
        try (Workbook workbook = new HSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {

            Sheet sheet = workbook.createSheet(SHEET_NAME);
            addUsersToSheet(userList, sheet);

            workbook.write(fileOutputStream);

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании файла Excel: " + FILE_NAME, e);
        }
    }

    private void addUsersToSheet(List<User> userList, Sheet sheet) {
        List<User> sortedUsers = userList.stream()
                .sorted(Comparator.comparingInt(User::getRatingPoints).reversed())
                .collect(Collectors.toList());

        createHeaderRow(sheet);

        int rowIndex = 1;
        for (User user : sortedUsers) {
            Row row = sheet.createRow(rowIndex++);

            createCell(row, 0, user.getName());
            createCell(row, 1, user.getRatingPoints());
        }
    }

    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);

        createCell(headerRow, 0, "Имя");
        createCell(headerRow, 1, "Очки рейтинга");
    }

    private void createCell(Row row, int columnIndex, Object value) {
        Cell cell = row.createCell(columnIndex);

        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue(String.valueOf(value));
        }
    }
}
