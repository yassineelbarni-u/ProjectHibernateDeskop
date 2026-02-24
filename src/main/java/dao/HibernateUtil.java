package dao;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;


public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static {
		try {
			Configuration config = new Configuration();
			sessionFactory = config.configure("hibernate.cfg.xml").buildSessionFactory();
			System.out.println("SessionFactory créée avec succès");
		} catch (HibernateException e) {
			System.err.println("Erreur lors de la création de la SessionFactory");
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * Retourne l'instance unique de SessionFactory
	 * @return SessionFactory partag\u00e9e
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			throw new IllegalStateException("SessionFactory n'a pas \u00e9t\u00e9 initialis\u00e9e correctement");
		}
		return sessionFactory;
	}
	
	/**
	 * Ferme proprement la SessionFactory lors de l'arr\u00eat de l'application
	 */
	public static void shutdown() {
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
			System.out.println("SessionFactory fermée proprement");
		}
	}
	
	/**
	 * M\u00e9thode de test
	 */
	public static void main(String[] argv) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		System.out.println("SessionFactory: " + sf);
		HibernateUtil.shutdown();
	}
}
