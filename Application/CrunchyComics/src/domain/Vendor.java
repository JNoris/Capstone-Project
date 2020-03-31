/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
@Table(name = "vendor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendor.findAll", query = "SELECT v FROM Vendor v")
    , @NamedQuery(name = "Vendor.findByVendorID", query = "SELECT v FROM Vendor v WHERE v.vendorID = :vendorID")
    , @NamedQuery(name = "Vendor.findByVendorName", query = "SELECT v FROM Vendor v WHERE v.vendorName = :vendorName")})
public class Vendor implements Serializable {

    @OneToMany(mappedBy = "vendorID", fetch = FetchType.EAGER)
    private List<Orders> ordersList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "vendorID")
    private Integer vendorID;
    @Basic(optional = false)
    @Column(name = "vendor_name")
    private String vendorName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorID", fetch = FetchType.EAGER)
    private List<Item> itemList;

    public Vendor() {
    }

    public Vendor(Integer vendorID) {
        this.vendorID = vendorID;
    }

    public Vendor(Integer vendorID, String vendorName) {
        this.vendorID = vendorID;
        this.vendorName = vendorName;
    }

    public Integer getVendorID() {
        return vendorID;
    }

    public void setVendorID(Integer vendorID) {
        this.vendorID = vendorID;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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
        hash += (vendorID != null ? vendorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendor)) {
            return false;
        }
        Vendor other = (Vendor) object;
        if ((this.vendorID == null && other.vendorID != null) || (this.vendorID != null && !this.vendorID.equals(other.vendorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "broker.Vendor[ vendorID=" + vendorID + " ]";
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
    
}
