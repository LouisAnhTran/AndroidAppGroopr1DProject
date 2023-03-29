package Groopr.Model.FactoryDesignForPillar;

public class ShapeFactory extends AbstractFactory{
    @Override
    public Pillar getProduct(String s) {
        if(s.compareToIgnoreCase("CSD")==0) return new CSD();
        else if(s.compareToIgnoreCase("EPD")==0) return new EPD();
        else if (s.compareToIgnoreCase("ESD")==0) return new ESD();
        return null;
    }
}
