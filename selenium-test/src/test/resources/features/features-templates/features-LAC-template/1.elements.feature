@Portfolio
Feature: Elements Test

  Rule: Text Box tests

    Background:
      When UŻYTKOWNIK przechodzi do zakładki Elementy

    Scenario: Text Box test
      When UŻYTKOWNIK przechodzi do zakładki TextBox
      When UŻYTKOWNIK uzupełnia dane
      When UŻYTKOWNIK zatwierdza dane
      Then SYSTEM wyświetla dane

    Scenario: Check Box test

  Rule: Radio Button tests

    Background:
      When UŻYTKOWNIK przechodzi do zakładki Elementy

    Scenario Outline:  Radio Button test
      When UŻYTKOWNIK przechodzi do zakładki RadioButton
      When UŻYTKOWNIK zaznaczy <button>
      Then SYSTEM wyświetla <komunikat>
      Examples:
        | button     | komunikat                    |
        | yes        | You have selected Yes        |
        | impressive | You have selected Impressive |
        | no         |                              |


    Scenario: Web Tables test

  Rule : Buttons Test
    Scenario Outline: Użytkownik klika w buttony
      When UŻYTKOWNIK przechodzi do zakładki Buttons
      When UŻYTKOWNIK klika w <button>
      Then SYSTEM po kliknięciu wyświetla <komunikat>
      Examples:
        | button          | komunikat                     |
        | Click Me        | You have done a dynamic click |
#        | Double Click Me | You have done a double click  |
#        | Right Click Me  | You have done a right click   |




    Scenario: Links test

    Scenario: Broken Links - Images test

    Scenario: Upload and Download test

    Scenario: Dynamic Properties test