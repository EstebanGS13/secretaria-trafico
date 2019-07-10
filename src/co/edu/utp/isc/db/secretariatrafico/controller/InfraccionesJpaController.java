
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.IllegalOrphanException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.PreexistingEntityException;
import co.edu.utp.isc.db.secretariatrafico.model.Infracciones;
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


public class InfraccionesJpaController implements Serializable {

    public InfraccionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Infracciones infracciones) throws PreexistingEntityException, Exception {
        if (infracciones.getMultasList() == null) {
            infracciones.setMultasList(new ArrayList<Multas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Multas> attachedMultasList = new ArrayList<Multas>();
            for (Multas multasListMultasToAttach : infracciones.getMultasList()) {
                multasListMultasToAttach = em.getReference(multasListMultasToAttach.getClass(), multasListMultasToAttach.getIdMulta());
                attachedMultasList.add(multasListMultasToAttach);
            }
            infracciones.setMultasList(attachedMultasList);
            em.persist(infracciones);
            for (Multas multasListMultas : infracciones.getMultasList()) {
                Infracciones oldCodigoInfraccionOfMultasListMultas = multasListMultas.getCodigoInfraccion();
                multasListMultas.setCodigoInfraccion(infracciones);
                multasListMultas = em.merge(multasListMultas);
                if (oldCodigoInfraccionOfMultasListMultas != null) {
                    oldCodigoInfraccionOfMultasListMultas.getMultasList().remove(multasListMultas);
                    oldCodigoInfraccionOfMultasListMultas = em.merge(oldCodigoInfraccionOfMultasListMultas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInfracciones(infracciones.getCodigoInfraccion()) != null) {
                throw new PreexistingEntityException("Infracciones " + infracciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Infracciones infracciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Infracciones persistentInfracciones = em.find(Infracciones.class, infracciones.getCodigoInfraccion());
            List<Multas> multasListOld = persistentInfracciones.getMultasList();
            List<Multas> multasListNew = infracciones.getMultasList();
            List<String> illegalOrphanMessages = null;
            for (Multas multasListOldMultas : multasListOld) {
                if (!multasListNew.contains(multasListOldMultas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Multas " + multasListOldMultas + " since its codigoInfraccion field is not nullable.");
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
            infracciones.setMultasList(multasListNew);
            infracciones = em.merge(infracciones);
            for (Multas multasListNewMultas : multasListNew) {
                if (!multasListOld.contains(multasListNewMultas)) {
                    Infracciones oldCodigoInfraccionOfMultasListNewMultas = multasListNewMultas.getCodigoInfraccion();
                    multasListNewMultas.setCodigoInfraccion(infracciones);
                    multasListNewMultas = em.merge(multasListNewMultas);
                    if (oldCodigoInfraccionOfMultasListNewMultas != null && !oldCodigoInfraccionOfMultasListNewMultas.equals(infracciones)) {
                        oldCodigoInfraccionOfMultasListNewMultas.getMultasList().remove(multasListNewMultas);
                        oldCodigoInfraccionOfMultasListNewMultas = em.merge(oldCodigoInfraccionOfMultasListNewMultas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = infracciones.getCodigoInfraccion();
                if (findInfracciones(id) == null) {
                    throw new NonexistentEntityException("The infracciones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Infracciones infracciones;
            try {
                infracciones = em.getReference(Infracciones.class, id);
                infracciones.getCodigoInfraccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infracciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Multas> multasListOrphanCheck = infracciones.getMultasList();
            for (Multas multasListOrphanCheckMultas : multasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Infracciones (" + infracciones + ") cannot be destroyed since the Multas " + multasListOrphanCheckMultas + " in its multasList field has a non-nullable codigoInfraccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(infracciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Infracciones> findInfraccionesEntities() {
        return findInfraccionesEntities(true, -1, -1);
    }

    public List<Infracciones> findInfraccionesEntities(int maxResults, int firstResult) {
        return findInfraccionesEntities(false, maxResults, firstResult);
    }

    private List<Infracciones> findInfraccionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Infracciones.class));
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

    public Infracciones findInfracciones(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Infracciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfraccionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Infracciones> rt = cq.from(Infracciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
