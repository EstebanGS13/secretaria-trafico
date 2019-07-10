
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
@Table(name = "agentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agentes.findAll", query = "SELECT a FROM Agentes a")
    , @NamedQuery(name = "Agentes.findByIdAgente", query = "SELECT a FROM Agentes a WHERE a.idAgente = :idAgente")
    , @NamedQuery(name = "Agentes.findByNombreAgente", query = "SELECT a FROM Agentes a WHERE a.nombreAgente = :nombreAgente")
    , @NamedQuery(name = "Agentes.findByApellidosAgente", query = "SELECT a FROM Agentes a WHERE a.apellidosAgente = :apellidosAgente")})
public class Agentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_agente")
    private Integer idAgente;
    @Basic(optional = false)
    @Column(name = "nombre_agente")
    private String nombreAgente;
    @Basic(optional = false)
    @Column(name = "apellidos_agente")
    private String apellidosAgente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgente")
    private List<Multas> multasList;

    public Agentes() {
    }

    public Agentes(Integer idAgente) {
        this.idAgente = idAgente;
    }

    public Agentes(Integer idAgente, String nombreAgente, String apellidosAgente) {
        this.idAgente = idAgente;
        this.nombreAgente = nombreAgente;
        this.apellidosAgente = apellidosAgente;
    }

    public Integer getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(Integer idAgente) {
        this.idAgente = idAgente;
    }

    public String getNombreAgente() {
        return nombreAgente;
    }

    public void setNombreAgente(String nombreAgente) {
        this.nombreAgente = nombreAgente;
    }

    public String getApellidosAgente() {
        return apellidosAgente;
    }

    public void setApellidosAgente(String apellidosAgente) {
        this.apellidosAgente = apellidosAgente;
    }

    @XmlTransient
    public List<Multas> getMultasList() {
        return multasList;
    }

    public void setMultasList(List<Multas> multasList) {
        this.multasList = multasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgente != null ? idAgente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agentes)) {
            return false;
        }
        Agentes other = (Agentes) object;
        if ((this.idAgente == null && other.idAgente != null) || (this.idAgente != null && !this.idAgente.equals(other.idAgente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.utp.isc.db.secretariatrafico.model.Agentes[ idAgente=" + idAgente + " ]";
    }

}
