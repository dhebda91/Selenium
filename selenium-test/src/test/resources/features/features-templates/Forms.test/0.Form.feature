@Form

Feature: Student uzupełnia formularz osobowy

  @to
  Scenario: Scneariusz Podstawowy
    Given STUDENT przechodzi do formularza
    Given SYSTEM wyświetla formularz
    When STUDENT uzupełnia pole imię
    When STUDENT uzupełnia pole nazwisko
    When STUDENT uzupełnia pole email
    When STUDENT uzupełnia wybiera płeć
    When STUDENT uzupełnia pole numer telefonu
    When STUDENT uzupełnia pole data urodzenia
    When STUDENT uzupełnia pole przedmiot
    When STUDENT uzupełnia wybiera hobby
    When STUDENT wgrywa zdjęcie
    When STUDENT uzupełnia pole obecny adres
    When STUDENT z listy rozwijanej wybiera stan
    When STUDENT z listy rozwijanej wybiera miasto
    When STUDENT klika przycisk Submit
    Then SYSTEM wyświetla podsumowanie formularza
    Then STUDENT sprawdza dane
    Then STUDENT klika w przycisk Close




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