/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Franco
 */
@Entity
@Table(name = "promocion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promocion.findAll", query = "SELECT p FROM Promocion p"),
    @NamedQuery(name = "Promocion.findByIdCom", query = "SELECT p FROM Promocion p WHERE p.promocionPK.idCom = :idCom"),
    @NamedQuery(name = "Promocion.findByIdLoc", query = "SELECT p FROM Promocion p WHERE p.promocionPK.idLoc = :idLoc"),
    @NamedQuery(name = "Promocion.findByPrecio", query = "SELECT p FROM Promocion p WHERE p.precio = :precio"),
    @NamedQuery(name = "Promocion.findByDescPorcen", query = "SELECT p FROM Promocion p WHERE p.descPorcen = :descPorcen"),
    @NamedQuery(name = "Promocion.findByDescMonto", query = "SELECT p FROM Promocion p WHERE p.descMonto = :descMonto"),
    @NamedQuery(name = "Promocion.findByDescHasta", query = "SELECT p FROM Promocion p WHERE p.descHasta = :descHasta")})
public class Promocion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PromocionPK promocionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "desc_porcen")
    private Integer descPorcen;
    @Column(name = "desc_monto")
    private BigDecimal descMonto;
    @Column(name = "desc_hasta")
    @Temporal(TemporalType.DATE)
    private Date descHasta;
    @JoinColumn(name = "cod_ean", referencedColumnName = "cod_ean")
    @ManyToOne(optional = false)
    private Producto codEan;
    @JoinColumns({
        @JoinColumn(name = "id_com", referencedColumnName = "id_com", insertable = false, updatable = false),
        @JoinColumn(name = "id_loc", referencedColumnName = "id_loc", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Local local;

    public Promocion() {
    }

    public Promocion(PromocionPK promocionPK) {
        this.promocionPK = promocionPK;
    }

    public Promocion(PromocionPK promocionPK, BigDecimal precio) {
        this.promocionPK = promocionPK;
        this.precio = precio;
    }

    public Promocion(int idCom, int idLoc) {
        this.promocionPK = new PromocionPK(idCom, idLoc);
    }

    public PromocionPK getPromocionPK() {
        return promocionPK;
    }

    public void setPromocionPK(PromocionPK promocionPK) {
        this.promocionPK = promocionPK;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getDescPorcen() {
        return descPorcen;
    }

    public void setDescPorcen(Integer descPorcen) {
        this.descPorcen = descPorcen;
    }

    public BigDecimal getDescMonto() {
        return descMonto;
    }

    public void setDescMonto(BigDecimal descMonto) {
        this.descMonto = descMonto;
    }

    public Date getDescHasta() {
        return descHasta;
    }

    public void setDescHasta(Date descHasta) {
        this.descHasta = descHasta;
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
        hash += (promocionPK != null ? promocionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promocion)) {
            return false;
        }
        Promocion other = (Promocion) object;
        if ((this.promocionPK == null && other.promocionPK != null) || (this.promocionPK != null && !this.promocionPK.equals(other.promocionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Promocion[ promocionPK=" + promocionPK + " ]";
    }
    
}
