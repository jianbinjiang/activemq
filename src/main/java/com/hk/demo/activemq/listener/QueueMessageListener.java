package com.hk.demo.activemq.listener;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import com.hk.demo.entry.AppointmentRecord;
/**
 * 
 * @author jiangjianbin
 *
 */
public class QueueMessageListener implements MessageListener {

	// 当收到消息后，自动调用该方法
	public void onMessage(Message message) {

		/*
		 * TextMessage tm = (TextMessage) message; try {
		 * System.out.println("QueueMessageListener监听到了文本消息：\t" + tm.getText());
		 * //do something ... } catch (JMSException e) { e.printStackTrace(); }
		 */

		try {
			// 如果是文本消息
			if (message instanceof TextMessage) {
				TextMessage tm = (TextMessage) message;
				System.out.println(" get textMessage：\t" + tm.getText());
			}

			// 如果是Map消息
			if (message instanceof MapMessage) {
				MapMessage mm = (MapMessage) message;
				System.out.println(" get textMessage：\t" + mm.getString("msgId"));
			}

			// 如果是Object消息
			if (message instanceof ObjectMessage) {
				ObjectMessage om = (ObjectMessage) message;
				AppointmentRecord record = (AppointmentRecord) om.getObject();
				System.out.println(" get ObjectMessage：\t" + record.toString());
			}

			// 如果是bytes消息
			if (message instanceof BytesMessage) {
				byte[] b = new byte[1024];
				int len = -1;
				BytesMessage bm = (BytesMessage) message;
				while ((len = bm.readBytes(b)) != -1) {
					System.out.println(new String(b, 0, len));
				}
			}

			// 如果是Stream消息
			if (message instanceof StreamMessage) {
				StreamMessage sm = (StreamMessage) message;
				System.out.println(sm.readString());
				System.out.println(sm.readInt());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}