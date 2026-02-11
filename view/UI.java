package mvc.view;

import mvc.controller.StudentController;
import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UI {

    private static final StudentController studentController = new StudentController();
    private static final Scanner scanner = new Scanner(System.in);

    private static StudentCreateDto insertStudentData() {
        System.out.println("[+] Create user feature");

        System.out.print("[+] Insert student's name: ");
        String name = scanner.nextLine();

        System.out.print("[+] Insert student's email: ");
        String email = scanner.nextLine();

        System.out.print("[+] Insert student's password: ");
        String pass = scanner.nextLine();

        System.out.println("[+] Insert birth of date");
        System.out.print("[+] Insert year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("[+] Insert month: ");
        int month = Integer.parseInt(scanner.nextLine());

        System.out.print("[+] Insert day: ");
        int day = Integer.parseInt(scanner.nextLine());

        LocalDate bod = LocalDate.of(year, month, day);

        return new StudentCreateDto(name, email, pass, bod);
    }

    private static StudentUpdateDto updateStudentData() {
        System.out.print("[+] Insert new name: ");
        String name = scanner.nextLine();

        System.out.print("[+] Insert new email: ");
        String email = scanner.nextLine();

        System.out.print("[+] Insert new profile: ");
        String profile = scanner.nextLine();

        System.out.println("[+] Insert birth of date");
        System.out.print("[+] Insert year: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("[+] Insert month: ");
        int month = Integer.parseInt(scanner.nextLine());

        System.out.print("[+] Insert day: ");
        int day = Integer.parseInt(scanner.nextLine());

        LocalDate bod = LocalDate.of(year, month, day);

        return new StudentUpdateDto(name, email, profile, bod);
    }

    private static void tableDisplay(List<StudentResponseDto> students) {
        Table table = new Table(6, BorderStyle.CLASSIC);
        String[] columns = {"UUID", "NAME", "EMAIL", "PROFILE", "CARD_ID", "BIRTH_OF_DATE"};

        for (String col : columns) {
            table.addCell(col, new CellStyle(CellStyle.HorizontalAlign.center));
        }

        for (StudentResponseDto s : students) {
            table.addCell(s.uuid(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(s.userName(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(s.email(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(s.profile() == null ? "" : s.profile(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(s.cardId() == null ? "" : s.cardId(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(s.birthOfDate().toString(), new CellStyle(CellStyle.HorizontalAlign.center));
        }

        System.out.println(table.render());
    }

    private static void thumbnail() {
        System.out.println("""
                ============== Student Management ===============
                1. Create Student
                2. Search Student by UUID
                3. Update Student by UUID
                4. Delete Student by UUID
                5. View All Students
                0. Exit
                """);
    }

    private static void pressEnterToContinue() {
        System.out.print(">> Press Enter to continue...");
        scanner.nextLine();
    }

    public static void getUI() {
        while (true) {
            thumbnail();
            System.out.print("[+] Insert option: ");
            int opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1 -> {
                    StudentResponseDto student =
                            studentController.createNewStudent(insertStudentData());
                    tableDisplay(List.of(student));
                    pressEnterToContinue();
                }
                case 2 -> {
                    System.out.print("[+] Insert UUID: ");
                    String uuid = scanner.nextLine();
                    StudentResponseDto student =
                            studentController.searchStudentByUuid(uuid);
                    if (student != null) {
                        tableDisplay(List.of(student));
                    } else {
                        System.out.println("Student not found.");
                    }
                    pressEnterToContinue();
                }
                case 3 -> {
                    System.out.print("[+] Insert UUID to update: ");
                    String uuid = scanner.nextLine();
                    StudentResponseDto updated =
                            studentController.updateStudentByUuid(uuid, updateStudentData());
                    if (updated != null) {
                        tableDisplay(List.of(updated));
                    } else {
                        System.out.println("Student not found.");
                    }
                    pressEnterToContinue();
                }
                case 4 -> {
                    System.out.print("[+] Insert UUID to delete: ");
                    String uuid = scanner.nextLine();
                    String result =
                            studentController.deleteStudentByUuid(uuid);
                    System.out.println(result);
                    pressEnterToContinue();
                }
                case 5 -> {
                    tableDisplay(studentController.getAllStudents());
                    pressEnterToContinue();
                }
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid option");
            }
        }
    }
}
