# TP1


##Branches
### Quand faire une  branche
 1. Lorsqu'on veut développer une nouvelle fonctionnailité à Master
 2. Faire un fix sur master à Master
 3. Rajouter de la documentation à  Master
 4. Quand on souhaite ajouter à Master

Si nous sommes deja dans une branche, faire les changements recquis dans cette branche.

### Comment nommer nos branches
feat/nom-significatif
fix/...
doc/...
ci/...
etc.

Voir https://www.conventionalcommits.org/en/v1.0.0/

###Branches de base
Nous allons nous baser sur le Trunk-based development donc nous allons avoir:
1. Master
2. Branches de feature/fix/etc
3. Branches de releases

###Branche principale
Master

##Commits
###Comment nommer les commits
Suivre Conventional Commits https://www.conventionalcommits.org/en/v1.0.0/

###Quoi et quand commiter ?
Il n'y a pas de requis minimum pour commit excepté que ça doit  compiler.
Ex:  Faire un commit "random" pour qu'un collègue puisse continuer.

##Pull request
Nous pouvons ouvrir une pull request quand :
1. Une feature est complète
2. Un fix est  nécéssaire
3. Une unité de travail complete.

Une pull request doit être approuvé par 2 membres de l'équipe pour être mergé.

Lors de la review, les membres de l'équipe peuvent faire des demandes de changement.

Merge vers Master sauf en cas d'exception (hotfix sur branche de release, etc.)
