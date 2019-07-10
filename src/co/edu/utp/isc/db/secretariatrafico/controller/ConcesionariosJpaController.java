
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.IllegalOrphanException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.utp.isc.db.secretariatrafico.model.Ciudades;
import co.edu.utp.isc.db.secretariatrafico.model.Marcas;
import co.edu.utp.isc.db.secretariatrafico.model.Autos;
import co.edu.utp.isc.db.secretariatrafico.model.Concesionarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class ConcesionariosJpaController implements Serializable {

    public ConcesionariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Concesionarios concesionarios) {
        if (concesionarios.getAutosList() == null) {
            concesionarios.setAutosList(new ArrayList<Autos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudades idCiudad = concesionarios.getIdCiudad();
            if (idCiudad != null) {
                idCiudad = em.getReference(idCiudad.getClass(), idCiudad.getIdCiudad());
                concesionarios.setIdCiudad(idCiudad);
            }
            Marcas idMarca = concesionarios.getIdMarca();
            if (idMarca != null) {
                idMarca = em.getReference(idMarca.getClass(), idMarca.getIdMarca());
                concesionarios.setIdMarca(idMarca);
            }
            List<Autos> attachedAutosList = new ArrayList<Autos>();
            for (Autos autosListAutosToAttach : concesionarios.getAutosList()) {
                autosListAutosToAttach = em.getReference(autosListAutosToAttach.getClass(), autosListAutosToAttach.getMatricula());
                attachedAutosList.add(autosListAutosToAttach);
            }
            concesionarios.setAutosList(attachedAutosList);
            em.persist(concesionarios);
            if (idCiudad != null) {
                idCiudad.getConcesionariosList().add(concesionarios);
                idCiudad = em.merge(idCiudad);
            }
            if (idMarca != null) {
                idMarca.getConcesionariosList().add(concesionarios);
                idMarca = em.merge(idMarca);
            }
            for (Autos autosListAutos : concesionarios.getAutosList()) {
                Concesionarios oldIdConcesionarioOfAutosListAutos = autosListAutos.getIdConcesionario();
                autosListAutos.setIdConcesionario(concesionarios);
                autosListAutos = em.merge(autosListAutos);
                if (oldIdConcesionarioOfAutosListAutos != null) {
                    oldIdConcesionarioOfAutosListAutos.getAutosList().remove(autosListAutos);
                    oldIdConcesionarioOfAutosListAutos = em.merge(oldIdConcesionarioOfAutosListAutos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Concesionarios concesionarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Concesionarios persistentConcesionarios = em.find(Concesionarios.class, concesionarios.getIdConcesionario());
            Ciudades idCiudadOld = persistentConcesionarios.getIdCiudad();
            Ciudades idCiudadNew = concesionarios.getIdCiudad();
            Marcas idMarcaOld = persistentConcesionarios.getIdMarca();
            Marcas idMarcaNew = concesionarios.getIdMarca();
            List<Autos> autosListOld = persistentConcesionarios.getAutosList();
            List<Autos> autosListNew = concesionarios.getAutosList();
            List<String> illegalOrphanMessages = null;
            for (Autos autosListOldAutos : autosListOld) {
                if (!autosListNew.contains(autosListOldAutos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Autos " + autosListOldAutos + " since its idConcesionario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCiudadNew != null) {
                idCiudadNew = em.getReference(idCiudadNew.getClass(), idCiudadNew.getIdCiudad());
                concesionarios.setIdCiudad(idCiudadNew);
            }
            if (idMarcaNew != null) {
                idMarcaNew = em.getReference(idMarcaNew.getClass(), idMarcaNew.getIdMarca());
                concesionarios.setIdMarca(idMarcaNew);
            }
            List<Autos> attachedAutosListNew = new ArrayList<Autos>();
            for (Autos autosListNewAutosToAttach : autosListNew) {
                autosListNewAutosToAttach = em.getReference(autosListNewAutosToAttach.getClass(), autosListNewAutosToAttach.getMatricula());
                attachedAutosListNew.add(autosListNewAutosToAttach);
            }
            autosListNew = attachedAutosListNew;
            concesionarios.setAutosList(autosListNew);
            concesionarios = em.merge(concesionarios);
            if (idCiudadOld != null && !idCiudadOld.equals(idCiudadNew)) {
                idCiudadOld.getConcesionariosList().remove(concesionarios);
                idCiudadOld = em.merge(idCiudadOld);
            }
            if (idCiudadNew != null && !idCiudadNew.equals(idCiudadOld)) {
                idCiudadNew.getConcesionariosList().add(concesionarios);
                idCiudadNew = em.merge(idCiudadNew);
            }
            if (idMarcaOld != null && !idMarcaOld.equals(idMarcaNew)) {
                idMarcaOld.getConcesionariosList().remove(concesionarios);
                idMarcaOld = em.merge(idMarcaOld);
            }
            if (idMarcaNew != null && !idMarcaNew.equals(idMarcaOld)) {
                idMarcaNew.getConcesionariosList().add(concesionarios);
                idMarcaNew = em.merge(idMarcaNew);
            }
            for (Autos autosListNewAutos : autosListNew) {
                if (!autosListOld.contains(autosListNewAutos)) {
                    Concesionarios oldIdConcesionarioOfAutosListNewAutos = autosListNewAutos.getIdConcesionario();
                    autosListNewAutos.setIdConcesionario(concesionarios);
                    autosListNewAutos = em.merge(autosListNewAutos);
                    if (oldIdConcesionarioOfAutosListNewAutos != null && !oldIdConcesionarioOfAutosListNewAutos.equals(concesionarios)) {
                        oldIdConcesionarioOfAutosListNewAutos.getAutosList().remove(autosListNewAutos);
                        oldIdConcesionarioOfAutosListNewAutos = em.merge(oldIdConcesionarioOfAutosListNewAutos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = concesionarios.getIdConcesionario();
                if (findConcesionarios(id) == null) {
                    throw new NonexistentEntityException("The concesionarios with id " + id + " no longer exists.");
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
            Concesionarios concesionarios;
            try {
                concesionarios = em.getReference(Concesionarios.class, id);
                concesionarios.getIdConcesionario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The concesionarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Autos> autosListOrphanCheck = concesionarios.getAutosList();
            for (Autos autosListOrphanCheckAutos : autosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Concesionarios (" + concesionarios + ") cannot be destroyed since the Autos " + autosListOrphanCheckAutos + " in its autosList field has a non-nullable idConcesionario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Ciudades idCiudad = concesionarios.getIdCiudad();
            if (idCiudad != null) {
                idCiudad.getConcesionariosList().remove(concesionarios);
                idCiudad = em.merge(idCiudad);
            }
            Marcas idMarca = concesionarios.getIdMarca();
            if (idMarca != null) {
                idMarca.getConcesionariosList().remove(concesionarios);
                idMarca = em.merge(idMarca);
            }
            em.remove(concesionarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Concesionarios> findConcesionariosEntities() {
        return findConcesionariosEntities(true, -1, -1);
    }

    public List<Concesionarios> findConcesionariosEntities(int maxResults, int firstResult) {
        return findConcesionariosEntities(false, maxResults, firstResult);
    }

    private List<Concesionarios> findConcesionariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Concesionarios.class));
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

    public Concesionarios findConcesionarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Concesionarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getConcesionariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Concesionarios> rt = cq.from(Concesionarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
