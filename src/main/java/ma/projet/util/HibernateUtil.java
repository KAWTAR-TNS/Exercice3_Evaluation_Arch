package ma.projet.util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Properties props = new Properties();
            InputStream input = HibernateUtil.class.getClassLoader().getResourceAsStream("application.properties");
            if (input == null) {throw new RuntimeException("application.properties makaynsh !");}
            props.load(input);

            Configuration configuration = new Configuration();
            configuration.setProperties(props);
            configuration.addAnnotatedClass(ma.projet.beans.Personne.class);
            configuration.addAnnotatedClass(ma.projet.beans.Homme.class);
            configuration.addAnnotatedClass(ma.projet.beans.Femme.class);
            configuration.addAnnotatedClass(ma.projet.beans.Mariage.class);

            sessionFactory = configuration.buildSessionFactory();}
        catch (Exception e) {e.printStackTrace();
            throw new ExceptionInInitializerError(e);}
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;}}
