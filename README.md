# pictureslave
图片下载服务器的slave  主要功能是通过rpc  接受master的图片下载请求 去下载图片保存到本地 并且每分钟向zookeeper 汇报自己线程中队列的大小
