package dao;

import org.springframework.stereotype.Component;

@Component
public class DaoImp implements Dao {

    @Override
    public double getData() {
        double temp= Math.random()*40;

        return temp;
    }
}
