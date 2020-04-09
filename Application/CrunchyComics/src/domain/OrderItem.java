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
 * Creates the OrderItem object.
 * @author Vinicius Smith
 */
@Entity
@Table(name = "order_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderItem.findAll", query = "SELECT o FROM OrderItem o")
    , @NamedQuery(name = "OrderItem.findByItemID", query = "SELECT o FROM OrderItem o WHERE o.orderItemPK.itemID = :itemID")
    , @NamedQuery(name = "OrderItem.findByOrderNo", query = "SELECT o FROM OrderItem o WHERE o.orderItemPK.orderNo = :orderNo")
    , @NamedQuery(name = "OrderItem.findByPurchasePrice", query = "SELECT o FROM OrderItem o WHERE o.purchasePrice = :purchasePrice")
    , @NamedQuery(name = "OrderItem.findByOrderQuantity", query = "SELECT o FROM OrderItem o WHERE o.orderQuantity = :orderQuantity")})
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderItemPK orderItemPK;
    @Basic(optional = false)
    @Column(name = "purchase_price")
    private float purchasePrice;
    @Column(name = "orderQuantity")
    private Integer orderQuantity;
    @JoinColumn(name = "itemID", referencedColumnName = "itemID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Item item;
    @JoinColumn(name = "order_no", referencedColumnName = "order_no", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Orders orders;

    /**
     * Default constructor for the item object.
     */
    public OrderItem() {
    }

    /**
     * Non-default constructor for the orderItem object based on ID alone.
     * @param orderItemPK 
     */
    public OrderItem(OrderItemPK orderItemPK) {
        this.orderItemPK = orderItemPK;
    }

    /**
     * Non-default constructor for the orderItemObject object based on the primary keys and purchase price.
     * @param orderItemPK
     * @param purchasePrice 
     */
    public OrderItem(OrderItemPK orderItemPK, float purchasePrice) {
        this.orderItemPK = orderItemPK;
        this.purchasePrice = purchasePrice;
    }

    /**
     * Non-default constructor for the orderItemObject object based on the ID and order number.
     * @param itemID
     * @param orderNo 
     */
    public OrderItem(int itemID, int orderNo) {
        this.orderItemPK = new OrderItemPK(itemID, orderNo);
    }

    /**
     * Gets the Order Item primary key
     * @return orderItemPK
     */
    public OrderItemPK getOrderItemPK() {
        return orderItemPK;
    }

    /**
     * Sets the Order Item Primary key.
     * @param orderItemPK 
     */
    public void setOrderItemPK(OrderItemPK orderItemPK) {
        this.orderItemPK = orderItemPK;
    }

    /**
     * Gets the purchase price.
     * @return purchase price.
     */
    public float getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Sets the Purchase Price.
     * @param purchasePrice 
     */
    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Gets the Order Quantity
     * @return orderQuantity
     */
    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * Sets the order quantity.
     * @param orderQuantity 
     */
    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    /**
     * Gets the item related to Order Item.
     * @return item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the item related to the Order Item.
     * @param item 
     */
    public void setItem(Item item) {
        this.item = item;
    }

    
    /**
     * Gets the orders related to the Order items.
     * @return order
     */
    public Orders getOrders() {
        return orders;
    }

    /**
     * Sets the orders related to the Order items.
     * @param orders 
     */
    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    /**
     * Returns the hash value of the Order Item.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderItemPK != null ? orderItemPK.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if the value of the order item equals another item.
     * @param object
     * @return true if it equals otherwise false
     */
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

    /**
     * Returns the order item as a string value.
     * @return "domain.OrderItem[ orderItemPK=" + orderItemPK + " ]"
     */
    @Override
    public String toString() {
        return "domain.OrderItem[ orderItemPK=" + orderItemPK + " ]";
    }
    
}
