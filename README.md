# Authorizer

This is the nubank code challenge made by **Diego de Morais**

## Implementation

- I chose to use *Enum Strategy* to execute each business validation, as it is easy to add new rules and has a clean usage.
- I used *groovy* with *spock specification* to implement unit and integration tests quickly.
- I configured to create a *jar* including all dependencies by *shade plugin* to avoid install problems.
- I kept every responsibility separated, like the input and output methods, to be fastly changeable if needed.

## Installation

```bash
./make
```
or
```bash
mvn clean install
```

## Usage

Indicate the transactions file path as **stdin** input for the program. In this example, `operations` (on project directory):

```bash
./authorize < operations
```
or
```bash
cd target
java -jar java -jar authorizer-1.0.jar < ../operations
```


By Diego de Morais
