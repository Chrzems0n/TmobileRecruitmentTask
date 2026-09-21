Feature: T-Mobile Simfree - weryfikacja koszyka

  Scenario: Wybranie telefonu z listy ofert i weryfikacja cen w koszyku
Given Otwórz stronę główną T-Mobile
And Z górnej belki wybierz "Sklep"
And Kliknij "Bez abonamentu" z sekcji "Smartfony"
And Kliknij element o nazwie "Xiaomi Redmi 15C 5G"
    And Dodaj produkt do koszyka
    Then Zweryfikuj ceny na stronie koszyka
And Otwórz stronę główną T-Mobile
    And Kliknij ikonę koszyka
    Then Zweryfikuj ceny na stronie koszyka
      Then Zweryfikuj czy urządzenie jest widoczne w koszyku


#"Samsung Galaxy A37 5G"