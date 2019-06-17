package nj.ccq.yh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**  

* <p>Description:用来接受队列中的消息 </p>  

* @author ZTY  

* @date 2019年6月17日  

*/
@Component
public class ConsumerService {
	@Autowired
	private JmsMessagingTemplate jmsTemplate;
	/**
	 * 接收点对点的queue
	 * @param name
	 * @return
	 */
	// 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
	@JmsListener(destination="ActiveMQQueue")
	// SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
	@SendTo("SQueue")
	public String handlemessage(String name) {
		System.out.println("成功接受Name" + name);
		return "成功接受Name" + name;
	}
	
	/**
	 * 接受topic队列
	 */
	
	@JmsListener(destination="zh-topic")
	@SendTo("return-queue")
	public void recevietopic(String text) {
		System.out.println("Consumer收到:"+text);
	}

}
