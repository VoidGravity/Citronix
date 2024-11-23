package org.abdellah.citronix.model;

public enum ArbreStatus {
    JEUNE(2.5),
    MATURE(12.0),
    VIEUX(20.0),
    NON_PRODUCTIF(0.0);

    private final double productionParSaison;

    ArbreStatus(double productionParSaison) {
        this.productionParSaison = productionParSaison;
    }

    public double getProductionParSaison() {
        return productionParSaison;
    }
}
