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
- `INVALID_PARAMETER` si un des champs est invalide.
- `MISSING_PARAMETER` si un des champs est manquant (`null`).
