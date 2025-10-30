# Übungsprojekt: Punch Clock

Punch Clock ist ein Zeiterfassungssystem, welches mit Quarkus entwickelt wird.

## Erste Schritte

1. Erstelle eine Kopie (fork) von diesem Projekt.
1. Stelle sicher, dass Docker installiert ist und läuft.
1. Stelle sicher, dass Visual Studio Code und die Erweiterung Remote Container installiert ist.
1. Klone (clone) das Projekt lokal, um damit arbeiten zu können.
1. Öffne das Projekt mit Visual Studio Code.
1. Öffne das Projekt im Entwicklungscontainer.
1. Starte das Projekt mit dem Kommando `Quarkus: Debug current Quarkus Project`
1. Probiere die Client-Applikation unter http://localhost:8080 aus.
1. Schaue die API auf http://localhost:8080/q/swagger-ui/ an.

## Datenbank

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.

### Datenbankadministration

Über http://localhost:5050 ist PgAdmin4 erreichbar. Damit lässt sich die Datenbank komfortabel verwalten. Der Benutzername lautet `zli@example.com` und das Passwort `zli*123`. Die Verbindung zur PostgreSQL-Datenbank muss zuerst mit folgenden Daten konfiguriert werden:
 - Host name/address: `db`
 - Port: `5432`
 - Maintenance database: `postgres`
 - Username: `postgres`
 - Password: `postgres`

## Automatische Tests

Die automatischen Tests können mit `./mvnw quarkus:test` ausgeführt werden. Für die automatischen Tests wird nicht die PostgreSQL-Datenbank verwendet, sondern eine H2-Datenbank, welche sich im Arbeitsspeicher während der Ausführung befindet.

### Tests in VSCode ausführen

Leider gibt es einen Fehler um alle Tests einer Klasse auszuführen. Der Fehler scheint mit einer inkompatiblen Version von JUnit in der `vscode-java-test`-Extension zu entstehen. Es ist möglich JUnit manuell zu aktualisieren:

```bash
cd /home/vscode/.vscode-server/extensions/vscjava.vscode-java-test-0.43.1/server
wget https://repo1.maven.org/maven2/org/eclipse/jdt/org.eclipse.jdt.junit5.runtime/1.1.400/org.eclipse.jdt.junit5.runtime-1.1.400.jar
rm org.eclipse.jdt.junit5.runtime_1.1.300.v20231214-1952.jar
mv org.eclipse.jdt.junit5.runtime-1.1.400.jar org.eclipse.jdt.junit5.runtime_1.1.300.v20231214-1952.jar
```