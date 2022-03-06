package Yasin.com;


import org.burningwave.core.classes.FieldCriteria;

import java.lang.reflect.Field;
import java.util.Collection;

import static org.burningwave.core.assembler.StaticComponentContainer.Fields;

public class AutoWireInjector {
    private AutoWireInjector() {
        super();
    }

    //this methode we use the concept of reflect field
    public static void autowire(InjectDepAnnotation InjectDepAnnotation, Class<?> classz, Object classInstance)
    //here we get the an instance of InjectDepAnnotation and the class and its instance
            throws InstantiationException, IllegalAccessException {
        //we extract all the fileds in the class param that have the annotation Autowired
        Collection<Field> fields = Fields.findAllAndMakeThemAccessible(
                FieldCriteria.forEntireClassHierarchy().allThoseThatMatch(field ->
                        field.isAnnotationPresent(Autowired.class)
                ),
                classz
        );
        //we iterate each field
        for (Field field : fields) {
            //we pass the field type to getBeanInstance which will return an instance of the class which implement this type
            Object fieldInstance = InjectDepAnnotation.getBeanInstance(field.getType());
            //here we inject that instance to the field
            Fields.setDirect(classInstance, field, fieldInstance);
            //we use the recursive concept to make sure when the injected class have its own dependencies
            autowire(InjectDepAnnotation, fieldInstance.getClass(), fieldInstance);
        }
    }

}

