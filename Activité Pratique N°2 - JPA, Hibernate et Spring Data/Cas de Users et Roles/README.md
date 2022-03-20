#Activité Pratique N°2 - JPA, Hibernate et Spring Data
we will be testing object mapping using JPA Hibernate and Spring Data


and the project structure :

![This is an image](Images/img.PNG)
 
as you can see we have 4 packages

 - entities
 
this package will hold our persistence classes 

-repositories 

this package will hold our persistence classes 

this will be holding our interfaces that extends JpaRepository, so we have 
all the pessary methods to persist our objects to the database

-service

this class will hols our Hospital service which will use the repositories interface 

in order to offer all services needed

-web

will hold a controller to test our application

####User class

![This is an image](Images/img_1.PNG)

####Role class

![This is an image](Images/img_2.PNG)


to test this we will be using h2 memory database
as also mysql database

###application properties
![This is an image](Images/img_3.PNG)

in for the main code 

![This is an image](Images/img_4.PNG)

##Test in h2 Database

as you can see here our database is successful created

![This is an image](Images/img_5.PNG)

-users table

![This is an image](Images/img_6.PNG)

-role table

![This is an image](Images/img_7.PNG)

-users_role table

![This is an image](Images/img_8.PNG)


##Test in mysql Database

as you can see here our database is successful created

![This is an image](Images/img_9.PNG)

-users table

![This is an image](Images/img_10.PNG)

-role table

![This is an image](Images/img_11.PNG)

-users_role table

![This is an image](Images/img_12.PNG)




###web test
 the following code

 ![This is an image](Images/img_13.PNG)

result

![This is an image](Images/img_14.PNG)






