package ma.projet.service;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class FemmeService {

    public void add(Femme f) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(f);
        session.getTransaction().commit();
        session.close();
    }

    public List<Femme> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Femme> list = session.createQuery("FROM Femme", Femme.class).list();
        session.close();
        return list;
    }

    // 1️⃣ Femme la plus âgée
    public Femme getFemmePlusAgee() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String jpql = "FROM Femme f ORDER BY f.dateNaissance ASC";
        Query<Femme> query = session.createQuery(jpql, Femme.class);
        query.setMaxResults(1);
        Femme f = query.uniqueResult();
        session.close();
        return f;
    }

    public int getNombreEnfants(Femme f, Date start, Date end) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT SUM(nbrEnfant) FROM Mariage WHERE femme_id = :f AND dateDebut BETWEEN :start AND :end";
        Query<Integer> query = session.createNativeQuery(sql);
        query.setParameter("f", f.getId());
        query.setParameter("start", start);
        query.setParameter("end", end);
        Integer result = query.uniqueResult();
        session.close();
        return result != null ? result : 0;
    }

    public List<Femme> getFemmes2FoisOuPlus() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String jpql = "SELECT m.femme FROM Mariage m GROUP BY m.femme HAVING COUNT(m.homme) >= 2";
        List<Femme> list = session.createQuery(jpql, Femme.class).getResultList();
        session.close();
        return list;
    }
}
