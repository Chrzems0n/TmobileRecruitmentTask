# Uruchamianie testów i raportów

Projekt używa Maven do uruchamiania testów oraz Allure do prezentowania raportów.

Polecenia należy wykonywać w katalogu głównym projektu, w którym znajduje się plik `pom.xml`.

## Testy API NBP + raport Allure

Uruchomienie wyłącznie scenariusza API oznaczonego tagiem `@api`:

```powershell
mvn clean test "-Dcucumber.filter.tags=@api"; if ($?) { mvn allure:serve }
```

Polecenie:

1. usuwa poprzednie wyniki,
2. uruchamia test API NBP,
3. zapisuje wyniki w `target/allure-results`,
4. uruchamia lokalny serwer Allure i otwiera raport w przeglądarce.

## Wszystkie testy GUI w Chrome + raport Allure

```powershell
mvn clean test "-Dbrowser=chrome"; if ($?) { mvn allure:serve }
```

To polecenie uruchamia zarówno scenariusz API, jak i scenariusz GUI z użyciem przeglądarki Chrome, a następnie otwiera raport Allure.

## Wszystkie testy GUI w Firefox + raport Allure

```powershell
mvn clean test "-Dbrowser=firefox"; if ($?) { mvn allure:serve }
```

To polecenie uruchamia zarówno scenariusz API, jak i scenariusz GUI z użyciem przeglądarki Firefox, a następnie otwiera raport Allure.

## Uruchomienie wszystkich testów bez wskazania przeglądarki

Jeżeli parametr `browser` nie zostanie podany, konfiguracja projektu użyje domyślnie Firefoksa:

```powershell
mvn clean test; if ($?) { mvn allure:serve }
```

## Raport statyczny bez uruchamiania serwera

Jeżeli raport ma zostać wygenerowany w katalogu projektu zamiast otwarcia lokalnego serwera:

```powershell
mvn allure:report
```

Wygenerowany raport znajduje się w:

```text
target/allure-report
```

## Raport Cucumber

Po wykonaniu testów raport HTML Cucumber jest dostępny w pliku:

```text
target/cucumber-reports.html
```

## Zatrzymanie raportu Allure

Polecenie `mvn allure:serve` uruchamia lokalny serwer i pozostaje aktywne w terminalu. Aby go zatrzymać, naciśnij:

```text
Ctrl+C
```

## Wymagania

Przed uruchomieniem testów należy mieć dostępne:

- Java 17 lub nowszą,
- Maven,
- Chrome lub Firefox wraz z odpowiednim sterownikiem zarządzanym przez Selenium,
- połączenie z Internetem dla testu API NBP i scenariusza GUI T-Mobile.

