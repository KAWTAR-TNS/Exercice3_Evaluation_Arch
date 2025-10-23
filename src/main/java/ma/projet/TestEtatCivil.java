package ma.projet;

import ma.projet.beans.*;
import ma.projet.service.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestEtatCivil {

    public static void main(String[] args) throws Exception {

        HommeService hommeService = new HommeService();
        FemmeService femmeService = new FemmeService();
        MariageService mariageService = new MariageService();
 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Les hommes
        Homme h1 = new Homme(); h1.setNom("Taqi"); h1.setPrenom("BouJamaa"); h1.setDateNaissance(sdf.parse("01/01/1973"));
        Homme h2 = new Homme(); h2.setNom("Tanassa"); h2.setPrenom("Youssef"); h2.setDateNaissance(sdf.parse("05/05/1999"));
        Homme h3 = new Homme(); h3.setNom("Shift"); h3.setPrenom("MOHAMED"); h3.setDateNaissance(sdf.parse("10/10/1990"));
        Homme h4 = new Homme(); h4.setNom("Zeetari"); h4.setPrenom("Rachid"); h4.setDateNaissance(sdf.parse("15/07/1988"));
        Homme h5 = new Homme(); h5.setNom("HICHAM"); h5.setPrenom("Bensamka"); h5.setDateNaissance(sdf.parse("20/12/1952"));

        hommeService.add(h1);hommeService.add(h2);hommeService.add(h3);hommeService.add(h4);hommeService.add(h5);

        // Les femmes
        Femme f1 = new Femme(); f1.setNom("ElOuardi"); f1.setPrenom("Fatima Zahra"); f1.setDateNaissance(sdf.parse("02/03/1979"));
        Femme f2 = new Femme(); f2.setNom("Rais"); f2.setPrenom("AMAL"); f2.setDateNaissance(sdf.parse("10/02/1989"));
        Femme f3 = new Femme(); f3.setNom("TANASSA"); f3.setPrenom("KAWTAR"); f3.setDateNaissance(sdf.parse("25/12/2001"));
        Femme f4 = new Femme(); f4.setNom("Hariri"); f4.setPrenom("KARIMA"); f4.setDateNaissance(sdf.parse("15/08/1968"));
        Femme f5 = new Femme(); f5.setNom("Fettah"); f5.setPrenom("NOURA"); f5.setDateNaissance(sdf.parse("12/03/1974"));
        Femme f6 = new Femme(); f6.setNom("HADDAD"); f6.setPrenom("FATIMA"); f6.setDateNaissance(sdf.parse("30/06/1976"));
        Femme f7 = new Femme(); f7.setNom("SAADI"); f7.setPrenom("LEILA"); f7.setDateNaissance(sdf.parse("05/11/1978"));
        Femme f8 = new Femme(); f8.setNom("OUAZZANI"); f8.setPrenom("IMANE"); f8.setDateNaissance(sdf.parse("22/09/1981"));
        Femme f9 = new Femme(); f9.setNom("Naass"); f9.setPrenom("Ahlam"); f9.setDateNaissance(sdf.parse("18/04/1990"));
        Femme f10 = new Femme(); f10.setNom("Essakhi"); f10.setPrenom("HANANE"); f10.setDateNaissance(sdf.parse("01/01/1983"));

        femmeService.add(f1);femmeService.add(f2);femmeService.add(f3);femmeService.add(f4);femmeService.add(f5);
        femmeService.add(f6);femmeService.add(f7);femmeService.add(f8);femmeService.add(f9);femmeService.add(f10);

        // Les mariages
        Mariage m1 = new Mariage(); m1.setHomme(h1); m1.setFemme(f1); m1.setDateDebut(sdf.parse("03/09/1990")); m1.setNbrEnfant(4);
        Mariage m2 = new Mariage(); m2.setHomme(h1); m2.setFemme(f2); m2.setDateDebut(sdf.parse("03/09/1995")); m2.setNbrEnfant(2);
        Mariage m3 = new Mariage(); m3.setHomme(h1); m3.setFemme(f3); m3.setDateDebut(sdf.parse("04/11/2000")); m3.setNbrEnfant(3);
        Mariage m4 = new Mariage(); m4.setHomme(h1); m4.setFemme(f4); m4.setDateDebut(sdf.parse("03/09/1989")); m4.setDateFin(sdf.parse("03/09/1990")); m4.setNbrEnfant(0);

        mariageService.add(m1);mariageService.add(m2);mariageService.add(m3);mariageService.add(m4);

        // Liste des femmes
        System.out.println("=== Liste des femmes ===");
        List<Femme> femmes = femmeService.findAll();
        femmes.forEach(f -> System.out.println(f.getNom() + " " + f.getPrenom()));

        //La femme la plus âgée
        Femme plusAgee = femmeService.getFemmePlusAgee();
        System.out.println("\nFemme la plus âgée : " + plusAgee.getNom() + " " + plusAgee.getPrenom());

        // Les épouses d’un homme ( hna l'homme 1 )
        System.out.println("\nÉpouses de " + h1.getNom() + " " + h1.getPrenom());
        List<Femme> epouses = hommeService.getEpouses(h1, sdf.parse("01/01/1980"), sdf.parse("31/12/2025"));
        epouses.forEach(f -> System.out.println(f.getNom() + " " + f.getPrenom()));

        // Le nombre d’enfants d’une femme entre deux dates
        int nbEnfants = femmeService.getNombreEnfants(f1, sdf.parse("01/01/1980"), sdf.parse("31/12/2025"));
        System.out.println("\nNombre d'enfants de " + f1.getNom() + " : " + nbEnfants);

        // Les femmes mariées 2 fois ou plus
        System.out.println("\nFemmes mariées deux fois ou plus :");
        List<Femme> femmesMulti = femmeService.getFemmes2FoisOuPlus();
        femmesMulti.forEach(f -> System.out.println(f.getNom() + " " + f.getPrenom()));

        // Les hommes mariés 4 femmes entre deux dates
        System.out.println("\nHommes mariés à 4 femmes entre 1990 et 2025 :");
        List<Homme> rabiaa = hommeService.H4F(
                sdf.parse("01/01/1990"), sdf.parse("31/12/2025"));
        rabiaa.forEach(h -> System.out.println(h.getNom() + " " + h.getPrenom()));


        // Les mariages d un homme avec details
        System.out.println("\nMariages de " + h1.getNom() + " " + h1.getPrenom() + " :");
        hommeService.getMariages(h1);
    }
}
