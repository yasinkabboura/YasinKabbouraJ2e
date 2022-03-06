#Dependency injection Framework Test
first our project structure is a presentation.dao package and a presentation.metier package

[Framework Repository ](https://github.com/yasinkabboura/YasinKabbouraJ2e/tree/main/YasinIoCMiniFramework)

##Project Structure

firstly we need to add our Framework to our Libraries in the project structure

![This is an image](Images/1.PNG)


###Dao package structure

![This is an image](Images/2.PNG)

####Dao interface

![This is an image](Images/3.PNG)

####DaoImp Class

![This is an image](Images/4.PNG)

###Metier package structure

![This is an image](Images/5.PNG)

####Metier interface

![This is an image](Images/6.PNG)

####MetierImp Class

![This is an image](Images/7.PNG)

###Presentation Package

####Presentation Class

![This is an image](Images/8.PNG)


#Test XML file injection

##XML Config File (proberty)

![This is an image](Images/9.PNG)

![This is an image](Images/10.PNG)


#Test XML file injection

##XML Config File (constructor-arg)

here we used the constructor to inject the dependency
![This is an image](Images/11.PNG)

![This is an image](Images/12.PNG)

#Test Annotaion

we add the @Component to each class
then we add  @Autowired to each field

![This is an image](Images/13.PNG)

![This is an image](Images/15.PNG)


we made sure that we change the main method also
![This is an image](Images/16.PNG)







