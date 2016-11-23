
package isti.cnr.sse.rest.data.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class ProvaHW implements Serializable, Comparable<ProvaHW> {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Matricola")
    @Expose
    private String matricola;

    @SerializedName("Modello")
    @Expose
    private String modello;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Tipo")
    @Expose
    private String tipo;

    @SerializedName("Esito")
    @Expose
    private Esito esito;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("TimeStartPHW")
    @Expose
    private String timeStartPHW;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("TimeEndPHW")
    @Expose
    private String timeEndPHW;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Userid")
    @Expose
    private String userid;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Note")
    @Expose
    private Note note;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Allegati")
    @Expose
    private Allegati allegati;
    private boolean edited;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The matricola
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     * 
     * (Required)
     * 
     * @param matricola
     *     The Matricola
     */
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * 
     * (Required)
     * 
     * @param tipo
     *     The Tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEsito(Esito esito) {
        this.esito = esito;
    }

    public Esito getEsito() {
        return this.esito;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The timeStartPHW
     */
    public String getTimeStartPHW() {
        return timeStartPHW;
    }

    /**
     * 
     * (Required)
     * 
     * @param timeStartPHW
     *     The TimeStartPHW
     */
    public void setTimeStartPHW(String timeStartPHW) {
        this.timeStartPHW = timeStartPHW;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The timeEndPHW
     */
    public String getTimeEndPHW() {
        return timeEndPHW;
    }

    /**
     * 
     * (Required)
     * 
     * @param timeEndPHW
     *     The TimeEndPHW
     */
    public void setTimeEndPHW(String timeEndPHW) {
        this.timeEndPHW = timeEndPHW;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 
     * (Required)
     * 
     * @param userid
     *     The Userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The note
     */
    public Note getNote() {
        return note;
    }

    /**
     * 
     * (Required)
     * 
     * @param note
     *     The Note
     */
    public void setNote(Note note) {
        this.note = note;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The allegati
     */
    public Allegati getAllegati() {
        if(allegati!=null) {
            return allegati;
        }
        allegati = new Allegati();
        return allegati;
    }

    /**
     * 
     * (Required)
     * 
     * @param allegati
     *     The Allegati
     */
    public void setAllegati(Allegati allegati) {
        this.allegati = allegati;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    @Override
    public String toString() {
        return "ProvaHW{" +
                "matricola='" + matricola + '\'' +
                ", modello='" + modello + '\'' +
                ", tipo='" + tipo + '\'' +
                ", esito=" + esito +
                ", timeStartPHW='" + timeStartPHW + '\'' +
                ", timeEndPHW='" + timeEndPHW + '\'' +
                ", userid='" + userid + '\'' +
                ", note=" + note +
                ", allegati=" + allegati +
                ", edited=" + edited +
                '}';
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
    public boolean isEdited(){
        return edited;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof ProvaHW){
            ProvaHW pp = (ProvaHW) other;
            boolean flag = TipoProve.get(pp.getTipo()).equals(TipoProve.get(this.getTipo()));
            if(pp.getTimeStartPHW().equals(this.getTimeStartPHW()) && pp.getMatricola().equals(this.getMatricola()) && flag){
                return true;
            }
        }
        return false;
    }


    @Override
    public int compareTo(ProvaHW o) {
        //TODO impl it
        return 0;
    }

    public void merge(ProvaHW nphw) {
        this.getAllegati().merge(nphw.getAllegati().getAllegato());
        this.setEsito(nphw.getEsito());
    }
}

