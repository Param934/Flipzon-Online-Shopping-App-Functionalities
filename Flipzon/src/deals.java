public class deals extends product {
    product p1;
    product p2;
    int normalDiscount;
    int primeDiscount;
    int eliteDiscount;

    public void setEliteDiscount(int eliteDiscount) {
        this.eliteDiscount = eliteDiscount;
    }

    public void setNormalDiscount(int normalDiscount) {
        this.normalDiscount = normalDiscount;
    }

    public void setPrimeDiscount(int primeDiscount) {
        this.primeDiscount = primeDiscount;
    }

    public void setP1(product p1) {
        this.p1 = p1;
    }

    public void setP2(product p2) {
        this.p2 = p2;
    }
    public String getDeal(){
        return ("buying "+p1.productName+"(pid="+p1.productID+") & "+p2.productName+"(pid="+p2.productID+") together will cost "+normalDiscount+"% less for NORMAL customer!\n"+
                "buying "+p1.productName+"(pid="+p1.productID+") & "+p2.productName+"(pid="+p2.productID+") together will cost "+primeDiscount+"% less for PRIME customer!\n"+
                "buying "+p1.productName+"(pid="+p1.productID+") & "+p2.productName+"(pid="+p2.productID+") together will cost "+eliteDiscount+"% less for ELITE customer!\n"+
                "----------------------------------------------------");
    }
}
