package Groopr.Model.FactoryDesignForPillar;

import android.graphics.drawable.AnimatedStateListDrawable;

public class ShapeFactory extends AbstractFactory{
    @Override
    public Pillar getProduct(String pillar,String term) {
        if(pillar.compareToIgnoreCase("CSD")==0) return new CSD(term);
        else if(pillar.compareToIgnoreCase("EPD")==0) return new EPD(term);
        else if (pillar.compareToIgnoreCase("ESD")==0) return new ESD(term);
        else if (pillar.compareToIgnoreCase("DAI")==0) return new DAI(term);
        else if(pillar.compareToIgnoreCase("ASD")==0) return new ASD(term);
        return null;
    }
}
