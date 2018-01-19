# Article-Viewer
Android Library to View an Article Offline

[ ![Download](https://api.bintray.com/packages/jeffg05/Article-Viewer/Article-Viewer/images/download.svg) ](https://bintray.com/jeffg05/Article-Viewer/Article-Viewer/_latestVersion)

## Getting Started

Follow these steps in order to import Article-Viewer into your project:

### Gradle

```
compile 'com.jeffg.articleviewer:article-viewer:1.0.1'
```

### Maven

```
<dependency>
  <groupId>com.jeffg.articleviewer</groupId>
  <artifactId>article-viewer</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

### Ivy

```
<dependency org='com.jeffg.articleviewer' name='article-viewer' rev='1.0.1'>
  <artifact name='article-viewer' ext='pom' ></artifact>
</dependency>
```



## Implementing into your project

### Use the Builder to create a html string
```
ArticleHtml articleHtml = new ArticleHtml(this);
articleHtml.setBuilder(new ArticleContent.Builder()
      .setImage(bitmap)
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




