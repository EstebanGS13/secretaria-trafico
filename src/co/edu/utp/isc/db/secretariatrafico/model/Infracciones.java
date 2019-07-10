
package co.edu.utp.isc.db.secretariatrafico.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "infracciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Infracciones.findAll", query = "SELECT i FROM Infracciones i")
    , @NamedQuery(name = "Infracciones.findByCodigoInfraccion", query = "SELECT i FROM Infracciones i WHERE i.codigoInfraccion = :codigoInfraccion")
    , @NamedQuery(name = "Infracciones.findByDescripcionInfraccion", query = "SELECT i FROM Infracciones i WHERE i.descripcionInfraccion = :descripcionInfraccion")
    , @NamedQuery(name = "Infracciones.findByImporteInfraccion", query = "SELECT i FROM Infracciones i WHERE i.importeInfraccion = :importeInfraccion")})
public class Infracciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_infraccion")
    private String codigoInfraccion;
    @Basic(optional = false)
    @Column(name = "descripcion_infraccion")
    private String descripcionInfraccion;
    @Basic(optional = false)
    @Column(name = "importe_infraccion")
    private int importeInfraccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoInfraccion")
    private List<Multas> multasList;

    public Infracciones() {
    }

    public Infracciones(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Infracciones(String codigoInfraccion, String descripcionInfraccion, int importeInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
        this.descripcionInfraccion = descripcionInfraccion;
        this.importeInfraccion = importeInfraccion;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public int getImporteInfraccion() {
        return importeInfraccion;
    }

    public void setImporteInfraccion(int importeInfraccion) {
        this.importeInfraccion = importeInfraccion;
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
        hash += (codigoInfraccion != null ? codigoInfraccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Infracciones)) {
            return false;
        }
        Infracciones other = (Infracciones) object;
        if ((this.codigoInfraccion == null && other.codigoInfraccion != null) || (this.codigoInfraccion != null && !this.codigoInfraccion.equals(other.codigoInfraccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.utp.isc.db.secretariatrafico.model.Infracciones[ codigoInfraccion=" + codigoInfraccion + " ]";
    }

}
