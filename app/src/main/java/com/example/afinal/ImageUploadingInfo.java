package com.example.afinal;

public class ImageUploadingInfo {
    String imgName;
    String ImgUrl;
    public ImageUploadingInfo(){

    }

    public ImageUploadingInfo(String imgName, String imgUrl) {
        this.imgName = imgName;
        ImgUrl = imgUrl;
    }

    public String getImgName() {
        return imgName;
    }

    public String getImgUrl() {
        return ImgUrl;
    }
}
