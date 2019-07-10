
package co.edu.utp.isc.db.secretariatrafico.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "multas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Multas.findAll", query = "SELECT m FROM Multas m")
    , @NamedQuery(name = "Multas.findByIdMulta", query = "SELECT m FROM Multas m WHERE m.idMulta = :idMulta")
    , @NamedQuery(name = "Multas.findByFechaInfraccion", query = "SELECT m FROM Multas m WHERE m.fechaInfraccion = :fechaInfraccion")
    , @NamedQuery(name = "Multas.findByDireccionInfraccion", query = "SELECT m FROM Multas m WHERE m.direccionInfraccion = :direccionInfraccion")})
public class Multas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_multa")
    private Integer idMulta;
    @Basic(optional = false)
    @Column(name = "fecha_infraccion")
    @Temporal(TemporalType.DATE)
    private Date fechaInfraccion;
    @Basic(optional = false)
    @Column(name = "direccion_infraccion")
    private String direccionInfraccion;
    @JoinColumn(name = "id_agente", referencedColumnName = "id_agente")
    @ManyToOne(optional = false)
    private Agentes idAgente;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Autos matricula;
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    @ManyToOne(optional = false)
    private Ciudades idCiudad;
    @JoinColumn(name = "codigo_infraccion", referencedColumnName = "codigo_infraccion")
    @ManyToOne(optional = false)
    private Infracciones codigoInfraccion;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;

    public Multas() {
    }

    public Multas(Integer idMulta) {
        this.idMulta = idMulta;
    }

    public Multas(Integer idMulta, Date fechaInfraccion, String direccionInfraccion) {
        this.idMulta = idMulta;
        this.fechaInfraccion = fechaInfraccion;
        this.direccionInfraccion = direccionInfraccion;
    }

    public Integer getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(Integer idMulta) {
        this.idMulta = idMulta;
    }

    public Date getFechaInfraccion() {
        return fechaInfraccion;
    }

    public void setFechaInfraccion(Date fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public String getDireccionInfraccion() {
        return direccionInfraccion;
    }

    public void setDireccionInfraccion(String direccionInfraccion) {
        this.direccionInfraccion = direccionInfraccion;
    }

    public Agentes getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(Agentes idAgente) {
        this.idAgente = idAgente;
    }

    public Autos getMatricula() {
        return matricula;
    }

    public void setMatricula(Autos matricula) {
        this.matricula = matricula;
    }

    public Ciudades getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudades idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Infracciones getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(Infracciones codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMulta != null ? idMulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Multas)) {
            return false;
        }
        Multas other = (Multas) object;
        if ((this.idMulta == null && other.idMulta != null) || (this.idMulta != null && !this.idMulta.equals(other.idMulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.utp.isc.db.secretariatrafico.model.Multas[ idMulta=" + idMulta + " ]";
    }

}
