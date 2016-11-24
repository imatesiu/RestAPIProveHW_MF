package isti.cnr.sse.rest.data.pojo;

/**
 * Created by m4rt3 on 20/11/2016.
 */

public enum CondizioniProva {

    Normale (1), //1 scontrino ogni 4 Minuti
    Fast (2),  // 1 scontrino ogni minuto 15 linee 15 caratteri
    Continuo (3),
    AlimentatoSenzaEmissione (4),
    NonAlimentato (5);

    CondizioniProva(int i) {
    }

    public static CondizioniProva get(String tipo) {
        return null;
    }
}
