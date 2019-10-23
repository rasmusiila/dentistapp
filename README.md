# DentistApp

This readme gives an overview of the project structure and contains tutorials on how to run the application and set it up in your environment.

## Requirements

For building and running the application you need:

- JDK 1.8
- Maven 3

## Quick Start Guide

This section guides you how to quickly start the application without having to set the project up in your environment.

## Full setup guide

This section guides you how to set the project up in your environment.

## Project Structure

This is a Spring Boot project built using Maven.

All the main components of the project can be found in the src folder.

`pom.xml` - contains the configuration necessary to build the project  
`dentistapp_ui_overview.pdf` - provides an overview of the user interface  
`worklog.txt` - a txt file containing the daily log of the work done during the project (gives some specific insight on what I was doing and why)

The `src` folder contains `main` and `test` folders - `test` is for testing the Java classes.

### Java packages

The Java packages can be found in `src/main/java`

#### Entity

The `entity` package contains the `DentistEntity` and `DentistVisitEntity` classes. Their purpose is to define the tables in the database. Each column has its restrictions which are defined by using Java annotations.

#### Repository

The `repository` package contains the `DentistRepository` and `DentistVisitRepository` interfaces which extend Spring's `CrudRepository` interface. This allows to perform crud operations on the entities.

#### Service

The `service` package contains the `DentistService` and `DentistVisitService` classes. The business logic goes into these classes and serve as mediators between the Controller and the Model layer.

#### Controller

The `controller` package contains the `DentistAppController` which handles the http-request and provides the necessary data to the views.

#### Config

The `config` package contains the configuration necessary to add Dentists to database from an existing list in a `.properties` file.

#### Dto

The `dto` package contains the `DentisVisitDTO` and `DentistDTO` (currently unnecessary) classes. These classes serve as containers to transform into respective entities after validation.

#### Exception

The `exception` package contains custom exceptions and the Exception Controller which allows back-end exceptions to direct the user to an error landing page (not internal server error).

#### Validation

The `validation` package contains the custom constraints and validators using these constraints. `javax.validation` allows for some simple checks like for null-checking but the more complicated validations are done by these classes.

### Resources

The `resources` folder contains the static web content like `html` files, fragments, `javascript` files, `css` files and `.properties` files.
