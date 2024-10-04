@Portfolio
Feature: Book Store Application

  Rule: Login

    Scenario Outline: Nowy użytkownik
      Given UŻYTKOWNIK przechodzi do zakładki Login
      When UŻYTKOWNIK przechodzi do rejestracji
      When UŻYTKOWNIK uzupełnia pole imię <name>
      When UŻYTKOWNIK uzupełnia pole nazwisko <lastName>
      When UŻYTKOWNIK uzupełnia pole nazwę użytkownika <userName>
      When UŻYTKOWNIK uzupełnia pole hasło <password>
      When UŻYTKOWNIK zatwierdza capche
      When UŻYTKOWNIK klika w przycisk Register
      Then SYSTEM tworzy nowego użytkownika
      Then UŻYTKOWNIK zamyka komunikat sukcesu
      Then UŻYTKOWNIK loguje sie do systemu
      Examples:
        | name | lastName | userName | password |
        | Jan  | Kowalski | jkowal   | Zaq123@! |

