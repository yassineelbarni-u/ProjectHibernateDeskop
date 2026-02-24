package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import bo.Commande;
import bo.Ligne_Commande;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation du DAO Commande
 */
public class CommandeDAO implements ICommandeDAO {
    
    @Override
    public void create(Commande commande) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(commande);

            if (commande.getLignes() != null) {
                for (Ligne_Commande ligne : commande.getLignes()) {
                    ligne.setCommande(commande); //associer la ligne de commande a la commande
                    session.persist(ligne);
                }
            }
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    
    @Override
    public Commande findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Commande.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Commande> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT DISTINCT c FROM Commande c " +
                "LEFT JOIN FETCH c.client " +
                "LEFT JOIN FETCH c.lignes l " +
                "LEFT JOIN FETCH l.produit", 
                Commande.class
            ).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
