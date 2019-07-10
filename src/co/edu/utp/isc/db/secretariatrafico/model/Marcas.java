
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
@Table(name = "marcas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marcas.findAll", query = "SELECT m FROM Marcas m")
    , @NamedQuery(name = "Marcas.findByIdMarca", query = "SELECT m FROM Marcas m WHERE m.idMarca = :idMarca")
    , @NamedQuery(name = "Marcas.findByNombreMarca", query = "SELECT m FROM Marcas m WHERE m.nombreMarca = :nombreMarca")})
public class Marcas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_marca")
    private Integer idMarca;
    @Basic(optional = false)
    @Column(name = "nombre_marca")
    private String nombreMarca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMarca")
    private List<Concesionarios> concesionariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMarca")
    private List<Modelos> modelosList;

    public Marcas() {
    }

    public Marcas(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Marcas(Integer idMarca, String nombreMarca) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    @XmlTransient
    public List<Concesionarios> getConcesionariosList() {
        return concesionariosList;
    }

    public void setConcesionariosList(List<Concesionarios> concesionariosList) {
        this.concesionariosList = concesionariosList;
    }

    @XmlTransient
    public List<Modelos> getModelosList() {
        return modelosList;
    }

    public void setModelosList(List<Modelos> modelosList) {
        this.modelosList = modelosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarca != null ? idMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marcas)) {
            return false;
        }
        Marcas other = (Marcas) object;
        if ((this.idMarca == null && other.idMarca != null) || (this.idMarca != null && !this.idMarca.equals(other.idMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.utp.isc.db.secretariatrafico.model.Marcas[ idMarca=" + idMarca + " ]";
    }

}
