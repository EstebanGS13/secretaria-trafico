
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
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p")
    , @NamedQuery(name = "Personas.findByIdPersona", query = "SELECT p FROM Personas p WHERE p.idPersona = :idPersona")
    , @NamedQuery(name = "Personas.findByNombrePersona", query = "SELECT p FROM Personas p WHERE p.nombrePersona = :nombrePersona")
    , @NamedQuery(name = "Personas.findByApellidosPersona", query = "SELECT p FROM Personas p WHERE p.apellidosPersona = :apellidosPersona")
    , @NamedQuery(name = "Personas.findByFechaNacimientoPersona", query = "SELECT p FROM Personas p WHERE p.fechaNacimientoPersona = :fechaNacimientoPersona")
    , @NamedQuery(name = "Personas.findByDireccionPersona", query = "SELECT p FROM Personas p WHERE p.direccionPersona = :direccionPersona")
    , @NamedQuery(name = "Personas.findByTelefonoPersona", query = "SELECT p FROM Personas p WHERE p.telefonoPersona = :telefonoPersona")})
public class Personas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Basic(optional = false)
    @Column(name = "nombre_persona")
    private String nombrePersona;
    @Basic(optional = false)
    @Column(name = "apellidos_persona")
    private String apellidosPersona;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento_persona")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoPersona;
    @Basic(optional = false)
    @Column(name = "direccion_persona")
    private String direccionPersona;
    @Column(name = "telefono_persona")
    private String telefonoPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Multas> multasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Autos> autosList;
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    @ManyToOne(optional = false)
    private Ciudades idCiudad;
    @JoinColumn(name = "id_tipo_persona", referencedColumnName = "id_tipo_persona")
    @ManyToOne(optional = false)
    private TiposPersonas idTipoPersona;

    public Personas() {
    }

    public Personas(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Personas(Integer idPersona, String nombrePersona, String apellidosPersona, Date fechaNacimientoPersona, String direccionPersona) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.apellidosPersona = apellidosPersona;
        this.fechaNacimientoPersona = fechaNacimientoPersona;
        this.direccionPersona = direccionPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidosPersona() {
        return apellidosPersona;
    }

    public void setApellidosPersona(String apellidosPersona) {
        this.apellidosPersona = apellidosPersona;
    }

    public Date getFechaNacimientoPersona() {
        return fechaNacimientoPersona;
    }

    public void setFechaNacimientoPersona(Date fechaNacimientoPersona) {
        this.fechaNacimientoPersona = fechaNacimientoPersona;
    }

    public String getDireccionPersona() {
        return direccionPersona;
    }

    public void setDireccionPersona(String direccionPersona) {
        this.direccionPersona = direccionPersona;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    @XmlTransient
    public List<Multas> getMultasList() {
        return multasList;
    }

    public void setMultasList(List<Multas> multasList) {
        this.multasList = multasList;
    }

    @XmlTransient
    public List<Autos> getAutosList() {
        return autosList;
    }

    public void setAutosList(List<Autos> autosList) {
        this.autosList = autosList;
    }

    public Ciudades getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudades idCiudad) {
        this.idCiudad = idCiudad;
    }

    public TiposPersonas getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(TiposPersonas idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.utp.isc.db.secretariatrafico.model.Personas[ idPersona=" + idPersona + " ]";
    }

}
