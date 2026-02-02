package mvc.view;

public class UI {
    private static void thumbnail(){
        System.out.println("""
                ============================== Student Management ===============================
                1. Create Student
                2. Search Student by UUID
                3. Update Student by UUID
                4. Delete Student by UUID
                5. Exit
                """);
    }
    public static void getUI() {
        thumbnail();
    }
}
