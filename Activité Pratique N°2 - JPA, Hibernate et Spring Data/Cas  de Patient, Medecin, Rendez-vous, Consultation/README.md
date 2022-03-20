#Activité Pratique N°2 - JPA, Hibernate et Spring Data
we will be testing object mapping using JPA Hibernate and Spring Data
first our class digramm is as followed 

![This is an image](Images/1.PNG)

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

this class will hols our Hospital service which will use the repositories interfaces 

in order to offer all services needed

-web

will hold a controller to test our application

####patient class

![This is an image](Images/img_1.PNG)

####Medecin class

![This is an image](Images/img_2.PNG)

####RendezVous class

![This is an image](Images/img_3.PNG)

####Consultation class

![This is an image](Images/img_4.PNG)

to test this we will be using h2 memory database

###application properties
![This is an image](Images/img_5.PNG)

in for the main code 

![This is an image](Images/img_6.PNG)

##Test

as you can see here our database is successful created

![This is an image](Images/img_7.PNG)

-table Patient

![This is an image](Images/img_8.PNG)

-table Medecin

![This is an image](Images/img_9.PNG)

-table RendezVous

![This is an image](Images/img_10.PNG)

-table Consultation

![This is an image](Images/img_11.PNG)


###web test
 the following code

 ![This is an image](Images/img_12.PNG)

result

![This is an image](Images/img_13.PNG)






