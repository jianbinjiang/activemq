package com.hk.demo.mvc.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hk.demo.activemq.consumer.ConsumerService;
import com.hk.demo.activemq.producer.ProducerService;
import com.hk.demo.entry.AppointmentRecord;

@Controller
public class DemoController {

	// 队列名hk.demo
	@Resource(name = "demoQueueDestination")
	private Destination demoQueueDestination;

	// 队列消息生产者
	@Resource(name = "producerService")
	private ProducerService producer;

	// 队列消息消费者
	@Resource(name = "consumerService")
	private ConsumerService consumer;

	@RequestMapping(value = "/producer", method = RequestMethod.GET)
	public ModelAndView producer() {
		System.out.println("------------go producer");

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(now);
		System.out.println(time);

		ModelAndView mv = new ModelAndView();
		mv.addObject("time", time);
		mv.setViewName("jms_producer");
		return mv;
	}

	@RequestMapping(value = "/onsend", method = RequestMethod.POST)
	public ModelAndView producer(@RequestParam("message") String message) {
		System.out.println("------------send to jms");
		ModelAndView mv = new ModelAndView();
		//producer.sendMessage(demoQueueDestination, message);
		for(int i = 0;i<100;i++){
			final AppointmentRecord record = new AppointmentRecord();
			record.setAppointmentRecordId("11111");
			record.setHospitalId("22222");
			record.setSchedulingDetailId("3333");
			producer.sendObjectMessage(demoQueueDestination, record);
		}
		mv.setViewName("welcome");
		return mv;
	}

	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public ModelAndView queue_receive() throws JMSException {
		System.out.println("------------receive message");
		ModelAndView mv = new ModelAndView();

		/*TextMessage tm = consumer.receive(demoQueueDestination);
		mv.addObject("textMessage", tm.getText());*/
		consumer.receiveType(demoQueueDestination);

		mv.setViewName("queue_receive");
		return mv;
	}

	/*
	 * ActiveMQ Manager Test
	 */
	@RequestMapping(value = "/jms", method = RequestMethod.GET)
	public ModelAndView jmsManager() throws IOException {
		System.out.println("------------jms manager");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("welcome");

		JMXServiceURL url = new JMXServiceURL("");
		JMXConnector connector = JMXConnectorFactory.connect(url);
		connector.connect();
		MBeanServerConnection connection = connector.getMBeanServerConnection();

		return mv;
	}

}