![Staging](https://github.com/GLO2003-H22-eq20/Floppa/actions/workflows/deploy-staging.yml/badge.svg)
![Deploy](https://github.com/GLO2003-H22-eq20/Floppa/actions/workflows/deploy-prod.yml/badge.svg)
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
## Open Source

### Code of conduct
Our code of conduct is present here: [Code of Conduct](CodeOfConduct.md)

### Contribution Guide
Our Contribution Guide is present here: [Contribution Guide](Contributions.md)

### License
The License used is detailed here: [License](License.md)

### Open Source Sources
* [Code of Conduct](https://www.contributor-covenant.org/version/2/1/code_of_conduct/code_of_conduct.md)
* [Contributions](https://gist.github.com/briandk/3d2e8b3ec8daf5a27a62#file-contributing-md)
* [License](https://choosealicense.com/licenses/mit/)
