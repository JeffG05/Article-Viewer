package com.jeffg.articleviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.CountDownLatch;

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
            "background-color: #";
    private String html2 = ";" +
            "}" +
            ".title {" +
            "width: 90%;" +
            "text-align: ";
    private String html3 = ";" +
            "color: #";
    private String html4 = ";" +
            "margin-bottom: 0px;" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "font-size: ";
    private String html5 = "px;" +
            "}" +
            ".source {" +
            "width: 90%;" +
            "text-align: ";
    private String html6 = ";" +
            "color: #";
    private String html7 = ";" +
            "margin-top: 5px" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "margin-bottom: 0px;" +
            "font-size: ";
    private String html8 = "px;" +
            "}" +
            ".content {" +
            "width: 90%;" +
            "text-align: ";
    private String html9 = ";" +
            "color: #";
    private String html10 = ";" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "margin-top: 50px;" +
            "font-size: ";
    private String html11 = "px;" +
            "}" +
            ".image {" +
            "width: 100%;" +
            "padding: 0px;" +
            "}" +
            "</style>";
    private String html12 = "<img class=\"image\" src=\"data:image/bmb;base64,";
    private String html13 = "\">" +
            "</img>";
    private String html14 = "<h3 class=\"title\">";
    private String html15 = "</h3>" +
            "<p class=\"source\">";
    private String html16 = "<i>- ";
    private String html17 = "</i>";
    private String html18 = "</p>" +
            "<p class=\"content\">";

    private String html19 = "</p>" +
            "</body>";


    public ArticleHtml(Context context) {
        this.context = context;
    }

    public ArticleHtml setBuilder(ArticleContent articleContent) {
        this.articleContent = articleContent;
        return this;
    }

    public String getHtml() {
        final CountDownLatch latch = new CountDownLatch(1);
        final StringBuilder stringBuilder = new StringBuilder();
        Thread thread = new Thread() {
            @Override
            public void run() {
                stringBuilder.append(html1);
                stringBuilder.append(Integer.toHexString(articleContent.backgroundColor).substring(2));
                stringBuilder.append(html2);
                stringBuilder.append(articleContent.titleTextAlign);
                stringBuilder.append(html3);
                stringBuilder.append(Integer.toHexString(articleContent.titleTextColor).substring(2));
                stringBuilder.append(html4);
                stringBuilder.append(articleContent.titleTextSize);
                stringBuilder.append(html5);
                stringBuilder.append(articleContent.sourceTextAlign);
                stringBuilder.append(html6);
                stringBuilder.append(Integer.toHexString(articleContent.sourceTextColor).substring(2));
                stringBuilder.append(html7);
                stringBuilder.append(articleContent.sourceTextSize);
                stringBuilder.append(html8);
                stringBuilder.append(articleContent.contentTextAlign);
                stringBuilder.append(html9);
                stringBuilder.append(Integer.toHexString(articleContent.contentTextColor).substring(2));
                stringBuilder.append(html10);
                stringBuilder.append(articleContent.contentTextSize);
                stringBuilder.append(html11);

                if (articleContent.image != null) {
                    stringBuilder.append(html12);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    articleContent.image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream .toByteArray();
                    stringBuilder.append(Base64.encodeToString(byteArray, Base64.DEFAULT));
                    stringBuilder.append(html13);
                }


                stringBuilder.append(html14);
                if (articleContent.title != null) {
                    stringBuilder.append(articleContent.title);
                } else {
                    throw new NullPointerException("The title cannot be null");
                }
                stringBuilder.append(html15);
                if (articleContent.source != null) {
                    stringBuilder.append(html16);
                    stringBuilder.append(articleContent.source);
                    stringBuilder.append(html17);
                }
                stringBuilder.append(html18);
                if (articleContent.content != null) {
                    stringBuilder.append(articleContent.content.replaceAll("(?m)^[ \t]*\r?\n", "<br><br>"));
                } else {
                    throw new NullPointerException("The content cannot be null");
                }
                stringBuilder.append(html19);
                latch.countDown();
            }
        };
        thread.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "";
        }

        return stringBuilder.toString();

    }

    public ArticleContent fromHtml(String html) {
        String text1 = html.substring(html1.length());
        String backgroundColor = text1.split(";")[0];

        String text2 = text1.substring(backgroundColor.length() + html2.length());
        String titleTextAlign = text2.split(";")[0];

        String text3 = text2.substring(titleTextAlign.length() + html3.length());
        String titleTextColor = text3.split(";")[0];

        String text4 = text3.substring(titleTextColor.length() + html4.length());
        String titleTextSize = text4.split("px;")[0];

        String text5 = text4.substring(titleTextSize.length() + html5.length());
        String sourceTextAlign = text5.split(";")[0];

        String text6 = text5.substring(sourceTextAlign.length() + html6.length());
        String sourceTextColor = text6.split(";")[0];

        String text7 = text6.substring(sourceTextColor.length() + html7.length());
        String sourceTextSize = text7.split("px;")[0];

        String text8 = text7.substring(sourceTextSize.length() + html8.length());
        String contentTextAlign = text8.split(";")[0];

        String text9 = text8.substring(contentTextAlign.length() + html9.length());
        String contentTextColor = text9.split(";")[0];

        String text10 = text9.substring(contentTextColor.length() + html10.length());
        String contentTextSize = text10.split("px;")[0];

        String image = null;
        String text11;
        if (html.contains(html11)) {
            text11 = text10.substring(contentTextSize.length() + html11.length() + html12.length());
            image = text11.split(html13)[0];
            text11 = text11.substring(html13.length() + image.length());
            Log.i("SPLIT", image);
        } else {
            text11 = text10.substring(contentTextSize.length() + html11.length());
        }

        String text12 = text11.substring(html14.length());
        String title = text12.split("</h3>")[0];

        String source = null;
        String text13;
        if (html.contains(html16)) {
            text13 = text12.substring(title.length() + html15.length() + html16.length());
            source = text13.split(html17)[0];
            text13 = text13.substring(html17.length() + source.length());
            Log.i("SPLIT", source);
        } else {
            text13 = text12.substring(title.length() + html15.length());
        }

        String text14 = text13.substring(html18.length());
        String content = text14.split("</p>")[0];

        Log.i("SPLIT", titleTextAlign);
        Log.i("SPLIT", titleTextColor);
        Log.i("SPLIT", titleTextSize);
        Log.i("SPLIT", sourceTextAlign);
        Log.i("SPLIT", sourceTextColor);
        Log.i("SPLIT", sourceTextSize);
        Log.i("SPLIT", contentTextAlign);
        Log.i("SPLIT", contentTextColor);
        Log.i("SPLIT", contentTextSize);
        Log.i("SPLIT", title);
        Log.i("SPLIT", content.replace("<br><br>", "\n"));

        ArticleContent.Builder builder = new ArticleContent.Builder()
                .setTitleTextAlign(titleTextAlign)
                .setTitleTextColor(Color.parseColor("#" + titleTextColor))
                .setTitleTextSize(Integer.parseInt(titleTextSize))
                .setSourceTextAlign(sourceTextAlign)
                .setSourceTextColor(Color.parseColor("#" + sourceTextColor))
                .setSourceTextSize(Integer.parseInt(sourceTextSize))
                .setContentTextAlign(contentTextAlign)
                .setContentTextColor(Color.parseColor("#" + contentTextColor))
                .setContentTextSize(Integer.parseInt(contentTextSize))
                .setTitle(title)
                .setSource(source)
                .setContent(content);

        if (image != null) {
            builder.setImageBitmap(BitmapFactory.decodeByteArray(Base64.decode(image, Base64.DEFAULT), 0, Base64.decode(image, Base64.DEFAULT).length));
        }

        ArticleContent articleContent = builder.build();

        return articleContent;
    }



}
