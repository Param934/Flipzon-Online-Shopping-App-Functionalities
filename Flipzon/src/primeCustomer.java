import java.util.ArrayList;
import java.util.*;
public class primeCustomer extends customer implements cust{
    String status="primeCustomer";

    public double deliveryCharge(float totalAmount){
        return (100+0.02*totalAmount);
    }
    public void cancelOrder(customer c){
        for (int i=0;i<c.cart.size();i++){
            c.cart.get(i).productQuantity+=cart.get(i).cartQuantity;
        }
    }
    @Override
    public void checkoutCart(customer c) {
        if (c.cart.isEmpty()){
            System.out.println("Add a product to cart first");
            return;
        }
        Random r=new Random();
        int maxCoupon=0;
        if (!c.Coupons.isEmpty()) {
            maxCoupon = Collections.max(c.Coupons);
        }
        float totalAmount=0;
        float Discount=0;
        boolean usingMaxCoupon=false;
        int maxCouponUsed=0;
        //sorting cart
        for (int i=0;i<c.cart.size();i++){
            for (int j=i+1;j<cart.size();j++){
                product newProduct=new product();
                if (cart.get(i).productPrice<cart.get(j).productPrice){
                    newProduct=cart.get(i);
                    cart.remove(i);
                    cart.add(i, cart.get(j-1));
                    cart.remove(j);
                    cart.add(j,newProduct);
                }
            }
        }
        //Printing cart and calculating total price
        for (int i=0;i<cart.size();i++){
            System.out.println(cart.get(i).getProductID());
            System.out.println(cart.get(i).getProductName());
            System.out.println(cart.get(i).getProductPrice());
            System.out.println(cart.get(i).getCartQuantity());
            System.out.println("----------------------------------");
            cart.get(i).productQuantity-=cart.get(i).cartQuantity;
            totalAmount+=cart.get(i).productPrice* cart.get(i).cartQuantity;
        }
        System.out.println("Price: "+totalAmount);
        double deliveryCharge=deliveryCharge(totalAmount);
        System.out.println("Delivery Charges: "+deliveryCharge);
        for (int i=0;i<cart.size();i++){
            int finalUsingCoupon=0;
            if (cart.get(i).isDeal){
                for (int j=0; j<admin.dealsList.size();j++){
                    if (admin.dealsList.get(j).p1.productID==cart.get(i).productID || admin.dealsList.get(j).p2.productID==cart.get(i).productID){
                        finalUsingCoupon=admin.dealsList.get(j).primeDiscount;
                    }
                }
                System.out.println("Applied "+finalUsingCoupon+"% discount of deal on Product "+cart.get(i).productName);
            }
            else if (cart.get(i).primeDiscount>maxCoupon && cart.get(i).primeDiscount>5){
                finalUsingCoupon=cart.get(i).primeDiscount;
                System.out.println("Applied "+finalUsingCoupon+"% discount on Product "+cart.get(i).productName);
            }
            else if (maxCoupon>5){
                usingMaxCoupon=true;
                finalUsingCoupon=maxCoupon;
                maxCouponUsed=maxCoupon;
                maxCoupon=0;
                System.out.println("Applied "+finalUsingCoupon+"% discount on Product "+cart.get(i).productName);
            }
            else {
                finalUsingCoupon=5;
                System.out.println("Applied "+finalUsingCoupon+"% discount on Product "+cart.get(i).productName);
            }
            Discount+=cart.get(i).productPrice*cart.get(i).cartQuantity*finalUsingCoupon/100;
        }
        System.out.println("Total Discount: "+(Discount));
        System.out.println("Total Cost: "+(totalAmount+deliveryCharge-Discount));
        for (int i=0;i<cart.size();i++){
            if (cart.get(i).productQuantity<0){
                System.out.println("Not enough Quantity available of product with PID: "+cart.get(i).productID);
                cancelOrder(c);
                return;
            }
        }
        if ((totalAmount+deliveryCharge-Discount)>wallet){
            System.out.println("Insufficient balance!");
            cancelOrder(c);
            return;
        }
        else{
            wallet-=(totalAmount+deliveryCharge-Discount);
            for (int i=0;i<cart.size();i++){
                if (cart.get(i).isDeal){
                    cart.get(i).isDeal=false;
                }
            }
            c.cart.clear();
            if (usingMaxCoupon) Coupons.remove(maxCouponUsed);
        }
        int deliveryDays=r.nextInt(4)+3;
        System.out.println("Order Placed. It will be delivered in "+deliveryDays+" days!");
        if (totalAmount>=5000){
            ArrayList<Integer>coupons= generateCoupons(2);
            System.out.println("You have won two coupons of "+coupons.get(0)+"% and "+coupons.get(1)+"% discount. Congratulations!!");
            c.Coupons.addAll(coupons);
        }
    }
    @Override
    public customer upgradeStatus(customer c) {
        System.out.println("Current Status: Prime");
        wallet -= 100;
        if (c.wallet<0){
            System.out.println("Low Balance!!");
            wallet+=100;
        }
        else {
            System.out.println("Status updated to ELITE!");
            customer newc = new eliteCustomer();
            newc.pw=c.pw;
            newc.phno=c.phno;
            newc.name=c.name;
            newc.wallet=c.wallet;
            newc.email=c.email;
            newc.age=c.age;
            newc.cart=c.cart;
            newc.Coupons = c.Coupons;
            customerList.add(newc);
            customerList.remove(c);
            return newc;
        }
        return null;
    }
}
