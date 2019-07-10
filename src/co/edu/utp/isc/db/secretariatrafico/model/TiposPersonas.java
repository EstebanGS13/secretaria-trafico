
package co.edu.utp.isc.db.secretariatrafico.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "tipos_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposPersonas.findAll", query = "SELECT t FROM TiposPersonas t")
    , @NamedQuery(name = "TiposPersonas.findByIdTipoPersona", query = "SELECT t FROM TiposPersonas t WHERE t.idTipoPersona = :idTipoPersona")
    , @NamedQuery(name = "TiposPersonas.findByNombreTipoPersona", query = "SELECT t FROM TiposPersonas t WHERE t.nombreTipoPersona = :nombreTipoPersona")})
public class TiposPersonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_persona")
    private Integer idTipoPersona;
    @Basic(optional = false)
    @Column(name = "nombre_tipo_persona")
    private String nombreTipoPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPersona")
    private List<Personas> personasList;

    public TiposPersonas() {
    }

    public TiposPersonas(Integer idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public TiposPersonas(Integer idTipoPersona, String nombreTipoPersona) {
        this.idTipoPersona = idTipoPersona;
        this.nombreTipoPersona = nombreTipoPersona;
    }

    public Integer getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(Integer idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public String getNombreTipoPersona() {
        return nombreTipoPersona;
    }

    public void setNombreTipoPersona(String nombreTipoPersona) {
        this.nombreTipoPersona = nombreTipoPersona;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPersona != null ? idTipoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposPersonas)) {
            return false;
        }
        TiposPersonas other = (TiposPersonas) object;
        if ((this.idTipoPersona == null && other.idTipoPersona != null) || (this.idTipoPersona != null && !this.idTipoPersona.equals(other.idTipoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.utp.isc.db.secretariatrafico.model.TiposPersonas[ idTipoPersona=" + idTipoPersona + " ]";
    }

}
