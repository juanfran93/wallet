# Wallet

## How to Install

Clone repository and execute "gradlew build" in the repository root directory.

## How to run

After you clone and build the application, you need execute the follow command line:

"java -jar build\libs\wallet-0.0.1-SNAPSHOT.jar com.recargapay.wallet.WalletApplication.main"

It is necessary to have the java development kit(JDK) 17 version.

## How to test
if you run successfully the Wallet App, you can use the postman file inside postman root folder and importing it as a collection in the Postman app or related app.


# Design choices

The implementation was initialized using Spring Initializer and Java 17 and gradle as main Build tool. Spring framework and spring boot libs (web, validation data) was used to accelerate the development,  and Lombok lib too. I use H2 (hibernate) to define in-memory relational database, just for test the correct functionality. 
In a correct development we must use a separated Database like PosgreSQL or SQLServer, for example.

I defined 3 layer inside of the Wallet project: Controller Layer, Service Layer and DAO Layer.

The first one has the responsability of provide communication between the users ( or apps like Mobile or Web apps, for example) and the Wallet App.

The second one has the responsability to define business rules and interact with the Model system, in this case H2 database tables.

The third one represents the model that exist in a Database, in this case, living inside Hibernate.