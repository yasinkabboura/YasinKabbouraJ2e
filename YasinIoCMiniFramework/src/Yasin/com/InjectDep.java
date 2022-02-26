package Yasin.com;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InjectDep {
    String FileName;
    File file;
    HashMap<String, Object> Objects = new HashMap<String, Object>();

    public InjectDep(String fileName) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        FileName = fileName;
        file = new File(fileName);
        //prepare reading from XML file
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        //list of all nodes (tags)
        NodeList nodeList = doc.getElementsByTagName("bean");
        //iterate nodes
        for (int itr = 0; itr < nodeList.getLength(); itr++)
        {
            //take a node from the list make sure its a node element then start processing it
            Node node = nodeList.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                //cast node to an element
                Element eElement = (Element) node;
                //extract the class name and its id
                String ObName = eElement.getAttribute("class");
                String ObId = eElement.getAttribute("id");
                //create a class from the name extracted
                Class instance =Class.forName(ObName);
                //create an object from that class
                Object ObjectInstance =  instance.newInstance();
                //add that object to a hash map where key is the id extracted from the element node and the value is the object created
                Objects.put(ObId,ObjectInstance);

                //properties
                //now after creating the object we make sure that all dependencies of that object are created relaying on setter Methods
                //we extract a list of nodes with tag name property presented in the current node element we are processing
                NodeList nodeList2 = ((Element) node).getElementsByTagName("property") ;
                //check the list if its empty it means no property is present if its not empty we start process
                if(nodeList2.getLength() > 0){
                    //we iterate list of nodes like before
                    for (int itr2 = 0; itr2 < nodeList2.getLength(); itr2++)
                    {
                        //we take a node from the list we make sure its a node element like before
                        Node node2 = nodeList2.item(itr2);
                        if (node.getNodeType() == Node.ELEMENT_NODE)
                        {
                            //we cast node to an element
                            Element eElement2 = (Element) node2;
                            //we extract the name of the declared variable which will hold the instance as also the id of the class on which it depend
                            String ProbName = eElement2.getAttribute("name");
                            //make sure that the setter methode is written in Camel Case
                            ProbName = ProbName.substring(0,1).toUpperCase() + ProbName.substring(1).toLowerCase();
                            //the ref id is a reference to the already created object from this class
                            String ProbObject = eElement2.getAttribute("ref");
                            //the getMethode methode is not suitable for this process because it cant take Interface as a parameter
//                            Method meth=instance.getMethod("set"+ProbName,(Objects.get(ProbObject)).getClass());
                            //Use custom find Methode which gets the setter methode that we will invoke to inject the dependency
                            //we gave it the current object instance and the name of the methode which set+Name of the variable plus the type of class of the object it will hold
                            Method meth=findMethod(ObjectInstance,"set"+ProbName,(Objects.get(ProbObject)).getClass());
                            //invoke the methode with the current object instance and the right object from the hash map we extracted based on ref from node element
                            meth.invoke(ObjectInstance,Objects.get(ProbObject));
                        }}
                }

            }
        }


        }
        //methode that return the object based on its id we extract it and then return it
        public Object getBean(String BeanName){
            return Objects.get(BeanName);
        }

        //customer methode to help find the methode we are looking for as a setter
    //methode receive 3 parameters an Object on which we will call the methode(setter) the name of the methode and the type of the parameter
    public static Method findMethod(Object m, String methodName, Class DepType) throws NoSuchMethodException {
        //get the class from the object then extract al declared methods from that class on a array
        Method[] metody = m.getClass().getDeclaredMethods();
        //list which we will store all methods from that array that match our methode in Name
        List<Method> sameNames = new ArrayList<Method>();
        // start filtring based on the Name
        for (Method meth : metody) {
            //if names from the array and the methode parameter ar equals we add it to the list
            if (meth.getName().equals(methodName)) {
                sameNames.add(meth);
            }
        }
        // check if we find any methods of the same name or Not if we didnt we throw an exception of NoSuchMethodException
        if (sameNames.isEmpty()) {
            throw new NoSuchMethodException();
        } else {
            // now we have a list of all methodes of the same Name
            //based on this list we have to find all methods with the same number of parameters
            //this list will hold those methods
            List<Method> sameCountOfParameters = new ArrayList<Method>();
            //because we rely on setter methods which usually have one parameter we test only for one param
            /* in the future we can change this if we have only one methode to inject all dependencies so we can pass a list to this methode and work on how
            * many params we want
            * */
            for (Method meth : sameNames) {
                //if they have they same number of parameters we add it to the list
                if (meth.getParameterTypes().length == 1) {
                    sameCountOfParameters.add(meth);
                }
            }
            // check if we find any methods of the same number of params or Not if we didnt we throw an exception of NoSuchMethodException
            if (sameCountOfParameters.isEmpty()) {
                throw new NoSuchMethodException();
            } else {
                //now we have a list of the methods of the same name and the same number of params
                // we iterate this list to find the methode that type of the params match the type of params we need
                for (Method meth : sameCountOfParameters) {
                    // get a params types into this list
                    /** note that we only have one param but getParameterTypes() returns a list so we will try to work as we have more than one
                     * param
                     * **/
                    Class<?>[] params = meth.getParameterTypes();
                    //this boolean we help us keep track the parameters types and if we find the right methode
                    boolean good = true;
                    for (int i = 0; i < params.length && good; i++) {
                        /*here we check if the parameter passed to the methode is an interface then
                        * we get all the interfaces that our type is implementing and chick if one of them
                        * is the interface we have in params[i]
                        * if its true means that this methode have the interface stored in params[i] as one of the params
                        * we keep iterating other params
                        * */
                        if (params[i].isInterface() && Arrays.asList(DepType.getInterfaces()).contains(params[i])) {
                            good = true;
                            continue;
                        } else {
                            /*
                            * here if the first test failed we check maybe the param in the methode is type of superClass
                            * which mean maybe a type of class which another class inherit from
                            * we may encounter this situation in our framework, so we have to include it
                            * */
                            if (DepType.getSuperclass().equals(params[i])) {
                                good = true;
                                continue;
                            }
                            /*
                            * here finally we check the usual case if the param is type of class
                            * */
                            else if(DepType.equals(params[i])){
                                good = true;
                                continue;
                            }
                        }
                        //if we are here means none of the above conditions is true means this in not the right methode
                        good = false;
                    }
                    //after the end of iterating we check the boolean good its true means we have the right method
                    if (good) {
                        return meth;
                    }
                }
            }
        }
        //if the boolean good is false we throw an exception of NoSuchMethodException
        throw new NoSuchMethodException();
    }

}
