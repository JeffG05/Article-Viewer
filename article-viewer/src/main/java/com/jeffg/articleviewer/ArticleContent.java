package com.jeffg.articleviewer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
    public int titleTextColor;
    public int sourceTextColor;
    public int contentTextColor;
    public int backgroundColor;
    public String font;

    public static final String LEFT = "left";
    public static final String CENTER = "center";
    public static final String RIGHT = "right";

    public static final String ARIAL = "arial";
    public static final String PALATINO_LINOTYPE = "palatino_linotype";
    public static final String LUCIDA_CONSOLE = "lucida_console";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({LEFT, CENTER, RIGHT})
    private @interface TextAlignment {}

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ARIAL, PALATINO_LINOTYPE, LUCIDA_CONSOLE})
    private @interface Fonts {}

    private ArticleContent(Bitmap image, String title, String source, String content, int titleTextSize, int sourceTextSize, int contentTextSize, String titleTextAlign, String sourceTextAlign, String contentTextAlign, int titleTextColor, int sourceTextColor, int contentTextColor, int backgroundColor, String font) {
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
        this.titleTextColor = titleTextColor;
        this.sourceTextColor = sourceTextColor;
        this.contentTextColor = contentTextColor;
        this.backgroundColor = backgroundColor;
        this.font = font;
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
        private int titleTextColor;
        private int sourceTextColor;
        private int contentTextColor;
        private int backgroundColor;
        private String font;

        public Builder() {
            this.titleTextSize = 30;
            this.sourceTextSize = 20;
            this.contentTextSize = 20;
            this.titleTextAlign = CENTER;
            this.sourceTextAlign = RIGHT;
            this.contentTextAlign = CENTER;
            this.titleTextColor = Color.BLACK;
            this.sourceTextColor = Color.BLACK;
            this.contentTextColor = Color.BLACK;
            this.backgroundColor = Color.WHITE;
            this.font = ARIAL;
        }

        public Builder setImageBitmap (Bitmap bitmap) {
            this.image = bitmap;
            return this;
        }

        public Builder setImageDrawable (Drawable drawable) {
            if (drawable != null) {
                this.image = drawableToBitmap(drawable);
            } else {
                this.image = null;
            }
            return this;
        }

        public Builder setTitle (String title) {
            this.title = title;
            return this;
        }

        public Builder setSource (String source) {
            this.source = source;
            return this;
        }

        public Builder setContent (String content) {
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

        public Builder setTitleTextAlign(@TextAlignment String textAlign) {
            this.titleTextAlign = textAlign;
            return this;
        }

        public Builder setSourceTextAlign(@TextAlignment String textAlign) {
            this.sourceTextAlign = textAlign;
            return this;
        }

        public Builder setContentTextAlign(@TextAlignment String textAlign) {
            this.contentTextAlign = textAlign;
            return this;
        }

        public Builder setTitleTextColor(int color) {
            this.titleTextColor = color;
            return this;
        }

        public Builder setSourceTextColor(int color) {
            this.sourceTextColor = color;
            return this;
        }

        public Builder setContentTextColor(int color) {
            this.contentTextColor = color;
            return this;
        }

        public Builder setBackgroundColor(int color) {
            this.backgroundColor = color;
            return this;
        }

        public Builder setFont(@Fonts String font) {
            this.font = font;
            return this;
        }

        public ArticleContent build() {
            return new ArticleContent(this.image, this.title, this.source, this.content, this.titleTextSize, this.sourceTextSize, this.contentTextSize, this.titleTextAlign, this.sourceTextAlign, this.contentTextAlign, this.titleTextColor, this.sourceTextColor, this.contentTextColor, this.backgroundColor, this.font);
        }

    }

}
