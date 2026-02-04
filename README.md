[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/jJxaUXj9)


# Grupp3 Appstore API

---
## 📖 About the Project

**Grupp3 Appstore API** is a RESTful service to manage an app store apps and user accounts with secure API key authentication. Only registered users with a valid API key can access protected endpoints.

---
## ✨ Features

### App Management (/api/app)
- **GET /api/app**: Retrieve all apps  
- **GET /api/app/{id}**: Retrieve a single app by ID  
- **GET /api/app/count**: Count of total apps 
- **POST /api/app**: Create a new app  
- **PATCH /api/app/{id}/version**: Update app version  
- **PATCH /api/app/{id}/description**: Update app description  
- **PATCH /api/app/{id}/image**: Update app image  
- **DELETE /api/app/{id}**: Delete an app

 ### For more info please check the docs at:
  http://localhost:8080/q/dev-ui/quarkus-smallrye-openapi/swagger-ui

**Validations & Rules:**
- @NotEmpty fields ensure no empty values are stored
- PATCH endpoints validate non-null, non-empty updates
- Returns `404 Not Found` if an app doesn’t exist
- Returns `400 Bad Request` for invalid input

### User Management (/public/user)
- **POST /public/user/register**: Register a new user  
- **POST /public/user/login**: Login user and retrieve API key  
- **GET /public/user/generate**: Generate a new API key  

### Access api using Apikey
<ul>
 <li>
  Endpoint starting with "/public" is public and doesn't require a apikey to access
 </li>
 <li>
  Every other endpoint does require a apikey to access
 </li>
</ul>

### How to access the api using apikeys?

To access the api you need to have a apikey, with the project comes a starter key: 

```shell script
269343d1-f071-43ea-a666-bfbf36fee3a2
```

### Access thru postman
<ul>
        <li>
                Copy the key.
        </li>
        <li>
                Go to Headers.
        </li>
        <li>
                Set Key type to "x-api-key".
        </li>
        <li>
                Set Value to your key/starter key.
        </li>
</ul>

### Access by fetch

To access by fetch you need to set the header in your javascript.

Here is an example:

```
const response = await fetch("localhost:8080/api/app", {
  method: "GET",
  headers: {
    "x-api-key": "269343d1-f071-43ea-a666-bfbf36fee3a2",
  }
```

---
## ⚙️ Tech Used

- Java + Jakarta EE (JAX-RS)
- Quarkus
- Hibernate / JPA
- PostgreSQL / H2
- API key-based authentication
- Docker

---
## Configuration (Application properties)
```
quarkus.hibernate-orm.database.generation=drop-and-create
quarkus.hibernate-orm.scripts.generation=drop-and-create
quarkus.hibernate-orm.scripts.generation.create-target=create.ddl
quarkus.hibernate-orm.scripts.generation.drop-target=drop.ddl
quarkus.hibernate-orm.log.sql=true

```
---

##  How to Run It
 Have the Docker running in the background, go to the directory on your terminal and type 
 ./mvnw compile 
 to compile the program and then 
 ./mnvw quarkus:dev 
 to run it 
once running you can go to Postman to test the end points 
---

## Project Structure 
```

appstore-api/src/main/java/org/Grupp3Api/Api

├── entity folder 
├────── ApiKeyDTO               → Entity class DTO
├────── User                    → Entity class
├────── App                     → Entity class
├────── AppDTO                  → Entity class DTO 
├── filter folder       
├────── ApiKeyFilter            → Intercepts incoming HTTP requests to validate API keys
├── resource folder
├────── AppResource             → REST API endpoints 
├────── UserResource            → REST API endpoints 
├── service folder
├────── AppService              → Business logic
├────── UserService             → Business logic


/resources

├── application.properties
└── import.sql      → Initial apps to be send to the database when the application starts 


```
---

## Application Architecture 
```
In this application we are using a layered RESTful API architecture.

Resource (AppResource / UserResource) → Service (AppService / UserService) → EntityManager → Database
                       ↓
                 ApiKeyFilter


```
---



🧑‍💻 Authors: Jonatan Andersson, Ninos Chimon, Andreas Klasson, Caner Uluğ
- Created for a school assignment project with Quarkus, Jakarta EE (JAX-RS), JPA/Hibernate & PostgreSQL

