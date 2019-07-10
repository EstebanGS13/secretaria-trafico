
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.IllegalOrphanException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import co.edu.utp.isc.db.secretariatrafico.model.Agentes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.utp.isc.db.secretariatrafico.model.Multas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class AgentesJpaController implements Serializable {

    public AgentesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Agentes agentes) {
        if (agentes.getMultasList() == null) {
            agentes.setMultasList(new ArrayList<Multas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Multas> attachedMultasList = new ArrayList<Multas>();
            for (Multas multasListMultasToAttach : agentes.getMultasList()) {
                multasListMultasToAttach = em.getReference(multasListMultasToAttach.getClass(), multasListMultasToAttach.getIdMulta());
                attachedMultasList.add(multasListMultasToAttach);
            }
            agentes.setMultasList(attachedMultasList);
            em.persist(agentes);
            for (Multas multasListMultas : agentes.getMultasList()) {
                Agentes oldIdAgenteOfMultasListMultas = multasListMultas.getIdAgente();
                multasListMultas.setIdAgente(agentes);
                multasListMultas = em.merge(multasListMultas);
                if (oldIdAgenteOfMultasListMultas != null) {
                    oldIdAgenteOfMultasListMultas.getMultasList().remove(multasListMultas);
                    oldIdAgenteOfMultasListMultas = em.merge(oldIdAgenteOfMultasListMultas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agentes agentes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agentes persistentAgentes = em.find(Agentes.class, agentes.getIdAgente());
            List<Multas> multasListOld = persistentAgentes.getMultasList();
            List<Multas> multasListNew = agentes.getMultasList();
            List<String> illegalOrphanMessages = null;
            for (Multas multasListOldMultas : multasListOld) {
                if (!multasListNew.contains(multasListOldMultas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Multas " + multasListOldMultas + " since its idAgente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Multas> attachedMultasListNew = new ArrayList<Multas>();
            for (Multas multasListNewMultasToAttach : multasListNew) {
                multasListNewMultasToAttach = em.getReference(multasListNewMultasToAttach.getClass(), multasListNewMultasToAttach.getIdMulta());
                attachedMultasListNew.add(multasListNewMultasToAttach);
            }
            multasListNew = attachedMultasListNew;
            agentes.setMultasList(multasListNew);
            agentes = em.merge(agentes);
            for (Multas multasListNewMultas : multasListNew) {
                if (!multasListOld.contains(multasListNewMultas)) {
                    Agentes oldIdAgenteOfMultasListNewMultas = multasListNewMultas.getIdAgente();
                    multasListNewMultas.setIdAgente(agentes);
                    multasListNewMultas = em.merge(multasListNewMultas);
                    if (oldIdAgenteOfMultasListNewMultas != null && !oldIdAgenteOfMultasListNewMultas.equals(agentes)) {
                        oldIdAgenteOfMultasListNewMultas.getMultasList().remove(multasListNewMultas);
                        oldIdAgenteOfMultasListNewMultas = em.merge(oldIdAgenteOfMultasListNewMultas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = agentes.getIdAgente();
                if (findAgentes(id) == null) {
                    throw new NonexistentEntityException("The agentes with id " + id + " no longer exists.");
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
            Agentes agentes;
            try {
                agentes = em.getReference(Agentes.class, id);
                agentes.getIdAgente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agentes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Multas> multasListOrphanCheck = agentes.getMultasList();
            for (Multas multasListOrphanCheckMultas : multasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Agentes (" + agentes + ") cannot be destroyed since the Multas " + multasListOrphanCheckMultas + " in its multasList field has a non-nullable idAgente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(agentes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Agentes> findAgentesEntities() {
        return findAgentesEntities(true, -1, -1);
    }

    public List<Agentes> findAgentesEntities(int maxResults, int firstResult) {
        return findAgentesEntities(false, maxResults, firstResult);
    }

    private List<Agentes> findAgentesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Agentes.class));
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

    public Agentes findAgentes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agentes.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgentesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Agentes> rt = cq.from(Agentes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
