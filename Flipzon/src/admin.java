import java.util.*;
public class admin{
    static Scanner sc= new Scanner(System.in);
    public static ArrayList<productCategory> productCategoryList = new ArrayList<>();
    static ArrayList<deals> dealsList= new ArrayList<>();
    String Username="Param";
    String Password="123";
    public static void adminLogin(){
        boolean b=true;
        while (b){
            admin obj1= new admin();
            System.out.print("Enter Username: ");
            String tun=sc.nextLine();
            System.out.print("Enter Password: ");
            String tpw= sc.nextLine();
            if ((tun.equals(obj1.Username)) && (tpw.equals(obj1.Password))){
                boolean c=true;
                while (c){
                    System.out.println("Welcome Param!!!!!\n\nPlease choose any one of the following actions:\n\n\t1) Add category\n\t2) Delete category\n\t3) Add Product\n\t4) Delete Product\n\t5) Set Discount on Product\n\t6) Add giveaway deal\n\t7) Back");
                    int c2=Integer.parseInt(sc.nextLine());
                    switch (c2) {
                        case 1 -> addCategory();
                        case 2 -> {
                            productCategory pc=verifyCategory();
                            if (pc!=null) {
                                productCategoryList.remove(pc);
                                System.out.println("Product Category Successfully Deleted!");
                            }
                        }
                        case 3 -> {
                            productCategory pc=verifyCategory();
                            if (pc!=null) addProduct(pc);
                        }
                        case 4 -> {
                            productCategory pc=verifyCategory();
                            if (pc!=null) delProduct(pc);
                        }
                        case 5 -> setDiscount();
                        case 6 -> setDeals();
                        case 7 -> {b = false; c=false;}
                        default -> System.out.println("Enter valid choice!");
                    }
                }
            }
            else {
                System.out.println("Wrong Username or Password!\n1.Retry\n2.Back");
                int c2=Integer.parseInt(sc.nextLine());
                switch (c2) {
                    case (1):
                        continue;
                    case (2):
                        b=false;
                        continue;
                    default:
                        System.out.println("Wrong choice, please reselect");
                }
            }
        }
    }
    static private void addCategory(){
        productCategory cat = new productCategory();
        System.out.print("Enter Category ID: ");
        int tmpcatID = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Category Name: ");
        String tmpcatName = sc.nextLine();
        cat.setCategoryID(tmpcatID);
        cat.setCategoryName(tmpcatName);
        productCategoryList.add(cat);
        System.out.println("Product Category added Successfully!");
        System.out.println("Please add atleast one product!");
        addProduct(cat);
    }
    static void addProduct(productCategory cat){
        product p= new product();
        System.out.print("Enter Product ID: ");
        float tmpprid = Float.parseFloat(sc.nextLine());
        p.setProductID(tmpprid);
        System.out.print("Enter Product Name: ");
        String tmpprname = sc.nextLine();
        p.setProductName(tmpprname);
        System.out.print("Enter Product Price: ");
        float tmprprice = Float.parseFloat(sc.nextLine());
        p.setProductPrice(tmprprice);
        System.out.print("Enter Product Info: ");
        String tmpprinfo = sc.nextLine();
        p.setProductInfo(tmpprinfo);
        System.out.print("Enter Product Quantity: ");
        int tmpprquan = Integer.parseInt(sc.nextLine());
        p.setProductQuantity(tmpprquan);
        cat.productList.add(p);
        System.out.println("Product added Successfully!");
    }
    private static productCategory verifyCategory(){
        if (productCategoryList.isEmpty()) {
            System.out.println("Product Category List is Empty");
            return null;
        }
        System.out.print("Enter Category ID: ");
        int tmpcatID = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Category Name: ");
        String tmpcatName = sc.nextLine();
        for (int i=0; i<productCategoryList.size();i++){
            if (productCategoryList.get(i).categoryName.equals(tmpcatName) && productCategoryList.get(i).categoryID==(tmpcatID)){
                return productCategoryList.get(i);
            }
            if (i==(productCategoryList.size()-1)){
                System.out.println("No category found with given Category ID and Category Name!");
                boolean c4=true;
                while (c4){
                    System.out.println("1. Retry\n2. Back");
                    int c3= Integer.parseInt(sc.nextLine());
                    switch (c3) {
                        case 1:
                            c4 = false;
                            return (verifyCategory());
                        case 2:
                            c4=false;
                            return null;
                        default:
                            System.out.println("Enter valid choice!");
                    }
                }
            }
        }
        return null;
    }

