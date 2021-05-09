package com.example.e_study;

public class ScreenItem {
    String Title,Description;
    int ScreenImg;

    public ScreenItem(String title, String description, int screenImg){
            Title = title;
            Description = description;
            ScreenImg = screenImg;

    }
    public  void setTitle(String title){
            Title = title;
    }
    public  void setDescription(String description){
            Description = description;
    }
    public  void setScreenImg(String screenImg){
        Title = screenImg;
    }


    public String getTitle(){
            return Title;
    }
    public String getDescription(){
        return Description;
    }
    public int getScreenImg(){
        return ScreenImg;
    }
}
