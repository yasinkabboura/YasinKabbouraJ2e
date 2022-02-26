package présentation;
import Yasin.com.InjectDep;
import metier.Metier;
import metier.MetierImp;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
public class Presentation {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParserConfigurationException, InvocationTargetException, SAXException, NoSuchMethodException {
        InjectDep context = new InjectDep("config.xml");
        Metier m = (MetierImp) context.getBean("metier");
        System.out.println(m.calcul());
    }}
