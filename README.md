Podsumowanie Testów Automatycznych z użyciem Serenity BDD, Selenium i Javy 17

Testy zostały napisane w języku Java 17 z wykorzystaniem frameworku Selenium, który umożliwia automatyzację testowania aplikacji webowych. W celu uproszczenia kodu i zwiększenia elastyczności, użyto pliku CSV do wczytywania danych wejściowych do formularzy. Dzięki temu możliwe było przeprowadzenie testów z różnymi zestawami danych, eliminując konieczność ręcznego ich wprowadzania. Testy były wykonywane w przeglądarce Chrome, a zautomatyzowane przypadki obejmowały 5 różnych scenariuszy.

Do organizacji testów i raportowania wyników wykorzystano Serenity BDD, co pozwoliło na:

Łatwe zarządzanie testami,
Generowanie czytelnych raportów,
Lepsze śledzenie postępu testów w różnych scenariuszach.
Serenity BDD umożliwiło także integrację z narzędziami do raportowania, co pomogło w monitorowaniu wyników testów w sposób bardziej przejrzysty i zrozumiały.

Aby wygenerować raport należy:
1. Zbudować projekt i zainstalować zależności
mvn clean install
2. Następnie wygenerować raport Serenity
mvn serenity:aggregate
Po wykonaniu testów, raporty są dostępne w formie HTML, umożliwiając łatwą analizę wyników i postępu testów.

Napotkane problemy: Podczas testowania napotkano problem związany z wyświetlaniem komunikatów błędów w formularzach. Zakładając, że brak wypełnienia obowiązkowego pola powinien skutkować wyświetleniem komunikatu błędu, wystąpił przypadek, w którym komunikaty te nie pojawiały się w wielu sytuacjach. W wyniku tego, niektóre testy mogły nie przejść, ponieważ brak wyświetlonych komunikatów błędów wpływał na poprawność weryfikacji wyników testów.

Uwagi końcowe: Z powodu dużej ilości pracy związanej z projektem, nie udało się dopieścić go w pełni tak, jak bym chciała. Niemniej jednak, testy zostały zaimplementowane i wykonane w sposób umożliwiający dalszy rozwój i poprawki w przyszłości.