/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Franco
 */
@Entity
@Table(name = "loc_prod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocProd.findAll", query = "SELECT l FROM LocProd l"),
    @NamedQuery(name = "LocProd.findByIdCom", query = "SELECT l FROM LocProd l WHERE l.locProdPK.idCom = :idCom"),
    @NamedQuery(name = "LocProd.findByIdLoc", query = "SELECT l FROM LocProd l WHERE l.locProdPK.idLoc = :idLoc"),
    @NamedQuery(name = "LocProd.findByPrecio", query = "SELECT l FROM LocProd l WHERE l.precio = :precio")})
public class LocProd implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocProdPK locProdPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    @JoinColumn(name = "cod_ean", referencedColumnName = "cod_ean")
    @ManyToOne(optional = false)
    private Producto codEan;
    @JoinColumns({
        @JoinColumn(name = "id_com", referencedColumnName = "id_com", insertable = false, updatable = false),
        @JoinColumn(name = "id_loc", referencedColumnName = "id_loc", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Local local;

    public LocProd() {
    }

    public LocProd(LocProdPK locProdPK) {
        this.locProdPK = locProdPK;
    }

    public LocProd(LocProdPK locProdPK, BigDecimal precio) {
        this.locProdPK = locProdPK;
        this.precio = precio;
    }

    public LocProd(int idCom, int idLoc) {
        this.locProdPK = new LocProdPK(idCom, idLoc);
    }

    public LocProdPK getLocProdPK() {
        return locProdPK;
    }

    public void setLocProdPK(LocProdPK locProdPK) {
        this.locProdPK = locProdPK;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Producto getCodEan() {
        return codEan;
    }

    public void setCodEan(Producto codEan) {
        this.codEan = codEan;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locProdPK != null ? locProdPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocProd)) {
            return false;
        }
        LocProd other = (LocProd) object;
        if ((this.locProdPK == null && other.locProdPK != null) || (this.locProdPK != null && !this.locProdPK.equals(other.locProdPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.LocProd[ locProdPK=" + locProdPK + " ]";
    }
    
}
