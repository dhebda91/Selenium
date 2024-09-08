package page.objects;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainCLass {
    public static void main(String[] args) {
        String dateString = "22 February 1991";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMMMM yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);

        // Pobranie dnia tygodnia
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("Day of the week: " + dayOfWeek);
    }
}
