# Microservice-Architektur für ein Cocktail- und Kühlschrank-Managementsystem

Dieses Dokument beschreibt die grundlegende Struktur und Kommunikation der Services innerhalb eines Microservice-Systems zur Verwaltung von Cocktails und Zutaten im Kühlschrank.

Das System umfasst folgende Services:
- **Cocktail-Service**: Bietet eine API zur Verwaltung und Suche von Cocktails und deren Zutaten.
- **Fridge-Service**: Überwacht die Verfügbarkeit von Zutaten im Kühlschrank und gibt basierend darauf eine Liste möglicher Cocktails sowie eine Einkaufsliste aus.

## Kommunikationsfluss

Im folgenden Diagramm ist dargestellt, wie die verschiedenen Services miteinander kommunizieren:

```mermaid
flowchart LR
    subgraph CocktailService [cocktail-service]
        direction TB
        CS1["GET /rest/cocktails"]
        CS2["GET /rest/cocktails/{id}"]
        CS3["GET /rest/cocktails/search"]
        CS4["POST /rest/possible"]
        CS5["GET /rest/ingredients"]
        CS6["GET /rest/ingredients/{id}"]
        CS7["GET /rest/ingredients/{id}/cocktails"]
    end

    subgraph FridgeService [fridge-service]
        direction TB
        FS1["GET /fridge/ingredients"]
        FS2["PATCH /fridge/ingredients/{id}"]
        FS3["GET /fridge/possible"]
        FS4["GET /fridge/shopping"]
    end

    %% Verbindungen zwischen den Endpunkten
    FS1 --> CS5
    FS3 --> CS4
    FS4 --> CS5