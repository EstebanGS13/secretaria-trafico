
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
@Table(name = "tipos_vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposVehiculos.findAll", query = "SELECT t FROM TiposVehiculos t")
    , @NamedQuery(name = "TiposVehiculos.findByIdTipoVehiculo", query = "SELECT t FROM TiposVehiculos t WHERE t.idTipoVehiculo = :idTipoVehiculo")
    , @NamedQuery(name = "TiposVehiculos.findByNombreTipoVehiculo", query = "SELECT t FROM TiposVehiculos t WHERE t.nombreTipoVehiculo = :nombreTipoVehiculo")})
public class TiposVehiculos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_vehiculo")
    private Integer idTipoVehiculo;
    @Basic(optional = false)
    @Column(name = "nombre_tipo_vehiculo")
    private String nombreTipoVehiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoVehiculo")
    private List<Autos> autosList;

    public TiposVehiculos() {
    }

    public TiposVehiculos(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public TiposVehiculos(Integer idTipoVehiculo, String nombreTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }

    public Integer getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getNombreTipoVehiculo() {
        return nombreTipoVehiculo;
    }

    public void setNombreTipoVehiculo(String nombreTipoVehiculo) {
        this.nombreTipoVehiculo = nombreTipoVehiculo;
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
        hash += (idTipoVehiculo != null ? idTipoVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposVehiculos)) {
            return false;
        }
        TiposVehiculos other = (TiposVehiculos) object;
        if ((this.idTipoVehiculo == null && other.idTipoVehiculo != null) || (this.idTipoVehiculo != null && !this.idTipoVehiculo.equals(other.idTipoVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.utp.isc.db.secretariatrafico.model.TiposVehiculos[ idTipoVehiculo=" + idTipoVehiculo + " ]";
    }

}
