
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.IllegalOrphanException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.utp.isc.db.secretariatrafico.model.Personas;
import co.edu.utp.isc.db.secretariatrafico.model.TiposPersonas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class TiposPersonasJpaController implements Serializable {

    public TiposPersonasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiposPersonas tiposPersonas) {
        if (tiposPersonas.getPersonasList() == null) {
            tiposPersonas.setPersonasList(new ArrayList<Personas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Personas> attachedPersonasList = new ArrayList<Personas>();
            for (Personas personasListPersonasToAttach : tiposPersonas.getPersonasList()) {
                personasListPersonasToAttach = em.getReference(personasListPersonasToAttach.getClass(), personasListPersonasToAttach.getIdPersona());
                attachedPersonasList.add(personasListPersonasToAttach);
            }
            tiposPersonas.setPersonasList(attachedPersonasList);
            em.persist(tiposPersonas);
            for (Personas personasListPersonas : tiposPersonas.getPersonasList()) {
                TiposPersonas oldIdTipoPersonaOfPersonasListPersonas = personasListPersonas.getIdTipoPersona();
                personasListPersonas.setIdTipoPersona(tiposPersonas);
                personasListPersonas = em.merge(personasListPersonas);
                if (oldIdTipoPersonaOfPersonasListPersonas != null) {
                    oldIdTipoPersonaOfPersonasListPersonas.getPersonasList().remove(personasListPersonas);
                    oldIdTipoPersonaOfPersonasListPersonas = em.merge(oldIdTipoPersonaOfPersonasListPersonas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiposPersonas tiposPersonas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposPersonas persistentTiposPersonas = em.find(TiposPersonas.class, tiposPersonas.getIdTipoPersona());
            List<Personas> personasListOld = persistentTiposPersonas.getPersonasList();
            List<Personas> personasListNew = tiposPersonas.getPersonasList();
            List<String> illegalOrphanMessages = null;
            for (Personas personasListOldPersonas : personasListOld) {
                if (!personasListNew.contains(personasListOldPersonas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Personas " + personasListOldPersonas + " since its idTipoPersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Personas> attachedPersonasListNew = new ArrayList<Personas>();
            for (Personas personasListNewPersonasToAttach : personasListNew) {
                personasListNewPersonasToAttach = em.getReference(personasListNewPersonasToAttach.getClass(), personasListNewPersonasToAttach.getIdPersona());
                attachedPersonasListNew.add(personasListNewPersonasToAttach);
            }
            personasListNew = attachedPersonasListNew;
            tiposPersonas.setPersonasList(personasListNew);
            tiposPersonas = em.merge(tiposPersonas);
            for (Personas personasListNewPersonas : personasListNew) {
                if (!personasListOld.contains(personasListNewPersonas)) {
                    TiposPersonas oldIdTipoPersonaOfPersonasListNewPersonas = personasListNewPersonas.getIdTipoPersona();
                    personasListNewPersonas.setIdTipoPersona(tiposPersonas);
                    personasListNewPersonas = em.merge(personasListNewPersonas);
                    if (oldIdTipoPersonaOfPersonasListNewPersonas != null && !oldIdTipoPersonaOfPersonasListNewPersonas.equals(tiposPersonas)) {
                        oldIdTipoPersonaOfPersonasListNewPersonas.getPersonasList().remove(personasListNewPersonas);
                        oldIdTipoPersonaOfPersonasListNewPersonas = em.merge(oldIdTipoPersonaOfPersonasListNewPersonas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiposPersonas.getIdTipoPersona();
                if (findTiposPersonas(id) == null) {
                    throw new NonexistentEntityException("The tiposPersonas with id " + id + " no longer exists.");
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
            TiposPersonas tiposPersonas;
            try {
                tiposPersonas = em.getReference(TiposPersonas.class, id);
                tiposPersonas.getIdTipoPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposPersonas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Personas> personasListOrphanCheck = tiposPersonas.getPersonasList();
            for (Personas personasListOrphanCheckPersonas : personasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TiposPersonas (" + tiposPersonas + ") cannot be destroyed since the Personas " + personasListOrphanCheckPersonas + " in its personasList field has a non-nullable idTipoPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tiposPersonas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiposPersonas> findTiposPersonasEntities() {
        return findTiposPersonasEntities(true, -1, -1);
    }

    public List<TiposPersonas> findTiposPersonasEntities(int maxResults, int firstResult) {
        return findTiposPersonasEntities(false, maxResults, firstResult);
    }

    private List<TiposPersonas> findTiposPersonasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TiposPersonas.class));
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

    public TiposPersonas findTiposPersonas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiposPersonas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposPersonasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TiposPersonas> rt = cq.from(TiposPersonas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
