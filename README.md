# E-Store:  Customized T-shirts and phone cases
This will be an e-store specializing in products that help its buyers express their personalities through its products. As such, the store will offer t-shirts and phone cases that, beyond their practical uses, can serve as a canvas for users to express their tastes and ideas. (edited) 

## Modify this document to expand any and all sections that are applicable for a better understanding from your users/testers/collaborators (remove this comment and other instructions areas for your FINAL release)

OUr online E-store system is built with Java SpringBoot 8=>11 and Angular.
  
## Team

- Caluã de Lacerda Pataca 
- Dipkamal Bhusal
- Avinash Amudala
- Md Tanvirul Alam
- Galekwan Sango


## Prerequisites

- Java 8=>11 (Make sure to have correct JAVA_HOME setup in your environment)
- Maven
- Angular


## How to run it

1. Clone the repository and go to the root directory.
2. Change the directory to estore-api folder and Execute `mvn compile exec:java`
3. Open in your browser `http://localhost:8080/`
4. Make sure your port 8080 is not in use. If its in use, find the PID of the process and kill it first. 

## CURL commands for test 

1. Create a new product in the inventory: curl -X POST -H 'Content-Type:application/json' 'http://localhost:8080/products' -d '{"name": "Momo", "price":20, "quantity":20}'       
2. Delete a product in the inventory: curl -X DELETE 'http://localhost:8080/products/3'
3. Retrieve a specific product: curl -X GET 'http://localhost:8080/products/1'
4. Search for a product by partial name: curl -X GET 'http://localhost:8080/products/?name=glo' 
5. Update the price or quantity of a product in the inventory: curl -X PUT -H 'Content-Type:application/json' 'http://localhost:8080/products' -d '{"id": 4,"name":"Mo:Mo", "price":10, "quantity":200}' 
6. List all the products (name, price, quantity, etc.) in the inventory: curl -X GET 'http://localhost:8080/products'

Note: In Mac, you might to have to replace localhost with address 0.0.0.0!

## Known bugs and disclaimers
(It may be the case that your implementation is not perfect.)

Document any known bug or nuisance.
If any shortcomings, make clear what these are and where they are located.

## How to test it

The Maven build script provides hooks for run unit tests and generate code coverage
reports in HTML.

To run tests on all tiers together do this:

1. Execute `mvn clean test jacoco:report`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/index.html`

To run tests on a single tier do this:

1. Execute `mvn clean test-compile surefire:test@tier jacoco:report@tier` where `tier` is one of `controller`, `model`, `persistence`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/{controller, model, persistence}/index.html`

To run tests on all the tiers in isolation do this:

1. Execute `mvn exec:exec@tests-and-coverage`
2. To view the Controller tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
3. To view the Model tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
4. To view the Persistence tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`

*(Consider using `mvn clean verify` to attest you have reached the target threshold for coverage)
  
  
## How to generate the Design documentation PDF

1. Access the `PROJECT_DOCS_HOME/` directory
2. Execute `mvn exec:exec@docs`
3. The generated PDF will be in `PROJECT_DOCS_HOME/` directory


## How to setup/run/test program 
1. Tester, first obtain the Acceptance Test plan
2. IP address of target machine running the app
3. Execute ________
4. ...
5. ...

## License

MIT License

See LICENSE for details.
