package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
 * Creates the vendor object.
 * @author Vinicius Smith
 */
@Entity
@Table(name = "vendor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendor.findAll", query = "SELECT v FROM Vendor v")
    , @NamedQuery(name = "Vendor.findByVendorID", query = "SELECT v FROM Vendor v WHERE v.vendorID = :vendorID")
    , @NamedQuery(name = "Vendor.findByVendorName", query = "SELECT v FROM Vendor v WHERE v.vendorName = :vendorName")
    , @NamedQuery(name = "Vendor.findLastID", query = "SELECT MAX(v.vendorID) FROM Vendor v")})
public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "vendorID")
    private Integer vendorID;
    @Basic(optional = false)
    @Column(name = "vendor_name")
    private String vendorName;
    @OneToMany(mappedBy = "vendorID", fetch = FetchType.EAGER)
    private List<Orders> ordersList;

    /**
     * Default constructor for the vendor object.
     */
    public Vendor() {
    }

    /**
     * Non-default constructor for the vendor based on vendor ID alone.
     * @param vendorID 
     */
    public Vendor(Integer vendorID) {
        this.vendorID = vendorID;
    }

    /**
     * Non-default constructor for the vendor.
     * @param vendorID
     * @param vendorName 
     */
    public Vendor(Integer vendorID, String vendorName) {
        this.vendorID = vendorID;
        this.vendorName = vendorName;
    }

    /**
     * Gets the vendor ID.
     * @return vendorID
     */
    public Integer getVendorID() {
        return vendorID;
    }

    /**
     * Sets the vendor ID
     * @param vendorID 
     */
    public void setVendorID(Integer vendorID) {
        this.vendorID = vendorID;
    }

    /**
     * Gets the vendor name.
     * @return vendorName
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * Sets the vendor names.
     * @param vendorName 
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * Gets the orders list.
     * @return orderList
     */
    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    /**
     * Sets the orders list.
     * @param ordersList 
     */
    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    /**
     * Gets the hash value of the vendor.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorID != null ? vendorID.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if the value of the transaction equals another item.
     * @param object
     * @return true if it equals otherwise false
     */
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

    /**
     * Returns the vendor as a string value.
     * @return vendorName
     */
    @Override
    public String toString() {
        return vendorName;
    }

}
