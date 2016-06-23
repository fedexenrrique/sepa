/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Franco
 */
@Entity
@Table(name = "denuncia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Denuncia.findAll", query = "SELECT d FROM Denuncia d"),
    @NamedQuery(name = "Denuncia.findByIdDen", query = "SELECT d FROM Denuncia d WHERE d.idDen = :idDen"),
    @NamedQuery(name = "Denuncia.findByNombre", query = "SELECT d FROM Denuncia d WHERE d.nombre = :nombre")})
public class Denuncia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_den")
    private Integer idDen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "detalle")
    private String detalle;

    public Denuncia() {
    }

    public Denuncia(Integer idDen) {
        this.idDen = idDen;
    }

    public Denuncia(Integer idDen, String nombre, String detalle) {
        this.idDen = idDen;
        this.nombre = nombre;
        this.detalle = detalle;
    }

    public Integer getIdDen() {
        return idDen;
    }

    public void setIdDen(Integer idDen) {
        this.idDen = idDen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDen != null ? idDen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Denuncia)) {
            return false;
        }
        Denuncia other = (Denuncia) object;
        if ((this.idDen == null && other.idDen != null) || (this.idDen != null && !this.idDen.equals(other.idDen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Denuncia[ idDen=" + idDen + " ]";
    }
    
}
