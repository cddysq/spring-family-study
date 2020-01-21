## SpringBoot集成RabbitMQ

## 简述

- amqp-consumer 工程为消息监听端。编写监听类，使用`@RabbitListener(queues = "队列名")`绑定队列监听消息。
- amqp-producer 工程为消息提供端。编写配置类，绑定队列与交换机的对应关系，使用Spring提供的`RabbitTemplate`进行快捷发送消息。

#### 整体架构流程

![架构图](https://s2.ax1x.com/2020/01/21/1F5PXQ.png)