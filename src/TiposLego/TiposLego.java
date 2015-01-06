package TiposLego;

public enum TiposLego {
    ADMON_VENTANAS_PERSIANAS(1),
    ADMON_LUCES_AC(2),
    ADMON_PUERTA_ACCESO(3);

    private final int identificador;

    TiposLego(int identificador) {
        this.identificador = identificador;
    }

    public int getIdentificador() {
        return this.identificador;
    }
}
