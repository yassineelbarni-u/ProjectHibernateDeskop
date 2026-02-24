package dao;

import bo.Commande;
import java.util.List;

/**
 * Interface pour les opérations DAO Commande
 * Permet le découplage entre service et implémentation
 */
public interface ICommandeDAO {
    void create(Commande commande);
    Commande findById(int id);
    List<Commande> findAll();
}
