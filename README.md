# Bereitstellung eines Spring Boot basierten REST-Webservices

## Szenario
Innerhalb einer Microservice-Architektur soll ein Service bereitsgestellt werden, der Personaldaten anreichern kann.
Die Personal-Stammdaten sind zwar mit einer Postleitzahl versehen, leider jedoch nicht mit dem zugehörigen Ort.
Darüber hinaus fehlen noch weitere Informationen. 

## Zielgruppe
* Kandidat:innen mit bereits bestehender Entwicklungserfahrung

## Technologien:
Spring Boot, Maven, REST, JUnit, JPA, h2

## Anforderungen:
Die Software soll folgende Anforderungen erfüllen:
* Bereitstellung eines REST-Endpunktes, welchem man als Parameter die Personalnummer, den Personalnamen sowie Postleitzahl des Wohnorts enthält.
* Die Software soll den Request annehmen, und ihn in einer Logging-Tabelle in einer h2 Datenbank speichern (o.g. Daten + Anfragezeitpunkt)
* Die Anwendung soll einen geeigneten Request an einen Service aus dem Internet stellen, und über Angabe der Postleitzahl den entsprechenden
Wohnort sowie das Bundesland ermitteln
* Die Anwendung soll im Json-Format die Request-Daten sowie die ermittelten Daten (Wohnort und Bundesland) im JSON-Format als Antwort liefern.
* Die Software soll über eine geeignete Testautomatisierung verfügen (siehe Hinweise)

## Beispiele für Eingabedaten:
| Personalnummer      | Name           | Postleitzahl  |
| ------------- |:-------------:| -----:|
| 13    | Hans Mustermann | 53474 |
| 14    | Franziska Musterfrau      |   53340 |
| 15 | John Doe     |    56564 |

## Hinweise:
* Berücksichtigung Layered Architecture
* Testing: Komponenten- und Integrationstests, ggf. auch Mocking einsetzen. Hier kann sich ausgetobt werden.
