package présentation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import dao.DaoImp;
import metier.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class PresentationAvecSpring {
    public static void main(String[] args) {
     ApplicationContext ctx=new AnnotationConfigApplicationContext("dao","metier");
        //ApplicationContext ctx=new ClassPathXmlApplicationContext("app.xml");
        Metier metier=ctx.getBean(Metier.class);
        System.out.println(metier.calcul());
    }
}
