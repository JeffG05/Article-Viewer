package com.jeffg.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.jeffg.articleviewer.ArticleContent;
import com.jeffg.articleviewer.ArticleHtml;

import net.jevinstudios.skimmhtmllibrary.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webview);

        String newsText =
                "Sorry seems to be the hardest word. So sang Elton John on one of his biggest 1970s hits - but not every public figure seems to find it so tough to utter that powerful five-letter word.\n" +
                        "\n" +
                        "In recent months a broad spectrum of public figures, from politicians, to Hollywood actors and YouTube stars have all publicly expressed remorse.\n" +
                        "\n" +
                        "In the latest example, the new Conservative vice-chairman for youth, Ben Bradley, has been forced to apologise twice over previous remarks - the latest of which appear to back police brutality, according to the Times.\n" +
                        "\n" +
                        "But with so much remorse on the airwaves, just how can we differentiate a forced apology from a heartfelt expression of remorse?\n" +
                        "\n" +
                        "Make it personal\n" +
                        "In its purest form, saying sorry should be an \"act of contrition, a realisation that something you have said or done has hurt someone and you want to make amends\", says psychologist Geraldine Joaquim.\n" +
                        "\n" +
                        "Made early, a well-crafted apology can be hugely beneficial and can \"diffuse the situation and takes the wind out of an accuser's sails\", she says.\n" +
                        "\n" +
                        "A need to say sorry can arise in someone's public life and equally at home with their family and friends but, whatever the environment, how well it is received depends on how personalised it feels.\n" +
                        "\n" +
                        "Experts say the formula for an affective apology can be summed up with the acronym CAR:\n" +
                        "\n" +
                        "show concern\n" +
                        "demonstrate action\n" +
                        "offer reassurance\n" +
                        "\"People want the response to be personal to them, to feel that they're being listened to and taken seriously,\" says Martin Stone, of PR agency Tank.\n" +
                        "\n" +
                        "He says that, in the professional sphere, the phrase \"formal apology\" is often used, but, in reality, the opposite is what is required.";



        ArticleHtml articleHtml = new ArticleHtml(this);
        articleHtml.setBuilder(new ArticleContent.Builder()
                .setImage(getDrawable(R.drawable.pic))
                .setTitle("'I'm sorry' - but how do you tell if an apology is fake or genuine?")
                .setSource("BBC News")
                .setContent(newsText)
                .build());
        webView.loadDataWithBaseURL("", articleHtml.getHtml(), "text/html", "UTF-8", "");


    }
}
