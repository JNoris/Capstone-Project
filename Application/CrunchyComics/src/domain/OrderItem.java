/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 695553
 */
@Entity
@Table(name = "order_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderItem.findAll", query = "SELECT o FROM OrderItem o")
    , @NamedQuery(name = "OrderItem.findByItemID", query = "SELECT o FROM OrderItem o WHERE o.orderItemPK.itemID = :itemID")
    , @NamedQuery(name = "OrderItem.findByOrderNo", query = "SELECT o FROM OrderItem o WHERE o.orderItemPK.orderNo = :orderNo")
    , @NamedQuery(name = "OrderItem.findByPurchasePrice", query = "SELECT o FROM OrderItem o WHERE o.purchasePrice = :purchasePrice")})
public class OrderItem implements Serializable {

    @Column(name = "orderQuantity")
    private Integer orderQuantity;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderItemPK orderItemPK;
    @Basic(optional = false)
    @Column(name = "purchase_price")
    private float purchasePrice;
    @JoinColumn(name = "itemID", referencedColumnName = "itemID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Item item;
    @JoinColumn(name = "order_no", referencedColumnName = "order_no", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Orders orders;

    public OrderItem() {
    }

    public OrderItem(OrderItemPK orderItemPK) {
        this.orderItemPK = orderItemPK;
    }

    public OrderItem(OrderItemPK orderItemPK, float purchasePrice) {
        this.orderItemPK = orderItemPK;
        this.purchasePrice = purchasePrice;
    }

    public OrderItem(int itemID, int orderNo) {
        this.orderItemPK = new OrderItemPK(itemID, orderNo);
    }

    public OrderItemPK getOrderItemPK() {
        return orderItemPK;
    }

    public void setOrderItemPK(OrderItemPK orderItemPK) {
        this.orderItemPK = orderItemPK;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderItemPK != null ? orderItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderItem)) {
            return false;
        }
        OrderItem other = (OrderItem) object;
        if ((this.orderItemPK == null && other.orderItemPK != null) || (this.orderItemPK != null && !this.orderItemPK.equals(other.orderItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "broker.OrderItem[ orderItemPK=" + orderItemPK + " ]";
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
    
}
