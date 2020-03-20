package com.example.rainmusic.bean;

public class mineItemBean {
    public int mineListImgId;       //图像资源ID
    public String mineListTitle;    //标题
    public String mineListContent;  //内容
    public boolean bool;            //是否显示下方的歌曲数

    public mineItemBean(int imgID,String title,String content,boolean bool){
        this.mineListImgId = imgID;
        this.mineListTitle = title;
        this.mineListContent = content;
        this.bool = bool;
    }

}
