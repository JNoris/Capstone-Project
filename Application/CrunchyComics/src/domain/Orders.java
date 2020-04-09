package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Creates the Orders object.
 * @author Vinicius Smith
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findByOrderNo", query = "SELECT o FROM Orders o WHERE o.orderNo = :orderNo")
    , @NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate")
    , @NamedQuery(name = "Orders.findByArrivalDate", query = "SELECT o FROM Orders o WHERE o.arrivalDate = :arrivalDate")
    , @NamedQuery(name = "Orders.findLastID", query = "SELECT MAX(o.orderNo) FROM Orders o")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "order_no")
    private Integer orderNo;
    @Column(name = "orderDate")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Column(name = "arrivalDate")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders", fetch = FetchType.EAGER)
    private List<OrderItem> orderItemList;
    @JoinColumn(name = "VendorID", referencedColumnName = "vendorID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Vendor vendorID;

    /**
     * Default constructor for the orders object.
     */
    public Orders() {
    }

    /**
     * Non-default constructor for the item object based on Order number alone.
     * @param orderNo 
     */
    public Orders(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Gets the order number
     * @return orderNo
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * Sets the order number
     * @param orderNo 
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Gets the order date.
     * @return orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the order date.
     * @param orderDate 
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets the arrival date.
     * @return arrivalDate
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Sets the arrival date.
     * @param arrivalDate 
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Gets the list of order items.
     * @return orderItemList.
     */
    @XmlTransient
    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    /**
     * Sets the order item list.
     * @param orderItemList 
     */
    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    /**
     * Gets the vendor ID.
     * @return vendorID
     */
    public Vendor getVendorID() {
        return vendorID;
    }

    /**
     * Sets the vendor ID.
     * @param vendorID 
     */
    public void setVendorID(Vendor vendorID) {
        this.vendorID = vendorID;
    }

    /**
     * Gets the hash value of orders
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if the value of the orders equals another item.
     * @param object
     * @return true if it equals otherwise false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        return true;
    }

    /**
     * Returns the order as a string value
     * @return "domain.Orders[ orderNo=" + orderNo + " ]"
     */
    @Override
    public String toString() {
        return "domain.Orders[ orderNo=" + orderNo + " ]";
    }
    
}
