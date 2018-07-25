# python notes

**参考**
- [redis-py][redis-py]
- [Python 3.7.0 documentation][Python 3.7.0 documentation]
- [flask][flask]


[redis-py]:https://github.com/andymccurdy/redis-py
[Python 3.7.0 documentation]:https://docs.python.org/3/
[flask]:https://www.palletsprojects.com/p/flask/

## FAQ
- [安装注意事项](#安装注意事项)
- [mysql访问客户端](#安装mysql访问客户端)
+ 常用命令
  - [启动一个简单web工程](#启动一个简单web工程)
  - [csv文件](#csv文件)


### 安装注意事项
install 3.6.5 on win10, unchecking 'Install launcher for all users'

### 安装mysql访问客户端
pip install mysqlclient

### 启动一个简单web工程
```
python -m SimpleHTTPServer 8000
```
windows
```
python -m http.server 8000
```

### csv文件
```
import csv
import codecs
# 先给文件写一个Windows系统用来识别编码的头(UTF-8 BOM)
with open('./outputs/tetcsv.csv', 'wb') as outfile:
    outfile.write(codecs.BOM_UTF8)

with open('./outputs/tetcsv.csv', 'a', newline='', encoding='UTF-8') as outfile:
    writer = csv.writer(outfile, dialect='excel')	
    # 写入一行
    writer.writerow(["名称", "分数"])	
```
Writerow must be inside with , because Outside the with block, the file is closed.

读取包含bom的csv文件，指定utf-8-sig解码
```
open(fpath, 'r', newline='', encoding='utf-8-sig')
```

### create restful service
dependency : flask

```
#!flask/bin/python
from flask import Flask

app = Flask(__name__)

@app.route('/')
def index():
    return "Hello, World!"

if __name__ == '__main__':
    app.run(debug=True)
```
