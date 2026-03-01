Únik z podzemního komplexu

Textová hra v javě, ve které se probouzíte jako Marek v uzamčeném robotickém komplexu. Vaším cílem je získat důvěru strojů, plnit úkoly, odemknout výtahovou šachtu a uniknout na povrch. Hra kombinuje průzkum lokací, práci s inventářem, dialogy s postavami a finální hackovací souboj.

## Ovládání hry

Do konzole zadáváte příkazy. Přehled hlavních commandů:

- `jdi <směr>` – přesun mezi lokacemi (`sever`, `jih`, `východ`, `západ`)
- `vstup <místo>` – vstup do sousední lokace podle názvu
- `seber <předmět>` – sebrání předmětu z aktuální lokace
- `pouzij <předmět>` – použití předmětu z inventáře
- `inventar` – výpis inventáře
- `mluv <jméno>` – zahájení dialogu s postavou
- `odpovez <číslo>` – volba odpovědi v dialogu
- `ukonci` – ukončení aktivního dialogu
- `ukoly` – seznam aktivních/splněných úkolů
- `detail <úkol>` – detail konkrétního úkolu
- `utok` – útok ve fázi hackovacího souboje
- `obrana` – obrana ve fázi hackovacího souboje
- `rada` – kontextová rada, jak pokračovat
- `pomoc` / `napoveda` – nápověda k příkazům
- `konec` – ukončení hry

## Základní herní mechaniky

- **Průzkum mapy**: lokace jsou propojené směrovými východy.
- **Quest systém**: postup je řízen plněním úkolů od robotických postav.
- **Dialogy**: NPC mají vlastní dialogové větve.
- **Inventář**: předměty se sbírají a používají pro odemykání postupu.
- **Hackovací duel**: finální střet s Hektorem používá příkazy `utok` a `obrana`.

## Jak hru spustit

### 1) Spuštění z IDE

- Otevřete projekt a spusťte třídu `Main` (Java 17).

### 2) Spuštění z JAR souboru

Po sestavení najdete spustitelný JAR v `target/`:

bash
mvn clean package
java -jar target/jackson-demo-1.0.0-jar-with-dependencies.jar


Výsledný soubor:

- `target/jackson-demo-1.0.0-jar-with-dependencies.jar`

Tento JAR je spustitelný přímo přes `java -jar ...`.
> Pokud po změnách stále spouštíte starý JAR, smažte staré artefakty a sestavte projekt znovu:
> ```bash
> mvn clean package
> ```
> Poté spusťte nově vytvořený soubor v `target/`.


## Použité technologie

- **Java 17**
- **Maven** (build a dependency management)
- **Jackson (databind/core/annotations)** – práce s daty
- **Gson**
- **JUnit 5** – testování