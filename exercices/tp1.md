# TP1


##Branches
### Quand faire une branche
 1. Lorsqu'on veut développer une nouvelle fonctionnalité à Master.
 2. Faire un fix sur master.
 3. Rajouter de la documentation à Master.
 4. Quand on souhaite ajouter à Master.

Si nous sommes deja dans une branche de feature, faire les changements requis dans cette branche.

### Comment nommer nos branches
feat/nom-significatif
fix/...
doc/...
ci/...
etc.

Voir https://www.conventionalcommits.org/en/v1.0.0/

###Branches de base
Nous allons nous baser sur le Trunk-based development donc nous allons avoir :
1. Master
2. Branches de feature/fix/etc
3. Branches de releases

###Branche principale
Master

##Commits
###Comment nommer les commits
Suivre Conventional Commits https://www.conventionalcommits.org/en/v1.0.0/

###Quoi et quand commiter ?
Il n'y a pas de requis minimum pour un commit excepté que l'application doit compiler.
Ex : Faire un commit "random" pour qu'un collègue puisse continuer.

##Pull request
Nous pouvons ouvrir une pull request quand :
1. Une feature est complète.
2. Un fix est nécessaire.
3. Une unité de travail complete.

Une pull request doit être approuvé par 2 membres de l'équipe pour être mergée.

Lors de la review, les membres de l'équipe peuvent faire des demandes de changement.

Merge vers Master sauf en cas d'exception (hotfix sur branche de release, etc.)

##Screenshots


1. Project
![Alt text](tp1_screenshots/Project.PNG?raw=true "1. Project")
2. Milestones
![Alt text](tp1_screenshots/Milestones.PNG?raw=true "2. Milestones")
3. Issues
![Alt text](tp1_screenshots/Issue1.PNG?raw=true "3. Issue 1")
![Alt text](tp1_screenshots/Issue2.PNG?raw=true "3. Issue 2")
![Alt text](tp1_screenshots/Issue3.PNG?raw=true "3. Issue 3")
4. Pull requests
![Alt text](tp1_screenshots/PR1.PNG?raw=true "4. Pull request 1")
![Alt text](tp1_screenshots/PR2.PNG?raw=true "4. Pull request 2")
![Alt text](tp1_screenshots/PR3.PNG?raw=true "4. Pull request 3")