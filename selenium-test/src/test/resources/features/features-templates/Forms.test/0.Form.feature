@Form

Feature: Uzupełnienie formularza

  Scenario: Scneariusz Podstawowy
    When UŻYTKOWNIK wybiera kafel Forms
    When SYSTEM wyświetla komunikat o konieczności wyboru elementu do ćwiczeń
    When UŻYTKOWNIK wybiera opcję Practice Form
    When SYSTEM wyświetla formularz
    When UŻYTKOWNIK uzupełnia pole imię
    When UŻYTKOWNIK uzupełnia pole nazwisko
    When UŻYTKOWNIK uzupełnia pole email
    When UŻYTKOWNIK uzupełnia wybiera płeć
    When UŻYTKOWNIK uzupełnia pole numer telefonu
    When UŻYTKOWNIK uzupełnia pole data urodzenia
    When UŻYTKOWNIK uzupełnia pole przedmiot
    When UŻYTKOWNIK uzupełnia wybiera hobby
    When UŻYTKOWNIK wgrywa zdjęcie
    When UŻYTKOWNIK uzupełnia pole obeczny adres
    When UŻYTKOWNIK z listy rozwijanej wybiera stan
    When UŻYTKOWNIK z listy rozwijanej wybiera miasto
    When UŻYTKOWNIK klika przycisk Submit




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