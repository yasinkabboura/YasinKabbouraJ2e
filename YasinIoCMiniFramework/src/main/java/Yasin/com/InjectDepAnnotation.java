package Yasin.com;


import org.burningwave.core.assembler.ComponentContainer;
import org.burningwave.core.classes.ClassCriteria;
import org.burningwave.core.classes.ClassHunter;
import org.burningwave.core.classes.SearchConfig;
import javax.management.RuntimeErrorException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class InjectDepAnnotation {

    //here we declared 2 Hashmaps one to map classes with their implemented interfaces and the other for class and the objects we created from it
    private HashMap<Class<?>, List<Class<?>>> ClassesColc;
    private HashMap<Class<?>, Object> ClassObjCol;
    //we created objects for the declared hashmaps
    public InjectDepAnnotation(String mainClass) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super();
        ClassesColc = new HashMap<>();
        ClassObjCol = new HashMap<>();
        //also we call the methode initFramework and we give it the main package name
        //this methode will be responsible on analyzing all classes and injecting all dependencies
        this.initFramework(mainClass);
    }
    //this is the methode responsible on returning the bean depending on the class name
    public <T> T getBean(Class<T> classz) {
        try {
            return (T) ClassObjCol.get(classz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //main methode that do almost all the work
    private void initFramework(String mainClass)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        //we get all the classes depending on the package path which we get from parametre
        Class<?>[] classes = getClasses(mainClass, true);
        // we use componentConatiner from burningwave to get classHunter
        ComponentContainer componentConatiner = ComponentContainer.getInstance();
        //we initiate our Class Hunter
        ClassHunter classHunter = componentConatiner.getClassHunter();
        //we replace '.' by '/' in the package so we have a right path
        String packageRelPath = mainClass.replace(".", "/");
        //here we use clas hunter methode findBy and Search config
        //so we can get all the classes that have the annotation Component
        // from the package path
        try (ClassHunter.SearchResult result = classHunter.findBy(
                SearchConfig.forResources(
                        packageRelPath
                ).by(ClassCriteria.create().allThoseThatMatch(cls -> {
                    return cls.getAnnotation(Component.class) != null;
                }))
        ))

        {
            //we get the classes from that result
            Collection<Class<?>> types = result.getClasses();
            //here we start looking for the interfaces each class is implementing and we add them to our Hashmap ClassesColc
            for (Class<?> implementationClass : types) {
                Class<?>[] interfaces = implementationClass.getInterfaces();
                //we make sure also to include the case where may the class inherit from a superClass
                Class<?> SuperClasses = implementationClass.getSuperclass();
                List<Class<?>> c = new ArrayList<>();
                //if interfaces.length == 0 means class doesn't implement any interface and we add it to the list with itself
                if (interfaces.length == 0) {
                    c.add(implementationClass);
                    ClassesColc.put(implementationClass, c);
                } else {
                    //if class has interfaces we make sure to add them all to a list and include them
                    for (Class<?> iface : interfaces) {
                        c.add(iface);

                    }
                    ClassesColc.put(implementationClass,c);
                }
                //if class have super classe we make sure to include it too
                if (SuperClasses != null) {
                    c.add(SuperClasses);
                    ClassesColc.put(implementationClass,c);
                }

            }
            //now we create objects from all found classes that have annotation Component
            for (Class<?> classz : classes) {
                if (classz.isAnnotationPresent(Component.class)) {
                    Object classInstance = classz.newInstance();
                    ClassObjCol.put(classz, classInstance);
                    //we use the methode autowire of AutoWireInjector class to inject the needed dependency
                    //we pass an object of this class because we will need a methode from it
                    //also the instance and it class
                    AutoWireInjector.autowire(this, classz, classInstance);
                }
            }
        };

    }
    //this methode is similare to what we did before with class hunter
    //only this time it return all found classes no search criteria placed
    public Class<?>[] getClasses(String packageName, boolean recursive) throws ClassNotFoundException, IOException {
        ComponentContainer componentConatiner = ComponentContainer.getInstance();
        ClassHunter classHunter = componentConatiner.getClassHunter();
        String packageRelPath = packageName.replace(".", "/");
        SearchConfig config = SearchConfig.forResources(
                packageRelPath
        );
        if (!recursive) {
            config.findInChildren();
        }

        try (ClassHunter.SearchResult result = classHunter.findBy(config)) {
            Collection<Class<?>> classes = result.getClasses();
            return classes.toArray(new Class[classes.size()]);
        }
    }

    //this methode return an instance of a bean that ready to get injected to a field
    public <T> T getBeanInstance(Class<T> interfaceClass)
            throws InstantiationException, IllegalAccessException {
        //first we use getImplimentationClass to get the Implimentation interface or superclass
        Class<?> implementationClass = getImplimentationClass(interfaceClass);
        //then based on it we search our hashmap ClassObjCol for object from that class to return
        // if we didnt find it we create one and we add it to the map before returning it
        if (ClassObjCol.containsKey(implementationClass)) {
            return (T) ClassObjCol.get(implementationClass);
        }
        synchronized (ClassObjCol) {
            Object service = implementationClass.newInstance();
            ClassObjCol.put(implementationClass, service);
            return (T) service;
        }
    }
    //this methode search the ClassesColc  all the interfaces or superclass until we found one that match our param class
    //this means that after autowire findout the type of the field
    //we use that type which probably an interface
    //we use it to search for the drived class so we return if to get injected
    private Class<?> getImplimentationClass(Class<?> interfaceClass) {
        //we get a set of filtred ClassesColc searching each map set for our class
        Set<Map.Entry<Class<?>, List<Class<?>>>>implementationClasses = ClassesColc.entrySet().stream()
                .filter(entry -> {
                    for (Class C:entry.getValue()) {
                        if(C==interfaceClass){
                            return true;
                        }

                    }
                    return false;
                }).collect(Collectors.toSet());
        //if not match found we throw an error that no implementation found
        //this means that we declared an object of interface, and we have no class to inject their
        if (implementationClasses == null || implementationClasses.size() == 0) {
            throw new RuntimeErrorException(new Error("no implementation found for interface " + interfaceClass.getName()));
        } else {
            //here we return the first one from the set which is the right match silly will be only one since our map
            //use the class name as a key
            Optional<Map.Entry<Class<?>, List<Class<?>>>> optional = implementationClasses.stream().findFirst();

            if (optional.isPresent()) {
                return optional.get().getKey();
            }
        }
        return null;
    }
}
