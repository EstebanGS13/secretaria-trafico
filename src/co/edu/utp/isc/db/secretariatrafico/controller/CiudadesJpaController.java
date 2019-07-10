
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.IllegalOrphanException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import co.edu.utp.isc.db.secretariatrafico.model.Ciudades;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.utp.isc.db.secretariatrafico.model.Concesionarios;
import java.util.ArrayList;
import java.util.List;
import co.edu.utp.isc.db.secretariatrafico.model.Multas;
import co.edu.utp.isc.db.secretariatrafico.model.Personas;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class CiudadesJpaController implements Serializable {

    public CiudadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciudades ciudades) {
        if (ciudades.getConcesionariosList() == null) {
            ciudades.setConcesionariosList(new ArrayList<Concesionarios>());
        }
        if (ciudades.getMultasList() == null) {
            ciudades.setMultasList(new ArrayList<Multas>());
        }
        if (ciudades.getPersonasList() == null) {
            ciudades.setPersonasList(new ArrayList<Personas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Concesionarios> attachedConcesionariosList = new ArrayList<Concesionarios>();
            for (Concesionarios concesionariosListConcesionariosToAttach : ciudades.getConcesionariosList()) {
                concesionariosListConcesionariosToAttach = em.getReference(concesionariosListConcesionariosToAttach.getClass(), concesionariosListConcesionariosToAttach.getIdConcesionario());
                attachedConcesionariosList.add(concesionariosListConcesionariosToAttach);
            }
            ciudades.setConcesionariosList(attachedConcesionariosList);
            List<Multas> attachedMultasList = new ArrayList<Multas>();
            for (Multas multasListMultasToAttach : ciudades.getMultasList()) {
                multasListMultasToAttach = em.getReference(multasListMultasToAttach.getClass(), multasListMultasToAttach.getIdMulta());
                attachedMultasList.add(multasListMultasToAttach);
            }
            ciudades.setMultasList(attachedMultasList);
            List<Personas> attachedPersonasList = new ArrayList<Personas>();
            for (Personas personasListPersonasToAttach : ciudades.getPersonasList()) {
                personasListPersonasToAttach = em.getReference(personasListPersonasToAttach.getClass(), personasListPersonasToAttach.getIdPersona());
                attachedPersonasList.add(personasListPersonasToAttach);
            }
            ciudades.setPersonasList(attachedPersonasList);
            em.persist(ciudades);
            for (Concesionarios concesionariosListConcesionarios : ciudades.getConcesionariosList()) {
                Ciudades oldIdCiudadOfConcesionariosListConcesionarios = concesionariosListConcesionarios.getIdCiudad();
                concesionariosListConcesionarios.setIdCiudad(ciudades);
                concesionariosListConcesionarios = em.merge(concesionariosListConcesionarios);
                if (oldIdCiudadOfConcesionariosListConcesionarios != null) {
                    oldIdCiudadOfConcesionariosListConcesionarios.getConcesionariosList().remove(concesionariosListConcesionarios);
                    oldIdCiudadOfConcesionariosListConcesionarios = em.merge(oldIdCiudadOfConcesionariosListConcesionarios);
                }
            }
            for (Multas multasListMultas : ciudades.getMultasList()) {
                Ciudades oldIdCiudadOfMultasListMultas = multasListMultas.getIdCiudad();
                multasListMultas.setIdCiudad(ciudades);
                multasListMultas = em.merge(multasListMultas);
                if (oldIdCiudadOfMultasListMultas != null) {
                    oldIdCiudadOfMultasListMultas.getMultasList().remove(multasListMultas);
                    oldIdCiudadOfMultasListMultas = em.merge(oldIdCiudadOfMultasListMultas);
                }
            }
            for (Personas personasListPersonas : ciudades.getPersonasList()) {
                Ciudades oldIdCiudadOfPersonasListPersonas = personasListPersonas.getIdCiudad();
                personasListPersonas.setIdCiudad(ciudades);
                personasListPersonas = em.merge(personasListPersonas);
                if (oldIdCiudadOfPersonasListPersonas != null) {
                    oldIdCiudadOfPersonasListPersonas.getPersonasList().remove(personasListPersonas);
                    oldIdCiudadOfPersonasListPersonas = em.merge(oldIdCiudadOfPersonasListPersonas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciudades ciudades) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudades persistentCiudades = em.find(Ciudades.class, ciudades.getIdCiudad());
            List<Concesionarios> concesionariosListOld = persistentCiudades.getConcesionariosList();
            List<Concesionarios> concesionariosListNew = ciudades.getConcesionariosList();
            List<Multas> multasListOld = persistentCiudades.getMultasList();
            List<Multas> multasListNew = ciudades.getMultasList();
            List<Personas> personasListOld = persistentCiudades.getPersonasList();
            List<Personas> personasListNew = ciudades.getPersonasList();
            List<String> illegalOrphanMessages = null;
            for (Concesionarios concesionariosListOldConcesionarios : concesionariosListOld) {
                if (!concesionariosListNew.contains(concesionariosListOldConcesionarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Concesionarios " + concesionariosListOldConcesionarios + " since its idCiudad field is not nullable.");
                }
            }
            for (Multas multasListOldMultas : multasListOld) {
                if (!multasListNew.contains(multasListOldMultas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Multas " + multasListOldMultas + " since its idCiudad field is not nullable.");
                }
            }
            for (Personas personasListOldPersonas : personasListOld) {
                if (!personasListNew.contains(personasListOldPersonas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Personas " + personasListOldPersonas + " since its idCiudad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Concesionarios> attachedConcesionariosListNew = new ArrayList<Concesionarios>();
            for (Concesionarios concesionariosListNewConcesionariosToAttach : concesionariosListNew) {
                concesionariosListNewConcesionariosToAttach = em.getReference(concesionariosListNewConcesionariosToAttach.getClass(), concesionariosListNewConcesionariosToAttach.getIdConcesionario());
                attachedConcesionariosListNew.add(concesionariosListNewConcesionariosToAttach);
            }
            concesionariosListNew = attachedConcesionariosListNew;
            ciudades.setConcesionariosList(concesionariosListNew);
            List<Multas> attachedMultasListNew = new ArrayList<Multas>();
            for (Multas multasListNewMultasToAttach : multasListNew) {
                multasListNewMultasToAttach = em.getReference(multasListNewMultasToAttach.getClass(), multasListNewMultasToAttach.getIdMulta());
                attachedMultasListNew.add(multasListNewMultasToAttach);
            }
            multasListNew = attachedMultasListNew;
            ciudades.setMultasList(multasListNew);
            List<Personas> attachedPersonasListNew = new ArrayList<Personas>();
            for (Personas personasListNewPersonasToAttach : personasListNew) {
                personasListNewPersonasToAttach = em.getReference(personasListNewPersonasToAttach.getClass(), personasListNewPersonasToAttach.getIdPersona());
                attachedPersonasListNew.add(personasListNewPersonasToAttach);
            }
            personasListNew = attachedPersonasListNew;
            ciudades.setPersonasList(personasListNew);
            ciudades = em.merge(ciudades);
            for (Concesionarios concesionariosListNewConcesionarios : concesionariosListNew) {
                if (!concesionariosListOld.contains(concesionariosListNewConcesionarios)) {
                    Ciudades oldIdCiudadOfConcesionariosListNewConcesionarios = concesionariosListNewConcesionarios.getIdCiudad();
                    concesionariosListNewConcesionarios.setIdCiudad(ciudades);
                    concesionariosListNewConcesionarios = em.merge(concesionariosListNewConcesionarios);
                    if (oldIdCiudadOfConcesionariosListNewConcesionarios != null && !oldIdCiudadOfConcesionariosListNewConcesionarios.equals(ciudades)) {
                        oldIdCiudadOfConcesionariosListNewConcesionarios.getConcesionariosList().remove(concesionariosListNewConcesionarios);
                        oldIdCiudadOfConcesionariosListNewConcesionarios = em.merge(oldIdCiudadOfConcesionariosListNewConcesionarios);
                    }
                }
            }
            for (Multas multasListNewMultas : multasListNew) {
                if (!multasListOld.contains(multasListNewMultas)) {
                    Ciudades oldIdCiudadOfMultasListNewMultas = multasListNewMultas.getIdCiudad();
                    multasListNewMultas.setIdCiudad(ciudades);
                    multasListNewMultas = em.merge(multasListNewMultas);
                    if (oldIdCiudadOfMultasListNewMultas != null && !oldIdCiudadOfMultasListNewMultas.equals(ciudades)) {
                        oldIdCiudadOfMultasListNewMultas.getMultasList().remove(multasListNewMultas);
                        oldIdCiudadOfMultasListNewMultas = em.merge(oldIdCiudadOfMultasListNewMultas);
                    }
                }
            }
            for (Personas personasListNewPersonas : personasListNew) {
                if (!personasListOld.contains(personasListNewPersonas)) {
                    Ciudades oldIdCiudadOfPersonasListNewPersonas = personasListNewPersonas.getIdCiudad();
                    personasListNewPersonas.setIdCiudad(ciudades);
                    personasListNewPersonas = em.merge(personasListNewPersonas);
                    if (oldIdCiudadOfPersonasListNewPersonas != null && !oldIdCiudadOfPersonasListNewPersonas.equals(ciudades)) {
                        oldIdCiudadOfPersonasListNewPersonas.getPersonasList().remove(personasListNewPersonas);
                        oldIdCiudadOfPersonasListNewPersonas = em.merge(oldIdCiudadOfPersonasListNewPersonas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ciudades.getIdCiudad();
                if (findCiudades(id) == null) {
                    throw new NonexistentEntityException("The ciudades with id " + id + " no longer exists.");
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
            Ciudades ciudades;
            try {
                ciudades = em.getReference(Ciudades.class, id);
                ciudades.getIdCiudad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudades with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Concesionarios> concesionariosListOrphanCheck = ciudades.getConcesionariosList();
            for (Concesionarios concesionariosListOrphanCheckConcesionarios : concesionariosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ciudades (" + ciudades + ") cannot be destroyed since the Concesionarios " + concesionariosListOrphanCheckConcesionarios + " in its concesionariosList field has a non-nullable idCiudad field.");
            }
            List<Multas> multasListOrphanCheck = ciudades.getMultasList();
            for (Multas multasListOrphanCheckMultas : multasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ciudades (" + ciudades + ") cannot be destroyed since the Multas " + multasListOrphanCheckMultas + " in its multasList field has a non-nullable idCiudad field.");
            }
            List<Personas> personasListOrphanCheck = ciudades.getPersonasList();
            for (Personas personasListOrphanCheckPersonas : personasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ciudades (" + ciudades + ") cannot be destroyed since the Personas " + personasListOrphanCheckPersonas + " in its personasList field has a non-nullable idCiudad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ciudades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ciudades> findCiudadesEntities() {
        return findCiudadesEntities(true, -1, -1);
    }

    public List<Ciudades> findCiudadesEntities(int maxResults, int firstResult) {
        return findCiudadesEntities(false, maxResults, firstResult);
    }

    private List<Ciudades> findCiudadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudades.class));
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

    public Ciudades findCiudades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciudades.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudades> rt = cq.from(Ciudades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
