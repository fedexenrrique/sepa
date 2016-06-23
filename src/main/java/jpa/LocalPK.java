/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Franco
 */
@Embeddable
public class LocalPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_com")
    private int idCom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_loc")
    private int idLoc;

    public LocalPK() {
    }

    public LocalPK(int idCom, int idLoc) {
        this.idCom = idCom;
        this.idLoc = idLoc;
    }

    public int getIdCom() {
        return idCom;
    }

    public void setIdCom(int idCom) {
        this.idCom = idCom;
    }

    public int getIdLoc() {
        return idLoc;
    }

    public void setIdLoc(int idLoc) {
        this.idLoc = idLoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCom;
        hash += (int) idLoc;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalPK)) {
            return false;
        }
        LocalPK other = (LocalPK) object;
        if (this.idCom != other.idCom) {
            return false;
        }
        if (this.idLoc != other.idLoc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.LocalPK[ idCom=" + idCom + ", idLoc=" + idLoc + " ]";
    }
    
}
