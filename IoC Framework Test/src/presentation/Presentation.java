package presentation;
import Yasin.com.InjectDep;
import Yasin.com.InjectDepAnnotation;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
public class Presentation {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParserConfigurationException, InvocationTargetException, SAXException, NoSuchMethodException {

        InjectDepAnnotation context = new InjectDepAnnotation("presentation");
        Metier m = (Metier) context.getBean(MetierImp.class);
        System.out.println("******************************************************************************************");
        System.out.println(m.calcul());
        System.out.println("******************************************************************************************");
    }}
