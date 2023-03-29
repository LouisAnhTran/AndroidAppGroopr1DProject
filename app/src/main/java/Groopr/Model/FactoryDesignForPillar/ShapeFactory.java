package Groopr.Model.FactoryDesignForPillar;

public class ShapeFactory extends AbstractFactory{
    @Override
    public Pillar getProduct(String pillar,String term) {
        if(pillar.compareToIgnoreCase("CSD")==0) return new CSD(term);
//        else if(pillar.compareToIgnoreCase("EPD")==0) return new EPD(term);
        else if (pillar.compareToIgnoreCase("ESD")==0) return new ESD(term);
        return null;
    }
}
