import java.util.ArrayList;

public class productCategory{
    public  ArrayList<product> productList = new ArrayList<>();
    public int categoryID;
    public String categoryName;

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    public void setCategoryName(String categoryName){
        this.categoryName=categoryName;
    }

    public String getCategoryID() {
        return ("Category ID: "+categoryID);
    }

    public String getCategoryName() {
        return ("Category Name: "+categoryName);
    }
}
