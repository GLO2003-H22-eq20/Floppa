# TP4

# Statistique de visionnements
****
## Description

En tant que vendeur, je peux voir le nombre de visionnements de chacun de mes produits.
## Route
`GET /sellers/@me/stats`

## Critères de succès

1. Le compteur de visionnement augmente du produit quand un utilisateur le récupère par son id.
2. Les nombre de visionnements de tous les produits d'un vendeur sont visible dans la réponse.
3. Un produit commence avec aucun visionnement.

## Détails techniques
### Requête

`GET /sellers/@me/stats`

#### Headers

- `X-Seller-Id` : `string`
    - ID du vendeur.
### Réponse
###Body
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

#### Status

- `200 OK`

### Exceptions

- `ITEM_NOT_FOUND` si le seller n'existe pas.
- `MISSING_PARAMETER` si un des champs est manquant (`null`).

### Screenshots
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