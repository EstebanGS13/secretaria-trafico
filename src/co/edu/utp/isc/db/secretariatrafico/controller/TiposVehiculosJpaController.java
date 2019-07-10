
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.IllegalOrphanException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.utp.isc.db.secretariatrafico.model.Autos;
import co.edu.utp.isc.db.secretariatrafico.model.TiposVehiculos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class TiposVehiculosJpaController implements Serializable {

    public TiposVehiculosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiposVehiculos tiposVehiculos) {
        if (tiposVehiculos.getAutosList() == null) {
            tiposVehiculos.setAutosList(new ArrayList<Autos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Autos> attachedAutosList = new ArrayList<Autos>();
            for (Autos autosListAutosToAttach : tiposVehiculos.getAutosList()) {
                autosListAutosToAttach = em.getReference(autosListAutosToAttach.getClass(), autosListAutosToAttach.getMatricula());
                attachedAutosList.add(autosListAutosToAttach);
            }
            tiposVehiculos.setAutosList(attachedAutosList);
            em.persist(tiposVehiculos);
            for (Autos autosListAutos : tiposVehiculos.getAutosList()) {
                TiposVehiculos oldIdTipoVehiculoOfAutosListAutos = autosListAutos.getIdTipoVehiculo();
                autosListAutos.setIdTipoVehiculo(tiposVehiculos);
                autosListAutos = em.merge(autosListAutos);
                if (oldIdTipoVehiculoOfAutosListAutos != null) {
                    oldIdTipoVehiculoOfAutosListAutos.getAutosList().remove(autosListAutos);
                    oldIdTipoVehiculoOfAutosListAutos = em.merge(oldIdTipoVehiculoOfAutosListAutos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiposVehiculos tiposVehiculos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposVehiculos persistentTiposVehiculos = em.find(TiposVehiculos.class, tiposVehiculos.getIdTipoVehiculo());
            List<Autos> autosListOld = persistentTiposVehiculos.getAutosList();
            List<Autos> autosListNew = tiposVehiculos.getAutosList();
            List<String> illegalOrphanMessages = null;
            for (Autos autosListOldAutos : autosListOld) {
                if (!autosListNew.contains(autosListOldAutos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Autos " + autosListOldAutos + " since its idTipoVehiculo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Autos> attachedAutosListNew = new ArrayList<Autos>();
            for (Autos autosListNewAutosToAttach : autosListNew) {
                autosListNewAutosToAttach = em.getReference(autosListNewAutosToAttach.getClass(), autosListNewAutosToAttach.getMatricula());
                attachedAutosListNew.add(autosListNewAutosToAttach);
            }
            autosListNew = attachedAutosListNew;
            tiposVehiculos.setAutosList(autosListNew);
            tiposVehiculos = em.merge(tiposVehiculos);
            for (Autos autosListNewAutos : autosListNew) {
                if (!autosListOld.contains(autosListNewAutos)) {
                    TiposVehiculos oldIdTipoVehiculoOfAutosListNewAutos = autosListNewAutos.getIdTipoVehiculo();
                    autosListNewAutos.setIdTipoVehiculo(tiposVehiculos);
                    autosListNewAutos = em.merge(autosListNewAutos);
                    if (oldIdTipoVehiculoOfAutosListNewAutos != null && !oldIdTipoVehiculoOfAutosListNewAutos.equals(tiposVehiculos)) {
                        oldIdTipoVehiculoOfAutosListNewAutos.getAutosList().remove(autosListNewAutos);
                        oldIdTipoVehiculoOfAutosListNewAutos = em.merge(oldIdTipoVehiculoOfAutosListNewAutos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiposVehiculos.getIdTipoVehiculo();
                if (findTiposVehiculos(id) == null) {
                    throw new NonexistentEntityException("The tiposVehiculos with id " + id + " no longer exists.");
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
            TiposVehiculos tiposVehiculos;
            try {
                tiposVehiculos = em.getReference(TiposVehiculos.class, id);
                tiposVehiculos.getIdTipoVehiculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposVehiculos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Autos> autosListOrphanCheck = tiposVehiculos.getAutosList();
            for (Autos autosListOrphanCheckAutos : autosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TiposVehiculos (" + tiposVehiculos + ") cannot be destroyed since the Autos " + autosListOrphanCheckAutos + " in its autosList field has a non-nullable idTipoVehiculo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tiposVehiculos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiposVehiculos> findTiposVehiculosEntities() {
        return findTiposVehiculosEntities(true, -1, -1);
    }

    public List<TiposVehiculos> findTiposVehiculosEntities(int maxResults, int firstResult) {
        return findTiposVehiculosEntities(false, maxResults, firstResult);
    }

    private List<TiposVehiculos> findTiposVehiculosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TiposVehiculos.class));
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

    public TiposVehiculos findTiposVehiculos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiposVehiculos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposVehiculosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TiposVehiculos> rt = cq.from(TiposVehiculos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
