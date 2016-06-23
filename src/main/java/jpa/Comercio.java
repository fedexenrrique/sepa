/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "comercio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comercio.findAll", query = "SELECT c FROM Comercio c"),
    @NamedQuery(name = "Comercio.findByIdCom", query = "SELECT c FROM Comercio c WHERE c.idCom = :idCom"),
    @NamedQuery(name = "Comercio.findByNomCom", query = "SELECT c FROM Comercio c WHERE c.nomCom = :nomCom"),
    @NamedQuery(name = "Comercio.findByRazonsocial", query = "SELECT c FROM Comercio c WHERE c.razonsocial = :razonsocial")})
public class Comercio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_com")
    private Integer idCom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom_com")
    private String nomCom;
    @Size(max = 50)
    @Column(name = "razonsocial")
    private String razonsocial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comercio")
    private Collection<Local> localCollection;

    public Comercio() {
    }

    public Comercio(Integer idCom) {
        this.idCom = idCom;
    }

    public Comercio(Integer idCom, String nomCom) {
        this.idCom = idCom;
        this.nomCom = nomCom;
    }

    public Integer getIdCom() {
        return idCom;
    }

    public void setIdCom(Integer idCom) {
        this.idCom = idCom;
    }

    public String getNomCom() {
        return nomCom;
    }

    public void setNomCom(String nomCom) {
        this.nomCom = nomCom;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    @XmlTransient
    public Collection<Local> getLocalCollection() {
        return localCollection;
    }

    public void setLocalCollection(Collection<Local> localCollection) {
        this.localCollection = localCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCom != null ? idCom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comercio)) {
            return false;
        }
        Comercio other = (Comercio) object;
        if ((this.idCom == null && other.idCom != null) || (this.idCom != null && !this.idCom.equals(other.idCom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Comercio[ idCom=" + idCom + " ]";
    }
    
}
