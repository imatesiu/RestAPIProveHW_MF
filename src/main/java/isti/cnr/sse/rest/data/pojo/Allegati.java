
package isti.cnr.sse.rest.data.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Allegati implements Serializable {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Allegato")
    @Expose
    private List<Allegato> allegato;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The allegato
     */
    public List<Allegato> getAllegato() {
        if(allegato!=null) {
            return allegato;
        }
        allegato = new ArrayList<Allegato>();
        return allegato;
    }

    /**
     * 
     * (Required)
     * 
     * @param allegato
     *     The Allegato
     */
    public void setAllegato(List<Allegato> allegato) {
        this.allegato = allegato;
    }

    @Override
    public String toString() {
        return "Allegati{" +
                "allegato=" + allegato +
                '}';
    }

    public int size() {
        return getAllegato().size();
    }

    public void merge(List<Allegato> allegati){
        for (Allegato a: allegati) {
            if(!getAllegato().contains(a)){
                getAllegato().add(a);
            }
        }

    }
}
