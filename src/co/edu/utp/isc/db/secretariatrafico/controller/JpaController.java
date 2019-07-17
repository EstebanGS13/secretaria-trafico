

package co.edu.utp.isc.db.secretariatrafico.controller;

import java.io.Serializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaController implements Serializable {
    
    private static JpaController instance;
    private final EntityManagerFactory emf;
    private AgentesJpaController agentesControlador;
    private AutosJpaController autosControlador;
    private CiudadesJpaController ciudadesControlador;
    private InfraccionesJpaController infraccionesControlador;
    private MultasJpaController multasControlador;
    private PersonasJpaController personasControlador;

    public JpaController() {
        this.emf = Persistence.createEntityManagerFactory("SecretariaTraficoPU");
        this.multasControlador = new MultasJpaController(emf);
        this.agentesControlador = new AgentesJpaController(emf);
        this.autosControlador= new AutosJpaController(emf);
        this.ciudadesControlador = new CiudadesJpaController(emf);
        this.infraccionesControlador = new InfraccionesJpaController(emf);
        this.personasControlador = new PersonasJpaController(emf);
    }
    
    public static JpaController getInstance() {
        if (instance == null) {
            instance = new JpaController();
        }
        return instance;
    }

    public AgentesJpaController getAgentesControlador() {
        return agentesControlador;
    }

    public AutosJpaController getAutosControlador() {
        return autosControlador;
    }

    public CiudadesJpaController getCiudadesControlador() {
        return ciudadesControlador;
    }

    public InfraccionesJpaController getInfraccionesControlador() {
        return infraccionesControlador;
    }

    public MultasJpaController getMultasControlador() {
        return multasControlador;
    }

    public PersonasJpaController getPersonasControlador() {
        return personasControlador;
    }

}
