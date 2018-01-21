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
            "}" +
            ".title {" +
            "width: 90%;" +
            "text-align: ";
    private String html2 = ";" +
            "color: #";
    private String html3 = ";" +
            "margin-bottom: 0px;" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "font-size: ";
    private String html4 = "px;" +
            "}" +
            ".source {" +
            "width: 90%;" +
            "text-align: ";
    private String html5 = ";" +
            "color: #";
    private String html6 = ";" +
            "margin-top: 5px" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "margin-bottom: 0px;" +
            "font-size: ";
    private String html7 = "px;" +
            "}" +
            ".content {" +
            "width: 90%;" +
            "text-align: ";
    private String html8 = ";" +
            "color: #";
    private String html9 = ";" +
            "margin-left: 5%;" +
            "margin-right: 5%;" +
            "margin-top: 50px;" +
            "font-size: ";
    private String html10 = "px;" +
            "}" +
            ".image {" +
            "width: 100%;" +
            "padding: 0px;" +
            "}" +
            "</style>";
    private String html11 = "<img class=\"image\" src=\"data:image/bmb;base64,";
    private String html12 = "\">" +
            "</img>";
    private String html13 = "<h3 class=\"title\">";
    private String html14 = "</h3>" +
            "<p class=\"source\">";
    private String html15 = "<i>- ";
    private String html16 = "</i>";
    private String html17 = "</p>" +
            "<p class=\"content\">";

    private String html18 = "</p>" +
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
                stringBuilder.append(articleContent.titleTextAlign);
                stringBuilder.append(html2);
                stringBuilder.append(Integer.toHexString(articleContent.titleTextColor).substring(2));
                stringBuilder.append(html3);
                stringBuilder.append(articleContent.titleTextSize);
                stringBuilder.append(html4);
                stringBuilder.append(articleContent.sourceTextAlign);
                stringBuilder.append(html5);
                stringBuilder.append(Integer.toHexString(articleContent.sourceTextColor).substring(2));
                stringBuilder.append(html6);
                stringBuilder.append(articleContent.sourceTextSize);
                stringBuilder.append(html7);
                stringBuilder.append(articleContent.contentTextAlign);
                stringBuilder.append(html8);
                stringBuilder.append(Integer.toHexString(articleContent.contentTextColor).substring(2));
                stringBuilder.append(html9);
                stringBuilder.append(articleContent.contentTextSize);
                stringBuilder.append(html10);

                if (articleContent.image != null) {
                    stringBuilder.append(html11);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    articleContent.image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream .toByteArray();
                    stringBuilder.append(Base64.encodeToString(byteArray, Base64.DEFAULT));
                    stringBuilder.append(html12);
                }


                stringBuilder.append(html13);
                if (articleContent.title != null) {
                    stringBuilder.append(articleContent.title);
                } else {
                    throw new NullPointerException("The title cannot be null");
                }
                stringBuilder.append(html14);
                if (articleContent.source != null) {
                    stringBuilder.append(html15);
                    stringBuilder.append(articleContent.source);
                    stringBuilder.append(html16);
                }
                stringBuilder.append(html17);
                if (articleContent.content != null) {
                    stringBuilder.append(articleContent.content.replaceAll("(?m)^[ \t]*\r?\n", "<br><br>"));
                } else {
                    throw new NullPointerException("The content cannot be null");
                }
                stringBuilder.append(html18);
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
        String titleTextAlign = text1.split(";")[0];

        String text2 = text1.substring(titleTextAlign.length() + html2.length());
        String titleTextColor = text2.split(";")[0];

        String text3 = text2.substring(titleTextColor.length() + html3.length());
        String titleTextSize = text3.split("px;")[0];

        String text4 = text3.substring(titleTextSize.length() + html4.length());
        String sourceTextAlign = text4.split(";")[0];

        String text5 = text4.substring(sourceTextAlign.length() + html5.length());
        String sourceTextColor = text5.split(";")[0];

        String text6 = text5.substring(sourceTextColor.length() + html6.length());
        String sourceTextSize = text6.split("px;")[0];

        String text7 = text6.substring(sourceTextSize.length() + html7.length());
        String contentTextAlign = text7.split(";")[0];

        String text8 = text7.substring(contentTextAlign.length() + html8.length());
        String contentTextColor = text8.split(";")[0];

        String text9 = text8.substring(contentTextColor.length() + html9.length());
        String contentTextSize = text9.split("px;")[0];

        String image = null;
        String text10;
        if (html.contains(html11)) {
            text10 = text9.substring(contentTextSize.length() + html10.length() + html11.length());
            image = text10.split(html12)[0];
            text10 = text10.substring(html12.length() + image.length());
            Log.i("SPLIT", image);
        } else {
            text10 = text9.substring(contentTextSize.length() + html10.length());
        }

        String text11 = text10.substring(html13.length());
        String title = text11.split("</h3>")[0];

        String source = null;
        String text12;
        if (html.contains(html15)) {
            text12 = text11.substring(title.length() + html14.length() + html15.length());
            source = text12.split(html16)[0];
            text12 = text12.substring(html16.length() + source.length());
            Log.i("SPLIT", source);
        } else {
            text12 = text11.substring(title.length() + html14.length());
        }

        String text13 = text12.substring(html17.length());
        String content = text13.split("</p>")[0];

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
