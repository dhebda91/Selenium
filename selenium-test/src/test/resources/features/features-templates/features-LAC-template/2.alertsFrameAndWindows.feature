@Portfolio
Feature: Form Test

  Rule: Browser Windows

    Background:
      Given UŻYTKOWNIK przechodzi do zakładki Browser Windows

    Scenario: Użytkownik klika w New Tab
      When UŻYTKOWNIK klika w przycisk New Tab
      Then SYSTEM otwiera nową kartę

    Scenario: Użytkownik klika w New Window
      When UŻYTKOWNIK klika w przycisk New Window
      Then SYSTEM otwiera okno

    Scenario: Użytkownik klika w New Window Message
      When UŻYTKOWNIK klika w przycisk New Window Message
      Then SYSTEM otwiera nowe małe okno
