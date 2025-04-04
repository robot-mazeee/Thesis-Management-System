To run the project locally, follow the steps bellow:

# 0) Install Dependencies

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Node.js 14+](https://nodejs.org/en/) ([Node Version Manager](https://github.com/nvm-sh/nvm) recommended)
- [Docker](https://www.docker.com/)
- [Spring Boot](https://spring.io/) (Handled by Maven)
- [Vue.js](https://vuejs.org/) (Handled by npm)

# 1) Clone the project

```bash
git clone https://gitlab.rnl.tecnico.ulisboa.pt/dei/candidaturas/dei-pms-sols-candidatos/dei-pms-madalena-mota.git
```

# 2) Navigate to the project directory

```bash
cd dei-pms-madalena-mota
```

# 3) Run the database with Docker

```bash
docker compose up
```

# OR (if using a Linux subsystem)

```bash
sudo docker compose up
```

# To stop the database

```bash
(sudo) docker compose down
```

# 4) Run the backend

```bash
cd src/backend  
mvn clean spring-boot:run
```

# 5) Run the frontend

```bash
cd src/frontend
npm i
npm run dev
```

# 6) Access the database

```bash
psql -h localhost -p <PORT> -U <USER> <DB_NAME>
```

```bash
psql -h localhost -p 7654 -U postgres deidb  # If using Docker default
```