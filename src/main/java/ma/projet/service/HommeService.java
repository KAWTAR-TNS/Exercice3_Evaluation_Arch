package ma.projet.service;
import ma.projet.beans.Homme;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class HommeService {

    public void add(Homme h) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(h);
        session.getTransaction().commit();
        session.close();
    }

    public List<Homme> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Homme> list = session.createQuery("FROM Homme", Homme.class).list();
        session.close();
        return list;
    }

    public Homme findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Homme h = session.get(Homme.class, id);
        session.close();
        return h;
    }

    public List<Femme> getEpouses(Homme h, Date start, Date end) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String jpql = "SELECT m.femme FROM Mariage m WHERE m.homme = :h AND m.dateDebut BETWEEN :start AND :end";
        Query<Femme> query = session.createQuery(jpql, Femme.class);
        query.setParameter("h", h);
        query.setParameter("start", start);
        query.setParameter("end", end);
        List<Femme> epouses = query.getResultList();
        session.close();
        return epouses;
    }

    // Les mariages d’un homme avec tous les détails
    public List<Mariage> getMariages(Homme h) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String jpql = "FROM Mariage m WHERE m.homme.id = :h";
        Query<Mariage> query = session.createQuery(jpql, Mariage.class);
        query.setParameter("h", h.getId());
        List<Mariage> mariages = query.getResultList();
        session.close();
        return mariages;
    }

    public List<Homme> H4F(Date start, Date end) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String jpql = "SELECT m.homme FROM Mariage m " +
                "WHERE m.dateDebut BETWEEN :start AND :end " +
                "GROUP BY m.homme HAVING COUNT(m.femme) = 4";
        Query<Homme> query = session.createQuery(jpql, Homme.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        List<Homme> hommes = query.getResultList();
        session.close();
        return hommes;
    }

}
