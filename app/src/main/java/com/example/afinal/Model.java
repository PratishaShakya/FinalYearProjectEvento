package com.example.afinal;

public class Model {
    private String id;
    private String categorytype;
    private String rate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype;
    }

    public String getCategorytype() {
        return categorytype;
    }

    public Model(String rate,String categorytype, String imgUrl, String title, Double lat, Double lng, String eventLocation, String eventDateTime, String eventDesc, String userType, String status, String createdTime, String createdBy, int like) {

        this.rate = rate;
        this.categorytype = categorytype;
        this.imgUrl = imgUrl;
        this.title = title;
        this.lat = lat;
        this.lng = lng;
        this.eventLocation = eventLocation;
        this.eventDateTime = eventDateTime;
        this.eventDesc = eventDesc;
        this.userType = userType;
        this.status = status;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String imgUrl;
    private String title;
    private Double lat;
    private Double lng;
    private String eventLocation;
    private String eventDateTime;
    private String eventDesc;
    private String userType;
    private String status;
    private String createdTime;
    private String createdBy;
    private int like;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }



    public Model(){
        /*
        empty
         */
    }


}
