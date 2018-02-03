# Article-Viewer
Android Library to View an Article Offline

 [ ![Download](https://api.bintray.com/packages/jeffg05/Article-Viewer/Article-Viewer/images/download.svg) ](https://bintray.com/jeffg05/Article-Viewer/Article-Viewer/_latestVersion) [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

## Getting Started

Follow one of these steps to import Article-Viewer into your project:

### Gradle

```
compile 'com.jeffg.articleviewer:article-viewer:1.3.1'
```

### Maven

```
<dependency>
  <groupId>com.jeffg.articleviewer</groupId>
  <artifactId>article-viewer</artifactId>
  <version>1.3.1</version>
  <type>pom</type>
</dependency>
```

### Ivy

```
<dependency org='com.jeffg.articleviewer' name='article-viewer' rev='1.3.1'>
  <artifact name='article-viewer' ext='pom' ></artifact>
</dependency>
```



## Implementing into your project

### Use the Builder to create a html string
```
ArticleHtml articleHtml = new ArticleHtml(this);
articleHtml.setBuilder(new ArticleContent.Builder()
      .setImageBitmap(bitmap)
      .setTitle("Title")
      .setSource("Source")
      .setContent("Content")
      .build());
  
String html = articleHtml.getHtml();
```
### Insert the html into the webview
```
webview.loadDataWithBaseURL("", html, "text/html", "UTF-8", "");
```
### Result
<img src="https://raw.github.com/JeffG05/Article-Viewer/master/screenshot.png" width="216" height="391">

### All Builder() Commands
```
.setImageBitmap(Bitmap bitmap)
.setImageDrawable(Drawable drawable)
.setBackgroundColor(int color)
.setFont(String font)
.setTitle(String title)
.setTitleTextSize(int px)
.setTitleTextAlign(String textAlign)
.setTitleTextColor(int color)
.setSource(String source)
.setSourceTextSize(int px)
.setSourceTextAlign(String textAlign)
.setSourceTextColor(int color)
.setContent(String content)
.setContentTextSize(int px)
.setContentTextAlign(String textAlign)
.setContentTextColor(int color)
.build()
```

## License

```
Copyright 2018 Jeff Gugelmann

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```



