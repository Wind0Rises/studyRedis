package com.liu.study.redis.template.operation;

/**
 * @author lwa
 * @version 1.0.0
 * @createTime 2020/7/28 17:07
 */
public class Description {
    /**
     * opsFor* ===> 获取对应的类型处理器，这个对应的处理器包含对应数据结构类型的操作类。
     *      1、opsForValue：操作字符串的。
     *      2、opsForHash：操作Hash的。
     *      3、opsForList：操作List的
     *      4、opsForSet：操作set的
     *      5、opsForZSet；操作ZSet的。
     *
     *
     *      6、opsForGeo：操作位置信息。
     *      7、opsForCluster：集群相关的操作。
     *      8、opsForHyperLogLog：统计相关操作。
     */

    /**
     * bound***Ops
     *      1、boundStringOps：操作字符串的。
     *      2、boundHashOps：操作Hash的。
     *      3、boundListOps：操作List的
     *      4、boundSetOps：操作set的
     *      5、boundZSetOps；操作ZSet的。
     *
     *      6、boundForGeo：操作位置信息。
     *
     */

    /**
     * opsFor**与bound**Ops的区别：
     * opsFor**可以操作多个Key，但是bound**Ops只能对某一个Key操作。
     */

    /**
     * redisTemplate：常用命令：
     *      1、move：把数据移到指定目录。
     */
}