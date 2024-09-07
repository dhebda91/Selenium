@Form

Feature: Student uzupełnia formularz osobowy

  Scenario: Scenariusz Podstawowy
    * STUDENT przechodzi do formularza
    * SYSTEM wyświetla formularz
    * STUDENT uzupełnia pole imię
    * STUDENT uzupełnia pole nazwisko
    * STUDENT uzupełnia pole email
    * STUDENT uzupełnia wybiera płeć
    * STUDENT uzupełnia pole numer telefonu
    * STUDENT uzupełnia pole data urodzenia
    * STUDENT uzupełnia pole przedmiot
    * STUDENT uzupełnia wybiera hobby
    * STUDENT wgrywa zdjęcie
    * STUDENT uzupełnia pole obecny adres
    * STUDENT z listy rozwijanej wybiera stan
    * STUDENT z listy rozwijanej wybiera miasto
    * STUDENT klika przycisk Submit
    * SYSTEM wyświetla podsumowanie formularza
    * STUDENT sprawdza dane
    * STUDENT klika w przycisk Close




#    @ValidCredentials
#    Scenario: Scenariusz alternatywny A???????
#
#
#    @InvalidCredentials
#    Scenario Outline: Stenariusz wyjątku A - Logowanie z nieprawidłowymi danymi
#
#
#      Examples:
#        | username   | password  | errorMessage                      |
#        | Admin      | admin12$$ | Invalid credentials               |
#        | admin$$    | admin123  | Invalid credentials               |
#        | abc123     | xyz$$     | Invalid credentials               |
#
#  Rule: Drugi test - logowanie
#
#    Background:
#
#    @ScenPodstawowy
#    Scenario: Scneariusz Podstawowy
#
#    @ScenAlternatywnyA
#    Scenario Outline: Scenariusz alternatywny A -
#      Examples:
#        |  |  |
#        |  |  |
#
#    @ScenWyjatkuA
#    Scenario: Stenariusz wyjątku A -