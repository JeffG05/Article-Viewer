package com.jeffg.articleviewer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

public class ArticleContent {

    public Bitmap image;
    public String title;
    public String source;
    public String content;
    public int titleTextSize;
    public int sourceTextSize;
    public int contentTextSize;
    public String titleTextAlign;
    public String sourceTextAlign;
    public String contentTextAlign;

    public static String LEFT = "left";
    public static String CENTER = "center";
    public static String RIGHT = "right";

    private ArticleContent(Bitmap image, String title, String source, String content, int titleTextSize, int sourceTextSize, int contentTextSize, String titleTextAlign, String sourceTextAlign, String contentTextAlign) {
        this.image = image;
        this.title = title;
        this.source = source;
        this.content = content;
        this.titleTextSize = titleTextSize;
        this.sourceTextSize = sourceTextSize;
        this.contentTextSize = contentTextSize;
        this.titleTextAlign = titleTextAlign;
        this.sourceTextAlign = sourceTextAlign;
        this.contentTextAlign = contentTextAlign;
    }

    private static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static class Builder {

        private Bitmap image;
        private String title;
        private String source;
        private String content;
        private int titleTextSize;
        private int sourceTextSize;
        private int contentTextSize;
        private String titleTextAlign;
        private String sourceTextAlign;
        private String contentTextAlign;

        public Builder() {
            this.titleTextSize = 30;
            this.sourceTextSize = 20;
            this.contentTextSize = 20;
            this.titleTextAlign = CENTER;
            this.sourceTextAlign = RIGHT;
            this.contentTextAlign = CENTER;
        }

        public Builder setImageBitmap (Bitmap bitmap) {
            this.image = bitmap;
            return this;
        }

        public Builder setImageDrawable (@NonNull Drawable drawable) {
            this.image = drawableToBitmap(drawable);
            return this;
        }

        public Builder setTitle (@NonNull String title) {
            this.title = title;
            return this;
        }

        public Builder setSource (@NonNull String source) {
            this.source = source;
            return this;
        }

        public Builder setContent (@NonNull String content) {
            this.content = content;
            return this;
        }

        public Builder setTitleTextSize(int px) {
            this.titleTextSize = px;
            return this;
        }

        public Builder setSourceTextSize(int px) {
            this.sourceTextSize = px;
            return this;
        }

        public Builder setContentTextSize(int px) {
            this.contentTextSize = px;
            return this;
        }

        public Builder setTitleTextAlign(String textAlign) {
            this.titleTextAlign = textAlign;
            return this;
        }

        public Builder setSourceTextAlign(String textAlign) {
            this.sourceTextAlign = textAlign;
            return this;
        }

        public Builder setContentTextAlign(String textAlign) {
            this.contentTextAlign = textAlign;
            return this;
        }

        public ArticleContent build() {
            return new ArticleContent(this.image, this.title, this.source, this.content, this.titleTextSize, this.sourceTextSize, this.contentTextSize, this.titleTextAlign, this.sourceTextAlign, this.contentTextAlign);
        }

    }

}
