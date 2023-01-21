import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class normalCustomer extends customer implements cust{
    String status= "normalCustomer";
    public double deliveryCharge(float totalAmount){
        return (100+0.05*totalAmount);
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
        for (int i=0;i<c.cart.size();i++){
            System.out.println(c.cart.get(i).getProductID());
            System.out.println(c.cart.get(i).getProductName());
            System.out.println(c.cart.get(i).getProductPrice());
            System.out.println(c.cart.get(i).getCartQuantity());
            System.out.println("----------------------------------");
            c.cart.get(i).productQuantity-=c.cart.get(i).cartQuantity;
            totalAmount+=c.cart.get(i).productPrice*c.cart.get(i).cartQuantity;
        }
        System.out.println("Price: "+totalAmount);
        double deliveryCharge=deliveryCharge(totalAmount);
        System.out.println("Delivery Charges: "+deliveryCharge);
        for (int i=0;i<cart.size();i++){
            int finalUsingCoupon=0;
            if (cart.get(i).isDeal){
                for (int j=0; j<admin.dealsList.size();j++){
                    if (admin.dealsList.get(j).p1.productID==cart.get(i).productID || admin.dealsList.get(j).p2.productID==cart.get(i).productID){
                        finalUsingCoupon=admin.dealsList.get(j).normalDiscount;
                    }
                }
                System.out.println("Applied "+finalUsingCoupon+"% discount of deal on Product "+cart.get(i).productName);
            }
            else if (cart.get(i).normalDiscount>maxCoupon && cart.get(i).normalDiscount>0){
                finalUsingCoupon=cart.get(i).normalDiscount;
                System.out.println("Applied "+finalUsingCoupon+"% discount on Product "+cart.get(i).productName);
            }
            else if (maxCoupon>0){
                usingMaxCoupon=true;
                finalUsingCoupon=maxCoupon;
                maxCouponUsed=maxCoupon;
                maxCoupon=0;
                System.out.println("Applied "+finalUsingCoupon+"% discount on Product "+cart.get(i).productName);
            }
            else {
                finalUsingCoupon=0;
                System.out.println("Applied "+finalUsingCoupon+"% discount on Product "+cart.get(i).productName);
            }
            Discount+=cart.get(i).productPrice*cart.get(i).cartQuantity*finalUsingCoupon/100;
        }
        System.out.println("Total Discount: "+Discount);
        System.out.println("Total Cost: "+(totalAmount+deliveryCharge-Discount));
        for (int i=0;i<cart.size();i++){
            if (cart.get(i).productQuantity<0){
                System.out.println("Not enough Quantity available of product with PID: "+cart.get(i).productID);
                cancelOrder(c);
                return;
            }
        }
        if ((totalAmount+deliveryCharge-Discount)>c.wallet){
            System.out.println("Insufficient balance!");
            cancelOrder(c);
            return;
        }
        else{
            c.wallet-=(totalAmount+deliveryCharge-Discount);
            for (int i=0;i<cart.size();i++){
                if (cart.get(i).isDeal){
                    cart.get(i).isDeal=false;
                }
            }
            c.cart.clear();
            if (usingMaxCoupon) Coupons.remove(maxCouponUsed);
        }
        int deliveryDays=r.nextInt(3)+7;
        System.out.println("Order Placed. It will be delivered in "+deliveryDays+" days!");
    }
    public customer upgradeStatus(customer c) {
        boolean d = true;
        while (d) {
            System.out.println("Current Status: Normal");
            System.out.print("Choose new Status: ");
            String newStatus = sc.nextLine().toLowerCase();
            if (newStatus.equals("prime")) {
                c.wallet -= 200;
                if (c.wallet < 0) {
                    System.out.println("Low Balance!!");
                    c.wallet += 200;
                    break;
                }
                System.out.println("Status updated to PRIME!");
                customer newc = new primeCustomer();
                newc.pw = c.pw;
                newc.phno = c.phno;
                newc.name = c.name;
                newc.wallet = c.wallet;
                newc.email = c.email;
                newc.age = c.age;
                newc.cart = c.cart;
                newc.Coupons = c.Coupons;
                customerList.add(newc);
                customerList.remove(c);
                d = false;
                return newc;
            } else if (newStatus.equals("elite")) {
                c.wallet -= 300;
                if (c.wallet < 0) {
                    System.out.println("Low Balance!!");
                    c.wallet += 300;
                    break;
                }
                System.out.println("Status updated to ELITE!");
                customer newc = new eliteCustomer();
                newc.pw = c.pw;
                newc.phno = c.phno;
                newc.name = c.name;
                newc.wallet = c.wallet;
                newc.email = c.email;
                newc.age = c.age;
                newc.cart = c.cart;
                newc.Coupons = c.Coupons;
                customerList.add(newc);
                customerList.remove(c);
                d = false;
                return newc;
            } else {
                System.out.println("Type either prime or elite");
            }
        }
        return null;
    }
}
