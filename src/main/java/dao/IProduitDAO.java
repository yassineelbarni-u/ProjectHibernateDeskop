package dao;

import bo.Produit;
import java.util.List;


public interface IProduitDAO {
    void create(Produit produit);
    void update(Produit produit);
    void delete(Produit produit);
    Produit findById(int id);
    List<Produit> findAll();
    List<Produit> findAllInStock();
}
