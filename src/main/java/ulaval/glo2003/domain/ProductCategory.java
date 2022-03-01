package ulaval.glo2003.domain;

public enum ProductCategory {
    SPORTS {
        public String toString() {
            return "sports";
        }
    },
    ELECTRONICS {
        public String toString() {
            return "electronics";
        }
    },
    APPAREL {
        public String toString() {
            return "apparel";
        }
    },
    BEAUTY {
        public String toString() {
            return "beauty";
        }
    },
    HOUSING {
        public String toString() {return "housing";}
    },
    OTHER {
        public String toString() {
            return "other";
        }
    }
}
