/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.Comercio;

/**
 *
 * @author Franco
 */
@Stateless
public class ComercioFacade extends AbstractFacade<Comercio> {

    @PersistenceContext(unitName = "com.mycompany_Lucifer_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComercioFacade() {
        super(Comercio.class);
    }
    
}
