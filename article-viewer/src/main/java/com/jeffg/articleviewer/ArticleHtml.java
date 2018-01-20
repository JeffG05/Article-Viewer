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
            "body {" +
            "margin: 0px;" +
            "}" +
            ".title {" +
            "width: 90%;" +
            "text-align: ";
    private String html2 = ";" +
            "margin-bottom: 0px;" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "font-size: ";
    private String html3 = "px;" +
            "}" +
            ".source {" +
            "width: 90%;" +
            "text-align: ";
    private String html4 = ";" +
            "margin-top: 5px" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "margin-bottom: 0px;" +
            "font-size: ";
    private String html5 = "px;" +
            "}" +
            ".content {" +
            "width: 90%;" +
            "text-align: ";
    private String html6 = ";" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "margin-top: 50px;" +
            "font-size: ";
    private String html7 = "px;" +
            "}" +
            ".image {" +
            "width: 100%;" +
            "padding: 0px;" +
            "}" +
            "</style>" +
            "<img class=\"image\" src=\"";

    String html8 = "\">" +
            "</img>" +
            "<h3 class=\"title\">";

    String html9 = "</h3>" +
            "<p class=\"source\"><i>- ";

    String html10 = "</i></p>" +
            "<p class=\"content\">";

    String html11 = "</p>" +
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
        stringBuilder.append(articleContent.titleTextAlign);
        stringBuilder.append(html2);
        stringBuilder.append(articleContent.titleTextSize);
        stringBuilder.append(html3);
        stringBuilder.append(articleContent.sourceTextAlign);
        stringBuilder.append(html4);
        stringBuilder.append(articleContent.sourceTextSize);
        stringBuilder.append(html5);
        stringBuilder.append(articleContent.contentTextAlign);
        stringBuilder.append(html6);
        stringBuilder.append(articleContent.contentTextSize);
        stringBuilder.append(html7);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        articleContent.image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();

        stringBuilder.append("data:image/bmb;base64,");
        stringBuilder.append(Base64.encodeToString(byteArray, Base64.DEFAULT));
        stringBuilder.append(html8);
        stringBuilder.append(articleContent.title);
        stringBuilder.append(html9);
        stringBuilder.append(articleContent.source);
        stringBuilder.append(html10);
        stringBuilder.append(articleContent.content.replaceAll("(?m)^[ \t]*\r?\n", "<br>"));
        stringBuilder.append(html11);
        return stringBuilder.toString();
    }



}
