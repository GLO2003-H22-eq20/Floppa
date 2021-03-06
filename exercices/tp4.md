# TP4

## Rétrospective finale 
### Questions

#### 1) Décrivez 2 problématiques que possèdent votre processus et développer 2 plans distincts afin de les résoudres. Soyez constructifs dans vos critiques et évitez de mettre la faute sur une ou un groupe de personne en particulier.
La taille des issues a été quelques fois problématique puisque parfois certaines issues n'était vraiment pas de taille similaire. Cette situation a causé une division des tâches qui n'était pas toujours égale. D'un autre côté même si cela se produisait les autres membres de l'équipe n'ont jamais hésité à s'entraider ce qui atténuait cette difficulté. Une façon de résoudre ce problème serait de passer plus temps sur la création des issues et prendre le temps de bien évaluer et diviser les tâches qui nous sont demandé.\
Nous avons également eu une problématique au niveau du nommage des tests qui n'étaient pas tous nommé de la même façon. Il aurait été important au début du projet de spécifier dans la convention de code de spécifier une structure de test et un nommage uniforme pour qu'il n'y ait aucune ambiguïté.
#### 2) Décrivez la démarche que vous aviez entrepris afin d'intégrer de nouveaux outils technologiques. Quelles étaient les étapes du processus? Comment avez-vous réagis aux différents bogues? Exploriez-vous à l'aide de tests unitaires ou manuels? Qu'avez-vous appris suite à cette démarche ?\
La première étape était de lire la documentation de la nouvelle technologie à implanter dans notre projet. En effet, il était nécessaire de comprendre comment elle fonctionne, comment l'installer, comment la configurer et l'intégrer a notre projet. \
L'exploration s'est principalement fait de façon manuelle. Lors de celle-ci, plusieurs bugs sont survenus lors de l'intégration des nouvelles technologies, ce qui nous a obligé à passer beaucoup de temps avec le debugger et l'outil permettant de configurer la nouvelle technologie. \
Nous avons appris à la suite de la démarche qu'il était important de bien lire la documentation associée au nouvel outil et ne pas hésiter à aller chercher d'autres ressources externes.\
#### 3) Quels sont les bons coups de votre équipe? De quelles parties êtes-vous fiers? Nommez-en 3.\
Nous pensons que la communication constante dans notre équipe a été fortement bénéfique pour le déroulement du projet. Ça nous a permis d'être plus efficace dans certaines tâches et de résoudre des problématiques techniques plus rapidement. \
L’absence de conflit nous as permis de concentrer tous nos énergies vers les tâches à effectuer et de ne pas perdre du temps avec des activités qui n'avance pas le projet. \
Le CI est une partie dont on est particulièrement fier. La validation du code détecte et cible les erreurs plus tôt ce qui nous permet de déboguer le projet plus facilement. Le CI nous permet également de gérer la mise en forme du code et de voir que le système est en grosse partie fonctionnel avant d'être mis en production.\
#### 4) Quel conseil donneriez-vous aux prochains étudiants qui doivent faire ce projet?\
Un des conseils serait de ne pas trop attendre avant de commencer coder. Surtout lorsque l'intégration de nouvelles technologies sont nécessaires.\ 
Également de ne pas attendre avant de faire du refactoring pour avoir une bonne architecture, puisque c'est de plus en plus difficile et plus long restructurer le code lorsque tu attends trop.\
#### 5) Quels apprentissages, trucs ou techniques appris dans ce projet croyez-vous pouvoir utiliser plus tard? Décrivez-en au moins 2. Cela peut être des apprentissages techniques, pratiques, sur le travail d'équipe ou encore par rapport au processus.\
Il est quasi impossible de passer à côté d'un environnement CI/CD. Ce processus nous permet entre autre d'avoir un développement plus agile, d'avoir une meilleure qualité du code et d'avoir des cycles de test beaucoup plus fréquent.\
L'utilisation de technologie cloud est un gros avantage qui permet à n'importe quel équipe de développement d'avoir un déploiement rapide et une simplicité d’intégration.\

## Outils de métriques

### Screenshots for metric tools
1. Global view
   ![Alt text](tp4_screenshots/global-vue.PNG?raw=true "1. Global view")
2. Code Analysis
   ![Alt text](tp4_screenshots/error-prone.PNG?raw=true "2. Code Analysis")
   ![Alt text](tp4_screenshots/code-style.PNG?raw=true "2. Code Analysis")
3. Security Vulnerabilities (0 found)
   ![Alt text](tp4_screenshots/security.PNG?raw=true "3. Security")