    static private void delProduct(productCategory pc){
        System.out.print("Enter Product ID: ");
        float tmpprid = Float.parseFloat(sc.nextLine());
        for (int i=0; i<pc.productList.size();i++) {
            if (pc.productList.get(i).productID==(tmpprid)){
                pc.productList.remove(pc.productList.get(i));
                System.out.println("Product Successfully deleted!");
            }
            else if (i==pc.productList.size()-1){
                System.out.println("No product found with provided Product ID!");
                boolean c4=true;
                while (c4){
                    System.out.println("1. Retry\n2. Back");
                    int c3= sc.nextInt();
                    switch (c3) {
                        case 1:
                            delProduct(pc);
                            c4 = false;
                            break;
                        case 2:
                            c4=false;
                            break;
                        default:
                            System.out.println("Enter valid choice!");
                    }
                }
            }
        }
        if (pc.productList.isEmpty()){
            System.out.println("Product Category with Product Category ID "+pc.categoryID+" is empty. It has to be deleted");
            productCategoryList.remove(pc);
        }
    }
    static private void setDiscount(){
        if (productCategoryList.isEmpty()){
            System.out.println("No products available right now, please try again later");
            return;
        }
        System.out.print("Enter Product ID: ");
        float tmpprid = Float.parseFloat(sc.nextLine());
        int tmpprcat=(int)tmpprid;
        System.out.print("Enter discount for Elite, Prime and Normal customers respectively (in % terms): ");
        int tmpnordis = sc.nextInt();
        int tmpprimdis= sc.nextInt();
        int tmpelidis=sc.nextInt();
        sc.nextLine();
        for (int i=0; i< productCategoryList.size();i++) {
            if (productCategoryList.get(i).categoryID == tmpprcat) {
                for (int j = 0; j < productCategoryList.get(i).productList.size(); j++) {
                    if (productCategoryList.get(i).productList.get(j).productID == tmpprid) {
                        productCategoryList.get(i).productList.get(j).normalDiscount=tmpnordis;
                        productCategoryList.get(i).productList.get(j).primeDiscount=tmpprimdis;
                        productCategoryList.get(i).productList.get(j).eliteDiscount=tmpelidis;
                        System.out.println("Discount will be applied!");
                        break;
                    }
                }
            }
        }
    }
    static private void setDeals(){
        if (productCategoryList.isEmpty()){
            System.out.println("No products available right now, please try again later");
            return;
        }
        System.out.print("Enter first Product ID: ");
        float tmpprid1 = Float.parseFloat(sc.nextLine());
        System.out.print("Enter second Product ID: ");
        float tmpprid2 = Float.parseFloat(sc.nextLine());
        System.out.print("Enter space separated Discount Percentages for normal, prime and elite customers: ");
        int tmpnordis = sc.nextInt();
        int tmpprimdis= sc.nextInt();
        int tmpelidis= sc.nextInt();
        sc.nextLine();
        deals d=new deals();
        d.setNormalDiscount(tmpnordis);
        d.setPrimeDiscount(tmpprimdis);
        d.setEliteDiscount(tmpelidis);
        int tmpprcat1=(int)tmpprid1;
        int tmpprcat2=(int)tmpprid2;
        for (int i=0; i< productCategoryList.size();i++) {
            if (productCategoryList.get(i).categoryID == tmpprcat1) {
                for (int j = 0; j < productCategoryList.get(i).productList.size(); j++) {
                    if (productCategoryList.get(i).productList.get(j).productID == tmpprid1) {
                        d.setP1(productCategoryList.get(i).productList.get(j));
                        productCategoryList.get(i).productList.get(j).isDeal=true;
                        break;
                    }
                }
            }
        }
        for (int i=0; i< productCategoryList.size();i++) {
            if (productCategoryList.get(i).categoryID == tmpprcat2) {
                for (int j = 0; j < productCategoryList.get(i).productList.size(); j++) {
                    if (productCategoryList.get(i).productList.get(j).productID == tmpprid2) {
                        d.setP2(productCategoryList.get(i).productList.get(j));
                        productCategoryList.get(i).productList.get(j).isDeal=true;
                        break;
                    }
                }
            }
        }
        dealsList.add(d);
    }
    public static void productCatalog() {
        if (productCategoryList.isEmpty()){
            System.out.println("No Products available right now, please check later");
        }
        else {
            for (int i = 0; i < productCategoryList.size(); i++) {
                System.out.println("---------------------------------------------------------");
                System.out.println(productCategoryList.get(i).getCategoryID() + "\t" + productCategoryList.get(i).getCategoryName());
                System.out.println("---------------------------------------------------------");
                for (int j = 0; j < productCategoryList.get(i).productList.size(); j++) {
                    System.out.println("\t" + productCategoryList.get(i).productList.get(j).getProductID());
                    System.out.println("\t" + productCategoryList.get(i).productList.get(j).getProductName());
                    System.out.println("\t" + productCategoryList.get(i).productList.get(j).getProductInfo());
                    System.out.println("\t" + productCategoryList.get(i).productList.get(j).getProductPrice());
                    System.out.println("\t" + productCategoryList.get(i).productList.get(j).getProductQuantity());
                    System.out.println("\tDiscount for normal, prime and elite customers: "+
                            productCategoryList.get(i).productList.get(j).normalDiscount+"% "+
                            productCategoryList.get(i).productList.get(j).primeDiscount+"% "+
                            productCategoryList.get(i).productList.get(j).eliteDiscount+"% ");
                    System.out.println("---------------------------------------------------------");
                }
            }
        }
    }

    public static void deals() {
        if (dealsList.isEmpty()) System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals");
        for (deals deals : dealsList) {
            System.out.println("\n" + deals.getDeal());
        }
    }
}