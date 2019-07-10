
package co.edu.utp.isc.db.secretariatrafico.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "modelos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelos.findAll", query = "SELECT m FROM Modelos m")
    , @NamedQuery(name = "Modelos.findByIdModelo", query = "SELECT m FROM Modelos m WHERE m.idModelo = :idModelo")
    , @NamedQuery(name = "Modelos.findByNombreModelo", query = "SELECT m FROM Modelos m WHERE m.nombreModelo = :nombreModelo")
    , @NamedQuery(name = "Modelos.findByAnioModelo", query = "SELECT m FROM Modelos m WHERE m.anioModelo = :anioModelo")})
public class Modelos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_modelo")
    private Integer idModelo;
    @Basic(optional = false)
    @Column(name = "nombre_modelo")
    private String nombreModelo;
    @Basic(optional = false)
    @Column(name = "anio_modelo")
    @Temporal(TemporalType.DATE)
    private Date anioModelo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModelo")
    private List<Autos> autosList;
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca")
    @ManyToOne(optional = false)
    private Marcas idMarca;

    public Modelos() {
    }

    public Modelos(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public Modelos(Integer idModelo, String nombreModelo, Date anioModelo) {
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
        this.anioModelo = anioModelo;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public Date getAnioModelo() {
        return anioModelo;
    }

    public void setAnioModelo(Date anioModelo) {
        this.anioModelo = anioModelo;
    }

    @XmlTransient
    public List<Autos> getAutosList() {
        return autosList;
    }

    public void setAutosList(List<Autos> autosList) {
        this.autosList = autosList;
    }

    public Marcas getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marcas idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModelo != null ? idModelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelos)) {
            return false;
        }
        Modelos other = (Modelos) object;
        if ((this.idModelo == null && other.idModelo != null) || (this.idModelo != null && !this.idModelo.equals(other.idModelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.utp.isc.db.secretariatrafico.model.Modelos[ idModelo=" + idModelo + " ]";
    }

}
