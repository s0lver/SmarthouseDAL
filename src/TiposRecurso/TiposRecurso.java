package TiposRecurso;

public enum TiposRecurso {
    VENTANA(1),
    PERSIANA(2),
    LUCES(3),
    AIRE_ACONDICIONADO(4),
    PUERTA(5),
    ALARMA(8);

    private final int identificador;

    TiposRecurso(int identificador) {
        this.identificador = identificador;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public static TiposRecurso getTipoFromIdentificador(int id){
        switch(id){
            case 1:
                return VENTANA;
            case 2:
                return PERSIANA;
            case 3:
                return LUCES;
            case 4:
                return AIRE_ACONDICIONADO;
            case 5:
                return PUERTA;
            case 8:
                return ALARMA;
            default:
                throw new RuntimeException("No puedo convertir ese id en un tipo de recurso");
        }
    }
}
