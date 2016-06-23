/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Franco
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByCodEan", query = "SELECT p FROM Producto p WHERE p.codEan = :codEan"),
    @NamedQuery(name = "Producto.findByPreUnitario", query = "SELECT p FROM Producto p WHERE p.preUnitario = :preUnitario"),
    @NamedQuery(name = "Producto.findByPeso", query = "SELECT p FROM Producto p WHERE p.peso = :peso"),
    @NamedQuery(name = "Producto.findByMedAnchoCm", query = "SELECT p FROM Producto p WHERE p.medAnchoCm = :medAnchoCm"),
    @NamedQuery(name = "Producto.findByMedAltoCm", query = "SELECT p FROM Producto p WHERE p.medAltoCm = :medAltoCm"),
    @NamedQuery(name = "Producto.findByMedFondo", query = "SELECT p FROM Producto p WHERE p.medFondo = :medFondo"),
    @NamedQuery(name = "Producto.findByNomProd", query = "SELECT p FROM Producto p WHERE p.nomProd = :nomProd")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_ean")
    private Integer codEan;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pre_unitario")
    private BigDecimal preUnitario;
    @Column(name = "peso")
    private BigDecimal peso;
    @Column(name = "med_ancho_cm")
    private Integer medAnchoCm;
    @Column(name = "med_alto_cm")
    private Integer medAltoCm;
    @Column(name = "med_fondo")
    private Integer medFondo;
    @Size(max = 30)
    @Column(name = "nom_prod")
    private String nomProd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEan")
    private Collection<Promocion> promocionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEan")
    private Collection<LocProd> locProdCollection;

    public Producto() {
    }

    public Producto(Integer codEan) {
        this.codEan = codEan;
    }

    public Integer getCodEan() {
        return codEan;
    }

    public void setCodEan(Integer codEan) {
        this.codEan = codEan;
    }

    public BigDecimal getPreUnitario() {
        return preUnitario;
    }

    public void setPreUnitario(BigDecimal preUnitario) {
        this.preUnitario = preUnitario;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Integer getMedAnchoCm() {
        return medAnchoCm;
    }

    public void setMedAnchoCm(Integer medAnchoCm) {
        this.medAnchoCm = medAnchoCm;
    }

    public Integer getMedAltoCm() {
        return medAltoCm;
    }

    public void setMedAltoCm(Integer medAltoCm) {
        this.medAltoCm = medAltoCm;
    }

    public Integer getMedFondo() {
        return medFondo;
    }

    public void setMedFondo(Integer medFondo) {
        this.medFondo = medFondo;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    @XmlTransient
    public Collection<Promocion> getPromocionCollection() {
        return promocionCollection;
    }

    public void setPromocionCollection(Collection<Promocion> promocionCollection) {
        this.promocionCollection = promocionCollection;
    }

    @XmlTransient
    public Collection<LocProd> getLocProdCollection() {
        return locProdCollection;
    }

    public void setLocProdCollection(Collection<LocProd> locProdCollection) {
        this.locProdCollection = locProdCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEan != null ? codEan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.codEan == null && other.codEan != null) || (this.codEan != null && !this.codEan.equals(other.codEan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Producto[ codEan=" + codEan + " ]";
    }
    
}
