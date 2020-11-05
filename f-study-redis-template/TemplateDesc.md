# RedisTemplate
## 一、RedisTemplate和StringRedisTemplate的区别
1、序列化与反序列化
比如在课堂上有位学生遇到一个问题，于是举手向老师请教，这时老师帮他解答，那么这位学生的举手其实就是一个标识，自己解决不了问题请教老师帮忙解决。在Java中的这个Serializable接口其实是给jvm看的，通知jvm，我不对这个类做序列化了，你（jvm）帮我序列化就好了。</br>

## 二、RedisTemplate的opsForValue()和boundValueOps()
1、opsForValue()  ==  ValueOperations <br>
&emsp;opsForValue()获取到一个对应类型的Operations，但是没有指定操作的key，可以在一个连接（事务）内操作多个key以及对应的value。

2、boundValueOps() == BoundValueOperations <br>
&emsp;boundValueOps()会获取到一个指定了key的Operations，在一个连接内只操作这个key对应的value。