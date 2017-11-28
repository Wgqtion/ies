package com.vsc.business.core.service.sys;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.vsc.util.CoreUtils;

/**
 * 发送数据库备份文件
 */
public class DatabaseBackupService {

	private static final String DEFAULT_ENCODING = "utf-8";

	private static Logger logger = LoggerFactory.getLogger(DatabaseBackupService.class);

	private JavaMailSender mailSender;

	private String toMail;
	private String fromMail;

	private String resourcePath;

	private String content;

	/**
	 * 发送MIME格式的用户修改通知邮件.
	 */
	public void sendNotificationMail() {

		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);
			helper.setTo(toMail);
			helper.setFrom(fromMail);
			helper.setSubject("数据库备份文档" + DateFormatUtils.format(CoreUtils.nowtime(), "yyyyMMdd"));
			helper.setText(content, false);
			File attachment = generateAttachment(resourcePath);
			if (attachment != null) {
				helper.addAttachment(attachment.getName(), attachment);
			}
			mailSender.send(msg);
			logger.info("数据库备份邮件已发送至" + toMail);
		} catch (MessagingException e) {
			logger.error("数据库备份邮件失败", e);
		} catch (Exception e) {
			logger.error("数据库备份邮件失败", e);
		}
	}

	/**
	 * 获取classpath中的最后创建的一个附件.
	 */
	private File generateAttachment(String path) {

		String dateString = DateFormatUtils.format(CoreUtils.nowtime(), "yyyyMMdd");
		File file = new File(path);
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if (StringUtils.indexOf(files[i].getName(), dateString) != StringUtils.INDEX_NOT_FOUND) {
					return files[i];
				}
			}
		}

		return null;

	}

	/**
	 * Spring的MailSender.
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
