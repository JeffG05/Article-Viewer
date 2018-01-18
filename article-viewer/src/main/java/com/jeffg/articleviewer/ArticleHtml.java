package com.jeffg.articleviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ArticleHtml {

    private ArticleContent articleContent;
    private Context context;

    private String html1 =
            "<!DOCTYPE html>" +
            "<head>" +
            "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>" +
            "<meta name=\"theme-color\" content=\"#FFFFFF\"/>" +
            "</head>" +
            "<body>" +
            "<style>" +
            "@media (min-width: 0px) {" +
            "h3 {" +
            "font-size: 30px;" +
            "}" +
            "p {" +
            "font-size: 20px;" +
            "}" +
            "}" +
            "@media (min-width: 768px) {" +
            "h3 {" +
            "font-size: 45px;" +
            "}" +
            "p {" +
            "font-size: 25px;" +
            "}" +
            "}" +
            "@media (min-width: 992px) {" +
            "h3 {" +
            "font-size: 60px;" +
            "}" +
            "p {" +
            "font-size: 30px;" +
            "}" +
            "}" +
            "@media (min-width: 1200px) {" +
            "h3 {" +
            "font-size: 75px;" +
            "}" +
            "p {" +
            "font-size: 35px;" +
            "}" +
            "}" +
            "body {" +
            "margin: 0px;" +
            "}" +
            ".title {" +
            "width: 90%;" +
            "text-align: center;" +
            "margin-bottom: 0px;" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "}" +
            ".source {" +
            "width: 90%;" +
            "text-align: right;" +
            "margin-top: 5px" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "margin-bottom: 0px;" +
            "}" +
            ".content {" +
            "width: 90%;" +
            "text-align: center;" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "margin-top: 50px;" +
            "}" +
            ".image {" +
            "width: 100%;" +
            "padding: 0px;" +
            "}" +
            "</style>" +
            "<img class=\"image\" src=\"";

    String html2 = "\">" +
            "</img>" +
            "<h3 class=\"title\">";

    String html3 = "</h3>" +
            "<p class=\"source\"><i>- ";

    String html4 = "</i></p>" +
            "<p class=\"content\">";

    String html5 = "</p>" +
            "</body>";


    public ArticleHtml(Context context) {
        this.context = context;
    }

    public ArticleHtml setBuilder(@NonNull ArticleContent articleContent) {
        this.articleContent = articleContent;
        return this;
    }

    public String getHtml() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(html1);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        articleContent.image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();

        stringBuilder.append("data:image/bmb;base64,");
        stringBuilder.append(Base64.encodeToString(byteArray, Base64.DEFAULT));
        stringBuilder.append(html2);
        stringBuilder.append(articleContent.title);
        stringBuilder.append(html3);
        stringBuilder.append(articleContent.source);
        stringBuilder.append(html4);
        stringBuilder.append(articleContent.content);
        stringBuilder.append(html5);
        return stringBuilder.toString();
    }



}
