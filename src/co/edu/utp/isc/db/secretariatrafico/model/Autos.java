
package co.edu.utp.isc.db.secretariatrafico.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "autos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autos.findAll", query = "SELECT a FROM Autos a")
    , @NamedQuery(name = "Autos.findByMatricula", query = "SELECT a FROM Autos a WHERE a.matricula = :matricula")
    , @NamedQuery(name = "Autos.findByColorAuto", query = "SELECT a FROM Autos a WHERE a.colorAuto = :colorAuto")
    , @NamedQuery(name = "Autos.findByCapacidadAuto", query = "SELECT a FROM Autos a WHERE a.capacidadAuto = :capacidadAuto")})
public class Autos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "color_auto")
    private String colorAuto;
    @Column(name = "capacidad_auto")
    private String capacidadAuto;
    @OneToMany(mappedBy = "matricula")
    private List<Multas> multasList;
    @JoinColumn(name = "id_concesionario", referencedColumnName = "id_concesionario")
    @ManyToOne(optional = false)
    private Concesionarios idConcesionario;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id_modelo")
    @ManyToOne(optional = false)
    private Modelos idModelo;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @JoinColumn(name = "id_tipo_vehiculo", referencedColumnName = "id_tipo_vehiculo")
    @ManyToOne(optional = false)
    private TiposVehiculos idTipoVehiculo;

    public Autos() {
    }

    public Autos(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColorAuto() {
        return colorAuto;
    }

    public void setColorAuto(String colorAuto) {
        this.colorAuto = colorAuto;
    }

    public String getCapacidadAuto() {
        return capacidadAuto;
    }

    public void setCapacidadAuto(String capacidadAuto) {
        this.capacidadAuto = capacidadAuto;
    }

    @XmlTransient
    public List<Multas> getMultasList() {
        return multasList;
    }

    public void setMultasList(List<Multas> multasList) {
        this.multasList = multasList;
    }

    public Concesionarios getIdConcesionario() {
        return idConcesionario;
    }

    public void setIdConcesionario(Concesionarios idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    public Modelos getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelos idModelo) {
        this.idModelo = idModelo;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public TiposVehiculos getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(TiposVehiculos idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autos)) {
            return false;
        }
        Autos other = (Autos) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "co.edu.utp.isc.db.secretariatrafico.model.Autos[ matricula=" + matricula + " ]";
        return matricula;
    }

}
