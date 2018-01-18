package com.jeffg.articleviewer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ArticleContent {

    public Bitmap image;
    public String title;
    public String source;
    public String content;

    private ArticleContent(Bitmap image, String title, String source, String content) {
        this.image = image;
        this.title = title;
        this.source = source;
        this.content = content;
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

        public Builder() {

        }

        public Builder setImage(Bitmap bitmap) {
            this.image = bitmap;
            return this;
        }

        public Builder setImage(Drawable drawable) {
            this.image = drawableToBitmap(drawable);
            return this;
        }

        public Builder setTitle (String title) {
            this.title = title;
            return this;
        }

        public Builder setTitle (CharSequence title) {
            this.title = title.toString();
            return this;
        }

        public Builder setSource (String source) {
            this.source = source;
            return this;
        }

        public Builder setSource (CharSequence source) {
            this.source = source.toString();
            return this;
        }

        public Builder setContent (String content) {
            this.content = content;
            return this;
        }

        public Builder setContent (CharSequence content) {
            this.content = content.toString();
            return this;
        }

        public ArticleContent build() {
            return new ArticleContent(this.image, this.title, this.source, this.content);
        }

    }

}
