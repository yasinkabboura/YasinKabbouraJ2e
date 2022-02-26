package metier;

import dao.Dao;

public class MetierImp implements Metier{

    private Dao dao=null;


    @Override
    public double calcul() {
        double data=dao.getData();
        double res = data;
        return res;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
}
