package com.example.fatim.makeawish;

public class Gift {
    private String giftname;
    private double price;
    private String username;
    private String wishlist;
    private int quantity;
    private String imgPath;

    public Gift(){}
    public Gift(String giftname,double price, String username, String wishlist,int quantity,String imgPath){
        this.giftname=giftname;
        this.price=price;
        this.username=username;
        this.quantity=quantity;
        this.wishlist=wishlist;
        this.imgPath=imgPath;
    }


    public void setGifttame(String giftname) { this.giftname = giftname; }
    public void setUserame(String username) { this.username = username; }
    public void setImgpath(String username) { this.imgPath = imgPath; }
    public void setPrice(double price) { this.price = price; }
    public void setWishlist(String wishlist) { this.wishlist = wishlist; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getUsername() { return username; }
    public String getGiftname() { return giftname; }
    public String getWishlist() { return wishlist; }
    public String getImgpath() { return imgPath; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

}
