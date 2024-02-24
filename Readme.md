# Document storage

## Spécifications

### Principes

Les informations non structurées sont partout dans nos SI et cherchent encore leur place. Leurs cousines, les informations structurées sont arrivées à maturité et ont des solutions de stockage moderne, robuste et multiple. Les informations non structurées doivent faire leur mue: c'est le but de ce projet.

De par leur lien avec le monde réel, les documents ont souvent une réalité physique bien antérieure aux SI avec leurs processus de modification, workflow de validation, signature, archivage, stockage, sécurisation, recherche, indexation, etc.

Les pistes explorées et déjà existantes:
- des solutions de stockage brute: file system, S3
- des solutions tout en un permettant de gérer toutes les problématiques citées au dessus: alfresco, documentum, datafile, etc

Dans une démarche KISS, on cherche à appliquer le principe séparation des responsabilités (très proche du 1er principe de SOLID).

### Ce qu'il nous faut (ou pas)

- un système de stockage brute des informations
- des meta-données ou index
- une gestion de la sécurisation des documents
- un système de recherche
- un système d'accès/modification/création/suppression de nos documents


#### le stockage brute

De multiples système existent déjà dans ce domaine. Il faudra donc s'appuyer sur les systèmes existants en restant générique pour limiter les adhérences et futures adaptations. L'organisation des données sur le support sera spécifique pour tirer le meilleur partie des performances du sous-système.

#### les meta-données

A l'image de la gestion des données structurés, il est pratique et efficace d'avoir des informations synthétiques sur des documents sans structure. Ces index peuvent permettent de remplir de multiples fonctions:
- organisation logique des documents suivant plusieurs perspectives (répertoire par artiste ou compositeur exemple)
- versioning des documents
- cycle de validation (métier (workflow métier) ou technique (anti virus par exemple)) et statut
- mot clés, auteur, etc extrait du contenu

Ces meta-données peuvent être inhérentes au document, à son contenu mais aussi annexes et complémentaire.

##### meta-données inhérentes

Ces données peuvent être extraites à l'aide d'outil dédié:
- type de contenu: text, image, tableau, etc
- mot clés
- références

Ces outils seront adaptable et pourront être multiple: on parlera de la chaîne d'extraction des meta-données


##### meta-données construites

Ces données seront fournies à la solution de stockage, à la création ou pas enrichissement/remplacement progressif.

##### stockage des meta-données

Ces meta-données se présentent sous forme de clé-valeur ou clé-liste de valeurs. De nombreuses solutions dédiées existent pour réaliser ce stockage. Ces solutions pourront être branchées au système de stockage.




### Solution proposée

- conception hexagonale pour s'adapter:
    - à plusieurs stockage brutes (FS, S3 ou autre)
    - à plusieurs moteur d'index (solr, lucène, mongo ou autre)


