
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.IllegalOrphanException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.utp.isc.db.secretariatrafico.model.Ciudades;
import co.edu.utp.isc.db.secretariatrafico.model.TiposPersonas;
import co.edu.utp.isc.db.secretariatrafico.model.Multas;
import java.util.ArrayList;
import java.util.List;
import co.edu.utp.isc.db.secretariatrafico.model.Autos;
import co.edu.utp.isc.db.secretariatrafico.model.Personas;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class PersonasJpaController implements Serializable {

    public PersonasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personas personas) {
        if (personas.getMultasList() == null) {
            personas.setMultasList(new ArrayList<Multas>());
        }
        if (personas.getAutosList() == null) {
            personas.setAutosList(new ArrayList<Autos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudades idCiudad = personas.getIdCiudad();
            if (idCiudad != null) {
                idCiudad = em.getReference(idCiudad.getClass(), idCiudad.getIdCiudad());
                personas.setIdCiudad(idCiudad);
            }
            TiposPersonas idTipoPersona = personas.getIdTipoPersona();
            if (idTipoPersona != null) {
                idTipoPersona = em.getReference(idTipoPersona.getClass(), idTipoPersona.getIdTipoPersona());
                personas.setIdTipoPersona(idTipoPersona);
            }
            List<Multas> attachedMultasList = new ArrayList<Multas>();
            for (Multas multasListMultasToAttach : personas.getMultasList()) {
                multasListMultasToAttach = em.getReference(multasListMultasToAttach.getClass(), multasListMultasToAttach.getIdMulta());
                attachedMultasList.add(multasListMultasToAttach);
            }
            personas.setMultasList(attachedMultasList);
            List<Autos> attachedAutosList = new ArrayList<Autos>();
            for (Autos autosListAutosToAttach : personas.getAutosList()) {
                autosListAutosToAttach = em.getReference(autosListAutosToAttach.getClass(), autosListAutosToAttach.getMatricula());
                attachedAutosList.add(autosListAutosToAttach);
            }
            personas.setAutosList(attachedAutosList);
            em.persist(personas);
            if (idCiudad != null) {
                idCiudad.getPersonasList().add(personas);
                idCiudad = em.merge(idCiudad);
            }
            if (idTipoPersona != null) {
                idTipoPersona.getPersonasList().add(personas);
                idTipoPersona = em.merge(idTipoPersona);
            }
            for (Multas multasListMultas : personas.getMultasList()) {
                Personas oldIdPersonaOfMultasListMultas = multasListMultas.getIdPersona();
                multasListMultas.setIdPersona(personas);
                multasListMultas = em.merge(multasListMultas);
                if (oldIdPersonaOfMultasListMultas != null) {
                    oldIdPersonaOfMultasListMultas.getMultasList().remove(multasListMultas);
                    oldIdPersonaOfMultasListMultas = em.merge(oldIdPersonaOfMultasListMultas);
                }
            }
            for (Autos autosListAutos : personas.getAutosList()) {
                Personas oldIdPersonaOfAutosListAutos = autosListAutos.getIdPersona();
                autosListAutos.setIdPersona(personas);
                autosListAutos = em.merge(autosListAutos);
                if (oldIdPersonaOfAutosListAutos != null) {
                    oldIdPersonaOfAutosListAutos.getAutosList().remove(autosListAutos);
                    oldIdPersonaOfAutosListAutos = em.merge(oldIdPersonaOfAutosListAutos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personas personas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas persistentPersonas = em.find(Personas.class, personas.getIdPersona());
            Ciudades idCiudadOld = persistentPersonas.getIdCiudad();
            Ciudades idCiudadNew = personas.getIdCiudad();
            TiposPersonas idTipoPersonaOld = persistentPersonas.getIdTipoPersona();
            TiposPersonas idTipoPersonaNew = personas.getIdTipoPersona();
            List<Multas> multasListOld = persistentPersonas.getMultasList();
            List<Multas> multasListNew = personas.getMultasList();
            List<Autos> autosListOld = persistentPersonas.getAutosList();
            List<Autos> autosListNew = personas.getAutosList();
            List<String> illegalOrphanMessages = null;
            for (Multas multasListOldMultas : multasListOld) {
                if (!multasListNew.contains(multasListOldMultas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Multas " + multasListOldMultas + " since its idPersona field is not nullable.");
                }
            }
            for (Autos autosListOldAutos : autosListOld) {
                if (!autosListNew.contains(autosListOldAutos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Autos " + autosListOldAutos + " since its idPersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCiudadNew != null) {
                idCiudadNew = em.getReference(idCiudadNew.getClass(), idCiudadNew.getIdCiudad());
                personas.setIdCiudad(idCiudadNew);
            }
            if (idTipoPersonaNew != null) {
                idTipoPersonaNew = em.getReference(idTipoPersonaNew.getClass(), idTipoPersonaNew.getIdTipoPersona());
                personas.setIdTipoPersona(idTipoPersonaNew);
            }
            List<Multas> attachedMultasListNew = new ArrayList<Multas>();
            for (Multas multasListNewMultasToAttach : multasListNew) {
                multasListNewMultasToAttach = em.getReference(multasListNewMultasToAttach.getClass(), multasListNewMultasToAttach.getIdMulta());
                attachedMultasListNew.add(multasListNewMultasToAttach);
            }
            multasListNew = attachedMultasListNew;
            personas.setMultasList(multasListNew);
            List<Autos> attachedAutosListNew = new ArrayList<Autos>();
            for (Autos autosListNewAutosToAttach : autosListNew) {
                autosListNewAutosToAttach = em.getReference(autosListNewAutosToAttach.getClass(), autosListNewAutosToAttach.getMatricula());
                attachedAutosListNew.add(autosListNewAutosToAttach);
            }
            autosListNew = attachedAutosListNew;
            personas.setAutosList(autosListNew);
            personas = em.merge(personas);
            if (idCiudadOld != null && !idCiudadOld.equals(idCiudadNew)) {
                idCiudadOld.getPersonasList().remove(personas);
                idCiudadOld = em.merge(idCiudadOld);
            }
            if (idCiudadNew != null && !idCiudadNew.equals(idCiudadOld)) {
                idCiudadNew.getPersonasList().add(personas);
                idCiudadNew = em.merge(idCiudadNew);
            }
            if (idTipoPersonaOld != null && !idTipoPersonaOld.equals(idTipoPersonaNew)) {
                idTipoPersonaOld.getPersonasList().remove(personas);
                idTipoPersonaOld = em.merge(idTipoPersonaOld);
            }
            if (idTipoPersonaNew != null && !idTipoPersonaNew.equals(idTipoPersonaOld)) {
                idTipoPersonaNew.getPersonasList().add(personas);
                idTipoPersonaNew = em.merge(idTipoPersonaNew);
            }
            for (Multas multasListNewMultas : multasListNew) {
                if (!multasListOld.contains(multasListNewMultas)) {
                    Personas oldIdPersonaOfMultasListNewMultas = multasListNewMultas.getIdPersona();
                    multasListNewMultas.setIdPersona(personas);
                    multasListNewMultas = em.merge(multasListNewMultas);
                    if (oldIdPersonaOfMultasListNewMultas != null && !oldIdPersonaOfMultasListNewMultas.equals(personas)) {
                        oldIdPersonaOfMultasListNewMultas.getMultasList().remove(multasListNewMultas);
                        oldIdPersonaOfMultasListNewMultas = em.merge(oldIdPersonaOfMultasListNewMultas);
                    }
                }
            }
            for (Autos autosListNewAutos : autosListNew) {
                if (!autosListOld.contains(autosListNewAutos)) {
                    Personas oldIdPersonaOfAutosListNewAutos = autosListNewAutos.getIdPersona();
                    autosListNewAutos.setIdPersona(personas);
                    autosListNewAutos = em.merge(autosListNewAutos);
                    if (oldIdPersonaOfAutosListNewAutos != null && !oldIdPersonaOfAutosListNewAutos.equals(personas)) {
                        oldIdPersonaOfAutosListNewAutos.getAutosList().remove(autosListNewAutos);
                        oldIdPersonaOfAutosListNewAutos = em.merge(oldIdPersonaOfAutosListNewAutos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personas.getIdPersona();
                if (findPersonas(id) == null) {
                    throw new NonexistentEntityException("The personas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas personas;
            try {
                personas = em.getReference(Personas.class, id);
                personas.getIdPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Multas> multasListOrphanCheck = personas.getMultasList();
            for (Multas multasListOrphanCheckMultas : multasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Multas " + multasListOrphanCheckMultas + " in its multasList field has a non-nullable idPersona field.");
            }
            List<Autos> autosListOrphanCheck = personas.getAutosList();
            for (Autos autosListOrphanCheckAutos : autosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Autos " + autosListOrphanCheckAutos + " in its autosList field has a non-nullable idPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Ciudades idCiudad = personas.getIdCiudad();
            if (idCiudad != null) {
                idCiudad.getPersonasList().remove(personas);
                idCiudad = em.merge(idCiudad);
            }
            TiposPersonas idTipoPersona = personas.getIdTipoPersona();
            if (idTipoPersona != null) {
                idTipoPersona.getPersonasList().remove(personas);
                idTipoPersona = em.merge(idTipoPersona);
            }
            em.remove(personas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personas> findPersonasEntities() {
        return findPersonasEntities(true, -1, -1);
    }

    public List<Personas> findPersonasEntities(int maxResults, int firstResult) {
        return findPersonasEntities(false, maxResults, firstResult);
    }

    private List<Personas> findPersonasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Personas findPersonas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personas> rt = cq.from(Personas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
