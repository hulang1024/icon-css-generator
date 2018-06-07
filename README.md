# icon-css-generator
从图片目录生成图标css。


## 用法
### 示例  
图片目录：
```txt
icons
├─ add.png
├─ del.png
├─ A
   ├─ Add.png
   ├─ ServiceSupport.jpg
```
**Java库调用方式**
```java
ImageFileToIconCssGenerator generator = new ImageFileToIconCssGenerator();
generator.setCssRuleGenerator(new DefaultCssRuleGenerator());
File dir = new File("images/icons");
generator.setInputDir(dir);
OutputStream os = new FileOutputStream(new File(dir.getParent(), "icons_test.css"));
generator.setOutputStream(os);
generator.generate();
```

**命令行方式**
```shell
java -jar IconCssGenerator.jar images/icons icons_test.css
```


生成 *icons_test.css*：
```css
.icon-a-add {
  background: url(icons/A/Add.png) no-repeat center center;
}
.icon-a-servicesupport {
  background: url(icons/A/ServiceSupport.jpg) no-repeat center center;
}
.icon-add {
  background: url(icons/add.png) no-repeat center center;
}
.icon-del {
  background: url(icons/del.png) no-repeat center center;
}
```
