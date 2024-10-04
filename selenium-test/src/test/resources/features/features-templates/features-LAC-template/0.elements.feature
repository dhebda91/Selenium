@Portfolio
Feature: Elements Test

  Rule: Text Box tests

    Scenario: Text Box test
      When UŻYTKOWNIK przechodzi do zakładki TextBox
      When UŻYTKOWNIK uzupełnia dane
      When UŻYTKOWNIK zatwierdza dane
      Then SYSTEM prezentuje odpowiednie dane

  Rule: Check Box Tests

    Scenario Outline: Check Box test
      When UŻYTKOWNIK przechodzi do zakładki CheckBox
      When UŻYTKOWNIK oznaczy <checkbox>
      Then SYSTEM wyświetli listę zaznaczonych <checkbox>
      Examples:
        | checkbox       |
        | Home           |
        | Desktop        |
        | Notes          |
        | Commands       |
        | Documents      |
        | WorkSpace      |
        | React          |
        | Angular        |
        | Veu            |
        | Office         |
        | Public         |
        | Private        |
        | Classified     |
        | General        |
        | Downloads      |
        | Word File.doc  |
        | Excel File.doc |

  Rule: Radio Button tests

    Scenario Outline:  Radio Button test
      When UŻYTKOWNIK przechodzi do zakładki RadioButton
      When UŻYTKOWNIK zaznaczy <button>
      Then SYSTEM wyświetla <komunikat>
      Examples:
        | button     | komunikat                    |
        | yes        | You have selected Yes        |
        | impressive | You have selected Impressive |
        | no         |                              |

  Rule: Web Tables tests

    Scenario: Web Tables test

  Rule : Buttons Test

    Scenario Outline: Użytkownik klika w buttony
      When UŻYTKOWNIK przechodzi do zakładki Buttons
      When UŻYTKOWNIK klika w button <button>
      Then SYSTEM po kliknięciu wyświetla odpowiedni <komunikat>
      Examples:
        | button          | komunikat                     |
        | Double Click Me | You have done a double click  |
        | Right Click Me  | You have done a right click   |
        | Click Me        | You have done a dynamic click |

  Rule: Links tests
    Background:
      Given UŻYTKOWNIK przechodzi do zakładki Links

    Scenario Outline: Links will open new tab
      When UŻYTKOWNIK klika w link <link>
      Then SYSTEM otwiera nowe okno
      Examples:
        | link      |
        | Home      |
        | HomePD0pM |

    Scenario Outline: Links will send an api call
      When UŻYTKOWNIK klika w link <link>
      Then SYSTEM zwraca odpowiedni <status>
      Examples:
        | link         | status |
        | Created      | 201    |
        | No Content   | 204    |
        | Moved        | 301    |
        | Bad Request  | 400    |
        | Unauthorized | 401    |
        | Forbidden    | 403    |
        | Not Found    | 404    |

  Rule: Broken Links - Images tests

    Scenario: Broken Links - Images test

  Rule: Upload and Download tests

    Scenario: Upload and Download test

  Rule: Dynamic Properties tests

    Scenario: Dynamic Properties test