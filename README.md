# Floppa

Le meilleur site de vente anonyme au Québec!

## Requis

- Java 11
- Maven

## Setup

### Compilation

```
mvn clean install
```

### Exécution

```
mvn exec:java
```

### Checkstyle

```
mvn checkstyle:check
```

### MongoDB
You will need a mongoDB database to be able to launch locally, you can install mongo locally.

Or create a database on MongoDB Atlas for free without the need to install it locally.

```
Community Server : https://www.mongodb.com/try/download
MongoDB Compass UI : https://www.mongodb.com/try/download/tools
```

### Environment variables
The app needs some environment variables. 
Since we use the dotenv-java package you'll need to create a `.env` file at the root of the project following this structure : 

```
FLOPPA_MONGO_CONNECTION_STRING=<either localhost or an atlas connection string>
FLOPPA_MONGO_DATABASE=Floppa
```