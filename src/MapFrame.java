import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MapFrame extends JFrame {
    private static BufferedImage mapImage;
    static {
        try {
            mapImage = ImageIO.read(new File(".\\resources\\europe5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final JLayeredPane layeredPane = new JLayeredPane();
    private String currentCountry = "";
    private Queue<String> countryQueue = new LinkedList<>();
    private final JLabel currentCountryLabel = new JLabel("Select country: ");
    private final JButton resetButton = new JButton("RESET");
    private final JButton menuButton = new JButton("Menu");
    private int correctGuesses = 0;
    private int totalGuesses = 0;
    private final JLabel guessesLabel = new JLabel("Correct guesses: "+correctGuesses+" out of "+totalGuesses+" attempts.");

    public MapFrame(){
        setSize(180, 320);
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
        layeredPane.setBounds(0, 0, 180, 320);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpBackground();
        //seedEuropeanButtons();
        setUpLabels();
        generateResetButton();
        setupMenuButton();
        generateContinentButtons();
        setVisible(true);
    }

    private void setUpBackground(){
        JPanel backgroundPane = new JPanel();
        backgroundPane.setBounds(0, 0, 180, 320);
        //JLabel pic = new JLabel(new ImageIcon(mapImage));
        //backgroundPane.add(pic);
        backgroundPane.setOpaque(true);
        layeredPane.add(backgroundPane, 0, 0);
    }

    private void setUpLabels(){
        currentCountryLabel.setBounds(180, 30, 300, 50);
        guessesLabel.setBounds(180, 1, 300, 50);
        layeredPane.add(currentCountryLabel, 2, 0);
        layeredPane.add(guessesLabel, 3, 0);
        currentCountryLabel.setVisible(false);
        guessesLabel.setVisible(false);
    }

    private void generateResetButton(){
        resetButton.setBounds(630, 10, 80, 40);
        resetButton.addMouseListener(new clickReset());
        layeredPane.add(resetButton, 4, 0);
        resetButton.setVisible(false);
    }

    private void generateContinentButtons(){
        int buttonWidth = 120;
        int buttonHeight = 40;

        JButton africa = new JButton("Play Africa");
        africa.setName("Africa");
        africa.setBounds(20, 20, buttonWidth, buttonHeight);
        africa.addMouseListener(new switchContinent());
        layeredPane.add(africa, 5, 1);

        JButton europe = new JButton("Play Europe");
        europe.setName("Europe");
        europe.setBounds(20, 70, buttonWidth, buttonHeight);
        europe.addMouseListener(new switchContinent());
        //europe.setVisible(false);
        layeredPane.add(europe, 5, 2);

        JButton asia = new JButton("Play Asia");
        asia.setName("Asia");
        asia.setBounds(20, 120, buttonWidth, buttonHeight);
        asia.addMouseListener(new switchContinent());
        layeredPane.add(asia, 5, 3);

        JButton states = new JButton("Play US States");
        states.setName("States");
        states.setBounds(20, 170, buttonWidth, buttonHeight);
        states.addMouseListener(new switchContinent());
        layeredPane.add(states, 5, 4);

        JButton sweden = new JButton("Play Sweden");
        sweden.setName("Sweden");
        sweden.setBounds(20, 220, buttonWidth, buttonHeight);
        sweden.addMouseListener(new switchContinent());
        layeredPane.add(sweden, 5, 5);
    }

    private void setupMenuButton(){
        menuButton.setBounds(10, 10, 80, 40);
        menuButton.setName("Menu");
        menuButton.addMouseListener(new switchContinent());
        layeredPane.add(menuButton, 4, 1);
        menuButton.setVisible(false);
    }

    private void seedEuropeanButtons(){
        countryQueue = new LinkedList<>();
        CountryButton iceland = new CountryButton("Iceland", 135, 60, 20, 20);
        iceland.addMouseListener(new clickCountry());
        layeredPane.add(iceland, 1, 0);
        countryQueue.add("Iceland");
        CountryButton norway = new CountryButton("Norway", 290, 180, 20, 20);
        norway.addMouseListener(new clickCountry());
        layeredPane.add(norway, 1, 1);
        countryQueue.add("Norway");
        CountryButton sweden = new CountryButton("Sweden", 330, 200, 20, 20);
        sweden.addMouseListener(new clickCountry());
        layeredPane.add(sweden, 1, 2);
        countryQueue.add("Sweden");
        CountryButton finland = new CountryButton("Finland", 400, 160, 30, 30);
        finland.addMouseListener(new clickCountry());
        layeredPane.add(finland, 1, 3);
        countryQueue.add("Finland");
        CountryButton russia = new CountryButton("Russia", 550, 190, 60, 40);
        russia.addMouseListener(new clickCountry());
        layeredPane.add(russia, 1, 4);
        countryQueue.add("Russia");
        CountryButton ukraine = new CountryButton("Ukraine", 480, 340, 50, 40);
        ukraine.addMouseListener(new clickCountry());
        layeredPane.add(ukraine, 1, 5);
        countryQueue.add("Ukraine");
        CountryButton belarus = new CountryButton("Belarus", 435, 290, 30, 30);
        belarus.addMouseListener(new clickCountry());
        layeredPane.add(belarus, 1, 6);
        countryQueue.add("Belarus");
        CountryButton lithuania = new CountryButton("Lithuania", 405, 270, 15, 15);
        lithuania.addMouseListener(new clickCountry());
        layeredPane.add(lithuania, 1, 7);
        countryQueue.add("Lithuania");
        CountryButton latvia = new CountryButton("Latvia", 415, 245, 15, 15);
        latvia.addMouseListener(new clickCountry());
        layeredPane.add(latvia, 1, 8);
        countryQueue.add("Latvia");
        CountryButton estonia = new CountryButton("Estonia", 413, 218, 15, 15);
        estonia.addMouseListener(new clickCountry());
        layeredPane.add(estonia, 1, 9);
        countryQueue.add("Estonia");
        CountryButton poland = new CountryButton("Poland", 345, 300, 50, 50);
        poland.addMouseListener(new clickCountry());
        layeredPane.add(poland, 1, 10);
        countryQueue.add("Poland");
        CountryButton germany = new CountryButton("Germany", 270, 305, 40, 40);
        germany.addMouseListener(new clickCountry());
        layeredPane.add(germany, 1, 11);
        countryQueue.add("Germany");
        CountryButton denmark = new CountryButton("Denmark", 280, 250, 20, 20);
        denmark.addMouseListener(new clickCountry());
        layeredPane.add(denmark, 1, 12);
        countryQueue.add("Denmark");
        CountryButton netherlands = new CountryButton("The Netherlands", 240, 300, 15, 15);
        netherlands.addMouseListener(new clickCountry());
        layeredPane.add(netherlands, 1, 13);
        countryQueue.add("The Netherlands");
        CountryButton belgium = new CountryButton("Belgium", 225, 325, 15, 15);
        belgium.addMouseListener(new clickCountry());
        layeredPane.add(belgium, 1, 14);
        countryQueue.add("Belgium");
        CountryButton britain = new CountryButton("Great Britain", 182, 265, 15, 50);
        britain.addMouseListener(new clickCountry());
        layeredPane.add(britain, 1, 15);
        countryQueue.add("Great Britain");
        CountryButton ireland = new CountryButton("Ireland", 125, 255, 20, 20);
        ireland.addMouseListener(new clickCountry());
        layeredPane.add(ireland, 1, 16);
        countryQueue.add("Ireland");
        CountryButton france = new CountryButton("France", 175, 350, 50, 75);
        france.addMouseListener(new clickCountry());
        layeredPane.add(france, 1, 17);
        countryQueue.add("France");
        CountryButton spain = new CountryButton("Spain", 100, 430, 40, 50);
        spain.addMouseListener(new clickCountry());
        layeredPane.add(spain, 1, 18);
        countryQueue.add("Spain");
        CountryButton portugal = new CountryButton("Portugal", 45, 450, 20, 20);
        portugal.addMouseListener(new clickCountry());
        layeredPane.add(portugal, 1, 19);
        countryQueue.add("Portugal");
        CountryButton italy = new CountryButton("Italy", 280, 450, 30, 30);
        italy.addMouseListener(new clickCountry());
        layeredPane.add(italy, 1, 20);
        countryQueue.add("Italy");
        CountryButton switzerland = new CountryButton("Switzerland", 245, 390, 18, 15);
        switzerland.addMouseListener(new clickCountry());
        layeredPane.add(switzerland, 1, 21);
        countryQueue.add("Switzerland");
        CountryButton austria = new CountryButton("Austria", 312, 382, 20, 20);
        austria.addMouseListener(new clickCountry());
        layeredPane.add(austria, 1, 22);
        countryQueue.add("Austria");
        CountryButton czech = new CountryButton("Czechia", 315, 349, 20, 20);
        czech.addMouseListener(new clickCountry());
        layeredPane.add(czech, 1, 23);
        countryQueue.add("Czechia");
        CountryButton slovakia = new CountryButton("Slovakia", 360, 370, 15, 15);
        slovakia.addMouseListener(new clickCountry());
        layeredPane.add(slovakia, 1, 24);
        countryQueue.add("Slovakia");
        CountryButton hungary = new CountryButton("Hungary", 350, 395, 35, 15);
        hungary.addMouseListener(new clickCountry());
        layeredPane.add(hungary, 1, 25);
        countryQueue.add("Hungary");
        CountryButton slovenia = new CountryButton("Slovenia", 317, 410, 10, 10);
        slovenia.addMouseListener(new clickCountry());
        layeredPane.add(slovenia, 1, 26);
        countryQueue.add("Slovenia");
        CountryButton croatia = new CountryButton("Croatia", 335, 415, 10, 10);
        croatia.addMouseListener(new clickCountry());
        layeredPane.add(croatia, 1, 27);
        countryQueue.add("Croatia");
        CountryButton bosnia = new CountryButton("Bosnia and Herzegovina", 345, 435, 15, 15);
        bosnia.addMouseListener(new clickCountry());
        layeredPane.add(bosnia, 1, 31);
        countryQueue.add("Bosnia and Herzegovina");
        CountryButton serbia = new CountryButton("Serbia", 375, 430, 15, 25);
        serbia.addMouseListener(new clickCountry());
        layeredPane.add(serbia, 1, 28);
        countryQueue.add("Serbia");
        CountryButton montenegro = new CountryButton("Montenegro", 362, 460, 10, 10);
        montenegro.addMouseListener(new clickCountry());
        layeredPane.add(montenegro, 1, 29);
        countryQueue.add("Montenegro");
        CountryButton kosovo = new CountryButton("Kosovo", 382, 462, 10, 10);
        kosovo.addMouseListener(new clickCountry());
        layeredPane.add(kosovo, 1, 30);
        countryQueue.add("Kosovo");
        CountryButton albania = new CountryButton("Albania", 372, 475, 10, 25);
        albania.addMouseListener(new clickCountry());
        layeredPane.add(albania, 1, 31);
        countryQueue.add("Albania");
        CountryButton macedonia = new CountryButton("North Macedonia", 388, 475, 15, 15);
        macedonia.addMouseListener(new clickCountry());
        layeredPane.add(macedonia, 1, 32);
        countryQueue.add("North Macedonia");
        CountryButton greece = new CountryButton("Greece", 388, 505, 20, 20);
        greece.addMouseListener(new clickCountry());
        layeredPane.add(greece, 1, 33);
        countryQueue.add("Greece");
        CountryButton bulgaria = new CountryButton("Bulgaria", 410, 455, 40, 20);
        bulgaria.addMouseListener(new clickCountry());
        layeredPane.add(bulgaria, 1, 34);
        countryQueue.add("Bulgaria");
        CountryButton romania = new CountryButton("Romania", 400, 400, 50, 40);
        romania.addMouseListener(new clickCountry());
        layeredPane.add(romania, 1, 35);
        countryQueue.add("Romania");
        CountryButton moldova = new CountryButton("Moldova", 455, 385, 15, 15);
        moldova.addMouseListener(new clickCountry());
        layeredPane.add(moldova, 1, 36);
        countryQueue.add("Moldova");
        CountryButton turkey = new CountryButton("Turkey", 500, 490, 80, 40);
        turkey.addMouseListener(new clickCountry());
        layeredPane.add(turkey, 1, 37);
        countryQueue.add("Turkey");
        CountryButton cyprus = new CountryButton("Cyprus", 535, 565, 10, 10);
        cyprus.addMouseListener(new clickCountry());
        layeredPane.add(cyprus, 1, 38);
        countryQueue.add("Cyprus");
        CountryButton azerbaijan = new CountryButton("Azerbaijan", 680, 438, 15, 15);
        azerbaijan.addMouseListener(new clickCountry());
        layeredPane.add(azerbaijan, 1, 39);
        countryQueue.add("Azerbaijan");
        CountryButton armenia = new CountryButton("Armenia", 647, 450, 15, 15);
        armenia.addMouseListener(new clickCountry());
        layeredPane.add(armenia, 1, 40);
        countryQueue.add("Armenia");
        CountryButton georgia = new CountryButton("Georgia", 620, 430, 30, 15);
        georgia.addMouseListener(new clickCountry());
        layeredPane.add(georgia, 1, 41);
        countryQueue.add("Georgia");
        shuffleQueue();
    }

    private void seedAfricanButtons(){
        countryQueue = new LinkedList<>();
        CountryButton senegal = new CountryButton("Senegal", 35, 215, 15, 15);
        senegal.addMouseListener(new clickCountry());
        layeredPane.add(senegal, 1, 0);
        countryQueue.add(senegal.getName());
        CountryButton mauritania = new CountryButton("Mauritania", 40, 170, 70, 30);
        mauritania.addMouseListener(new clickCountry());
        layeredPane.add(mauritania, 1, 1);
        countryQueue.add(mauritania.getName());
        CountryButton westernSahara = new CountryButton("Western Sahara", 45, 140, 15, 25);
        westernSahara.addMouseListener(new clickCountry());
        layeredPane.add(westernSahara, 1, 2);
        countryQueue.add(westernSahara.getName());
        CountryButton morocco = new CountryButton("Morocco", 120, 55, 25, 25);
        morocco.addMouseListener(new clickCountry());
        layeredPane.add(morocco, 1, 3);
        countryQueue.add(morocco.getName());
        CountryButton algiers = new CountryButton("Algiers", 180, 55, 50, 80);
        algiers.addMouseListener(new clickCountry());
        layeredPane.add(algiers, 1, 4);
        countryQueue.add(algiers.getName());
        CountryButton tunisia = new CountryButton("Tunisia", 250, 40, 15, 15);
        tunisia.addMouseListener(new clickCountry());
        layeredPane.add(tunisia, 1, 5);
        countryQueue.add(tunisia.getName());
        CountryButton libya = new CountryButton("Libya", 280, 90, 90, 50);
        libya.addMouseListener(new clickCountry());
        layeredPane.add(libya, 1, 6);
        countryQueue.add(libya.getName());
        CountryButton egypt = new CountryButton("Egypt", 400, 90, 50, 50);
        egypt.addMouseListener(new clickCountry());
        layeredPane.add(egypt, 1, 7);
        countryQueue.add(egypt.getName());
        CountryButton sudan = new CountryButton("Sudan", 400, 170, 80, 60);
        sudan.addMouseListener(new clickCountry());
        layeredPane.add(sudan, 1, 8);
        countryQueue.add(sudan.getName());
        CountryButton chad = new CountryButton("Chad", 320, 180, 40, 70);
        chad.addMouseListener(new clickCountry());
        layeredPane.add(chad, 1, 9);
        countryQueue.add(chad.getName());
        CountryButton niger = new CountryButton("Niger", 250, 170, 40, 50);
        niger.addMouseListener(new clickCountry());
        layeredPane.add(niger, 1, 10);
        countryQueue.add(niger.getName());
        CountryButton mali = new CountryButton("Mali", 140, 170, 40, 40);
        mali.addMouseListener(new clickCountry());
        layeredPane.add(mali, 1, 11);
        countryQueue.add(mali.getName());
        CountryButton guinea = new CountryButton("Guinea", 63, 253, 30, 10);
        guinea.addMouseListener(new clickCountry());
        layeredPane.add(guinea, 1, 12);
        countryQueue.add(guinea.getName());
        CountryButton guineaBissau = new CountryButton("Guinea-Bissau", 43, 243, 10, 10);
        guineaBissau.addMouseListener(new clickCountry());
        layeredPane.add(guineaBissau, 1, 13);
        countryQueue.add(guineaBissau.getName());
        CountryButton sierraLeone = new CountryButton("Sierra Leone", 67, 270, 15, 15);
        sierraLeone.addMouseListener(new clickCountry());
        layeredPane.add(sierraLeone, 1, 14);
        countryQueue.add(sierraLeone.getName());
        CountryButton liberia = new CountryButton("Liberia", 87, 288, 15, 15);
        liberia.addMouseListener(new clickCountry());
        layeredPane.add(liberia, 1, 15);
        countryQueue.add(liberia.getName());
        CountryButton ivoryCoast = new CountryButton("Ivory Coast", 115, 270, 30, 30);
        ivoryCoast.addMouseListener(new clickCountry());
        layeredPane.add(ivoryCoast, 1, 16);
        countryQueue.add(ivoryCoast.getName());
        CountryButton ghana = new CountryButton("Ghana", 160, 260, 15, 40);
        ghana.addMouseListener(new clickCountry());
        layeredPane.add(ghana, 1, 17);
        countryQueue.add(ghana.getName());
        CountryButton burkinaFaso = new CountryButton("Burkina Faso", 155, 233, 20, 20);
        burkinaFaso.addMouseListener(new clickCountry());
        layeredPane.add(burkinaFaso, 1, 18);
        countryQueue.add(burkinaFaso.getName());
        CountryButton togo = new CountryButton("Togo", 180, 270, 10, 10);
        togo.addMouseListener(new clickCountry());
        layeredPane.add(togo, 1, 19);
        countryQueue.add(togo.getName());
        CountryButton benin = new CountryButton("Benin", 190, 255, 15, 15);
        benin.addMouseListener(new clickCountry());
        layeredPane.add(benin, 1, 20);
        countryQueue.add(benin.getName());
        CountryButton nigeria = new CountryButton("Nigeria", 215, 245, 55, 40);
        nigeria.addMouseListener(new clickCountry());
        layeredPane.add(nigeria, 1, 21);
        countryQueue.add(nigeria.getName());
        CountryButton cameroon = new CountryButton("Cameroon", 270, 300, 30, 30);
        cameroon.addMouseListener(new clickCountry());
        layeredPane.add(cameroon, 1, 22);
        countryQueue.add(cameroon.getName());
        CountryButton equatorialGuinea = new CountryButton("Equatorial Guinea", 263, 333, 10, 10);
        equatorialGuinea.addMouseListener(new clickCountry());
        layeredPane.add(equatorialGuinea, 1, 23);
        countryQueue.add(equatorialGuinea.getName());
        CountryButton gabon = new CountryButton("Gabon", 265, 348, 20, 20);
        gabon.addMouseListener(new clickCountry());
        layeredPane.add(gabon, 1, 24);
        countryQueue.add(gabon.getName());
        CountryButton congoBrazzaville = new CountryButton("Congo-Brazzaville", 305, 340, 20, 20);
        congoBrazzaville.addMouseListener(new clickCountry());
        layeredPane.add(congoBrazzaville, 1, 25);
        countryQueue.add(congoBrazzaville.getName());
        CountryButton centralAfricanRepublic = new CountryButton("Central African Republic", 317, 288, 70, 15);
        centralAfricanRepublic.addMouseListener(new clickCountry());
        layeredPane.add(centralAfricanRepublic, 1, 26);
        countryQueue.add(centralAfricanRepublic.getName());
        CountryButton southSudan = new CountryButton("South Sudan", 420, 275, 40, 30);
        southSudan.addMouseListener(new clickCountry());
        layeredPane.add(southSudan, 1, 27);
        countryQueue.add(southSudan.getName());
        CountryButton ethiopia = new CountryButton("Ethiopia", 490, 250, 45, 50);
        ethiopia.addMouseListener(new clickCountry());
        layeredPane.add(ethiopia, 1, 28);
        countryQueue.add(ethiopia.getName());
        CountryButton eritrea = new CountryButton("Eritrea", 501, 207, 15, 15);
        eritrea.addMouseListener(new clickCountry());
        layeredPane.add(eritrea, 1, 29);
        countryQueue.add(eritrea.getName());
        CountryButton djibouti = new CountryButton("Djibouti", 542, 245, 10, 10);
        djibouti.addMouseListener(new clickCountry());
        layeredPane.add(djibouti, 1, 30);
        countryQueue.add(djibouti.getName());
        CountryButton somalia = new CountryButton("Somalia", 590, 260, 20, 20);
        somalia.addMouseListener(new clickCountry());
        layeredPane.add(somalia, 1, 31);
        countryQueue.add(somalia.getName());
        CountryButton kenya = new CountryButton("Kenya", 485, 325, 40, 40);
        kenya.addMouseListener(new clickCountry());
        layeredPane.add(kenya, 1, 32);
        countryQueue.add(kenya.getName());
        CountryButton uganda = new CountryButton("Uganda", 450, 325, 20, 20);
        uganda.addMouseListener(new clickCountry());
        layeredPane.add(uganda, 1, 33);
        countryQueue.add(uganda.getName());
        CountryButton congoKinshasa = new CountryButton("Congo-Kinshasa", 350, 325, 70, 75);
        congoKinshasa.addMouseListener(new clickCountry());
        layeredPane.add(congoKinshasa, 1, 34);
        countryQueue.add(congoKinshasa.getName());
        CountryButton rwanda = new CountryButton("Rwanda", 430, 365, 10, 10);
        rwanda.addMouseListener(new clickCountry());
        layeredPane.add(rwanda, 1, 35);
        countryQueue.add(rwanda.getName());
        CountryButton burundi = new CountryButton("Burundi", 432, 377, 10, 10);
        burundi.addMouseListener(new clickCountry());
        layeredPane.add(burundi, 1, 36);
        countryQueue.add(burundi.getName());
        CountryButton tanzania = new CountryButton("Tanzania", 455, 380, 45, 45);
        tanzania.addMouseListener(new clickCountry());
        layeredPane.add(tanzania, 1, 37);
        countryQueue.add(tanzania.getName());
        CountryButton malawi = new CountryButton("Malawi", 465, 465, 10, 10);
        malawi.addMouseListener(new clickCountry());
        layeredPane.add(malawi, 1, 38);
        countryQueue.add(malawi.getName());
        CountryButton mozambique = new CountryButton("Mozambique", 493, 458, 30, 30);
        mozambique.addMouseListener(new clickCountry());
        layeredPane.add(mozambique, 1, 39);
        countryQueue.add(mozambique.getName());
        CountryButton madagascar = new CountryButton("Madagascar", 567, 490, 30, 50);
        madagascar.addMouseListener(new clickCountry());
        layeredPane.add(madagascar, 1, 40);
        countryQueue.add(madagascar.getName());
        CountryButton zambia = new CountryButton("Zambia", 390, 465, 30, 30);
        zambia.addMouseListener(new clickCountry());
        layeredPane.add(zambia, 1, 41);
        countryQueue.add(zambia.getName());
        CountryButton zimbabwe = new CountryButton("Zimbabwe", 425, 505, 30, 30);
        zimbabwe.addMouseListener(new clickCountry());
        layeredPane.add(zimbabwe, 1, 42);
        countryQueue.add(zimbabwe.getName());
        CountryButton botswana = new CountryButton("Botswana", 365, 520, 30, 40);
        botswana.addMouseListener(new clickCountry());
        layeredPane.add(botswana, 1, 43);
        countryQueue.add(botswana.getName());
        CountryButton southAfrica = new CountryButton("South Africa", 355, 590, 45, 45);
        southAfrica.addMouseListener(new clickCountry());
        layeredPane.add(southAfrica, 1, 44);
        countryQueue.add(southAfrica.getName());
        CountryButton lesotho = new CountryButton("Lesotho", 418, 603, 10, 10);
        lesotho.addMouseListener(new clickCountry());
        layeredPane.add(lesotho, 1, 45);
        countryQueue.add(lesotho.getName());
        CountryButton swaziland = new CountryButton("Swaziland", 444, 578, 10, 10);
        swaziland.addMouseListener(new clickCountry());
        layeredPane.add(swaziland, 1, 46);
        countryQueue.add(swaziland.getName());
        CountryButton namibia = new CountryButton("Namibia", 310, 510, 30, 70);
        namibia.addMouseListener(new clickCountry());
        layeredPane.add(namibia, 1, 47);
        countryQueue.add(namibia.getName());
        CountryButton angola = new CountryButton("Angola", 305, 430, 55, 65);
        angola.addMouseListener(new clickCountry());
        layeredPane.add(angola, 1, 48);
        countryQueue.add(angola.getName());
        shuffleQueue();
    }

    private void seedAsianButtons(){
        countryQueue = new LinkedList<>();
        CountryButton russia = new CountryButton("Russia", 400, 100, 100, 100);
        russia.addMouseListener(new clickCountry());
        layeredPane.add(russia, 1, 0);
        countryQueue.add(russia.getName());
        CountryButton mongolia = new CountryButton("Mongolia", 425, 260, 100, 30);
        mongolia.addMouseListener(new clickCountry());
        layeredPane.add(mongolia, 1, 1);
        countryQueue.add(mongolia.getName());
        CountryButton china = new CountryButton("China", 370, 320, 210, 90);
        china.addMouseListener(new clickCountry());
        layeredPane.add(china, 1, 2);
        countryQueue.add(china.getName());
        CountryButton northKorea = new CountryButton("North Korea", 630, 275, 10, 20);
        northKorea.addMouseListener(new clickCountry());
        layeredPane.add(northKorea, 1, 3);
        countryQueue.add(northKorea.getName());
        CountryButton southKorea = new CountryButton("South Korea", 650, 295, 10, 20);
        southKorea.addMouseListener(new clickCountry());
        layeredPane.add(southKorea, 1, 4);
        countryQueue.add(southKorea.getName());
        CountryButton japan = new CountryButton("Japan", 715, 255, 10, 30);
        japan.addMouseListener(new clickCountry());
        layeredPane.add(japan, 1, 5);
        countryQueue.add(japan.getName());
        CountryButton taiwan = new CountryButton("Taiwan", 643, 405, 10, 20);
        taiwan.addMouseListener(new clickCountry());
        layeredPane.add(taiwan, 1, 6);
        countryQueue.add(taiwan.getName());
        CountryButton philippines = new CountryButton("The Philippines", 670, 485, 30, 30);
        philippines.addMouseListener(new clickCountry());
        layeredPane.add(philippines, 1, 7);
        countryQueue.add(philippines.getName());
        CountryButton indonesia = new CountryButton("Indonesia", 560, 630, 50, 50);
        indonesia.addMouseListener(new clickCountry());
        layeredPane.add(indonesia, 1, 8);
        countryQueue.add(indonesia.getName());
        CountryButton malaysia = new CountryButton("Malaysia", 525, 595, 90, 20);
        malaysia.addMouseListener(new clickCountry());
        layeredPane.add(malaysia, 1, 9);
        countryQueue.add(malaysia.getName());
        CountryButton vietnam = new CountryButton("Vietnam", 568, 505, 10, 40);
        vietnam.addMouseListener(new clickCountry());
        layeredPane.add(vietnam, 1, 10);
        countryQueue.add(vietnam.getName());
        CountryButton cambodia = new CountryButton("Cambodia", 535, 527, 20, 20);
        cambodia.addMouseListener(new clickCountry());
        layeredPane.add(cambodia, 1, 11);
        countryQueue.add(cambodia.getName());
        CountryButton thailand = new CountryButton("Thailand", 500, 505, 25, 25);
        thailand.addMouseListener(new clickCountry());
        layeredPane.add(thailand, 1, 12);
        countryQueue.add(thailand.getName());
        CountryButton laos = new CountryButton("Laos", 512, 475, 15, 15);
        laos.addMouseListener(new clickCountry());
        layeredPane.add(laos, 1, 13);
        countryQueue.add(laos.getName());
        CountryButton myanmar = new CountryButton("Myanmar", 460, 457, 30, 30);
        myanmar.addMouseListener(new clickCountry());
        layeredPane.add(myanmar, 1, 14);
        countryQueue.add(myanmar.getName());
        CountryButton bangladesh = new CountryButton("Bangladesh", 420, 455, 15, 15);
        bangladesh.addMouseListener(new clickCountry());
        layeredPane.add(bangladesh, 1, 15);
        countryQueue.add(bangladesh.getName());
        CountryButton bhutan = new CountryButton("Bhutan", 424, 430, 10, 10);
        bhutan.addMouseListener(new clickCountry());
        layeredPane.add(bhutan, 1, 16);
        countryQueue.add(bhutan.getName());
        CountryButton nepal = new CountryButton("Nepal", 370, 430, 30, 10);
        nepal.addMouseListener(new clickCountry());
        layeredPane.add(nepal, 1, 17);
        countryQueue.add(nepal.getName());
        CountryButton india = new CountryButton("India", 315, 440, 50, 75);
        india.addMouseListener(new clickCountry());
        layeredPane.add(india, 1, 18);
        countryQueue.add(india.getName());
        CountryButton sriLanka = new CountryButton("Sri Lanka", 354, 585, 10, 20);
        sriLanka.addMouseListener(new clickCountry());
        layeredPane.add(sriLanka, 1, 19);
        countryQueue.add(sriLanka.getName());
        CountryButton pakistan = new CountryButton("Pakistan", 260, 410, 40, 20);
        pakistan.addMouseListener(new clickCountry());
        layeredPane.add(pakistan, 1, 20);
        countryQueue.add(pakistan.getName());
        CountryButton afghanistan = new CountryButton("Afghanistan", 240, 370, 30, 30);
        afghanistan.addMouseListener(new clickCountry());
        layeredPane.add(afghanistan, 1, 21);
        countryQueue.add(afghanistan.getName());
        CountryButton tajikistan = new CountryButton("Tajikistan", 280, 340, 10, 10);
        tajikistan.addMouseListener(new clickCountry());
        layeredPane.add(tajikistan, 1, 22);
        countryQueue.add(tajikistan.getName());
        CountryButton kyrgyzstan = new CountryButton("Kyrgyzstan", 313, 317, 15, 15);
        kyrgyzstan.addMouseListener(new clickCountry());
        layeredPane.add(kyrgyzstan, 1, 23);
        countryQueue.add(kyrgyzstan.getName());
        CountryButton kazakhstan = new CountryButton("Kazakhstan", 270, 235, 65, 65);
        kazakhstan.addMouseListener(new clickCountry());
        layeredPane.add(kazakhstan, 1, 24);
        countryQueue.add(kazakhstan.getName());
        CountryButton uzbekistan = new CountryButton("Uzbekistan", 245, 305, 20, 20);
        uzbekistan.addMouseListener(new clickCountry());
        layeredPane.add(uzbekistan, 1, 25);
        countryQueue.add(uzbekistan.getName());
        CountryButton turkmenistan = new CountryButton("Turkmenistan", 200, 318, 35, 20);
        turkmenistan.addMouseListener(new clickCountry());
        layeredPane.add(turkmenistan, 1, 26);
        countryQueue.add(turkmenistan.getName());
        CountryButton iran = new CountryButton("Iran", 145, 350, 70, 45);
        iran.addMouseListener(new clickCountry());
        layeredPane.add(iran, 1, 27);
        countryQueue.add(iran.getName());
        CountryButton azerbaijan = new CountryButton("Azerbaijan", 153, 306, 12, 10);
        azerbaijan.addMouseListener(new clickCountry());
        layeredPane.add(azerbaijan, 1, 28);
        countryQueue.add(azerbaijan.getName());
        CountryButton armenia = new CountryButton("Armenia", 137, 302, 10, 15);
        armenia.addMouseListener(new clickCountry());
        layeredPane.add(armenia, 1, 29);
        countryQueue.add(armenia.getName());
        CountryButton georgia = new CountryButton("Georgia", 130, 283, 10, 10);
        georgia.addMouseListener(new clickCountry());
        layeredPane.add(georgia, 1, 30);
        countryQueue.add(georgia.getName());
        CountryButton turkey = new CountryButton("Turkey", 45, 285, 60, 20);
        turkey.addMouseListener(new clickCountry());
        layeredPane.add(turkey, 1, 31);
        countryQueue.add(turkey.getName());
        CountryButton syria = new CountryButton("Syria", 75, 322, 20, 20);
        syria.addMouseListener(new clickCountry());
        layeredPane.add(syria, 1, 32);
        countryQueue.add(syria.getName());
        CountryButton iraq = new CountryButton("Iraq", 95, 345, 25, 25);
        iraq.addMouseListener(new clickCountry());
        layeredPane.add(iraq, 1, 33);
        countryQueue.add(iraq.getName());
        CountryButton lebanon = new CountryButton("Lebanon", 60, 330, 10, 10);
        lebanon.addMouseListener(new clickCountry());
        layeredPane.add(lebanon, 1, 34);
        countryQueue.add(lebanon.getName());
        CountryButton israel = new CountryButton("Israel", 48, 343, 10, 10);
        israel.addMouseListener(new clickCountry());
        layeredPane.add(israel, 1, 35);
        countryQueue.add(israel.getName());
        CountryButton jordan = new CountryButton("Jordan", 58, 348, 10, 10);
        jordan.addMouseListener(new clickCountry());
        layeredPane.add(jordan, 1, 36);
        countryQueue.add(jordan.getName());
        CountryButton saudiArabia = new CountryButton("Saudi Arabia", 65, 400, 60, 55);
        saudiArabia.addMouseListener(new clickCountry());
        layeredPane.add(saudiArabia, 1, 37);
        countryQueue.add(saudiArabia.getName());
        CountryButton yemen = new CountryButton("Yemen", 75, 490, 60, 20);
        yemen.addMouseListener(new clickCountry());
        layeredPane.add(yemen, 1, 38);
        countryQueue.add(yemen.getName());
        CountryButton oman = new CountryButton("Oman", 180, 460, 20, 20);
        oman.addMouseListener(new clickCountry());
        layeredPane.add(oman, 1, 39);
        countryQueue.add(oman.getName());
        CountryButton uae = new CountryButton("United Arab Emirates", 157, 442, 15, 10);
        uae.addMouseListener(new clickCountry());
        layeredPane.add(uae, 1, 40);
        countryQueue.add(uae.getName());
        CountryButton qatar = new CountryButton("Qatar", 142, 426, 10, 10);
        qatar.addMouseListener(new clickCountry());
        layeredPane.add(qatar, 1, 41);
        countryQueue.add(qatar.getName());
        CountryButton kuwait = new CountryButton("Kuwait", 125, 387, 10, 10);
        kuwait.addMouseListener(new clickCountry());
        layeredPane.add(kuwait, 1, 42);
        countryQueue.add(kuwait.getName());
        shuffleQueue();
    }

    private void seedStatesButtons(){
        countryQueue = new LinkedList<>();
        CountryButton washington = new CountryButton("Washington", 135, 90, 50, 50);
        washington.addMouseListener(new clickCountry());
        layeredPane.add(washington, 1, 0);
        countryQueue.add(washington.getName());
        CountryButton oregon = new CountryButton("Oregon", 90, 160, 65, 65);
        oregon.addMouseListener(new clickCountry());
        layeredPane.add(oregon, 1, 1);
        countryQueue.add(oregon.getName());
        CountryButton california = new CountryButton("California", 70, 330, 50, 50);
        california.addMouseListener(new clickCountry());
        layeredPane.add(california, 1, 2);
        countryQueue.add(california.getName());
        CountryButton nevada = new CountryButton("Nevada", 140, 280, 60, 60);
        nevada.addMouseListener(new clickCountry());
        layeredPane.add(nevada, 1, 3);
        countryQueue.add(nevada.getName());
        CountryButton idaho = new CountryButton("Idaho", 195, 200, 50, 50);
        idaho.addMouseListener(new clickCountry());
        layeredPane.add(idaho, 1, 4);
        countryQueue.add(idaho.getName());
        CountryButton montana = new CountryButton("Montana", 260, 130, 130, 60);
        montana.addMouseListener(new clickCountry());
        layeredPane.add(montana, 1, 5);
        countryQueue.add(montana.getName());
        CountryButton wyoming = new CountryButton("Wyoming", 300, 220, 90, 70);
        wyoming.addMouseListener(new clickCountry());
        layeredPane.add(wyoming, 1, 6);
        countryQueue.add(wyoming.getName());
        CountryButton utah = new CountryButton("Utah", 240, 310, 60, 70);
        utah.addMouseListener(new clickCountry());
        layeredPane.add(utah, 1, 7);
        countryQueue.add(utah.getName());
        CountryButton arizona = new CountryButton("Arizona", 220, 400, 60, 100);
        arizona.addMouseListener(new clickCountry());
        layeredPane.add(arizona, 1, 8);
        countryQueue.add(arizona.getName());
        CountryButton newMexico = new CountryButton("New Mexico", 320, 410, 80, 100);
        newMexico.addMouseListener(new clickCountry());
        layeredPane.add(newMexico, 1, 9);
        countryQueue.add(newMexico.getName());
        CountryButton texas = new CountryButton("Texas", 430, 487, 110, 90);
        texas.addMouseListener(new clickCountry());
        layeredPane.add(texas, 1, 10);
        countryQueue.add(texas.getName());
        CountryButton oklahoma = new CountryButton("Oklahoma", 490, 415, 80, 50);
        oklahoma.addMouseListener(new clickCountry());
        layeredPane.add(oklahoma, 1, 11);
        countryQueue.add(oklahoma.getName());
        CountryButton kansas = new CountryButton("Kansas", 450, 340, 100, 60);
        kansas.addMouseListener(new clickCountry());
        layeredPane.add(kansas, 1, 12);
        countryQueue.add(kansas.getName());
        CountryButton colorado = new CountryButton("Colorado", 330, 320, 90, 70);
        colorado.addMouseListener(new clickCountry());
        layeredPane.add(colorado, 1, 13);
        countryQueue.add(colorado.getName());
        CountryButton nebraska = new CountryButton("Nebraska", 450, 275, 80, 50);
        nebraska.addMouseListener(new clickCountry());
        layeredPane.add(nebraska, 1, 14);
        countryQueue.add(nebraska.getName());
        CountryButton southDakota = new CountryButton("South Dakota", 420, 200, 100, 60);
        southDakota.addMouseListener(new clickCountry());
        layeredPane.add(southDakota, 1, 15);
        countryQueue.add(southDakota.getName());
        CountryButton northDakota = new CountryButton("North Dakota", 420, 130, 100, 60);
        northDakota.addMouseListener(new clickCountry());
        layeredPane.add(northDakota, 1, 16);
        countryQueue.add(northDakota.getName());
        CountryButton minnesota = new CountryButton("Minnesota", 543, 133, 50, 100);
        minnesota.addMouseListener(new clickCountry());
        layeredPane.add(minnesota, 1, 17);
        countryQueue.add(minnesota.getName());
        CountryButton wisconsin = new CountryButton("Wisconsin", 635, 190, 40, 50);
        wisconsin.addMouseListener(new clickCountry());
        layeredPane.add(wisconsin, 1, 18);
        countryQueue.add(wisconsin.getName());
        CountryButton iowa = new CountryButton("Iowa", 560, 260, 70, 50);
        iowa.addMouseListener(new clickCountry());
        layeredPane.add(iowa, 1, 19);
        countryQueue.add(iowa.getName());
        CountryButton missouri = new CountryButton("Missouri", 590, 325, 40, 80);
        missouri.addMouseListener(new clickCountry());
        layeredPane.add(missouri, 1, 20);
        countryQueue.add(missouri.getName());
        CountryButton arkansas = new CountryButton("Arkansas", 595, 420, 50, 60);
        arkansas.addMouseListener(new clickCountry());
        layeredPane.add(arkansas, 1, 21);
        countryQueue.add(arkansas.getName());
        CountryButton louisiana = new CountryButton("Louisiana", 610, 500, 40, 70);
        louisiana.addMouseListener(new clickCountry());
        layeredPane.add(louisiana, 1, 22);
        countryQueue.add(louisiana.getName());
        CountryButton mississippi = new CountryButton("Mississippi", 666, 457, 40, 70);
        mississippi.addMouseListener(new clickCountry());
        layeredPane.add(mississippi, 1, 23);
        countryQueue.add(mississippi.getName());
        CountryButton alabama = new CountryButton("Alabama", 715, 440, 45, 80);
        alabama.addMouseListener(new clickCountry());
        layeredPane.add(alabama, 1, 24);
        countryQueue.add(alabama.getName());
        CountryButton florida = new CountryButton("Florida", 840, 540, 45, 60);
        florida.addMouseListener(new clickCountry());
        layeredPane.add(florida, 1, 25);
        countryQueue.add(florida.getName());
        CountryButton georgia = new CountryButton("Georgia", 785, 450, 45, 60);
        georgia.addMouseListener(new clickCountry());
        layeredPane.add(georgia, 1, 26);
        countryQueue.add(georgia.getName());
        CountryButton southCarolina = new CountryButton("South Carolina", 830, 415, 40, 30);
        southCarolina.addMouseListener(new clickCountry());
        layeredPane.add(southCarolina, 1, 27);
        countryQueue.add(southCarolina.getName());
        CountryButton northCarolina = new CountryButton("North Carolina", 840, 372, 60, 30);
        northCarolina.addMouseListener(new clickCountry());
        layeredPane.add(northCarolina, 1, 28);
        countryQueue.add(northCarolina.getName());
        CountryButton tennessee = new CountryButton("Tennessee", 690, 403, 80, 25);
        tennessee.addMouseListener(new clickCountry());
        layeredPane.add(tennessee, 1, 29);
        countryQueue.add(tennessee.getName());
        CountryButton kentucky = new CountryButton("Kentucky", 750, 340, 40, 40);
        kentucky.addMouseListener(new clickCountry());
        layeredPane.add(kentucky, 1, 30);
        countryQueue.add(kentucky.getName());
        CountryButton illinois = new CountryButton("Illinois", 655, 275, 40, 70);
        illinois.addMouseListener(new clickCountry());
        layeredPane.add(illinois, 1, 31);
        countryQueue.add(illinois.getName());
        CountryButton indiana = new CountryButton("Indiana", 712, 275, 30, 70);
        indiana.addMouseListener(new clickCountry());
        layeredPane.add(indiana, 1, 32);
        countryQueue.add(indiana.getName());
        CountryButton michigan = new CountryButton("Michigan", 710, 170, 50, 80);
        michigan.addMouseListener(new clickCountry());
        layeredPane.add(michigan, 1, 33);
        countryQueue.add(michigan.getName());
        CountryButton ohio = new CountryButton("Ohio", 760, 270, 45, 45);
        ohio.addMouseListener(new clickCountry());
        layeredPane.add(ohio, 1, 34);
        countryQueue.add(ohio.getName());
        CountryButton westVirginia = new CountryButton("West Virginia", 820, 300, 30, 30);
        westVirginia.addMouseListener(new clickCountry());
        layeredPane.add(westVirginia, 1, 35);
        countryQueue.add(westVirginia.getName());
        CountryButton virginia = new CountryButton("Virginia", 860, 317, 35, 35);
        virginia.addMouseListener(new clickCountry());
        layeredPane.add(virginia, 1, 36);
        countryQueue.add(virginia.getName());
        CountryButton maryland = new CountryButton("Maryland", 888, 278, 15, 15);
        maryland.addMouseListener(new clickCountry());
        layeredPane.add(maryland, 1, 37);
        countryQueue.add(maryland.getName());
        CountryButton pennsylvania = new CountryButton("Pennsylvania", 850, 235, 40, 40);
        pennsylvania.addMouseListener(new clickCountry());
        layeredPane.add(pennsylvania, 1, 38);
        countryQueue.add(pennsylvania.getName());
        CountryButton delaware = new CountryButton("Delaware", 920, 280, 13, 13);
        delaware.addMouseListener(new clickCountry());
        layeredPane.add(delaware, 1, 39);
        countryQueue.add(delaware.getName());
        CountryButton newJersey = new CountryButton("New Jersey", 925, 230, 13, 40);
        newJersey.addMouseListener(new clickCountry());
        layeredPane.add(newJersey, 1, 40);
        countryQueue.add(newJersey.getName());
        CountryButton newYork = new CountryButton("New York", 890, 150, 30, 60);
        newYork.addMouseListener(new clickCountry());
        layeredPane.add(newYork, 1, 41);
        countryQueue.add(newYork.getName());
        CountryButton connecticut = new CountryButton("Connecticut", 940, 200, 25, 15);
        connecticut.addMouseListener(new clickCountry());
        layeredPane.add(connecticut, 1, 42);
        countryQueue.add(connecticut.getName());
        CountryButton rhodeIsland = new CountryButton("Rhode Island", 967, 192, 10, 10);
        rhodeIsland.addMouseListener(new clickCountry());
        layeredPane.add(rhodeIsland, 1, 43);
        countryQueue.add(rhodeIsland.getName());
        CountryButton massachusetts = new CountryButton("Massachusetts", 940, 182, 20, 10);
        massachusetts.addMouseListener(new clickCountry());
        layeredPane.add(massachusetts, 1, 44);
        countryQueue.add(massachusetts.getName());
        CountryButton vermont = new CountryButton("Vermont", 928, 130, 10, 30);
        vermont.addMouseListener(new clickCountry());
        layeredPane.add(vermont, 1, 45);
        countryQueue.add(vermont.getName());
        CountryButton newHampshire = new CountryButton("New Hampshire", 948, 140, 10, 30);
        newHampshire.addMouseListener(new clickCountry());
        layeredPane.add(newHampshire, 1, 46);
        countryQueue.add(newHampshire.getName());
        CountryButton maine = new CountryButton("Maine", 957, 62, 30, 60);
        maine.addMouseListener(new clickCountry());
        layeredPane.add(maine, 1, 47);
        countryQueue.add(maine.getName());
        CountryButton alaska = new CountryButton("Alaska", 88, 513, 60, 80);
        alaska.addMouseListener(new clickCountry());
        layeredPane.add(alaska, 1, 48);
        countryQueue.add(alaska.getName());
        shuffleQueue();
    }

    private void seedSwedenButtons(){
        countryQueue = new LinkedList<>();
        CountryButton lappland = new CountryButton("Lappland", 215, 65, 75, 90);
        lappland.addMouseListener(new clickCountry());
        layeredPane.add(lappland, 1, 0);
        countryQueue.add(lappland.getName());
        CountryButton norrbotten = new CountryButton("Norrbotten", 340, 72, 20, 80);
        norrbotten.addMouseListener(new clickCountry());
        layeredPane.add(norrbotten, 1, 1);
        countryQueue.add(norrbotten.getName());
        CountryButton vaesterbotten = new CountryButton("Västerbotten", 280, 195, 30, 50);
        vaesterbotten.addMouseListener(new clickCountry());
        layeredPane.add(vaesterbotten, 1, 2);
        countryQueue.add(vaesterbotten.getName());
        CountryButton aangermanland = new CountryButton("Ångermanland", 208, 255, 45, 30);
        aangermanland.addMouseListener(new clickCountry());
        layeredPane.add(aangermanland, 1, 3);
        countryQueue.add(aangermanland.getName());
        CountryButton jaemtland = new CountryButton("Jämtland", 120, 250, 65, 40);
        jaemtland.addMouseListener(new clickCountry());
        layeredPane.add(jaemtland, 1, 4);
        countryQueue.add(jaemtland.getName());
        CountryButton medelpad = new CountryButton("Medelpad", 190, 305, 35, 20);
        medelpad.addMouseListener(new clickCountry());
        layeredPane.add(medelpad, 1, 5);
        countryQueue.add(medelpad.getName());
        CountryButton haerjedalen = new CountryButton("Härjedalen", 110, 305, 30, 20);
        haerjedalen.addMouseListener(new clickCountry());
        layeredPane.add(haerjedalen, 1, 6);
        countryQueue.add(haerjedalen.getName());
        CountryButton haelsingland = new CountryButton("Hälsingland", 175, 333, 40, 30);
        haelsingland.addMouseListener(new clickCountry());
        layeredPane.add(haelsingland, 1, 7);
        countryQueue.add(haelsingland.getName());
        CountryButton dalarna = new CountryButton("Dalarna", 120, 360, 50, 35);
        dalarna.addMouseListener(new clickCountry());
        layeredPane.add(dalarna, 1, 8);
        countryQueue.add(dalarna.getName());
        CountryButton gaestrikland = new CountryButton("Gästrikland", 202, 390, 20, 20);
        gaestrikland.addMouseListener(new clickCountry());
        layeredPane.add(gaestrikland, 1, 9);
        countryQueue.add(gaestrikland.getName());
        CountryButton uppland = new CountryButton("Uppland", 220, 420, 35, 35);
        uppland.addMouseListener(new clickCountry());
        layeredPane.add(uppland, 1, 10);
        countryQueue.add(uppland.getName());
        CountryButton vaestmanland = new CountryButton("Västmanland", 157, 438, 50, 15);
        vaestmanland.addMouseListener(new clickCountry());
        layeredPane.add(vaestmanland, 1, 11);
        countryQueue.add(vaestmanland.getName());
        CountryButton vaermland = new CountryButton("Värmland", 105, 428, 40, 30);
        vaermland.addMouseListener(new clickCountry());
        layeredPane.add(vaermland, 1, 12);
        countryQueue.add(vaermland.getName());
        CountryButton dalsland = new CountryButton("Dalsland", 90, 475, 15, 15);
        dalsland.addMouseListener(new clickCountry());
        layeredPane.add(dalsland, 1, 13);
        countryQueue.add(dalsland.getName());
        CountryButton bohuslaen = new CountryButton("Bohuslän", 70, 490, 15, 35);
        bohuslaen.addMouseListener(new clickCountry());
        layeredPane.add(bohuslaen, 1, 14);
        countryQueue.add(bohuslaen.getName());
        CountryButton vaestergoetland = new CountryButton("Västergötland", 100, 510, 30, 35);
        vaestergoetland.addMouseListener(new clickCountry());
        layeredPane.add(vaestergoetland, 1, 15);
        countryQueue.add(vaestergoetland.getName());
        CountryButton halland = new CountryButton("Halland", 100, 563, 15, 30);
        halland.addMouseListener(new clickCountry());
        layeredPane.add(halland, 1, 16);
        countryQueue.add(halland.getName());
        CountryButton skaane = new CountryButton("Skåne", 112, 603, 30, 40);
        skaane.addMouseListener(new clickCountry());
        layeredPane.add(skaane, 1, 17);
        countryQueue.add(skaane.getName());
        CountryButton blekinge = new CountryButton("Blekinge", 153, 600, 35, 15);
        blekinge.addMouseListener(new clickCountry());
        layeredPane.add(blekinge, 1, 18);
        countryQueue.add(blekinge.getName());
        CountryButton smaaland = new CountryButton("Småland", 135, 545, 60, 45);
        smaaland.addMouseListener(new clickCountry());
        layeredPane.add(smaaland, 1, 19);
        countryQueue.add(smaaland.getName());
        CountryButton oeland = new CountryButton("Öland", 205, 555, 10, 45);
        oeland.addMouseListener(new clickCountry());
        layeredPane.add(oeland, 1, 20);
        countryQueue.add(oeland.getName());
        CountryButton gotland = new CountryButton("Gotland", 245, 535, 25, 35);
        gotland.addMouseListener(new clickCountry());
        layeredPane.add(gotland, 1, 21);
        countryQueue.add(gotland.getName());
        CountryButton oestergoetland = new CountryButton("Östergötland", 170, 490, 25, 25);
        oestergoetland.addMouseListener(new clickCountry());
        layeredPane.add(oestergoetland, 1, 22);
        countryQueue.add(oestergoetland.getName());
        CountryButton naerke = new CountryButton("Närke", 160, 460, 20, 20);
        naerke.addMouseListener(new clickCountry());
        layeredPane.add(naerke, 1, 23);
        countryQueue.add(naerke.getName());
        CountryButton soedermanland = new CountryButton("Södermanland", 195, 460, 30, 25);
        soedermanland.addMouseListener(new clickCountry());
        layeredPane.add(soedermanland, 1, 24);
        countryQueue.add(soedermanland.getName());
        shuffleQueue();
    }

    private void switchToEurope(){
        switchMap("Europe");
        clearOldCountries();
        hideGameOptions();
        moveResetAndLabels("Europe");
        seedEuropeanButtons();
    }

    private void switchToAfrica(){
        switchMap("Africa");
        clearOldCountries();
        hideGameOptions();
        moveResetAndLabels("Africa");
        seedAfricanButtons();
    }

    private void switchToAsia(){
        switchMap("Asia");
        clearOldCountries();
        hideGameOptions();
        moveResetAndLabels("Asia");
        seedAsianButtons();
    }

    private void switchToStates(){
        switchMap("States");
        clearOldCountries();
        hideGameOptions();
        moveResetAndLabels("States");
        seedStatesButtons();
    }

    private void switchToSweden(){
        switchMap("Sweden");
        clearOldCountries();
        hideGameOptions();
        moveResetAndLabels("Sweden");
        seedSwedenButtons();
    }

    private void switchToMenu(){
        switchMap("Menu");
        clearOldCountries();
        unhideGameOptions();
        hideResetMenuAndLabels();
    }

    private void hideGameOptions(){
        Component[] continents = layeredPane.getComponentsInLayer(5);
        for(Component continent : continents){
            continent.setVisible(false);
        }
    }

    private void unhideGameOptions(){
        Component[] continents = layeredPane.getComponentsInLayer(5);
        for(Component continent : continents){
            continent.setVisible(true);
        }
    }

    private void hideResetMenuAndLabels(){
        resetButton.setVisible(false);
        menuButton.setVisible(false);
        currentCountryLabel.setVisible(false);
        guessesLabel.setVisible(false);
    }

    private void moveResetAndLabels(String continentName){
        resetButton.setVisible(true);
        currentCountryLabel.setVisible(true);
        guessesLabel.setVisible(true);
        menuButton.setVisible(true);
        switch (continentName) {
            case "Africa" -> {
                menuButton.setBounds(20, 520, 80, 40);
                resetButton.setBounds(20, 470, 80, 40);
                guessesLabel.setBounds(20, 550, 300, 50);
                currentCountryLabel.setBounds(20, 580, 300, 50);
            }
            case "Europe" -> {
                resetButton.setBounds(15, 140, 80, 40);
                menuButton.setBounds(15, 190, 80, 40);
                currentCountryLabel.setBounds(15, 75, 300, 50);
                guessesLabel.setBounds(15, 100, 300, 50);
            }
            case "Asia" -> {
                resetButton.setBounds(740, 100, 80, 40);
                menuButton.setBounds(740, 150, 80, 40);
                currentCountryLabel.setBounds(160, 150, 300, 50);
                guessesLabel.setBounds(160, 120, 300, 50);
            }
            case "States" -> {
                resetButton.setBounds(550, 15, 80, 40);
                menuButton.setBounds(550, 60, 80, 40);
                currentCountryLabel.setBounds(140, 25, 300, 50);
                guessesLabel.setBounds(140, 1, 300, 50);
            }
            case "Sweden" -> {
                resetButton.setBounds(10, 70, 80, 40);
                menuButton.setBounds(10, 115, 80, 40);
                currentCountryLabel.setBounds(10, 30, 300, 50);
                guessesLabel.setBounds(10, 1, 300, 50);
            }
        }
    }

    private void clearOldCountries(){
        Component[] oldCountries = layeredPane.getComponentsInLayer(1);
        for(Component country : oldCountries){
            layeredPane.remove(country);
        }
    }

    private void switchMap(String continentName){
        Component[] background = layeredPane.getComponentsInLayer(0);
        Component backgroundComponent = background[0];
        JPanel backgroundPanel = (JPanel) backgroundComponent;
        backgroundPanel.removeAll();
        int width = 0;
        int height = 0;
        switch (continentName) {
            case "Africa" -> {
                width = 640;
                height = 700;
                try {
                    mapImage = ImageIO.read(new File(".\\resources\\africa4.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "Europe" -> {
                width = 750;
                height = 650;
                try {
                    mapImage = ImageIO.read(new File(".\\resources\\europe5.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "Asia" -> {
                width = 850;
                height = 750;
                try {
                    mapImage = ImageIO.read(new File(".\\resources\\asia.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "States" -> {
                width = 1040;
                height = 715;
                try {
                    mapImage = ImageIO.read(new File(".\\resources\\america.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "Sweden" -> {
                width = 450;
                height = 720;
                try {
                    mapImage = ImageIO.read(new File(".\\resources\\sweden.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "Menu" -> {
                width = 180;
                height = 320;
                setSize(width, height);
                layeredPane.setBounds(0, 0, width, height);
                backgroundPanel.setBounds(0, 0, width, height);
                return;
            }
        }
        setSize(width, height);
        layeredPane.setBounds(0, 0, width, height);
        backgroundPanel.setBounds(0, 0, width, height);
        JLabel pic = new JLabel(new ImageIcon(mapImage));
        backgroundPanel.add(pic);
    }

    private void shuffleQueue(){
        LinkedList<String> tempList = new LinkedList<>(countryQueue.stream().toList());
        Collections.shuffle(tempList, new Random());
        countryQueue = new LinkedList<>(tempList);
        currentCountry = countryQueue.poll();
        currentCountryLabel.setText("Select country: "+currentCountry);
    }

    public static void main(String[] args){
        new MapFrame();
    }

    private class clickCountry implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            CountryButton button = (CountryButton) e.getSource();
            totalGuesses++;
            if(button.isCorrectCountry(currentCountry)){
                correctGuesses++;
                currentCountry = countryQueue.poll();
                button.setVisible(false);
                if(currentCountry == null){
                    currentCountryLabel.setText("Game Over!");
                }else{
                    currentCountryLabel.setText("Select country: "+currentCountry);
                }
            }
            guessesLabel.setText("Correct guesses: "+correctGuesses+" out of "+totalGuesses+" attempts.");
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    private class clickReset implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            while(!countryQueue.isEmpty()){
                countryQueue.poll();
            }
            correctGuesses = 0;
            totalGuesses = 0;
            guessesLabel.setText("Correct guesses: "+correctGuesses+" out of "+totalGuesses+" attempts.");
            Component[] comps = layeredPane.getComponentsInLayer(1);
            for(Component comp : comps){
                comp.setVisible(true);
                countryQueue.add(comp.getName());
            }
            shuffleQueue();
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    private class switchContinent implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            correctGuesses = 0;
            totalGuesses = 0;
            guessesLabel.setText("Correct guesses: "+correctGuesses+" out of "+totalGuesses+" attempts.");
            JButton buttonClicked = (JButton) e.getSource();
            switch (buttonClicked.getName()) {
                case "Africa" -> switchToAfrica();
                case "Europe" -> switchToEurope();
                case "Asia" -> switchToAsia();
                case "States" -> switchToStates();
                case "Sweden" -> switchToSweden();
                case "Menu" -> switchToMenu();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }
}
