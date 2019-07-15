
package co.edu.utp.isc.db.secretariatrafico.controller;

import co.edu.utp.isc.db.secretariatrafico.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.utp.isc.db.secretariatrafico.model.Agentes;
import co.edu.utp.isc.db.secretariatrafico.model.Autos;
import co.edu.utp.isc.db.secretariatrafico.model.Ciudades;
import co.edu.utp.isc.db.secretariatrafico.model.Infracciones;
import co.edu.utp.isc.db.secretariatrafico.model.Multas;
import co.edu.utp.isc.db.secretariatrafico.model.Personas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class MultasJpaController implements Serializable {

    public MultasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Multas multas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agentes idAgente = multas.getIdAgente();
            if (idAgente != null) {
                idAgente = em.getReference(idAgente.getClass(), idAgente.getIdAgente());
                multas.setIdAgente(idAgente);
            }
            Autos matricula = multas.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                multas.setMatricula(matricula);
            }
            Ciudades idCiudad = multas.getIdCiudad();
            if (idCiudad != null) {
                idCiudad = em.getReference(idCiudad.getClass(), idCiudad.getIdCiudad());
                multas.setIdCiudad(idCiudad);
            }
            Infracciones codigoInfraccion = multas.getCodigoInfraccion();
            if (codigoInfraccion != null) {
                codigoInfraccion = em.getReference(codigoInfraccion.getClass(), codigoInfraccion.getCodigoInfraccion());
                multas.setCodigoInfraccion(codigoInfraccion);
            }
            Personas idPersona = multas.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                multas.setIdPersona(idPersona);
            }
            em.persist(multas);
            if (idAgente != null) {
                idAgente.getMultasList().add(multas);
                idAgente = em.merge(idAgente);
            }
            if (matricula != null) {
                matricula.getMultasList().add(multas);
                matricula = em.merge(matricula);
            }
            if (idCiudad != null) {
                idCiudad.getMultasList().add(multas);
                idCiudad = em.merge(idCiudad);
            }
            if (codigoInfraccion != null) {
                codigoInfraccion.getMultasList().add(multas);
                codigoInfraccion = em.merge(codigoInfraccion);
            }
            if (idPersona != null) {
                idPersona.getMultasList().add(multas);
                idPersona = em.merge(idPersona);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Multas multas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Multas persistentMultas = em.find(Multas.class, multas.getIdMulta());
            Agentes idAgenteOld = persistentMultas.getIdAgente();
            Agentes idAgenteNew = multas.getIdAgente();
            Autos matriculaOld = persistentMultas.getMatricula();
            Autos matriculaNew = multas.getMatricula();
            Ciudades idCiudadOld = persistentMultas.getIdCiudad();
            Ciudades idCiudadNew = multas.getIdCiudad();
            Infracciones codigoInfraccionOld = persistentMultas.getCodigoInfraccion();
            Infracciones codigoInfraccionNew = multas.getCodigoInfraccion();
            Personas idPersonaOld = persistentMultas.getIdPersona();
            Personas idPersonaNew = multas.getIdPersona();
            if (idAgenteNew != null) {
                idAgenteNew = em.getReference(idAgenteNew.getClass(), idAgenteNew.getIdAgente());
                multas.setIdAgente(idAgenteNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                multas.setMatricula(matriculaNew);
            }
            if (idCiudadNew != null) {
                idCiudadNew = em.getReference(idCiudadNew.getClass(), idCiudadNew.getIdCiudad());
                multas.setIdCiudad(idCiudadNew);
            }
            if (codigoInfraccionNew != null) {
                codigoInfraccionNew = em.getReference(codigoInfraccionNew.getClass(), codigoInfraccionNew.getCodigoInfraccion());
                multas.setCodigoInfraccion(codigoInfraccionNew);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                multas.setIdPersona(idPersonaNew);
            }
            multas = em.merge(multas);
            if (idAgenteOld != null && !idAgenteOld.equals(idAgenteNew)) {
                idAgenteOld.getMultasList().remove(multas);
                idAgenteOld = em.merge(idAgenteOld);
            }
            if (idAgenteNew != null && !idAgenteNew.equals(idAgenteOld)) {
                idAgenteNew.getMultasList().add(multas);
                idAgenteNew = em.merge(idAgenteNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getMultasList().remove(multas);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getMultasList().add(multas);
                matriculaNew = em.merge(matriculaNew);
            }
            if (idCiudadOld != null && !idCiudadOld.equals(idCiudadNew)) {
                idCiudadOld.getMultasList().remove(multas);
                idCiudadOld = em.merge(idCiudadOld);
            }
            if (idCiudadNew != null && !idCiudadNew.equals(idCiudadOld)) {
                idCiudadNew.getMultasList().add(multas);
                idCiudadNew = em.merge(idCiudadNew);
            }
            if (codigoInfraccionOld != null && !codigoInfraccionOld.equals(codigoInfraccionNew)) {
                codigoInfraccionOld.getMultasList().remove(multas);
                codigoInfraccionOld = em.merge(codigoInfraccionOld);
            }
            if (codigoInfraccionNew != null && !codigoInfraccionNew.equals(codigoInfraccionOld)) {
                codigoInfraccionNew.getMultasList().add(multas);
                codigoInfraccionNew = em.merge(codigoInfraccionNew);
            }
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getMultasList().remove(multas);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getMultasList().add(multas);
                idPersonaNew = em.merge(idPersonaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = multas.getIdMulta();
                if (findMultas(id) == null) {
                    throw new NonexistentEntityException("The multas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Multas multas;
            try {
                multas = em.getReference(Multas.class, id);
                multas.getIdMulta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The multas with id " + id + " no longer exists.", enfe);
            }
            Agentes idAgente = multas.getIdAgente();
            if (idAgente != null) {
                idAgente.getMultasList().remove(multas);
                idAgente = em.merge(idAgente);
            }
            Autos matricula = multas.getMatricula();
            if (matricula != null) {
                matricula.getMultasList().remove(multas);
                matricula = em.merge(matricula);
            }
            Ciudades idCiudad = multas.getIdCiudad();
            if (idCiudad != null) {
                idCiudad.getMultasList().remove(multas);
                idCiudad = em.merge(idCiudad);
            }
            Infracciones codigoInfraccion = multas.getCodigoInfraccion();
            if (codigoInfraccion != null) {
                codigoInfraccion.getMultasList().remove(multas);
                codigoInfraccion = em.merge(codigoInfraccion);
            }
            Personas idPersona = multas.getIdPersona();
            if (idPersona != null) {
                idPersona.getMultasList().remove(multas);
                idPersona = em.merge(idPersona);
            }
            em.remove(multas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Multas> findMultasEntities() {
        return findMultasEntities(true, -1, -1);
    }

    public List<Multas> findMultasEntities(int maxResults, int firstResult) {
        return findMultasEntities(false, maxResults, firstResult);
    }

    private List<Multas> findMultasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Multas.class));
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

    public Multas findMultas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Multas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMultasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Multas> rt = cq.from(Multas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
