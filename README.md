# 🛒 Application de Gestion Commerciale - Hibernate Desktop

[![Java](https://img.shields.io/badge/Java-11%2B-orange.svg)](https://www.oracle.com/java/)
[![Hibernate](https://img.shields.io/badge/Hibernate-6.2.22-green.svg)](https://hibernate.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0.33-blue.svg)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.x-red.svg)](https://maven.apache.org/)

## 📖 Description

Projet Maven Desktop de gestion de commandes développé dans le cadre de la formation **ILISI** (2ème année cycle ingénieur), module **Frameworks JEE**.

Application complète utilisant **Hibernate 6.2.22** pour la persistance des données avec **MySQL 8.0.33**.

## ✨ Fonctionnalités

- ✅ **Gestion des Clients** (Ajout, Modification, Suppression, Recherche)
- ✅ **Gestion des Produits** (Avec gestion de stock)
- ✅ **Gestion des Commandes** (Création, Suivi)
- ✅ **Lignes de Commande** (Détails des produits commandés)
- ✅ **Interface Graphique Moderne** (Java Swing + Nimbus Look and Feel)
- ✅ **Auto-génération des IDs** (Clés primaires automatiques)

## 🏗️ Architecture

Le projet suit une **architecture MVC en 6 couches**:

| Couche | Package | Rôle |
|--------|---------|------|
| **View** | `view` | Interface graphique Swing |
| **Controller** | `controller` | Coordination des requêtes |
| **Service** | `service` | Logique métier |
| **DAO** | `dao` | Accès aux données |
| **Entity** | `bo` | Entités JPA (@Entity) |
| **DTO** | `dto` | Objets de transfert |

```
View → Controller → Service → DAO → Database
```

## 📚 Documentation Complète

### 🎯 [MES_AMELIORATIONS.md](./MES_AMELIORATIONS.md) ⭐ **NOUVEAU**
**Améliorations personnelles apportées au projet original** avec:
- Auto-génération automatique de la date des commandes
- Refonte complète de l'interface utilisateur (Form_Main)
- Nettoyage du code (37+ commentaires inutiles supprimés)
- Suppression des emojis causant des artefacts visuels
- Métriques d'amélioration et comparaisons avant/après
- **📌 Document démontrant mes contributions au projet cloné depuis GitHub**

### 📘 [GUIDE_PROJET.md](./GUIDE_PROJET.md)
**Guide complet du projet** avec:
- Architecture détaillée de chaque couche
- Explication des concepts (Entity vs DTO, Singleton, etc.)
- Modèle de données et relations
- Corrections critiques appliquées
- Questions fréquentes

### 🔄 [FLUX_DONNEES.md](./FLUX_DONNEES.md)
**Flux de données expliqué** avec:
- Diagramme de séquence complet
- Exemple concret: "Ajouter un Client"
- Code détaillé de chaque étape
- Requêtes SQL générées

## 🚀 Installation et Lancement

### Prérequis

- **Java JDK 11+** ([Télécharger](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.x** ([Télécharger](https://maven.apache.org/download.cgi))
- **MySQL 8.0+** ([Télécharger](https://dev.mysql.com/downloads/))

### Configuration Base de Données

1. **Créer la base de données:**
```sql
CREATE DATABASE gestion_commande CHARACTER SET utf8mb4;
```

2. **Exécuter le script SQL:**
```bash
mysql -u root -p gestion_commande < script.sql
```

3. **Configurer `hibernate.cfg.xml`:**
```xml
<property name="hibernate.connection.url">
    jdbc:mysql://localhost:3306/gestion_commande?useSSL=false
</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">votre_password</property>
```

### Lancer l'Application

**Option 1: Via Maven**
```bash
mvn clean compile exec:java
```

**Option 2: Via IDE (VS Code, IntelliJ, Eclipse)**
- Ouvrir `src/main/java/view/Form_Main.java`
- Cliquer sur **Run** / **Exécuter**

## 📊 Modèle de Données

```
Client (1) ────< (N) Commande (1) ────< (N) Ligne_Commande >──── (N) Produit
   │                    │                         │                     │
   ├─ id (PK)           ├─ idcmd (PK)            ├─ idligne (PK)       ├─ id (PK)
   ├─ nom              ├─ datecmd               ├─ quantite           ├─ libelle
   ├─ capital          ├─ id_client (FK)        ├─ id_commande (FK)  ├─ prix
   └─ adresse          └─                       └─ id_produit (FK)   └─ qtstock
```

## 🔧 Corrections Majeures Appliquées

### ✅ 1. **Fuite Mémoire HibernateUtil**
**Avant:** Nouvelle SessionFactory créée à chaque appel  
**Après:** Pattern Singleton avec initialisation statique

### ✅ 2. **État Statique CommandeService**
**Avant:** Variable static partagée (corruption de données)  
**Après:** Instance par objet (thread-safe)

### ✅ 3. **Clés Étrangères Mal Nommées**
**Avant:** `@JoinColumn(name="id")` (ambiguë)  
**Après:** `@JoinColumn(name="id_client")` (explicite)

### ✅ 4. **Interface Graphique Modernisée**
- Champs agrandis (30px hauteur)
- IDs auto-générés
- Boutons colorés avec emojis
- Couleurs modernes (palette professionnelle)

## 🧪 Tests

Lancer les tests unitaires:
```bash
mvn test
```

## 📁 Structure du Projet

```
src/main/java/
├── bo/                      # Entités JPA
│   ├── Client.java
│   ├── Produit.java
│   ├── Commande.java
│   └── Ligne_Commande.java
├── controller/              # Contrôleurs
│   ├── ClientController.java
│   └── ...
├── dao/                     # Data Access Objects
│   ├── ClientDAO.java
│   ├── HibernateUtil.java
│   └── ...
├── dto/                     # Data Transfer Objects
│   ├── ClientDTO.java
│   └── ...
├── service/                 # Logique métier
│   ├── ClientService.java
│   └── ...
├── view/                    # Interfaces Swing
│   ├── Form_Main.java
│   ├── Form_Client.java
│   └── ...
└── exception/               # Exceptions métier
    └── ClientNotFoundException.java

src/main/resources/
└── hibernate.cfg.xml        # Configuration Hibernate
```

## 🛠️ Technologies Utilisées

| Technologie | Version | Utilisation |
|-------------|---------|-------------|
| Java | 11+ | Langage principal |
| Hibernate | 6.2.22.Final | ORM (JPA) |
| MySQL Connector | 8.0.33 | Driver JDBC |
| Maven | 3.x | Build & Dépendances |
| Java Swing | Built-in | Interface graphique |

## 👥 Auteurs

**Projet ILISI - 2ème Année Cycle Ingénieur**  
Module: Frameworks JEE  
Encadrant: **Pr. Omar EL BEGGAR**

## 📝 Licence

Projet académique - ILISI 2024-2025

## 🆘 Support

Pour toute question:
1. Consulter [GUIDE_PROJET.md](./GUIDE_PROJET.md)
2. Consulter [FLUX_DONNEES.md](./FLUX_DONNEES.md)
3. Vérifier les logs de la console
4. Contacter l'équipe pédagogique

---

**⭐ N'oubliez pas de consulter la documentation complète dans GUIDE_PROJET.md !**
