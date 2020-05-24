##Streaming site application

Simple application that allows registering a user into the database.
After registration when logged in the user can see the list of movies it the database.
Movies can be searched and up or down voted, the user can add them to their favourites list
which is unique for every user and can be seen in the Menu.
If the user is an admin then an Admin panel is available.
In the Admin panel the admin can see the list of users and able to add or remove users.
The admin can search from the users by their username or search for the admin or non admin users.
The admin can add movies to the database.
###Usage
Execute either of the following commands in the main directory:

    mvn package
    java -jar target/beadando-1.0.jar

or

    mvn compile
    mvn exec:java

###Requirements

* JDK 11 or above
* Maven 3.0 or above