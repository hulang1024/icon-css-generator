# icon-css-generator
扫描图片目录生成图标css。


## 用法

图标图片目录示例：
```txt
icons
├─ add.png
├─ del.png
├─ A
   ├─ Add.png
   ├─ ServiceSupport.jpg
   ├─ window_icon_max.png
```

### 命令行
```shell
java -jar IconCssGenerator.jar images/icons icons_test.css
```

生成 *icons_test.css*：
```css
.icon-add {
  background: url(icons/add.png) no-repeat center center;
}
.icon-del {
  background: url(icons/del.png) no-repeat center center;
}
.icon-a-add {
  background: url(icons/A/Add.png) no-repeat center center;
}
.icon-a-servicesupport {
  background: url(icons/A/ServiceSupport.jpg) no-repeat center center;
}
.icon-a-window-icon-max {
  background: url(icons/A/window_icon_max.png) no-repeat center center;
}
```


### Java API
示例：
```java
ImageFileToIconCssGenerator generator = new ImageFileToIconCssGenerator();
generator.setCssRuleGenerator( new DefaultCssRuleGenerator() ); //使用 DefaultCssRuleGenerator
generator.setInputDir( new File("images/icons") );
generator.setOutputStream( new FileOutputStream(new File("icons_test.css")) );
generator.generate();
```
##### `DefaultCssRuleGenerator`
`ImageFileToIconCssGenerator`有一个方法`setCssRuleGenerator`，其参数类型为`AbstractCssRuleGenerator`，抽象了如何生成**css选择器**和**css声明块**。内置了一个子类`DefaultCssRuleGenerator`实现:  
**css选择器**
* 目录名作为名字部分
* 多个单词用横线连接
* 小写

**css声明块**  
使用以下模板:
```css
{
  background: url(根路径/图片路径) no-repeat center center;
}
```
`setRelativeBasePath`方法可以设置`background url`的根路径。
