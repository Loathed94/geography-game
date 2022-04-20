import javax.swing.*;

public class CountryButton extends JButton {
    private final String countryName;

    public CountryButton(String name, int x, int y, int width, int height){
        this.countryName = name;
        setBounds(x, y, width, height);
    }

    public boolean isCorrectCountry(String other){
        return countryName.toLowerCase().equals(other.toLowerCase());
    }

    public String getCountryName(){return this.countryName;}

    @Override
    public String getName(){return this.countryName;}

    @Override
    public int hashCode(){
        return this.countryName.hashCode();
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof CountryButton){
            return ((CountryButton) other).isCorrectCountry(this.countryName);
        }else{
            return false;
        }
    }
}
