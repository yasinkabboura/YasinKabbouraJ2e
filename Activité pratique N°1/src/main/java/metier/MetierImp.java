package metier;

import dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetierImp implements Metier{
    @Autowired
    private Dao dao ;
    @Override
    public double calcul() {
        double data=dao.getData();
        double res=data*223*Math.cos(data);
        return res;

    }
    public void setDao(Dao dao) {
        this.dao = dao;
    }
}