4. Code Coverage
   ![Alt text](tp4_screenshots/test-coverage.PNG?raw=true "3. Issue 1")
   ![Alt text](tp4_screenshots/test-example-coverage.PNG?raw=true "3. Issue 2")
   ![Alt text](tp4_screenshots/example-datastore-provider.PNG?raw=true "3. Issue 3")

## Planification du travail sur Github

### Screenshots for project
1. Project
   ![Alt text](tp4_screenshots/Project.PNG?raw=true "1. Project")
2. Milestones
   ![Alt text](tp4_screenshots/Milestones.PNG?raw=true "2. Milestones")
3. Issues
   ![Alt text](tp4_screenshots/Issue1.PNG?raw=true "3. Issue 1")
   ![Alt text](tp4_screenshots/Issue2.PNG?raw=true "3. Issue 2")
   ![Alt text](tp4_screenshots/Issue3.PNG?raw=true "3. Issue 3")
4. Pull requests
   ![Alt text](tp4_screenshots/PR1.PNG?raw=true "4. Pull request 1")
   ![Alt text](tp4_screenshots/PR2.PNG?raw=true "4. Pull request 2(1)")
   ![Alt text](tp4_screenshots/PR3.PNG?raw=true "4. Pull request 3(1)")
5. Arbre de commits et de branches
   ![Alt text](tp4_screenshots/Git_arbre_commits_branches.PNG?raw=true "4. Arbre commits et branches")

## Description de la story

### Statistique de visionnements
****
#### Description

En tant que vendeur, je peux voir le nombre de visionnements de chacun de mes produits.
## Route
`GET /sellers/@me/stats`

#### Critères de succès

1. Le compteur de visionnement augmente du produit quand un utilisateur le récupère par son id.
2. Les nombre de visionnements de tous les produits d'un vendeur sont visible dans la réponse.
3. Un produit commence avec aucun visionnement.

#### Détails techniques
##### Requête

`GET /sellers/@me/stats`

###### Headers

- `X-Seller-Id` : `string`
   - ID du vendeur.
##### Réponse
##### Body
```ts
{
    products: [
        {
            id: string,
            title: string,
            views: number
        }
    ]
}
```

###### Status

- `200 OK`

##### Exceptions

- `ITEM_NOT_FOUND` si le seller n'existe pas.
- `MISSING_PARAMETER` si un des champs est manquant (`null`).

## Open source

### Nommez 3 avantages à contribuer à des projets open source en tant qu’entreprise.
1) Le premier avantage est qu’il est possible d’améliorer le logiciel qu’on utilise. Comme le décrit le texte
   “How to contribute to Open Source”, souvent, ceux qui viennent apporter des améliorations au logiciel sont
   des utilisateurs en premier. Cela veut donc dire qu’ils voient un défaut, ils décident de le régler en apportant
   une correction et lorsque la prochaine livraison arrive, le problème ne sera plus présent. L’expérience utilisateur
   s’en vient donc améliorée. La correction de ces erreurs peut bénéficier à tout le monde puisque si quelqu’un a vu une
   erreur dans le logiciel, il n’est surement pas le seul. Bien sûr, la correction doit être acceptée (un des 4 types de
   verdicts présenté dans le document), mais s’il est accepté, on peut être sûr que le problème était assez gros ou assez
   limitant pour que l’expérience utilisateur en soit amputée. Donc les changements sont bénéfiques pour tous.

2) Le deuxième avantage est qu’il est possible d’aller chercher de l’aide dans ce genre de projet et aussi d’apporter de
   l’aide. En effet, le rôle de mentorat et le rôle d’étudiant peuvent être rempli grâce aux corrections open source.
   On peut mentorer pour expliquer les changements qu’on a voulu apporter au logiciel et ainsi les changements peuvent
   servir d’apprentissage aux autres. Ensuite, les changements d’autre personne peuvent nous servir puisqu’on apprend
   possiblement de nouvelles techniques. Il est donc clair que tous peuvent trouver du bon dans cet échange de
   connaissances.

3) Le troisième avantage qui peut être bénéfique est de créer des artéfacts publics qui permettent de se créer une
   réputation. En effet, puisque le document est open source n’importe qui peut prendre votre travail et le démontrer
   (en citant bien sûr). En se faisant prendre en exemple ou tout simplement en étant présent sur les sites de
   programmation on peut se construire une réputation et par le fait même indiquer que la compagnie pour laquelle
   on travaille possède des atouts considérables niveau personnel.

### Décrivez 3 défis qu’impose la mise en place d’un projet open source et justifiez.
La mise en place d’un projet open source peut contenir quelques défis pour que ce dernier fonctionne bien. Que
ce soit au niveau des développeurs qui vont participer au projet librement ou bien au niveau technique, plusieurs
éléments sont à considérer durant la mise en place d’un projet open source.

