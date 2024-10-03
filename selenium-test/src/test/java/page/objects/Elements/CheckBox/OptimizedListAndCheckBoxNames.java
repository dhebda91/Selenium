package page.objects.Elements.CheckBox;

import java.util.List;
import java.util.stream.Collectors;

public class OptimizedListAndCheckBoxNames {

    // Metoda zwracająca przetworzoną listę na podstawie przekazanej listy bazowej
    public static List<String> getProcessedList(List<String> originalList) {
        return originalList.stream()
                .map(OptimizedListAndCheckBoxNames::processCheckboxName)
                .collect(Collectors.toList());
    }

    // Metoda przekształcająca nazwę checkboxa
    public static String processCheckboxName(String checkbox) {
        checkbox = checkbox.trim().replaceAll(" ", "");

        // Sprawdzenie i usunięcie ".doc"
        if (checkbox.endsWith(".doc")) {
            checkbox = checkbox.substring(0, checkbox.length() - 4); // Usunięcie ".doc"
            // Zmieniamy tylko pierwszą literę na małą, reszta zostaje bez zmian
            return checkbox.substring(0, 1).toLowerCase() + checkbox.substring(1);
        } else {
            // Zmieniamy cały tekst na małe litery
            return checkbox.toLowerCase();
        }
    }
}
