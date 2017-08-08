/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Studentgrades;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Inga
 */
@Stateless
public class StudentgradesFacade extends AbstractFacade<Studentgrades> {

    @PersistenceContext(unitName = "SchoolProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentgradesFacade() {
        super(Studentgrades.class);
    }
    
}