1) Le premier défi est de trouver un nom qui n’entre pas en conflit avec un projet existant. En effet, pour créer un
   projet open source il faut trouver un nom qui ne cause aucun conflit. Ces conflits pourraient résulter en une
   confusion pour les utilisateurs. Si deux projets sont nommés de la même façon, il est difficile de se retrouver.
   Ensuite, il peut y avoir des conflits qui peuvent se rendre en cour. Il est donc impératif de bien choisir le nom
   du projet pour éviter ces problèmes inutiles. De plus, il est possible de regarder le WIPO Global Brand Database pour
   s’assurer que notre nom n’entre en conflit avec aucun autre nom de projet recensé.

2) Le deuxième défi est d’instaurer un code de conduite. Il peut être complexe d’instaurer un code de conduite pour
   les parties prenantes du logiciel. C’est un défi de gestion pour un propriétaire de document open source de gérer
   l’activité sur le site. Il est possible de produire un code de conduite qui restreint les actions possibles.
   Il est impératif d’avoir un code de conduite pour éviter les débordements. Il n’est pas toujours aisé de gérer
   des situations épineuses et ce document permet de les gérer plus efficacement.

3) Le troisième défi est la manière dont on écrit et dont on code. En effet, la manière de coder le logiciel va
   venir influencer grandement comment les gens le voit. Si c’est du code dépourvu de toute architecture et que
   c’est du code novice, il est possible que notre réputation en soit impactée. De plus, tout ce qui a trait au
   document format texte, doit être écrit de manière irréprochable, il ne faut pas d’erreur de grammaire ou
   de syntaxe, sans quoi notre réputation pourrait en être affectée. Pour finir, il est important d’écrire avec un
   langage populaire puisque tous ne parlent pas anglais au même niveau. Cela facilite donc la compréhension des
   différents utilisateurs.

### Quelle information vous a-t-elle le plus surprise à propos de l’open source?
Nous avons été surpris du nombre de documents parallèles à produire lorsqu’on décide de se lancer dans l’open
source. En effet, si on ne se penche pas réellement sur la question, on ne se rend pas compte qu’il faut par
exemple une licence, un code de conduite, un document pour les contributions, une liste de pré-lancement et
autres. Cela fait beaucoup de documents qui ne sont pas directement relié au code. Cela prouve donc que dans
le développement logiciel il n’y a pas que le code, il y a tous les aspects qui entourent le code qui sont aussi
important si on veut se faciliter la vie et si on veut éviter les problèmes au fil de l’avancement.

### Pourquoi avoir choisi le template utilisé dans le code de conduite?
Source: https://www.contributor-covenant.org/version/2/1/code_of_conduct/code_of_conduct.md

Tout d'abord nous avons décidé d'adopter le Contributor Convenant puisque nous adhérons aux principes véhiculés à
l'intérieur de ce dernier. Nous voulions adopter un code de conduite éprouvé qui a été accepté par des centaines
de milliers de communautés Open Source. Nous voulions aussi être certain que le document ne reste pas static, ce qu'on
entend par là est que nous souhaitons voir un code de conduite qui évolue avec l'air du temps. Le Contributor Convenant
possède à ce jour une demi-douzaine de version, cela nous démontre donc que le document est en constante évolution et
reste à jour.

Nous avons choisi ce code de conduite puisque :
1) Leur engagement, présenté en début de code comporte des valeurs qui sont très près des nôtres. Nous sommes persuadés
   que la communauté du logiciel devrait être un endroit exempt de toute forme de harcèlement. De plus, l'engagement
   présenté est inclusif pour tout type de personne. Nous trouvons que l'inclusion démontrée par ce code de conduite nous
   correspond et nous ne pourrions trouver mieux.
2) Les exemples fournis nous donne un guide pour agir positivement. Il y a plusieurs codes de conduite qui ne font que
   présenter les sentences ou les réprimandes, mais très peu viennent donner des idées sur comment garder la communauté
   positive. Ces exemples viennent mettre les choses en perspective et apportent, selon nous, un plus à ce code de
   conduite.
3) Nous avons ensuite analysé les exemples de mauvaises pratiques qui contribuent à un climat toxique. Nous avons
   passé point par point et avons trouvé que chacun a sa place dans le code de conduite. De plus, nous avons trouvé
   qu'inclure "Other conduct which could reasonably be considered inappropriate in a professional setting" est un geste
   très intelligent puisque ça laisse la porte ouverte à d'autres situations qui n'ont pas été incluse par faute d'espace.
4) Pour ce qui est des responsabilités nous avons trouvé adéquat le niveau de pouvoir donner aux dirigeants. Nous
   trouvons juste que les comportements déplacés soient puni par la suppression du matériel compromettant et que les
   dirigeants communique avec la personne les décisions qui ont été prises.
