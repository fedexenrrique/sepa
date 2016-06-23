/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Franco
 */
@Entity
@Table(name = "local")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Local.findAll", query = "SELECT l FROM Local l"),
    @NamedQuery(name = "Local.findByIdCom", query = "SELECT l FROM Local l WHERE l.localPK.idCom = :idCom"),
    @NamedQuery(name = "Local.findByIdLoc", query = "SELECT l FROM Local l WHERE l.localPK.idLoc = :idLoc"),
    @NamedQuery(name = "Local.findByDomicilio", query = "SELECT l FROM Local l WHERE l.domicilio = :domicilio"),
    @NamedQuery(name = "Local.findByUbicX", query = "SELECT l FROM Local l WHERE l.ubicX = :ubicX"),
    @NamedQuery(name = "Local.findByUbicY", query = "SELECT l FROM Local l WHERE l.ubicY = :ubicY"),
    @NamedQuery(name = "Local.findByCuit", query = "SELECT l FROM Local l WHERE l.cuit = :cuit")})
public class Local implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocalPK localPK;
    @Size(max = 70)
    @Column(name = "domicilio")
    private String domicilio;
    @Column(name = "ubic_x")
    private BigInteger ubicX;
    @Column(name = "ubic_y")
    private BigInteger ubicY;
    @Column(name = "cuit")
    private Long cuit;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "local")
    private Promocion promocion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "local")
    private LocProd locProd;
    @JoinColumn(name = "id_com", referencedColumnName = "id_com", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comercio comercio;

    public Local() {
    }

    public Local(LocalPK localPK) {
        this.localPK = localPK;
    }

    public Local(int idCom, int idLoc) {
        this.localPK = new LocalPK(idCom, idLoc);
    }

    public LocalPK getLocalPK() {
        return localPK;
    }

    public void setLocalPK(LocalPK localPK) {
        this.localPK = localPK;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public BigInteger getUbicX() {
        return ubicX;
    }

    public void setUbicX(BigInteger ubicX) {
        this.ubicX = ubicX;
    }

    public BigInteger getUbicY() {
        return ubicY;
    }

    public void setUbicY(BigInteger ubicY) {
        this.ubicY = ubicY;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public LocProd getLocProd() {
        return locProd;
    }

    public void setLocProd(LocProd locProd) {
        this.locProd = locProd;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localPK != null ? localPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.localPK == null && other.localPK != null) || (this.localPK != null && !this.localPK.equals(other.localPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Local[ localPK=" + localPK + " ]";
    }
    
}
