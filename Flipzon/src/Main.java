import java.util.*;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        boolean a = true;
        while (a) {
            System.out.println("WELCOME TO FLIPZON\n\n\t1) Enter as Admin\n\t2) Explore the Product Catalog\n\t3) Show Available Deals\n\t4) Enter as Customer\n\t5) Exit the Application");
            Scanner sc = new Scanner(System.in);
            int c1 = sc.nextInt();
            switch (c1) {
                case (1) -> {
                   admin.adminLogin();
                    // productCategory pc1=new productCategory();
                    // pc1.categoryID=1;
                    // pc1.categoryName="eyes";
                    // productCategory pc2=new productCategory();
                    // pc2.categoryID=2;
                    // pc2.categoryName="ears";
                    // productCategory pc3=new productCategory();
                    // pc3.categoryID=3;
                    // pc3.categoryName="fingers";
                    // product p1=new product();
                    // p1.productID=1.1f;
                    // p1.productName="blue eyes";
                    // p1.productPrice=2300;
                    // p1.productQuantity=50;
                    // p1.productInfo="These are lovely blue eyes";
                    // product p2=new product();
                    // p2.productID=1.2f;
                    // p2.productName="black eyes";
                    // p2.productPrice=1800;
                    // p2.productQuantity=50;
                    // p2.productInfo="These are lovely black eyes";
                    // product p3=new product();
                    // p3.productID=1.3f;
                    // p3.productName="green eyes";
                    // p3.productPrice=3000;
                    // p3.productQuantity=10;
                    // p3.productInfo="These are alien green or lovely eyes";
                    // product p4=new product();
                    // p4.productID=2.1f;
                    // p4.productName="blue ears";
                    // p4.productPrice=1200;
                    // p4.productQuantity=100;
                    // p4.productInfo="These are lovely blue ears";
                    // product p5=new product();
                    // p5.productID=2.2f;
                    // p5.productName="green ears";
                    // p5.productPrice=100;
                    // p5.productQuantity=10;
                    // p5.productInfo="These are alien green ears";
                    // product p6=new product();
                    // p6.productID=3.1f;
                    // p6.productName="6 fingers";
                    // p6.productPrice=1200;
                    // p6.productQuantity=100;
                    // p6.productInfo="These are rare 6 fingers";
                    // product p7=new product();
                    // p7.productID=3.2f;
                    // p7.productName="4 fingers";
                    // p7.productPrice=2500;
                    // p7.productQuantity=30;
                    // p7.productInfo="These are alien 4 fingers";
                    // pc1.productList.add(p1);
                    // pc1.productList.add(p2);
                    // pc1.productList.add(p3);
                    // pc2.productList.add(p4);
                    // pc2.productList.add(p5);
                    // pc3.productList.add(p6);
                    // pc3.productList.add(p7);
                    // admin.productCategoryList.add(pc1);
                    // admin.productCategoryList.add(pc2);
                    // admin.productCategoryList.add(pc3);
                    // deals d1=new deals();
                    // deals d2=new deals();
                    // d1.p1=p3;
                    // d1.p2=p5;
                    // d1.normalDiscount=5;
                    // d1.primeDiscount=10;
                    // d1.eliteDiscount=15;
                    // admin.dealsList.add(d1);
                    // d2.p1=p1;
                    // d2.p2=p4;
                    // d2.normalDiscount=2;
                    // d2.primeDiscount=6;
                    // d2.eliteDiscount=10;
                    // admin.dealsList.add(d2);
                    // p2.normalDiscount=10;
                    // p2.primeDiscount=15;
                    // p2.eliteDiscount=20;
                    // p7.normalDiscount=6;
                    // p7.primeDiscount=12;
                    // p7.eliteDiscount=18;

                }
                case (2) -> admin.productCatalog();
                case (3) -> admin.deals();
                case (4) -> customer.customerLogin();
                case (5) -> a = false;
                default -> System.out.println("Enter correct choice!");
            }
        }
    }
}