import java.util.*;
import java.lang.Cloneable;
public interface cust {
    void checkoutCart(customer c);
    void cancelOrder(customer c);

    customer upgradeStatus(customer c) throws CloneNotSupportedException;
    double deliveryCharge(float totalAmount);
}