5) Pour finir, nous avons réfléchi longuement sur les conséquences que devraient encourir les personnes qui ne
   respectent pas les règles. Le code de conduite présenté défini 4 types de déviations. Nous trouvons que 4 est un nombre
   correct et que plus deviendrait trop complexe à gérer et que moins de 4 ne nous donne pas assez de jeu pour trouver le bon
   verdict. Le verdict est en fonction de la gravité de l'action fautive ce qu'on trouve très bien. D'autres codes de
   conduite ne regarde que le nombre d'infractions sans se pencher sur la gravité de ces dernières. Nous pensons que les
   sanctions devraient être appliquées en fonction de la gravité de l'action posée et c'est ce qui est décrit dans le
   Contributor Convenant.
   Source: https://www.contributor-covenant.org/version/2/1/code_of_conduct/code_of_conduct.md

### Pourquoi avoir choisi cette licence par rapport aux autres?
Nous avons choisi d'utiliser la licence MIT.
1) Nous avons choisi cette licence à cause de sa permissivité. En effet, c'est une licence qui est très permissive et
   qui est très ouverte aux nouveaux utilisateurs. Que ce soit pour des développeurs Open Source ou des entreprises, cette
   licence est accueillante. Elle permet à tout type d'utilisateurs d'utiliser le logiciel en autant qu'il inclue la
   licence dans son travail.
2) La deuxième raison pour laquelle nous avons choisi cette licence est sa simplicité. En effet, puisque notre
   logiciel est simple, nous voulions avoir une licence permissive qui était simple à comprendre. Nous trouvons que le
   niveau de complexité de la licence devrait être identique au niveau de complexité du logiciel. La licence oblige
   essentiellement l'utilisateur du code Open Source à mettre une copie de la licence dans le travail dérivé du travail
   original. C'est simple et direct ce qui est un parfait candidat pour notre situation. Si nous voulons avoir des
   contributions, de l'aide extérieure et des ajouts par des particuliers, il ne faut pas effrayer les développeurs avec
   une licence incompréhensible et trop complexe.
3) La troisième raison nous provient de l'article "Why I use the MIT License" publié par Bill Patrianakos qui explique
   pourquoi il utilise la licence MIT s'il veut monétiser son logiciel. Il raconte qu'il utilise les changements apportés
   par les utilisateurs de son logiciel pour modifier son propre logiciel et ainsi le rendre plus attrayant aux
   utilisateurs. Cet argument nous a convaincus, nous avons décidé qu'il serait bien, d'avoir une licence très permissive
   pour monitorer, en quelque sorte, les changements des utilisateurs du logiciel pour ainsi apporter des correctifs et
   des améliorations au nôtre. C'est grâce à la permissivité de la licence qu'on peut se permettre de faire ce genre
   d'expérience et ainsi accroître nos performances. La licence MIT est donc le choix idéal.

### Pourquoi avoir choisi ce template des contributions?
En cherchant sur le web pour trouver un bon exemple de guide de contributions, nous avons trouvé celui présenté dans le
fichier Contributions.md. Le modèle a été modifié pour comprendre les essentiels de notre projet Floppa. Nous avons
choisi ce modèle puisque nous trouvions qu'il avait beaucoup de points en communs avec notre projet. Par exemple,
l'utilisation de GitHub ou bien l'utilisation de la même licence que nous. Le modèle nous semblait bon puisque dès
le départ, il offre des options à l'utilisateur. Ensuite, pour les changements, il est clairement dit que c'est via des
pull requests, ce qui est notre cas. Tous changements doivent passer par des pull requests et être acceptés par deux
individus (qui ont les permissions nécessaires). Ensuite, une brève description des étapes à suivre pour faire parvenir
une pull request avec les prérequis est inclus. Cette description s'applique à Floppa donc rien à changer de ce côté.
Pour ce qui est des bugs, une description des étapes à suivre est fournie. Cela donne à l'utilisateur une petite idée
de ce qu'on veut voir pour résoudre rapidement et efficacement le problème. Cette description nous satisfait. Pour
finir, une note sur la licence est ajoutée pour mettre en garde les utilisateurs et ainsi éviter tout type de problème
au niveau de la licence. Étant notre premier guide des contributions à produire, nous avons décidé d'y aller avec un
document déjà produit pour s'assurer de ne rien oublier. De plus, nous avions de la difficulté à venir avec les idées
essentielles qui devaient s'y trouver. Ce guide nous semble adéquat, léger et facile à comprendre. De plus, il est
détaillé pour permettre à tout type d'utilisateur de soumettre une pull request ou tout simplement rapporter un bug.
Source: https://gist.github.com/briandk/3d2e8b3ec8daf5a27a62#file-contributing-md
