public class product extends productCategory{
    boolean isDeal=false;
    float productID;
    int primeDiscount=0;
    int normalDiscount=0;
    int eliteDiscount=0;
    String productName;
    float productPrice;
    String productInfo;
    int productQuantity;
    int cartQuantity=1;
    void setProductID(float productID){this.productID=productID;}
    void setProductName(String productName) {
        this.productName = productName;
    }
    void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
    void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }
    void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    public String getProductID() {
        return ("Product ID: "+productID);
    }

    public String getProductPrice() {
        return ("Price: "+productPrice);
    }

    public String getProductQuantity() {
        return ("No. of items left: "+productQuantity);
    }

    public String getProductInfo() {
        return  productInfo;
    }

    public String getProductName() {
        return productName;
    }

    public String getCartQuantity() {return "Quantity in cart: "+cartQuantity;}
}
