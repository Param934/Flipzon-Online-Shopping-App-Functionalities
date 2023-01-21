import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class eliteCustomer extends customer implements cust{
    String status="eliteCustomer";
    public double deliveryCharge(float totalAmount){
        return (100);
    }
    public void cancelOrder(customer c){
        for (int i=0;i<c.cart.size();i++){
            c.cart.get(i).productQuantity+=cart.get(i).cartQuantity;
        }
    }
    public void checkoutCart(customer c) {
        if (c.cart.isEmpty()){
            System.out.println("Add a product to cart first");
            return;
        }
        Random r = new Random();
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
        for (int i = 0; i < cart.size(); i++) {
            System.out.println(cart.get(i).getProductID());
            System.out.println(cart.get(i).getProductName());
            System.out.println(cart.get(i).getProductPrice());
            System.out.println(cart.get(i).getCartQuantity());
            System.out.println("----------------------------------");
            cart.get(i).productQuantity-=cart.get(i).cartQuantity;
            totalAmount += cart.get(i).productPrice * cart.get(i).cartQuantity;
        }
        System.out.println("Price: "+totalAmount);
        double deliveryCharge=deliveryCharge(totalAmount);
        System.out.println("Delivery Charges: "+deliveryCharge);
        for (int i=0;i<cart.size();i++){
            int finalUsingCoupon=0;
            if (cart.get(i).isDeal){
                for (int j=0; j<admin.dealsList.size();j++){
                    if (admin.dealsList.get(j).p1.productID==cart.get(i).productID || admin.dealsList.get(j).p2.productID==cart.get(i).productID){
                        finalUsingCoupon=admin.dealsList.get(j).eliteDiscount;
                    }
                }
                System.out.println("Applied "+finalUsingCoupon+"% discount of deal on Product "+cart.get(i).productName);
            }
            else if (cart.get(i).eliteDiscount>maxCoupon && cart.get(i).eliteDiscount>10){
                finalUsingCoupon=cart.get(i).eliteDiscount;
                System.out.println("Applied "+finalUsingCoupon+"% discount on Product "+cart.get(i).productName);
            }
            else if (maxCoupon>10){
                usingMaxCoupon=true;
                finalUsingCoupon=maxCoupon;
                maxCouponUsed=maxCoupon;
                maxCoupon=0;
                System.out.println("Applied "+finalUsingCoupon+"% discount on Product "+cart.get(i).productName);
            }
            else {
                finalUsingCoupon=10;
                System.out.println("Applied "+finalUsingCoupon+"% discount on Product "+cart.get(i).productName);
            }
            Discount+=cart.get(i).productPrice*cart.get(i).cartQuantity*finalUsingCoupon/100;
        }
        System.out.println("Total Discount: " + Discount);
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
        int deliveryDays=r.nextInt(1)+1;
        System.out.println("Order Placed. It will be delivered in "+deliveryDays+" days!");
        if (totalAmount >= 5000) {
            ArrayList<Integer> coupons = generateCoupons(4);
            System.out.println("You have won four coupons of " + coupons.get(0) + "%, " + coupons.get(1)+ "%, " + coupons.get(2)+ "% and " + coupons.get(3) + "% discount. Congratulations!!");
            c.Coupons.addAll(coupons);
        }
        int randomPCID=r.nextInt(admin.productCategoryList.size());
        int randomPID=r.nextInt(admin.productCategoryList.get(randomPCID).productList.size());
        product freeProduct=admin.productCategoryList.get(randomPCID).productList.get(randomPID);
        admin.productCategoryList.get(randomPCID).productList.get(randomPID).productQuantity-=1;
        System.out.println("Congratulations, you got a "+admin.productCategoryList.get(randomPCID).productList.get(randomPID).productName+" for free!");
    }

    @Override
    public customer upgradeStatus(customer c) {
        System.out.println("No options to upgrade status from ELITE!");
        return null;
    }
}
