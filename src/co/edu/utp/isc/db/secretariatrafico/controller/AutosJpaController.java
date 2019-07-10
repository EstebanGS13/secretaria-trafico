
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.PreexistingEntityException;
import co.edu.utp.isc.db.secretariatrafico.model.Autos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.utp.isc.db.secretariatrafico.model.Concesionarios;
import co.edu.utp.isc.db.secretariatrafico.model.Modelos;
import co.edu.utp.isc.db.secretariatrafico.model.Personas;
import co.edu.utp.isc.db.secretariatrafico.model.TiposVehiculos;
import co.edu.utp.isc.db.secretariatrafico.model.Multas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class AutosJpaController implements Serializable {

    public AutosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autos autos) throws PreexistingEntityException, Exception {
        if (autos.getMultasList() == null) {
            autos.setMultasList(new ArrayList<Multas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Concesionarios idConcesionario = autos.getIdConcesionario();
            if (idConcesionario != null) {
                idConcesionario = em.getReference(idConcesionario.getClass(), idConcesionario.getIdConcesionario());
                autos.setIdConcesionario(idConcesionario);
            }
            Modelos idModelo = autos.getIdModelo();
            if (idModelo != null) {
                idModelo = em.getReference(idModelo.getClass(), idModelo.getIdModelo());
                autos.setIdModelo(idModelo);
            }
            Personas idPersona = autos.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                autos.setIdPersona(idPersona);
            }
            TiposVehiculos idTipoVehiculo = autos.getIdTipoVehiculo();
            if (idTipoVehiculo != null) {
                idTipoVehiculo = em.getReference(idTipoVehiculo.getClass(), idTipoVehiculo.getIdTipoVehiculo());
                autos.setIdTipoVehiculo(idTipoVehiculo);
            }
            List<Multas> attachedMultasList = new ArrayList<Multas>();
            for (Multas multasListMultasToAttach : autos.getMultasList()) {
                multasListMultasToAttach = em.getReference(multasListMultasToAttach.getClass(), multasListMultasToAttach.getIdMulta());
                attachedMultasList.add(multasListMultasToAttach);
            }
            autos.setMultasList(attachedMultasList);
            em.persist(autos);
            if (idConcesionario != null) {
                idConcesionario.getAutosList().add(autos);
                idConcesionario = em.merge(idConcesionario);
            }
            if (idModelo != null) {
                idModelo.getAutosList().add(autos);
                idModelo = em.merge(idModelo);
            }
            if (idPersona != null) {
                idPersona.getAutosList().add(autos);
                idPersona = em.merge(idPersona);
            }
            if (idTipoVehiculo != null) {
                idTipoVehiculo.getAutosList().add(autos);
                idTipoVehiculo = em.merge(idTipoVehiculo);
            }
            for (Multas multasListMultas : autos.getMultasList()) {
                Autos oldMatriculaOfMultasListMultas = multasListMultas.getMatricula();
                multasListMultas.setMatricula(autos);
                multasListMultas = em.merge(multasListMultas);
                if (oldMatriculaOfMultasListMultas != null) {
                    oldMatriculaOfMultasListMultas.getMultasList().remove(multasListMultas);
                    oldMatriculaOfMultasListMultas = em.merge(oldMatriculaOfMultasListMultas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutos(autos.getMatricula()) != null) {
                throw new PreexistingEntityException("Autos " + autos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autos autos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autos persistentAutos = em.find(Autos.class, autos.getMatricula());
            Concesionarios idConcesionarioOld = persistentAutos.getIdConcesionario();
            Concesionarios idConcesionarioNew = autos.getIdConcesionario();
            Modelos idModeloOld = persistentAutos.getIdModelo();
            Modelos idModeloNew = autos.getIdModelo();
            Personas idPersonaOld = persistentAutos.getIdPersona();
            Personas idPersonaNew = autos.getIdPersona();
            TiposVehiculos idTipoVehiculoOld = persistentAutos.getIdTipoVehiculo();
            TiposVehiculos idTipoVehiculoNew = autos.getIdTipoVehiculo();
            List<Multas> multasListOld = persistentAutos.getMultasList();
            List<Multas> multasListNew = autos.getMultasList();
            if (idConcesionarioNew != null) {
                idConcesionarioNew = em.getReference(idConcesionarioNew.getClass(), idConcesionarioNew.getIdConcesionario());
                autos.setIdConcesionario(idConcesionarioNew);
            }
            if (idModeloNew != null) {
                idModeloNew = em.getReference(idModeloNew.getClass(), idModeloNew.getIdModelo());
                autos.setIdModelo(idModeloNew);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                autos.setIdPersona(idPersonaNew);
            }
            if (idTipoVehiculoNew != null) {
                idTipoVehiculoNew = em.getReference(idTipoVehiculoNew.getClass(), idTipoVehiculoNew.getIdTipoVehiculo());
                autos.setIdTipoVehiculo(idTipoVehiculoNew);
            }
            List<Multas> attachedMultasListNew = new ArrayList<Multas>();
            for (Multas multasListNewMultasToAttach : multasListNew) {
                multasListNewMultasToAttach = em.getReference(multasListNewMultasToAttach.getClass(), multasListNewMultasToAttach.getIdMulta());
                attachedMultasListNew.add(multasListNewMultasToAttach);
            }
            multasListNew = attachedMultasListNew;
            autos.setMultasList(multasListNew);
            autos = em.merge(autos);
            if (idConcesionarioOld != null && !idConcesionarioOld.equals(idConcesionarioNew)) {
                idConcesionarioOld.getAutosList().remove(autos);
                idConcesionarioOld = em.merge(idConcesionarioOld);
            }
            if (idConcesionarioNew != null && !idConcesionarioNew.equals(idConcesionarioOld)) {
                idConcesionarioNew.getAutosList().add(autos);
                idConcesionarioNew = em.merge(idConcesionarioNew);
            }
            if (idModeloOld != null && !idModeloOld.equals(idModeloNew)) {
                idModeloOld.getAutosList().remove(autos);
                idModeloOld = em.merge(idModeloOld);
            }
            if (idModeloNew != null && !idModeloNew.equals(idModeloOld)) {
                idModeloNew.getAutosList().add(autos);
                idModeloNew = em.merge(idModeloNew);
            }
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getAutosList().remove(autos);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getAutosList().add(autos);
                idPersonaNew = em.merge(idPersonaNew);
            }
            if (idTipoVehiculoOld != null && !idTipoVehiculoOld.equals(idTipoVehiculoNew)) {
                idTipoVehiculoOld.getAutosList().remove(autos);
                idTipoVehiculoOld = em.merge(idTipoVehiculoOld);
            }
            if (idTipoVehiculoNew != null && !idTipoVehiculoNew.equals(idTipoVehiculoOld)) {
                idTipoVehiculoNew.getAutosList().add(autos);
                idTipoVehiculoNew = em.merge(idTipoVehiculoNew);
            }
            for (Multas multasListOldMultas : multasListOld) {
                if (!multasListNew.contains(multasListOldMultas)) {
                    multasListOldMultas.setMatricula(null);
                    multasListOldMultas = em.merge(multasListOldMultas);
                }
            }
            for (Multas multasListNewMultas : multasListNew) {
                if (!multasListOld.contains(multasListNewMultas)) {
                    Autos oldMatriculaOfMultasListNewMultas = multasListNewMultas.getMatricula();
                    multasListNewMultas.setMatricula(autos);
                    multasListNewMultas = em.merge(multasListNewMultas);
                    if (oldMatriculaOfMultasListNewMultas != null && !oldMatriculaOfMultasListNewMultas.equals(autos)) {
                        oldMatriculaOfMultasListNewMultas.getMultasList().remove(multasListNewMultas);
                        oldMatriculaOfMultasListNewMultas = em.merge(oldMatriculaOfMultasListNewMultas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = autos.getMatricula();
                if (findAutos(id) == null) {
                    throw new NonexistentEntityException("The autos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autos autos;
            try {
                autos = em.getReference(Autos.class, id);
                autos.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autos with id " + id + " no longer exists.", enfe);
            }
            Concesionarios idConcesionario = autos.getIdConcesionario();
            if (idConcesionario != null) {
                idConcesionario.getAutosList().remove(autos);
                idConcesionario = em.merge(idConcesionario);
            }
            Modelos idModelo = autos.getIdModelo();
            if (idModelo != null) {
                idModelo.getAutosList().remove(autos);
                idModelo = em.merge(idModelo);
            }
            Personas idPersona = autos.getIdPersona();
            if (idPersona != null) {
                idPersona.getAutosList().remove(autos);
                idPersona = em.merge(idPersona);
            }
            TiposVehiculos idTipoVehiculo = autos.getIdTipoVehiculo();
            if (idTipoVehiculo != null) {
                idTipoVehiculo.getAutosList().remove(autos);
                idTipoVehiculo = em.merge(idTipoVehiculo);
            }
            List<Multas> multasList = autos.getMultasList();
            for (Multas multasListMultas : multasList) {
                multasListMultas.setMatricula(null);
                multasListMultas = em.merge(multasListMultas);
            }
            em.remove(autos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autos> findAutosEntities() {
        return findAutosEntities(true, -1, -1);
    }

    public List<Autos> findAutosEntities(int maxResults, int firstResult) {
        return findAutosEntities(false, maxResults, firstResult);
    }

    private List<Autos> findAutosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autos.class));
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

    public Autos findAutos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autos.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Autos> rt = cq.from(Autos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
