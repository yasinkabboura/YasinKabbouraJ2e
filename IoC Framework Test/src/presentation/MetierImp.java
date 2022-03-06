package presentation;

import Yasin.com.Autowired;
import Yasin.com.Component;

@Component
public class MetierImp implements Metier{

    @Autowired
    private Dao dao;

    public MetierImp() {
    }

    public MetierImp(Dao dao) {
        this.dao = dao;
    }

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
