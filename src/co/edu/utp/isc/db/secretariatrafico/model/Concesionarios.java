
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "concesionarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concesionarios.findAll", query = "SELECT c FROM Concesionarios c")
    , @NamedQuery(name = "Concesionarios.findByIdConcesionario", query = "SELECT c FROM Concesionarios c WHERE c.idConcesionario = :idConcesionario")
    , @NamedQuery(name = "Concesionarios.findByNombreConcesionario", query = "SELECT c FROM Concesionarios c WHERE c.nombreConcesionario = :nombreConcesionario")
    , @NamedQuery(name = "Concesionarios.findByDireccionConcesionario", query = "SELECT c FROM Concesionarios c WHERE c.direccionConcesionario = :direccionConcesionario")
    , @NamedQuery(name = "Concesionarios.findByTelefonoConcesionario", query = "SELECT c FROM Concesionarios c WHERE c.telefonoConcesionario = :telefonoConcesionario")})
public class Concesionarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_concesionario")
    private Integer idConcesionario;
    @Basic(optional = false)
    @Column(name = "nombre_concesionario")
    private String nombreConcesionario;
    @Basic(optional = false)
    @Column(name = "direccion_concesionario")
    private String direccionConcesionario;
    @Basic(optional = false)
    @Column(name = "telefono_concesionario")
    private String telefonoConcesionario;
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    @ManyToOne(optional = false)
    private Ciudades idCiudad;
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca")
    @ManyToOne(optional = false)
    private Marcas idMarca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConcesionario")
    private List<Autos> autosList;

    public Concesionarios() {
    }

    public Concesionarios(Integer idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    public Concesionarios(Integer idConcesionario, String nombreConcesionario, String direccionConcesionario, String telefonoConcesionario) {
        this.idConcesionario = idConcesionario;
        this.nombreConcesionario = nombreConcesionario;
        this.direccionConcesionario = direccionConcesionario;
        this.telefonoConcesionario = telefonoConcesionario;
    }

    public Integer getIdConcesionario() {
        return idConcesionario;
    }

    public void setIdConcesionario(Integer idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    public String getNombreConcesionario() {
        return nombreConcesionario;
    }

    public void setNombreConcesionario(String nombreConcesionario) {
        this.nombreConcesionario = nombreConcesionario;
    }

    public String getDireccionConcesionario() {
        return direccionConcesionario;
    }

    public void setDireccionConcesionario(String direccionConcesionario) {
        this.direccionConcesionario = direccionConcesionario;
    }

    public String getTelefonoConcesionario() {
        return telefonoConcesionario;
    }

    public void setTelefonoConcesionario(String telefonoConcesionario) {
        this.telefonoConcesionario = telefonoConcesionario;
    }

    public Ciudades getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudades idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Marcas getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marcas idMarca) {
        this.idMarca = idMarca;
    }

    @XmlTransient
    public List<Autos> getAutosList() {
        return autosList;
    }

    public void setAutosList(List<Autos> autosList) {
        this.autosList = autosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConcesionario != null ? idConcesionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concesionarios)) {
            return false;
        }
        Concesionarios other = (Concesionarios) object;
        if ((this.idConcesionario == null && other.idConcesionario != null) || (this.idConcesionario != null && !this.idConcesionario.equals(other.idConcesionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.utp.isc.db.secretariatrafico.model.Concesionarios[ idConcesionario=" + idConcesionario + " ]";
    }

}
