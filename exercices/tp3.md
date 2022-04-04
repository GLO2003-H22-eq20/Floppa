# TP3

###Pipeline CI
1) Combien de temps passiez-vous à vérifier et tester manuellement le code lors des intégrations et des remises avant l'implantation du pipeline de tests automatisés?
   Approximativement 15 minutes étaient nécessaires pour les tests
2) Combien de temps passiez-vous à faire ces vérifications après l'implantation du CI?
   Nous regardons plus particulièrement les tests E2E avant l'implantation dans le CI, mais celui vient confirmer que le tout et bon, donc nous passons que quelques minutes à le regarder.
3) Quels sont les points positifs que le CI a apporté à votre processus? Donnez-en au moins 3.
   Un point fort du CI est qu'il s'assure que le code est bien structuré avec le checkstyle.
   Un autre point fort est qu'il assure à l'équipe que le code merger passe bien tous les tests, même ceux qui n'ont pas été touchés lors de l'implémentation de la feature.
   Un autre point est qu'il nous assure d'avoir une version fonctionnelle
4) Le pipeline CI amène-t-il un élément qui pourrait devenir négatif ou dangereux pour le processus, le produit et/ou l'équipe? Justifiez.
   Il peut être dangereux de justement trop s'y fier puisqu'il ne peut pas tout voir. En effet, il ne peut pas voir si la fonctionnalité répond bien à la demande du client. Il ne peut également pas voir si les tests ont tous bien été définis.

###Test
1) Quel proportion de temps passez-vous à faire l'implémentation du code fonctionnel versus celui des tests? Est-ce que cette proportion évolue au fil du temps? Pourquoi?
   La proportion est d'environ 50/50. La fonctionnalité prend habituellement plus de temps de réflexion pour l'implémenter tandis que les tests demandent habituellement moins de temps de réflexion, mais demandent plus de code.
2) L'implémentation de tests augmente naturellement la charge de travail. Comment cela a-t-il affecté votre processus? (ex : taille des issues/PRs, temps d'implémentation, planification, etc.)
   Effectivement les tests a augmenté la taille de nos issues puisque celles-ci demandent plus de temps d'implémentation. Pour ce qui est des PRs et de la planification nous n'avons pas noté de différence.
3) Avez-vous plus confiance en votre code maintenant que vous avez des tests? Justifiez.
   En effet, les tests nous procurent une certaine confiance supplémentaire pour notre code. Il nous permet de s'assurer rapidement que notre production est conforme à ce que nous attendons de nos fonctionnalités. Par contre, nous savons qu'ils ne peuvent pas tout vérifier. D'où l'importance de continuer à effectuer des révisions par les pairs.
4) Que pouvez-vous faire pour améliorer l'état actuel (début TP2) de vos tests? Donnez au moins 3 solutions.
   Avoir des métriques en lien avec les tests pour voir si on teste assez. Exemple: le test coverage
   Il serait bon d'aller vérifier si nous pouvons encore subdiviser nos tests pour être en mesure de mieux repérer les bugs lorsque ceux-ci arrivent.
   Dans l'optique de faire de meilleures tests unitaires, nos classes de tests devraient, dans la mesure du possible, mocker toutes les classes qui dépendent de la classe testée.

###Stories
1) "En tant que client, je souhaite pouvoir trier les produits en ordre alphabetique"
2) "En tant que client, je souhaite pouvoir rechercher un produit spécifique"
3) "En tant que client, je souhaite pouvoir afficher tous les produits d'une catégorie"

### Screenshots

1. Project
   ![Alt text](tp3_screenshots/Project.PNG?raw=true "1. Project")
2. Milestones
   ![Alt text](tp3_screenshots/Milestones1.PNG?raw=true "2. Milestones")
   ![Alt text](tp3_screenshots/Milestones2.PNG?raw=true "2. Milestones")
3. Issues
   ![Alt text](tp3_screenshots/Issue1.PNG?raw=true "3. Issue 1")
   ![Alt text](tp3_screenshots/Issue2.PNG?raw=true "3. Issue 2")
   ![Alt text](tp2_screenshots/Issue3.PNG?raw=true "3. Issue 3")
4. Pull requests
   ![Alt text](tp3_screenshots/PR1.PNG?raw=true "4. Pull request 1")
   ![Alt text](tp3_screenshots/PR2(1).PNG?raw=true "4. Pull request 2(1)")
   ![Alt text](tp3_screenshots/PR2(2).PNG?raw=true "4. Pull request 2(2)")
   ![Alt text](tp3_screenshots/PR3(1).PNG?raw=true "4. Pull request 3(1)")
   ![Alt text](tp3_screenshots/PR3(2).PNG?raw=true "4. Pull request 3(2)")
5. Arbre de commits et de branches
   ![Alt text](tp3_screenshots/Git_arbre_commits_branches.PNG?raw=true "4. Arbre commits et branches")