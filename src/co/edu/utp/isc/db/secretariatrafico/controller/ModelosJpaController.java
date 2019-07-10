
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.IllegalOrphanException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.utp.isc.db.secretariatrafico.model.Marcas;
import co.edu.utp.isc.db.secretariatrafico.model.Autos;
import co.edu.utp.isc.db.secretariatrafico.model.Modelos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class ModelosJpaController implements Serializable {

    public ModelosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Modelos modelos) {
        if (modelos.getAutosList() == null) {
            modelos.setAutosList(new ArrayList<Autos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marcas idMarca = modelos.getIdMarca();
            if (idMarca != null) {
                idMarca = em.getReference(idMarca.getClass(), idMarca.getIdMarca());
                modelos.setIdMarca(idMarca);
            }
            List<Autos> attachedAutosList = new ArrayList<Autos>();
            for (Autos autosListAutosToAttach : modelos.getAutosList()) {
                autosListAutosToAttach = em.getReference(autosListAutosToAttach.getClass(), autosListAutosToAttach.getMatricula());
                attachedAutosList.add(autosListAutosToAttach);
            }
            modelos.setAutosList(attachedAutosList);
            em.persist(modelos);
            if (idMarca != null) {
                idMarca.getModelosList().add(modelos);
                idMarca = em.merge(idMarca);
            }
            for (Autos autosListAutos : modelos.getAutosList()) {
                Modelos oldIdModeloOfAutosListAutos = autosListAutos.getIdModelo();
                autosListAutos.setIdModelo(modelos);
                autosListAutos = em.merge(autosListAutos);
                if (oldIdModeloOfAutosListAutos != null) {
                    oldIdModeloOfAutosListAutos.getAutosList().remove(autosListAutos);
                    oldIdModeloOfAutosListAutos = em.merge(oldIdModeloOfAutosListAutos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Modelos modelos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modelos persistentModelos = em.find(Modelos.class, modelos.getIdModelo());
            Marcas idMarcaOld = persistentModelos.getIdMarca();
            Marcas idMarcaNew = modelos.getIdMarca();
            List<Autos> autosListOld = persistentModelos.getAutosList();
            List<Autos> autosListNew = modelos.getAutosList();
            List<String> illegalOrphanMessages = null;
            for (Autos autosListOldAutos : autosListOld) {
                if (!autosListNew.contains(autosListOldAutos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Autos " + autosListOldAutos + " since its idModelo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idMarcaNew != null) {
                idMarcaNew = em.getReference(idMarcaNew.getClass(), idMarcaNew.getIdMarca());
                modelos.setIdMarca(idMarcaNew);
            }
            List<Autos> attachedAutosListNew = new ArrayList<Autos>();
            for (Autos autosListNewAutosToAttach : autosListNew) {
                autosListNewAutosToAttach = em.getReference(autosListNewAutosToAttach.getClass(), autosListNewAutosToAttach.getMatricula());
                attachedAutosListNew.add(autosListNewAutosToAttach);
            }
            autosListNew = attachedAutosListNew;
            modelos.setAutosList(autosListNew);
            modelos = em.merge(modelos);
            if (idMarcaOld != null && !idMarcaOld.equals(idMarcaNew)) {
                idMarcaOld.getModelosList().remove(modelos);
                idMarcaOld = em.merge(idMarcaOld);
            }
            if (idMarcaNew != null && !idMarcaNew.equals(idMarcaOld)) {
                idMarcaNew.getModelosList().add(modelos);
                idMarcaNew = em.merge(idMarcaNew);
            }
            for (Autos autosListNewAutos : autosListNew) {
                if (!autosListOld.contains(autosListNewAutos)) {
                    Modelos oldIdModeloOfAutosListNewAutos = autosListNewAutos.getIdModelo();
                    autosListNewAutos.setIdModelo(modelos);
                    autosListNewAutos = em.merge(autosListNewAutos);
                    if (oldIdModeloOfAutosListNewAutos != null && !oldIdModeloOfAutosListNewAutos.equals(modelos)) {
                        oldIdModeloOfAutosListNewAutos.getAutosList().remove(autosListNewAutos);
                        oldIdModeloOfAutosListNewAutos = em.merge(oldIdModeloOfAutosListNewAutos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = modelos.getIdModelo();
                if (findModelos(id) == null) {
                    throw new NonexistentEntityException("The modelos with id " + id + " no longer exists.");
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
            Modelos modelos;
            try {
                modelos = em.getReference(Modelos.class, id);
                modelos.getIdModelo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modelos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Autos> autosListOrphanCheck = modelos.getAutosList();
            for (Autos autosListOrphanCheckAutos : autosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Modelos (" + modelos + ") cannot be destroyed since the Autos " + autosListOrphanCheckAutos + " in its autosList field has a non-nullable idModelo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Marcas idMarca = modelos.getIdMarca();
            if (idMarca != null) {
                idMarca.getModelosList().remove(modelos);
                idMarca = em.merge(idMarca);
            }
            em.remove(modelos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Modelos> findModelosEntities() {
        return findModelosEntities(true, -1, -1);
    }

    public List<Modelos> findModelosEntities(int maxResults, int firstResult) {
        return findModelosEntities(false, maxResults, firstResult);
    }

    private List<Modelos> findModelosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Modelos.class));
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

    public Modelos findModelos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Modelos.class, id);
        } finally {
            em.close();
        }
    }

    public int getModelosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Modelos> rt = cq.from(Modelos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
