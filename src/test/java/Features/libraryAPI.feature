Feature: Validating Books  API

This feature covers adding muitple books to the library system,
verifying that all books are added successfully,
and also supports updating and deleting books.


Scenario: Verify multiple books are successfully added using Add Book API

Given the "AddBook" Properties And Payload are Ready
When User calls the "AddBook" Api
Then "AddBook" Api Response is successful


Scenario: Verify added books are retrievd using getBook API

Given the "GetBook" Properties And Payload are Ready
When User calls the "GetBook" Api
Then "GetBook" Api Response is successful








