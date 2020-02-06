/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 695553
 */
@Entity
@Table(name = "type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t")
    , @NamedQuery(name = "Type.findByItemType", query = "SELECT t FROM Type t WHERE t.itemType = :itemType")})
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "item_type")
    private String itemType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemType", fetch = FetchType.EAGER)
    private List<Item> itemList;

    public Type() {
    }

    public Type(String itemType) {
        this.itemType = itemType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemType != null ? itemType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type) object;
        if ((this.itemType == null && other.itemType != null) || (this.itemType != null && !this.itemType.equals(other.itemType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "broker.Type[ itemType=" + itemType + " ]";
    }
    
}
