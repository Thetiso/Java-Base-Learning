## 安装
- 方法A
    1. eclipse-market中搜索jd → 找到Enhanced Class Decompiler → 安装
    2. 安装结束后再eclipse根目录plugins中找到procyon对应的jar包
- 方法B
    1. 下载：https://bitbucket.org/mstrobel/procyon
- 方法C
...

## 反编译
- 确认配置JDK环境(procyon要求1.7以上)
- 命令行：java -jar procyon.jar  -jar  source.jar -o target-folder
    1. procyon.jar: 下载到的源码包
    2. source.jar: 准备反编译的包
    3. target-folder: 存放用的目标文件夹