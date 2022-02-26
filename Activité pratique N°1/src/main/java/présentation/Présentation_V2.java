package présentation;

import dao.Dao;
import metier.Metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Présentation_V2 {




    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Scanner scanner=new Scanner(new File("config.txt"));
       String daoClassName=scanner.nextLine();
        Class cDao=Class.forName(daoClassName);
        Dao dao= (Dao) cDao.newInstance();

        String metierClassName=scanner.next();
        Class cmetier=Class.forName(metierClassName);
        Metier metier=(Metier) cmetier.newInstance();
        Method meth=cmetier.getMethod("setDao",Dao.class);
        meth.invoke(metier,dao);
        System.out.println(metier.calcul());

    }}
