
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.IllegalOrphanException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.utp.isc.db.secretariatrafico.model.Concesionarios;
import co.edu.utp.isc.db.secretariatrafico.model.Marcas;
import java.util.ArrayList;
import java.util.List;
import co.edu.utp.isc.db.secretariatrafico.model.Modelos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class MarcasJpaController implements Serializable {

    public MarcasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marcas marcas) {
        if (marcas.getConcesionariosList() == null) {
            marcas.setConcesionariosList(new ArrayList<Concesionarios>());
        }
        if (marcas.getModelosList() == null) {
            marcas.setModelosList(new ArrayList<Modelos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Concesionarios> attachedConcesionariosList = new ArrayList<Concesionarios>();
            for (Concesionarios concesionariosListConcesionariosToAttach : marcas.getConcesionariosList()) {
                concesionariosListConcesionariosToAttach = em.getReference(concesionariosListConcesionariosToAttach.getClass(), concesionariosListConcesionariosToAttach.getIdConcesionario());
                attachedConcesionariosList.add(concesionariosListConcesionariosToAttach);
            }
            marcas.setConcesionariosList(attachedConcesionariosList);
            List<Modelos> attachedModelosList = new ArrayList<Modelos>();
            for (Modelos modelosListModelosToAttach : marcas.getModelosList()) {
                modelosListModelosToAttach = em.getReference(modelosListModelosToAttach.getClass(), modelosListModelosToAttach.getIdModelo());
                attachedModelosList.add(modelosListModelosToAttach);
            }
            marcas.setModelosList(attachedModelosList);
            em.persist(marcas);
            for (Concesionarios concesionariosListConcesionarios : marcas.getConcesionariosList()) {
                Marcas oldIdMarcaOfConcesionariosListConcesionarios = concesionariosListConcesionarios.getIdMarca();
                concesionariosListConcesionarios.setIdMarca(marcas);
                concesionariosListConcesionarios = em.merge(concesionariosListConcesionarios);
                if (oldIdMarcaOfConcesionariosListConcesionarios != null) {
                    oldIdMarcaOfConcesionariosListConcesionarios.getConcesionariosList().remove(concesionariosListConcesionarios);
                    oldIdMarcaOfConcesionariosListConcesionarios = em.merge(oldIdMarcaOfConcesionariosListConcesionarios);
                }
            }
            for (Modelos modelosListModelos : marcas.getModelosList()) {
                Marcas oldIdMarcaOfModelosListModelos = modelosListModelos.getIdMarca();
                modelosListModelos.setIdMarca(marcas);
                modelosListModelos = em.merge(modelosListModelos);
                if (oldIdMarcaOfModelosListModelos != null) {
                    oldIdMarcaOfModelosListModelos.getModelosList().remove(modelosListModelos);
                    oldIdMarcaOfModelosListModelos = em.merge(oldIdMarcaOfModelosListModelos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Marcas marcas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marcas persistentMarcas = em.find(Marcas.class, marcas.getIdMarca());
            List<Concesionarios> concesionariosListOld = persistentMarcas.getConcesionariosList();
            List<Concesionarios> concesionariosListNew = marcas.getConcesionariosList();
            List<Modelos> modelosListOld = persistentMarcas.getModelosList();
            List<Modelos> modelosListNew = marcas.getModelosList();
            List<String> illegalOrphanMessages = null;
            for (Concesionarios concesionariosListOldConcesionarios : concesionariosListOld) {
                if (!concesionariosListNew.contains(concesionariosListOldConcesionarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Concesionarios " + concesionariosListOldConcesionarios + " since its idMarca field is not nullable.");
                }
            }
            for (Modelos modelosListOldModelos : modelosListOld) {
                if (!modelosListNew.contains(modelosListOldModelos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Modelos " + modelosListOldModelos + " since its idMarca field is not nullable.");
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
            marcas.setConcesionariosList(concesionariosListNew);
            List<Modelos> attachedModelosListNew = new ArrayList<Modelos>();
            for (Modelos modelosListNewModelosToAttach : modelosListNew) {
                modelosListNewModelosToAttach = em.getReference(modelosListNewModelosToAttach.getClass(), modelosListNewModelosToAttach.getIdModelo());
                attachedModelosListNew.add(modelosListNewModelosToAttach);
            }
            modelosListNew = attachedModelosListNew;
            marcas.setModelosList(modelosListNew);
            marcas = em.merge(marcas);
            for (Concesionarios concesionariosListNewConcesionarios : concesionariosListNew) {
                if (!concesionariosListOld.contains(concesionariosListNewConcesionarios)) {
                    Marcas oldIdMarcaOfConcesionariosListNewConcesionarios = concesionariosListNewConcesionarios.getIdMarca();
                    concesionariosListNewConcesionarios.setIdMarca(marcas);
                    concesionariosListNewConcesionarios = em.merge(concesionariosListNewConcesionarios);
                    if (oldIdMarcaOfConcesionariosListNewConcesionarios != null && !oldIdMarcaOfConcesionariosListNewConcesionarios.equals(marcas)) {
                        oldIdMarcaOfConcesionariosListNewConcesionarios.getConcesionariosList().remove(concesionariosListNewConcesionarios);
                        oldIdMarcaOfConcesionariosListNewConcesionarios = em.merge(oldIdMarcaOfConcesionariosListNewConcesionarios);
                    }
                }
            }
            for (Modelos modelosListNewModelos : modelosListNew) {
                if (!modelosListOld.contains(modelosListNewModelos)) {
                    Marcas oldIdMarcaOfModelosListNewModelos = modelosListNewModelos.getIdMarca();
                    modelosListNewModelos.setIdMarca(marcas);
                    modelosListNewModelos = em.merge(modelosListNewModelos);
                    if (oldIdMarcaOfModelosListNewModelos != null && !oldIdMarcaOfModelosListNewModelos.equals(marcas)) {
                        oldIdMarcaOfModelosListNewModelos.getModelosList().remove(modelosListNewModelos);
                        oldIdMarcaOfModelosListNewModelos = em.merge(oldIdMarcaOfModelosListNewModelos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = marcas.getIdMarca();
                if (findMarcas(id) == null) {
                    throw new NonexistentEntityException("The marcas with id " + id + " no longer exists.");
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
            Marcas marcas;
            try {
                marcas = em.getReference(Marcas.class, id);
                marcas.getIdMarca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marcas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Concesionarios> concesionariosListOrphanCheck = marcas.getConcesionariosList();
            for (Concesionarios concesionariosListOrphanCheckConcesionarios : concesionariosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Marcas (" + marcas + ") cannot be destroyed since the Concesionarios " + concesionariosListOrphanCheckConcesionarios + " in its concesionariosList field has a non-nullable idMarca field.");
            }
            List<Modelos> modelosListOrphanCheck = marcas.getModelosList();
            for (Modelos modelosListOrphanCheckModelos : modelosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Marcas (" + marcas + ") cannot be destroyed since the Modelos " + modelosListOrphanCheckModelos + " in its modelosList field has a non-nullable idMarca field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(marcas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Marcas> findMarcasEntities() {
        return findMarcasEntities(true, -1, -1);
    }

    public List<Marcas> findMarcasEntities(int maxResults, int firstResult) {
        return findMarcasEntities(false, maxResults, firstResult);
    }

    private List<Marcas> findMarcasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Marcas.class));
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

    public Marcas findMarcas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marcas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Marcas> rt = cq.from(Marcas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
