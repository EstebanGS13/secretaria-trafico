

package co.edu.utp.isc.db.secretariatrafico.controller;

import java.io.Serializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaController implements Serializable {
    
    private static JpaController instance;
    private final EntityManagerFactory emf;
    private final AgentesJpaController agentesControlador;
    private final AutosJpaController autosControlador;
    private final CiudadesJpaController ciudadesControlador;
    private final ConcesionariosJpaController concesionariosControlador;
    private final InfraccionesJpaController infraccionesControlador;
    private final MarcasJpaController marcasControlador;
    private final ModelosJpaController modelosControlador;
    private final MultasJpaController multasControlador;
    private final PersonasJpaController personasControlador;
    private final TiposPersonasJpaController tiposPersonasControlador;
    private final TiposVehiculosJpaController tiposVehiculosControlador;


    public JpaController() {
        this.emf = Persistence.createEntityManagerFactory("SecretariaTraficoPU");
        this.agentesControlador = new AgentesJpaController(emf);
        this.autosControlador= new AutosJpaController(emf);
        this.ciudadesControlador = new CiudadesJpaController(emf);
        this.concesionariosControlador = new ConcesionariosJpaController(emf);
        this.infraccionesControlador = new InfraccionesJpaController(emf);
        this.marcasControlador = new MarcasJpaController(emf);
        this.modelosControlador = new ModelosJpaController(emf);
        this.multasControlador = new MultasJpaController(emf);
        this.personasControlador = new PersonasJpaController(emf);
        this.tiposPersonasControlador = new TiposPersonasJpaController(emf);
        this.tiposVehiculosControlador= new TiposVehiculosJpaController(emf);
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

    public ConcesionariosJpaController getConcesionariosControlador() {
        return concesionariosControlador;
    }

    public InfraccionesJpaController getInfraccionesControlador() {
        return infraccionesControlador;
    }

    public MarcasJpaController getMarcasControlador() {
        return marcasControlador;
    }

    public ModelosJpaController getModelosControlador() {
        return modelosControlador;
    }

    public MultasJpaController getMultasControlador() {
        return multasControlador;
    }

    public PersonasJpaController getPersonasControlador() {
        return personasControlador;
    }

    public TiposPersonasJpaController getTiposPersonasControlador() {
        return tiposPersonasControlador;
    }

    public TiposVehiculosJpaController getTiposVehiculosControlador() {
        return tiposVehiculosControlador;
    }

}
