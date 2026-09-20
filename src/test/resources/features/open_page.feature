Feature: T-Mobile smartphone purchase flow

  Scenario: User buys a smartphone without contract and verifies cart
Given Otwórz stronę główną T-Mobile
And Z górnej belki wybierz "Sklep"
And Kliknij "Bez abonamentu" z sekcji "Smartfony"
And Kliknij element o nazwie "Samsung Galaxy A37 5G"
   # And Z górnej belki wybierz "Sklep"
    And Dodaj produkt do koszyka
    And Kliknij element o nazwie "Samsung Galaxy A37 5G"


