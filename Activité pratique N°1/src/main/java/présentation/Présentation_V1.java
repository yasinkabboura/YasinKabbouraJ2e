package présentation;

import dao.DaoImp;
import metier.MetierImp;

public class Présentation_V1 {
        public static void main(String[] args) {
            MetierImp metier=new MetierImp();
            DaoImp dao=new DaoImp();
            metier.setDao(dao);
            System.out.println(metier.calcul());
        }
}
