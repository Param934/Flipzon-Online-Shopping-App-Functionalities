import java.sql.SQLOutput;
import java.util.*;
import java.lang.Cloneable;
public abstract class customer implements Cloneable{
    public static ArrayList<customer> customerList = new ArrayList<>();
    ArrayList<product> cart = new ArrayList<>();
    ArrayList<Integer> Coupons= new ArrayList<>();
    ArrayList<deals> dealsCart = new ArrayList<>();
    String name;
    float wallet=1000;
    int age;
    long phno;
    String email;
    String pw;
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    static Scanner sc = new Scanner(System.in);

    public static String getWallet(customer c) {return ("Current account balance is Rs."+ c.wallet);
    }

    static void customerLogin() throws CloneNotSupportedException {
        boolean ch=true;
        while(ch) {
            System.out.println("1. Sign Up\n2. Log In\n3. Back");
            int c = Integer.parseInt(sc.nextLine());
            switch (c) {
                case 1 -> {
                    System.out.println(signup());
                }
                case 2 -> {
                    login();
                }
                case 3 -> ch = false;
                default -> System.out.println("Wrong choice!");
            }
        }
    }

    static String signup() {
        customer obj=new normalCustomer();
        System.out.print("Enter name: ");
        obj.name=sc.nextLine();
        System.out.print("Enter age: ");
        obj.age= Integer.parseInt(sc.nextLine());
        System.out.print("Enter Phone Number: ");
        obj.phno = Long.parseLong(sc.nextLine());
        System.out.print("Enter Email ID: ");
        obj.email= sc.nextLine();
        System.out.print("Enter Password: ");
        obj.pw=sc.nextLine();
        customerList.add(obj);
        return ("Customer successfully registered!");
    }
    private static void login() throws CloneNotSupportedException {
        if (customerList.isEmpty()) {
            System.out.println(("Sign In as a user first!"));
            return;
        }
        System.out.print("Enter name: ");
        String tmpcustname=sc.nextLine();
        System.out.print("Enter Email ID: ");
        String tmpcustmail= sc.nextLine();
        System.out.print("Enter Password: ");
        String tmpcustpw=sc.nextLine();
        for (int i=0; i< customerList.size();i++){
            if (customerList.get(i).name.equals(tmpcustname) && customerList.get(i).email.equals(tmpcustmail) && customerList.get(i).pw.equals(tmpcustpw)){
                menu(customerList.get(i));
                break;
            }
            else if (i==customerList.size()-1){
                System.out.println("Seems like you are a new user, try Signing Up first!");
            }
        }
    }
    private static void menu(customer c) throws CloneNotSupportedException {
        boolean a=true;
        while (a){
            System.out.println("\nWelcome "+c.name+"!!\n" +
                    "1) Browse products\n" +
                    "2) Browse deals\n" +
                    "3) Add a product to cart\n" +
                    "4) Add products in deal to cart\n" +
                    "5) View coupons\n" +
                    "6) Check account balance\n" +
                    "7) View cart\n" +
                    "8) Empty cart\n" +
                    "9) Checkout cart\n" +
                    "10) Upgrade customer status\n" +
                    "11) Add amount to wallet\n" +
                    "12) Back");
            int ch= Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1 -> admin.productCatalog();
                case 2 -> admin.deals();
                case 3 -> addProductCart(c);
                case 4 -> addDealCart(c);
                case 5 -> viewCoupons(c);
                case 6 -> System.out.println(getWallet(c));
                case 7 -> viewCart(c);
                case 8 -> emptyCart(c);
                case 9 -> c.checkoutCart(c);
                case 10 -> {
                    customer newc=c.upgradeStatus(c);
                    if (newc!=null) c=newc;
                }

                case 11 -> addWallet(c);
                case 12 -> a=false;
                default->
                    System.out.println("Enter correct Input!");
            }
        }
    }
    static void addProductCart(customer c){
        if (admin.productCategoryList.isEmpty())System.out.println("Sorry no products available");
        System.out.print("Enter product ID and quantity: ");
        float tmppid=sc.nextFloat();
        int tmpquan= sc.nextInt();
        sc.nextLine();
        int tmpcatid= (int) tmppid;
        for (int i=0; i< admin.productCategoryList.size();i++){
            if (admin.productCategoryList.get(i).categoryID==tmpcatid){
                for (int j=0; j<admin.productCategoryList.get(i).productList.size();j++){
                    if (admin.productCategoryList.get(i).productList.get(j).productID==tmppid){
                        admin.productCategoryList.get(i).productList.get(j).cartQuantity=tmpquan;
                        c.cart.add(admin.productCategoryList.get(i).productList.get(j));
                        System.out.println("Product added Successfully!");
                        return;
                    }
                }
            }
        }
    }
    private static void addDealCart(customer c) {
        if (admin.dealsList.isEmpty()) System.out.println("Sorry! No deals at the moment! Please try later");
        ;
        System.out.print("Enter Product ID's of product in deals: ");
        float tmppid1 = sc.nextFloat();
        float tmppid2 = sc.nextFloat();
        sc.nextLine();
        int tmpcatid1 = (int) tmppid1;
        int tmpcatid2 = (int) tmppid2;
        for (int i = 0; i < admin.dealsList.size(); i++) {
            if (admin.dealsList.get(i).p1.productID == tmppid1 && admin.dealsList.get(i).p2.productID == tmppid2) {
                c.cart.add(admin.dealsList.get(i).p1);
                c.cart.add(admin.dealsList.get(i).p2);
                admin.dealsList.get(i).p1.isDeal=true;
                admin.dealsList.get(i).p2.isDeal=true;
                System.out.println("Deal added successfully!");
                return;
            } else if (i == admin.dealsList.size() - 1) {
                System.out.println("No deals found with given product ID's!");
            }
        }
    }
    private static void addWallet(customer c){
        System.out.print("Enter amount to add in Wallet: ");
        c.wallet+=Float.parseFloat(sc.nextLine());
        System.out.println(getWallet(c));
    }
    private static  void viewCart(customer c){
        if (c.cart.isEmpty()){
            System.out.println("Cart is Empty!");
        }
        for (int i=0; i<c.cart.size();i++){
            System.out.println(c.cart.get(i).productID+" "+c.cart.get(i).productName+" Quantity:"+c.cart.get(i).cartQuantity+" Price Rs."+c.cart.get(i).productPrice);
            System.out.println("------------------------------------------");
        }
    }
    private static void emptyCart(customer c){
        if (c.cart.isEmpty()){
            System.out.println("Cart is already empty!");
        }
        else {
            c.cart.clear();
            System.out.println("Cart emptied successfully!");
        }
    }
    private static void viewCoupons(customer c){
        if (c.Coupons.isEmpty()){
            System.out.println("No coupons right now! Check later!");
        }
        for (int i=0; i<c.Coupons.size();i++){
            System.out.println("You have a coupon of "+c.Coupons.get(i)+"% discount");
        }
    }
    ArrayList<Integer> generateCoupons(int n){
        Random r=new Random();
        ArrayList<Integer> coupons = new ArrayList<>();
        for (int i=0; i<n;i++){
            coupons.add(r.nextInt(16-5)+5);
        }
        return coupons;
    }
    abstract customer upgradeStatus(customer c ) throws CloneNotSupportedException;
    abstract void checkoutCart(customer c);
}
