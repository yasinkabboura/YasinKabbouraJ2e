#Dependency Injection FrameWork

this framework is for dependency injection based on Spring Logic 
on ApplicationContext 
the framework support XML file injection
also Annotation Injection

###1.XML file injection

##Requirements
you have to write an XML config File with all Classes that need to be Injected 
respect the following format

####1. based on setter (proberty)
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN 2.0//EN"
    "http://www.springframework.org/dtd/spring-beans-2.0.dtd" >
    <beans>
    <bean id="id1" class="package1.Class1"></bean>
    <bean id="id2" class="package2.Class2">
    <property name="Variable1" ref="id1"></property>
    </bean>
    </beans>
```
##How to Use
in your main class 
use the following syntax 
```java
InjectDep context = new InjectDep("config.xml");
```
after that you can use the getBean method to get beans dont forget to cast it
```java
Metier m = (MetierImp) context.getBean("metier");
```

####2. based on constructor (constructor-arg)

```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN 2.0//EN"
    "http://www.springframework.org/dtd/spring-beans-2.0.dtd" >
<Beans>
<bean id="id1" class="package1.Class1"></bean>
<bean id="id2" class="package2.Class2">
    <constructor-arg index="0" value="id1"/>
</bean>
</Beans>
```
for each argument of your constructor 
you have to create a constructor-arg tag
make sure to pay attention to the index whixh indecate the order of the
argument
##How to Use
in your main class use same syntxe as before
use the following syntax
```java
InjectDep context = new InjectDep("config.xml");
```
after that you can use the getBean method to get beans dont forget to cast it
```java
Metier m = (MetierImp) context.getBean("metier");
```

###1.Annotation injection
here you have no requirement or external files
you have to add @Component annotation to your classes

```java
@Component
public class Person {}
```

and when ever you have a field in a class which is type is one of your classes 
make sure to add the annotation @Autowired

```java
@Autowired
public Car car1;
```

and in you main class you have initiate an object of InjectDepAnnotation
and pass to the constructor the name of the package that holds all your subpackages and classes

```java
InjectDepAnnotation context = new InjectDepAnnotation("Test");
```
and after that you can use context variable to get you beans 
just make sure to passe to the getBean method the class name
and no casting needed in this case

```java
Person P = context.getBean(Person.class);
```






