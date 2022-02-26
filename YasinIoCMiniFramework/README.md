#Dependency Injection FrameWork

this framework is for dependency injection based on Spring Logic 
on ApplicationContext to be specific and no constructor injection is available for
the moment
##Requirements
you have to write an XML config File with all Classes that need to be Injected 
respect the following format
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



