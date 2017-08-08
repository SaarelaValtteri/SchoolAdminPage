/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Coursemodule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Inga
 */
@Stateless
public class CoursemoduleFacade extends AbstractFacade<Coursemodule> {

    @PersistenceContext(unitName = "SchoolProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoursemoduleFacade() {
        super(Coursemodule.class);
    }
    
}
